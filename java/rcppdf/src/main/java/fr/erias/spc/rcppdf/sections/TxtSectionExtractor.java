package fr.erias.spc.rcppdf.sections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import fr.erias.spc.rcphtml.sections.SPCsections;
import fr.erias.spc.rcppdf.files.ITxtFile;

public class TxtSectionExtractor implements ITxtSectionExtractor {

	private boolean previousLineWasEmpty = false; // keep track of empty line to detect pageNumber
	
	private final Map<String, SPCsections> mapSecId2section = new HashMap<String, SPCsections>();

	// <K,V> where key is the id of the section (ex: 4.1) and SB concatenates the string contained
	private Map<SPCsections, StringBuilder> mapSec2Content;

	// this first initialization is not linked to a section (not in mapSecId2Content object)
	private StringBuilder currentSB ; 

	public TxtSectionExtractor() {
		for (SPCsections section : SPCsections.values()) {
			mapSecId2section.put(section.getSectionId(), section);
		}
	}

	@Override
	public Map<SPCsections, String> extractSections(ITxtFile txtFile) {
		mapSec2Content = new HashMap<SPCsections, StringBuilder>();
		currentSB = new StringBuilder(); 
		
		Iterator<String> iter = txtFile.getLineIterator();
		while(iter.hasNext()) {
			String line = iter.next();
			if (isAnEmptyLine(line)) continue;
			if (isAline2ignore(line)) continue;
			if (isTheEndOfRCP(line)) break;
			if (isAnewSectionLine(line)) { // line: '4.1 Indications thérapeutiques'
				SPCsections newSection = getSectionFromLine(line);
				createNewBuilder(newSection); // will append all the line following
				currentSB = mapSec2Content.get(newSection);
				continue; // don't add the header '4.1 Indications thérapeutiques'
			}
			currentSB.append(line);
			currentSB.append(" ");
		}
		txtFile.closeFile();
		return getSectionsContent();
	}

	private boolean isAline2ignore(String line) {
		return isPageNumber(line) || 
				line.startsWith("4. ") || // 4. DONNEES CLINIQUES 
				line.startsWith("5. ") || // 5. PROPRIETES PHARMACOLOGIQUES 
				line.startsWith("6. ") || // 6. DONNEES PHARMACEUTIQUES
				false; // just to have place to add a comment above
	}

	private void createNewBuilder(SPCsections section) {
		mapSec2Content.put(section, new StringBuilder());
	}

	private boolean isAnEmptyLine(String line) {
		boolean lineIsEmpty = line.trim().equals("");
		if (lineIsEmpty) {
			previousLineWasEmpty = true;
		} else {
			previousLineWasEmpty = false;
		}
		return(lineIsEmpty);
	}
	
	private boolean isPageNumber(String line) {
		// Example (empty line followed by a single number):
		// 
		// 8
		// 
		String lineNorm = line.replaceAll("[0-9]+", "").trim();
		return isAnEmptyLine(lineNorm) && previousLineWasEmpty;
	}

	private boolean isAnewSectionLine(String line) {
		if (line.length() < 3) {
			return false;
		}
		String first3char = line.substring(0,3);
		boolean isAsection = mapSecId2section.containsKey(first3char);
		if (!isAsection) {
			return false;
		}
		SPCsections newSection = getSectionFromLine(line);
		if (sectionWasAlreadyExtracted(newSection)) {
			return false;
		}
		return true;
	}

	private boolean sectionWasAlreadyExtracted(SPCsections newSection) {
		// the section appears in order in the document: 3.1 before 4.1 before 5.1
		// but it can happen that the line refers to an old section and refers to it '4.1'
		// if a SB was created, it means the section was already extracted and we can ignore it here
		return mapSec2Content.containsKey(newSection);
	}

	private SPCsections getSectionFromLine(String line) {
		String first3char = line.substring(0,3);
		return(mapSecId2section.get(first3char));
	}

	private boolean isTheEndOfRCP(String line) {
		return line.startsWith("ANNEXE II");
	}

	private String getSectionContent(SPCsections section) {
		if (!mapSec2Content.containsKey(section)) {
			return "";
		} else {
			StringBuilder sb = mapSec2Content.get(section);
			return (sb.toString().trim());
		}
	}	

	private Map<SPCsections, String> getSectionsContent() {
		Map<SPCsections, String> outputMap = new HashMap<SPCsections, String>();
		for (SPCsections section : SPCsections.values()) {
			String content = getSectionContent(section);
			outputMap.put(section, content);
		}
		return(outputMap);
	}

	public static String getSectionContent(SPCsections section,  Map<SPCsections, StringBuilder> mapSecId2Content) {
		if (!mapSecId2Content.containsKey(section)) {
			return "";
		} else {
			StringBuilder sb = mapSecId2Content.get(section);
			return (sb.toString().trim());
		}
	}
}
