package fr.erias.spc.rcphtml.files;

import org.jsoup.nodes.Document;

public interface IHTMLFile {
	
	/**
	 * 
	 * @return The HTML document parsed
	 */
	public Document getDocument();
	
	/**
	 * 
	 * @return Code Identifiant de Specialite (CIS) linked to every SPC
	 */
	public String getCIScode();
	
}
