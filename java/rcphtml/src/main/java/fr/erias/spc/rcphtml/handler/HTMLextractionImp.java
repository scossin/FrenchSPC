package fr.erias.spc.rcphtml.handler;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.select.Elements;

import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.sections.SPCsections;

/**
 * Stores the results of the extraction process
 * 
 * @author Sebastien Cossin
 *
 */
public class HTMLextractionImp implements IHTMLextracted {
	
	private final String cisCode;
	private Map<SPCsections, Elements> htmlSections = new HashMap<SPCsections, Elements>();
	
	public HTMLextractionImp(IHTMLFile htmlFile, ISectionExtractor sectionExtractor) {
		this.cisCode = htmlFile.getCIScode();
		for (SPCsections section : SPCsections.values()) {
			Elements els =  sectionExtractor.extractSection(htmlFile, section);
			htmlSections.put(section, els);
		}
	}
	
	@Override
	public String getCisCode() {
		// TODO Auto-generated method stub
		return this.cisCode;
	}

	@Override
	public Map<SPCsections, Elements> getExtractedSection() {
		return htmlSections;
	}
}
