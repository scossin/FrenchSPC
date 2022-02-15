# French SPC

French Summaries of Product Characteristics (SPC) are available on the [ANSM's website](https://base-donnees-publique.medicaments.gouv.fr/) in a HTML or PDF format (from the European Medicines Agency)  .  

In the context of medical informatics research in natural language processing and information extraction, it is necessary to create a create a corpus of semi-structured information. 

This repository contains the source code to download French SPC and extract their content. 

If you are only interested by the corpus (not by the program to create this corpus), go to the **python** directory. 


## Folder and files
* HTML_SPC_DOWNLOADED: French SCP in HTML format  (1 example file)
* PDF_SPC_DOWNLOADED: European SCP in PDF format (from the European Medicines Agency)  (1 example file)
* SPC_europe_txt: output folder of European SCP in PDF format after txt extraction (1 example file)
* CIS_europe_link.tsv: mappings between the CIS code and the european SPC code
* python: a script example to load the corpus
* java: Java programs (see below)

## Java

They are three Java programs:

* Transform PDF files to txt files
* Extract the content of Txt files
* Extract the content of HTML files

A Java program is in charge of parsing the content of HTML files and extracting their content. First, you need to compile the project with Maven. 

```bash
mvn clean package ./java
```

To extract SPC sections from HTML files:
```bash
java -jar java/rcphtml/target/htmlrcp2json.jar HTML_SPC_DOWNLOADED/ ./html_spc.json
```

To convert PDF files to TXT files:
```bash
java -jar java/extractpdf/target/PDFExtractorSPC.jar PDF_SPC_DOWNLOADED/ ./SPC_europe_txt/
```

To extract SPC sections from TXT files:
```bash
java -jar java/rcppdf/target/pdfrcp2json.jar SPC_europe_txt/ ./pdf_spc.json
```

These two json files made up the French SPC corpus and are compressed with the following commands:
```bash
tar -zcf html_spc.tar.gz html_spc.json
tar -zcf pdf_spc.tar.gz pdf_spc.json
```
