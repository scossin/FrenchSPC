package fr.erias.spc.rcphtml.sections;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Util {
	/**
	 * Return the text content of a list of HTML elements
	 * @param els a list of HTML elements
	 * @return
	 */
	public static String getText(Elements els) {
		StringBuilder sb = new StringBuilder();
		for (Element el : els){
			String text = el.text();
			sb.append(text);
			sb.append("\n");
		}
		return(sb.toString());
	}
	
//	public static void main(String[] args) throws IOException {
//		// test 
//		File file = new File(Config.BASE + "/data/SPC/60002283.html");
//		ParseSPChtml parseSPC = new ParseSPChtml(file,file.getName().replace(".html", ""));
//		Elements el = parseSPC.extractSec(SectionsDonneesCliniques.RcpEffetsIndesirables);
//		System.out.println(el.html());
//	}
}
