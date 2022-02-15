package fr.erias.spc.rcphtml.main;

import java.io.File;
import java.io.IOException;

import org.jsoup.select.Elements;

import fr.erias.spc.rcphtml.files.HTMLFile;
import fr.erias.spc.rcphtml.files.HTMLfileIteratorImp;
import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.files.IHTMLfileIterator;
import fr.erias.spc.rcphtml.handler.ISectionExtractor;
import fr.erias.spc.rcphtml.handler.SectionExtractorImp;
import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcphtml.sections.Util;
import fr.erias.spc.rcphtml.writer.IOutputWriter;
import fr.erias.spc.rcphtml.writer.OutputWriterJson;

/**
 * Test in Eclipse
 * 
 * @author Sebastien Cossin
 *
 */
public class DevTest {

	// this was used to develop tests
	public static void testExtraction() throws IOException {
		String filename = "./src/test/resources/67960413.html";
		File file = new File(filename);
		IHTMLFile htmlFile = new HTMLFile(file);
		ISectionExtractor sectionExtractor = new SectionExtractorImp();
		Elements els = sectionExtractor.extractSection(htmlFile, SPCsections.RcpInstPrepRadioph);
		System.out.println(els.size());
		System.out.println(Util.getText(els));
		System.out.println(Util.getText(els).length());
	}
	
	public static void testPipeline() throws IOException {
		File inputFolder = new File("./../../HTML_SPC_DOWNLOADED");
		IHTMLfileIterator rcpFiles = new HTMLfileIteratorImp(inputFolder);
		ISectionExtractor sectionExtractor = new SectionExtractorImp();
		File outputFile = new File("dump.json");
		IOutputWriter writer = new OutputWriterJson(outputFile);
		new Main(rcpFiles, sectionExtractor, writer, 2);
	}
	
	public static void main(String[] args) throws IOException {
		testPipeline();
	}
}
