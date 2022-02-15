package fr.erias.spc.rcppdf.sections;

import java.util.Map;

import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcppdf.files.ITxtFile;

public interface ITxtSectionExtractor {

	public Map<SPCsections, String> extractSections(ITxtFile txtFile);

}
