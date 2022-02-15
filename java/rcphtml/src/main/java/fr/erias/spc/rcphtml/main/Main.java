package fr.erias.spc.rcphtml.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.files.IHTMLfileIterator;
import fr.erias.spc.rcphtml.handler.HTMLextractionImp;
import fr.erias.spc.rcphtml.handler.IHTMLextracted;
import fr.erias.spc.rcphtml.handler.ISectionExtractor;
import fr.erias.spc.rcphtml.writer.IOutputWriter;

/**
 * The full pipeline
 * 
 * @author Sebastien Cossin
 *
 */
public class Main {

	/**
	 * Read HTML SPC files, extract their content and write the results
	 * @param fileIterator provides an iterator
	 * @param sectionExtractor main logic on how to extract section information
	 * @param writer where the output goes
	 * @param nDocs stops after n documents is extracted. -1 if you want all the documents
	 * @throws IOException 
	 */
	public Main(IHTMLfileIterator fileIterator, ISectionExtractor sectionExtractor, IOutputWriter writer, int nDocs) throws IOException {
		Iterator<IHTMLFile> iter = fileIterator.getFileIterator();
		int count = 0;
		while(iter.hasNext()) {
			IHTMLFile htmlFile = iter.next();
			IHTMLextracted htmlContent = new HTMLextractionImp(htmlFile, sectionExtractor);
			writer.write(htmlContent);
			count+=1;
			if (count % 100 == 0) {
				System.out.println(count);
			}
			if (count == nDocs) {
				break;
			}
		}
		writer.close();
	}
}
