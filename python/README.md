# Corpus description

## Download
Go to https://www.romedi.fr/ website -> Télécharger -> Lien de téléchargement -> SPC. 

## Files
There are two files:

* html_spc.json (extracted from HTML files)
* pdf_scp.json (extracted from PDF files)

Each file is a json array. Each element of the array is a SPC. Each SPC contains the same sections. 
However, SPC identifier is the *cis* number in HTML files whereas *numCode* is the identifier in PDF files. A CIS code identifies a marketed drug.  
Links between numCode and CIS code can be found in the 'CIS_europe_link.tsv' file.  
More information about French marketed drugs is available here: https://base-donnees-publique.medicaments.gouv.fr/telechargement.php

Each file format is described in Python with [pydantic](https://pydantic-docs.helpmanual.io/) in the *model* folder. 

## Read files in Python
readJson.py gives an example of how to open a Json file. 
