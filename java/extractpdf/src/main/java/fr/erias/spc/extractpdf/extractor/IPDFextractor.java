package fr.erias.spc.extractpdf.extractor;

import java.io.File;
import java.io.IOException;

public interface IPDFextractor {

	/**
	 * Extract the content of a PDF file
	 * @param pdfFile PDF file
	 * @return txt
	 * @throws IOException 
	 */
	public String extractPDFcontent(File pdfFile) throws IOException;
}
