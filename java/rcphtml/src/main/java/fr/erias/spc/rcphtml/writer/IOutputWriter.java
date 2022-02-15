package fr.erias.spc.rcphtml.writer;

import java.io.IOException;

import fr.erias.spc.rcphtml.handler.IHTMLextracted;

public interface IOutputWriter {
	
	public void write(IHTMLextracted htmlRCPcontent) throws IOException;
	
	public void close() throws IOException;
	
}
