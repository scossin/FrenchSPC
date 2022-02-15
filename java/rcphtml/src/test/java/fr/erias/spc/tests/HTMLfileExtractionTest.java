package fr.erias.spc.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.jsoup.select.Elements;
import org.junit.Test;

import fr.erias.spc.rcphtml.files.HTMLFile;
import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.handler.ISectionExtractor;
import fr.erias.spc.rcphtml.handler.SectionExtractorImp;
import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcphtml.sections.Util;

public class HTMLfileExtractionTest {

	public IHTMLFile get67960413File() throws IOException {
		String filename = Thread.currentThread().getContextClassLoader().getResource("67960413.html").getFile();
		File file = new File(filename);
		IHTMLFile htmlFile = new HTMLFile(file);
		return(htmlFile);
	}
	
	public Elements extractElementsOf67960413(SPCsections section) throws IOException {
		IHTMLFile htmlFile = get67960413File();
		ISectionExtractor sectionExtractor = new SectionExtractorImp();
		Elements els = sectionExtractor.extractSection(htmlFile, section);
		return(els);
	}
	
	@Test
	public void RcpDenominationTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpDenomination);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 50);
	}
	
	@Test
	public void RcpFormePharmTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpFormePharm);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 54);
	}
	
	@Test
	public void RcpCompoQualiQuantiTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpCompoQualiQuanti);
		assertTrue(els.size() == 4);
		assertTrue(Util.getText(els).length() == 354);
	}
	
	@Test
	public void IndicationTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpIndicTherap);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 278);
	}
	
	@Test
	public void PosologieTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpPosoAdmin);
		assertTrue(els.size() == 10);
		assertTrue(Util.getText(els).length() == 796);
	}
	
	@Test
	public void ContreIndicationTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpContreindications);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 146);
	}
	
	@Test
	public void MiseEnGardeTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpMisesEnGarde);
		assertTrue(els.size() == 6);
		assertTrue(Util.getText(els).length() == 921);
	}
	
	@Test
	public void InteractionTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpInteractionsMed);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 342);
	}
	
	@Test
	public void RcpFertTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpFertGrossAllait);
		assertTrue(els.size() == 10);
		assertTrue(Util.getText(els).length() == 720);
	}
	
	@Test
	public void RcpConduiteTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpConduite);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 117);
	}
	
	@Test
	public void RcpEITest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpEffetsIndesirables);
		assertTrue(els.size() == 4);
		assertTrue(Util.getText(els).length() == 1536);
	}
	
	@Test
	public void RcpSurdosageTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpSurdosage);
		assertTrue(els.size() == 10);
		assertTrue(Util.getText(els).length() == 738);
	}
	
	@Test
	public void RcpPropPharmacodynamiqueTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpPropPharmacodynamiques);
		assertTrue(els.size() == 5);
		assertTrue(Util.getText(els).length() == 697);
	}
	
	@Test
	public void RcpPropPharmacocinetiquesTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpPropPharmacocinetiques);
		assertTrue(els.size() == 8);
		assertTrue(Util.getText(els).length() == 997);
	}
	
	@Test
	public void RcpSecuritePrecliniqueTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpSecuritePreclinique);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 479);
	}
	
	@Test
	public void RcpListeExcipientsTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpListeExcipients);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 170);
	}
	
	@Test
	public void RcpIncompatibilitesTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpIncompatibilites);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 12);
	}

	@Test
	public void RcpDureeConservationTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpDureeConservation);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 94);
	}

	@Test
	public void RcpPrecConservationTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpPrecConservation);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 53);
	}
	
	@Test
	public void RcpEmballageTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpEmballage);
		assertTrue(els.size() == 3);
		assertTrue(Util.getText(els).length() == 288);
	}
	
	@Test
	public void RcpPrecEmplTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpPrecEmpl);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 101);
	}
	
	@Test
	public void RcpTitulaireAmmTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpTitulaireAmm);
		assertTrue(els.size() == 3);
		assertTrue(Util.getText(els).length() == 59);
	}
	
	@Test
	public void RcpNumAutorTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpNumAutor);
		assertTrue(els.size() == 2);
		assertTrue(Util.getText(els).length() == 207);
	}
	
	@Test
	public void RcpDateRevisionTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpDateRevision);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 46);
	}

	@Test
	public void RcpDosimetrieTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpDosimetrie);
		assertTrue(els.size() == 1);
		assertTrue(Util.getText(els).length() == 12);
	}
	
	@Test
	public void RcpInstPrepRadiophTest() throws IOException {
		Elements els = extractElementsOf67960413(SPCsections.RcpInstPrepRadioph);
		assertTrue(els.size() == 5);
		assertTrue(Util.getText(els).length() == 130);
	}
}
