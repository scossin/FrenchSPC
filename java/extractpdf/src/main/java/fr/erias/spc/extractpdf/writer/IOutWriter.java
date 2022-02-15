package fr.erias.spc.extractpdf.writer;

import java.io.File;

public interface IOutWriter {

	/**
	 * Write the extraction result somewhere
	 * @param pdfFile In order to have the filename of the original PDF
	 * @param content The extracted content of the PDF
	 */
	public void write(File pdfFile, String content);
	
	public boolean fileAlreadyExtracted(File pdfFile);
	
}
