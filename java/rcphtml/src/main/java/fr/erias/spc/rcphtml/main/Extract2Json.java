package fr.erias.spc.rcphtml.main;

import java.io.File;
import java.io.IOException;

import fr.erias.spc.rcphtml.files.HTMLfileIteratorImp;
import fr.erias.spc.rcphtml.files.IHTMLfileIterator;
import fr.erias.spc.rcphtml.handler.ISectionExtractor;
import fr.erias.spc.rcphtml.handler.SectionExtractorImp;
import fr.erias.spc.rcphtml.writer.IOutputWriter;
import fr.erias.spc.rcphtml.writer.OutputWriterJson;

/**
 * Calls the pipeline on a folder containing SPC files
 * Output a Json file
 * 
 * @author Sebastien Cossin
 *
 */
public class Extract2Json {
	
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Expected 2 arguments:"
					+ "\t first argument is a folder containing SPC HTML files"
					+ "\t second argument is the json filename");
			return;
		}
		File htmlFolder = new File(args[0]);
		File outputFile = new File(args[1]);
		IHTMLfileIterator rcpFiles = new HTMLfileIteratorImp(htmlFolder);
		ISectionExtractor sectionExtractor = new SectionExtractorImp();
		IOutputWriter writer = new OutputWriterJson(outputFile);
		new Main(rcpFiles, sectionExtractor, writer, -1);
	}
}
