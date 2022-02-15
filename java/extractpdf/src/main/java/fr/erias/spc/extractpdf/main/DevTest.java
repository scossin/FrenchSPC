package fr.erias.spc.extractpdf.main;

import java.io.File;
import java.io.IOException;

import fr.erias.spc.extractpdf.extractor.IPDFextractor;
import fr.erias.spc.extractpdf.extractor.PDFextractor;
import fr.erias.spc.extractpdf.writer.ConsoleWriter;
import fr.erias.spc.extractpdf.writer.IOutWriter;

/**
 * Testing in Eclipse
 * 
 * @author Sebastien Cossin
 *
 */
public class DevTest {

	public static void testFileExtraction() throws IOException {
		File pdfFile = new File("../../anx_152237_fr.pdf");
		IPDFextractor pdfExtractor = new PDFextractor();
		IOutWriter writer = new ConsoleWriter();
		String content = pdfExtractor.extractPDFcontent(pdfFile);
		writer.write(pdfFile, content);
	}

	public static void testFolderExtraction() throws IOException {
		String[] arguments = {"../../PDF_SPC_DOWNLOADED","../../SPC_europe_txt"};
		SPCpdf2txt.main(arguments);
	}

	public static void main(String[] args) throws IOException {
		// testFileExtraction();
		testFolderExtraction();
	}
}
