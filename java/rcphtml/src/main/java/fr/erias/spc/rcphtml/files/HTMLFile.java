package fr.erias.spc.rcphtml.files;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Parses the HTML document 
 * 
 * @author Sebastien Cossin
 *
 */
public class HTMLFile implements IHTMLFile {

	/**
	 * Document parse by Jsoup
	 */
	private Document document;
	
	/**
	 * Code of the drug
	 */
	private String ciscode;
	
	/**
	 * Encoding of HTML files
	 */
	private final String encoding = "WINDOWS-1252";

	/**
	 * Parse a SPC html file
	 * @param rcpFile html SPC file
	 * @throws IOException
	 */
	public HTMLFile (File rcpFile) throws IOException {
		this.document = Jsoup.parse(rcpFile, this.encoding);
		this.ciscode = extractCIScode(rcpFile);
	}
	
	private String extractCIScode(File rcpFile) {
		String cisCode = rcpFile.getName().replace(".html", "");
		return(cisCode);
	}
	
	@Override
	public Document getDocument() {
		// TODO Auto-generated method stub
		return document;
	}

	@Override
	public String getCIScode() {
		// TODO Auto-generated method stub
		return ciscode;
	}
}
