package fr.erias.spc.rcppdf.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Map;

import org.junit.Test;

import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcppdf.InvalidFilenameException;
import fr.erias.spc.rcppdf.files.ITxtFile;
import fr.erias.spc.rcppdf.sections.TxtSectionExtractor;

public class FileExtractionTest {

	@Test
	public void TxtFileExtractionTest() throws IOException, InvalidFilenameException {
		ITxtFile txtFile = FileLoadTest.get128916File();
		TxtSectionExtractor sectionExtractor = new TxtSectionExtractor();
		Map<SPCsections, String> map = sectionExtractor.extractSections(txtFile);
		assertEquals(map.get(SPCsections.RcpDenomination).length(),49);
		assertEquals(map.get(SPCsections.RcpCompoQualiQuanti).length(),373);
		assertEquals(map.get(SPCsections.RcpFormePharm).length(),103);
		assertEquals(map.get(SPCsections.RcpFormePharm).length(),103);
		assertEquals(map.get(SPCsections.RcpIndicTherap).length(),340);
		assertEquals(map.get(SPCsections.RcpPosoAdmin).length(),10101);
		assertEquals(map.get(SPCsections.RcpContreindications).length(),391);
		assertEquals(map.get(SPCsections.RcpMisesEnGarde).length(),6249);
		assertEquals(map.get(SPCsections.RcpInteractionsMed).length(),2743);
		assertEquals(map.get(SPCsections.RcpFertGrossAllait).length(),1414);
		assertEquals(map.get(SPCsections.RcpConduite).length(),462);
		assertEquals(map.get(SPCsections.RcpEffetsIndesirables).length(),7827);
		assertEquals(map.get(SPCsections.RcpSurdosage).length(),502);
		assertEquals(map.get(SPCsections.RcpPropPharmacodynamiques).length(),4431);
		assertEquals(map.get(SPCsections.RcpPropPharmacocinetiques).length(),4257);
		assertEquals(map.get(SPCsections.RcpSecuritePreclinique).length(),1544);
		assertEquals(map.get(SPCsections.RcpListeExcipients).length(),34);
		assertEquals(map.get(SPCsections.RcpIncompatibilites).length(),120);
		assertEquals(map.get(SPCsections.RcpDureeConservation).length(),863);
		assertEquals(map.get(SPCsections.RcpPrecConservation).length(),211);
		assertEquals(map.get(SPCsections.RcpEmballage).length(),421);
		assertEquals(map.get(SPCsections.RcpPrecEmpl).length(),3341);
		assertEquals(map.get(SPCsections.RcpTitulaireAmm).length(),71);
		assertEquals(map.get(SPCsections.RcpNumAutor).length(),19);
		assertEquals(map.get(SPCsections.RcpPremiereAutorisation).length(),112);
		assertEquals(map.get(SPCsections.RcpDateRevision).length(),160);
		assertEquals(map.get(SPCsections.RcpDosimetrie).length(),0);
		assertEquals(map.get(SPCsections.RcpInstPrepRadioph).length(),0);
	}
}
