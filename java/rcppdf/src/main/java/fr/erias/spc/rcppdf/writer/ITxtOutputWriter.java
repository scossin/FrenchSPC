package fr.erias.spc.rcppdf.writer;

import java.io.IOException;
import java.util.Map;

import fr.erias.spc.rcphtml.sections.SPCsections;

public interface ITxtOutputWriter {
	
	public void write(Map<SPCsections, String> extractedSections, String numCode) throws IOException;
	
	public void close() throws IOException;
	
}
