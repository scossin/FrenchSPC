######################## Download French Summary of Product Characteristics
library(stringr)
library(httr)
source("./functions.R")

### list of CIS codes from Romedi
# deprecated since Romedi is not up-to-date
CIScodes <- get_cis_from_romedi_sparql()
length(CIScodes) # 16143
# temp solution: works only.
# TODO: update Romedi and use SPARQL to retrieve CIScodes
CIScodes <- getCIScodesLocalFiles()
length(CIScodes) # 14094

# folder where to put downloaded SPC:
HTML_FOLDER <- "./HTML_SPC_DOWNLOADED/"

# list donwloaded SPC:
html_files <- list.files(path = HTML_FOLDER, pattern="*.html")
html_files_codes <- gsub(".html", "", html_files)

# new SPC to download:
is_known_cis_code <- CIScodes %in% html_files_codes
cat(sum(is_known_cis_code), "spc already downloaded \n")
cis_to_download <- CIScodes[!is_known_cis_code]
cat(length(cis_to_download), "spc to download \n")

for (cis in cis_to_download) {
  print(which(cis_to_download == cis)) ## print progress
  tryCatch(
    expr = {
      results <- httr::GET(getSPCurl(cis))
      htmlContent <- rawToChar(results$content)
      fileName <- paste0(HTML_FOLDER, cis, ".html")
      writeLines(htmlContent, fileName)
    },
    error = function(e) {
      cat("an error occured for CIS:", cis)
    }
  )
}

## the size of the file indicates if SPC is missing (
# Le document demandé n'est pas disponible pour ce médicament)
html_files <- list.files(path = HTML_FOLDER, pattern = "*.html",full.names = T)
html_files_infos <- file.info(html_files)
html_files_infos$filename <- rownames(html_files_infos)


###################  European SPC:
#TODO: add european number in Romedi. # temp solution:
CIS_bdpm <- read.table(
  "ANSM/CIS_bdpm.txt",
  sep = "\t",
  header = F,
  fileEncoding = "ISO8859-1",
  quote = ""
)
CIS_bdpm <- subset(CIS_bdpm, select = c("V1", "V10"))
colnames(CIS_bdpm) <- c("CIS", "codeEurope")
is_european <- !CIS_bdpm$codeEurope == ""
sum(is_european)
CIS_europe <- subset(CIS_bdpm, is_european)
CIS_europe <- unique(CIS_europe)

CIS_europe$codeEurope[1] ### EU/1/14/944
CIS_europe$numEurope <-
  stringr::str_extract(CIS_europe$codeEurope, "[0-9]+$")

## link between numEurope and URL of the european SPC:
webpage <-
  httr::GET(url = "http://ec.europa.eu/health/documents/community-register/html/h_direct_anx.htm#1147_fr")
webpage <- rawToChar(webpage$content)
regexMapping <- "med_nr\\[[0-9]+\\] = \"[0-9]+\";"
mapping <- stringr::str_extract_all(webpage, pattern = regexMapping)
mapping <- unlist(mapping)
numsEurope <- sapply(mapping, function(x) {
  y <- str_extract(x, "med_nr\\[[0-9]+\\]")
  num <- str_extract(y, "[0-9]+")
  return(num)
})
numsEurope <- as.numeric(numsEurope)
fullNumber <- sapply(mapping, function(x) {
  y <- str_extract(x, "\"[0-9]+\";")
  num <- str_extract(y, "[0-9]+")
  return(num)
})
fullNumber <- as.numeric(fullNumber)
mapping <-
  data.frame(numEurope = numsEurope, fullNumber = fullNumber)
options(digits = 13)
mapping$numSPC <-
  substr(mapping$fullNumber, 9, nchar(mapping$fullNumber))
mapping$date <- substr(mapping$fullNumber, 1, 8)
mapping$date <- as.Date(mapping$date, format = "%Y%m%d")
mapping$year <- substr(mapping$date, 1, 4)

### merge CIS-numEurope with numEurope-webpage
CIS_europe$numEurope <- as.numeric(CIS_europe$numEurope)
bool <- CIS_europe$numEurope %in% mapping$numEurope
sum(bool)
no_mapping <- subset(CIS_europe,!bool) #TODO: check why not found

CIS_europe_link <- merge(CIS_europe, mapping, by = "numEurope")
CIS_europe_link$url <-
  getURLSPCeurope(CIS_europe_link$year,
                  CIS_europe_link$fullNumber,
                  CIS_europe_link$numSPC)
CIS_europe_link$fileName <-
  paste0("anx_", CIS_europe_link$numSPC, "_fr.pdf")
file_name_europe <- "./CIS_europe_link.tsv"
old_file <- read.table(file_name_europe, sep = "\t", header=T)
old_file <- unique(old_file)
is_already_known <- CIS_europe_link$fileName %in% old_file$fileName
new_CIS_europe_link <- subset(CIS_europe_link, !is_already_known)
if (nrow(new_CIS_europe_link) != 0) {
  write.table(
    x = new_CIS_europe_link,
    file = file_name_europe,
    sep = "\t",
    col.names = F,
    row.names = F,
    quote = F,
    append = T
  )
}

# combine both to have the url links:
colnames(new_CIS_europe_link)
CIS_urls_europe <- subset(new_CIS_europe_link, select = c("CIS", "url"))
CIS_url_france <- data.frame(CIS = CIScodes)
CIS_url_france$url <-
  paste0(
    "http://base-donnees-publique.medicaments.gouv.fr/affichageDoc.php?specid=",
    CIS_url_france$CIS,
    "&typedoc=R#RcpEffetsIndesirables"
  )
CIS_url <- rbind(CIS_url_france, CIS_urls_europe)
CIS_url$uri <- paste0("http://www.romedi.fr/romedi/CIS", CIS_url$CIS)
CIS_url$CIS <- NULL

# folder to download european SPC in pdf format:
folderEurope <- "./PDF_SPC_DOWNLOADED/"
spc_europe_files <- list.files(path = folderEurope, pattern = "*.pdf")
cat(length(spc_europe_files), "european SPC downloaded \n")
is_downloaded <- CIS_europe_link$fileName %in% spc_europe_files
sum(is_downloaded)
CIS_europe_link_2_download <-
  subset(new_CIS_europe_link, !is_downloaded, select = c(url, fileName))
CIS_europe_link_2_download <- unique(CIS_europe_link_2_download)
cat(length(unique(CIS_europe_link_2_download$url)),
    "european SPC to download\n")
# url_spc_europe <- CIS_europe_link_2_download$url[1]
for (url_spc_europe in CIS_europe_link_2_download$url) {
  print(which(CIS_europe_link_2_download$url == url_spc_europe))
  tryCatch(
    expr = {
      commande <- paste0("wget -q ", url_spc_europe)
      system(commande)
    },
    error = function(e) {
      cat("failed to download", url)
    }
  )
}
