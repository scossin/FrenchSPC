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
import fr.erias.spc.rcppdf.sections.ITxtSectionExtractor;
import fr.erias.spc.rcppdf.writer.ITxtOutputWriter;

public class Main {

	public Main(ITxtFileIterator fileIterator, ITxtSectionExtractor sectionExtractor, ITxtOutputWriter writer, int nDocs ) throws InvalidFilenameException, IOException {
		Iterator<File> filesIter = fileIterator.getFileIterator();
		int count = 0;
		while(filesIter.hasNext()) {
			File file = filesIter.next();
			ITxtFile txtFile = new TxtFileImp(file);
			Map<SPCsections, String> map = sectionExtractor.extractSections(txtFile);
			writer.write(map, txtFile.getNumCode());
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
