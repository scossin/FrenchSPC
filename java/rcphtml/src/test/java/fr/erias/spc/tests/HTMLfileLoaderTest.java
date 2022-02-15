package fr.erias.spc.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import fr.erias.spc.rcphtml.files.HTMLFile;
import fr.erias.spc.rcphtml.files.IHTMLFile;

public class HTMLfileLoaderTest {

	@Test
	public void SplitNewLinesTest() throws IOException {
		String filename = Thread.currentThread().getContextClassLoader().getResource("67960413.html").getFile();
		File file = new File(filename);
		IHTMLFile htmlFile = new HTMLFile(file);
		
		assertTrue(htmlFile.getCIScode().equals("67960413"));
	}
}
