package fr.erias.spc.rcppdf.main;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcppdf.InvalidFilenameException;
import fr.erias.spc.rcppdf.files.ITxtFile;
import fr.erias.spc.rcppdf.files.ITxtFileIterator;
import fr.erias.spc.rcppdf.files.TxtFileImp;
import fr.erias.spc.rcppdf.files.TxtFileIteratorImp;
import fr.erias.spc.rcppdf.sections.ITxtSectionExtractor;
import fr.erias.spc.rcppdf.sections.TxtSectionExtractor;
import fr.erias.spc.rcppdf.writer.ITxtOutputWriter;
import fr.erias.spc.rcppdf.writer.OutputWriterJson;

public class DevTest {

	public static final String FILENAME = "./src/test/resources/anx_128916_fr.txt";
	
	public static void testLoad() throws InvalidFilenameException, IOException {
		File file = new File(FILENAME);
		ITxtFile txtFile = new TxtFileImp(file);
		System.out.println(txtFile.getNumCode());
		Iterator<String> iter = txtFile.getLineIterator();
		int countLine = 0;
		while(iter.hasNext()) {
			iter.next();
			countLine += 1;
		}
		System.out.println(countLine);
	}
	
	public static void testsExtraction() throws InvalidFilenameException, IOException {
		File file = new File(FILENAME);
		ITxtFile txtFile = new TxtFileImp(file);
		TxtSectionExtractor sectionExtractor = new TxtSectionExtractor();
		Map<SPCsections, String> map = sectionExtractor.extractSections(txtFile);
		SPCsections section = SPCsections.RcpPosoAdmin;
		System.out.println(map.get(section).length());
		System.out.println(map.get(section));
	}
	
	public static void testPipeline() throws IOException, InvalidFilenameException {
		File inputFolder = new File("./../../SPC_europe_txt");
		ITxtFileIterator rcpFiles = new TxtFileIteratorImp(inputFolder);
		ITxtSectionExtractor sectionExtractor = new TxtSectionExtractor();
		File outputFile = new File("spc_europe.json");
		ITxtOutputWriter writer = new OutputWriterJson(outputFile);
		new Main(rcpFiles, sectionExtractor, writer, -1);
	}
	
	public static void main(String[] args) throws IOException, InvalidFilenameException {
		testPipeline();
	}
}
