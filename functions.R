get_cis_from_romedi_sparql <- function() {
  library(SPARQL)
  query <- "select distinct ?CIS ?label where {?CIS a <http://www.romedi.fr/romedi/CIS>}"
  codes <- SPARQL::SPARQL(url='http://www.romedi.fr:8890/sparql',query = query)
  # codes <- SPARQL::SPARQL(url='http://127.0.0.1:8889/bigdata/namespace/ROMEDI/sparql',query = query)
  codes <- codes$results
  CIScodes <- unique(codes$CIS)
  CIScodes <- gsub("<http://www.romedi.fr/romedi/CIS","",CIScodes)
  CIScodes <- gsub(">","",CIScodes)
  return(CIScodes)
}

#### 
#' @param CIS : drug code linked to its SPC 
getSPCurl <- function(CIS){
  url <- paste0("http://base-donnees-publique.medicaments.gouv.fr/affichageDoc.php?specid=",CIS,"&typedoc=R")
  return(url)
}

#### 
getCIScodesLocalFiles <- function() {
  ansm_compo <- data.table::fread("./ANSM/CIS_COMPO_bdpm.txt",
                                  sep = "\t",
                                  header = F,
                                  encoding = "Latin-1",
                                  fill = T)
  ansm_drugs <- data.table::fread("./ANSM/CIS_bdpm.txt",
                                  sep = "\t",
                                  header = F,
                                  encoding = "Latin-1")
  ansm_drugs <- ansm_drugs[,1:8]
  colnames(ansm_drugs) <- c(
    "cis",
    "drug",
    "dosageform",
    "route",
    "authorizationstatus",
    "authorizationtype",
    "iscommercialized",
    "scpcode"
  )
  cisDrugsHomeo <- RomediETL:::.detectHomeoInDrugs(ansm_drugs)
  cisSubstanceHomeo <- RomediETL:::.detectHomeoInSubstance(ansm_compo)
  cis_homeo <- unique(c(cisDrugsHomeo, cisSubstanceHomeo))
  is_not_homeo <- !ansm_drugs$cis %in% cis_homeo
  return(ansm_drugs$cis[is_not_homeo])
}

# example:https://ec.europa.eu/health/documents/community-register/2018/20181029142867/anx_142867_fr.pdf
getURLSPCeurope = function(year, fullNumber, numSPC){
  url <-  paste0("http://ec.europa.eu/health/documents/community-register/",year,"/",
                 fullNumber,"/anx_",numSPC,"_fr.pdf")
  return(url)
}
