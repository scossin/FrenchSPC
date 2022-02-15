package fr.erias.spc.rcphtml.handler;

import java.util.Map;

import org.jsoup.select.Elements;

import fr.erias.spc.rcphtml.sections.SPCsections;

public interface IHTMLextracted {

	public String getCisCode();
	
	public Map<SPCsections, Elements> getExtractedSection();
	
}
