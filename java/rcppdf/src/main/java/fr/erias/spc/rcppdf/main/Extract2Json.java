package fr.erias.spc.rcppdf.main;

import java.io.File;
import java.io.IOException;

import fr.erias.spc.rcppdf.InvalidFilenameException;
import fr.erias.spc.rcppdf.files.ITxtFileIterator;
import fr.erias.spc.rcppdf.files.TxtFileIteratorImp;
import fr.erias.spc.rcppdf.sections.ITxtSectionExtractor;
import fr.erias.spc.rcppdf.sections.TxtSectionExtractor;
import fr.erias.spc.rcppdf.writer.ITxtOutputWriter;
import fr.erias.spc.rcppdf.writer.OutputWriterJson;

/**
 * Calls the pipeline on a folder containing SPC txt files
 * 
 * Output a Json file
 * 
 * @author Sebastien Cossin
 *
 */
public class Extract2Json {
	
	public static void main(String[] args) throws IOException, InvalidFilenameException {
		if (args.length != 2) {
			System.out.println("Expected 2 arguments:"
					+ "\t first argument is a folder containing european SPC txt files"
					+ "\t second argument is the json filename");
			return;
		}
		File txtFolder = new File(args[0]);
		File outputFile = new File(args[1]);
		ITxtFileIterator rcpFiles = new TxtFileIteratorImp(txtFolder);
		ITxtSectionExtractor sectionExtractor = new TxtSectionExtractor();
		ITxtOutputWriter writer = new OutputWriterJson(outputFile);
		new Main(rcpFiles, sectionExtractor, writer, -1);
	}
}
