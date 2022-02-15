package fr.erias.spc.rcphtml.handler;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import fr.erias.spc.rcphtml.files.IHTMLFile;
import fr.erias.spc.rcphtml.sections.SPCsections;

/**
 * The most complication class, in charge of extracting specific section in the HTML file
 * 
 * @author Sebastien Cossin
 *
 */
public class SectionExtractorImp implements ISectionExtractor {

	@Override
	public Elements extractSection(IHTMLFile htmlFile, SPCsections section){
		/* HTML file structure:
		 * <p class='AmmAnnexeTitreN'>
		 * 	   <a name='sectionName'></a>
		 * </p>
		 * <p class='AmmCorpsText'> some interesting text to extract </p>
		 * <p class='AmmCorpsText'> another interesting paragraph </p>
		 * <p class='AmmAnnexeTitreN+1'> // beginning of another section
		 * 
		 * The algorithm to extract the content of a section:
		 * 1) Find the <a> element by name
		 * 2) Go up to its parent
		 * 3) Go to its next sibling
		 * 4) Extract paragraph content till next section
		 */
		Elements outputEls = new Elements();
		Elements sectionEls = htmlFile.getDocument().getElementsByAttributeValue("name", section.getSectionName());
		if (sectionEls.size() == 0) { // no elements = section not found
			return(outputEls);
		}
		Element parentElement = sectionEls.parents().first();
		Element parentSibling = parentElement.nextElementSibling();
		boolean nextSection = false;
		while(!nextSection) {
			outputEls.add(parentSibling);
			parentSibling = parentSibling.nextElementSibling();
			if (parentSibling == null || isSiblingOrParent(parentSibling, section.getLevel())) {
				nextSection = true;
			}
		}
		return(outputEls);
	}
	
	/**
	 * Check if current element is an element of next sibling/parent section
	 * @param el sibling / parent element to check 
	 * @param level level of the current element (ex: 2 for AmmAnnexeTitre2)
	 * @return true if element is next section
	 */
	private boolean isSiblingOrParent(Element el, int level) {
		int currentLevel = level;
		while (currentLevel != 0) {
			String className="AmmAnnexeTitre" + level;
			if(el.hasClass(className)) {
				return(true);
			}
			currentLevel = currentLevel - 1;
		}
		return(false);
	}
}
