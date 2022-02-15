package fr.erias.spc.extractpdf.main;

import java.io.File;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import fr.erias.spc.extractpdf.extractor.IPDFextractor;
import fr.erias.spc.extractpdf.extractor.PDFextractor;
import fr.erias.spc.extractpdf.utils.Utils;
import fr.erias.spc.extractpdf.writer.IOutWriter;
import fr.erias.spc.extractpdf.writer.Out2FileWriter;

/**
 * Extract the text of PDF content of SPC european files with Tika 
 * @author Sebastien Cossin
 *
 */
public class SPCpdf2txt {
	
	private File pdfFolder; // folder containing PDF
	private IPDFextractor pdfExtractor;
	private IOutWriter writer;
	
	/**
	 * Extract textual content of PDF files with Tika
	 * @param SPCfolder folder containing PDF
	 * @param SPCfolderTxt output folder
	 */
	public SPCpdf2txt(IPDFextractor pdfExtractor, IOutWriter writer, File SPCfolder) {
		System.out.println("Beginning extraction of PDF files in " + SPCfolder);
		Utils.checkFolderExists(SPCfolder);
		this.pdfExtractor = pdfExtractor;
		this.writer = writer;
		this.pdfFolder = SPCfolder;
	}
	
	/**
	 * Extract the content of PDF file
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public void pdf2txt() throws IOException {
		int nFiles = pdfFolder.listFiles().length;
		System.out.println(nFiles + " files detected");
		int counter = 0;
		for (File pdfFile : pdfFolder.listFiles(Utils.pdfFilter)) {
			counter ++;
			logCount(counter, nFiles);
			extract(pdfFile);
		}
	}
	
	private void logCount(int counter, int nFiles) {
		if (counter % 100 == 0) {
			System.out.println(counter + "/" + nFiles);
		}
	}
	
	private void extract(File pdfFile) throws IOException {
		if (writer.fileAlreadyExtracted(pdfFile)) { // don't override if file already exists
			System.out.println("\t outputFile:" + pdfFile.getName() + " already extracted");
			return ;
		}
		String content = pdfExtractor.extractPDFcontent(pdfFile);
		writer.write(pdfFile, content);
	}
	
	/**
	 * Method called in the jar file
	 * @param args
	 * @throws IOException
	 * @throws SAXException
	 * @throws TikaException
	 */
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Expected 2 arguments:"
					+ "\t first argument is a folder containing PDF files"
					+ "\t second folder is the output folder");
			return;
		}
		File pdfFolder = new File(args[0]);
		File outputFolder = new File(args[1]);
		IPDFextractor pdfExtractor = new PDFextractor();
		IOutWriter out2FilWriter = new Out2FileWriter(outputFolder);
		SPCpdf2txt pdf2txt = new SPCpdf2txt(pdfExtractor, out2FilWriter, pdfFolder);
		pdf2txt.pdf2txt();
	}
}
