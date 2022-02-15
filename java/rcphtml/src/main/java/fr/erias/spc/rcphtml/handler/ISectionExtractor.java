package fr.erias.spc.rcphtml.handler;

import org.jsoup.select.Elements;

import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.sections.SPCsections;

public interface ISectionExtractor {
	
	/**
	 * Extract all elements of a section
	 * @param section the section to extract from the document
	 * @return all the elements concerning this section
	 */
	public Elements extractSection(IHTMLFile htmlFile, SPCsections section);
	
}
