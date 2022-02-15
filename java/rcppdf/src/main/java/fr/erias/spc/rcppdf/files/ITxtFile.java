package fr.erias.spc.rcppdf.files;

import java.util.Iterator;

public interface ITxtFile {
	
	/**
	 * The European SPC code. 
	 * @return 128916 in anx_128916_fr.txt
	 */
	public String getNumCode();
	
	/**
	 * Iterates over each line of the file
	 * @return
	 */
	public Iterator<String> getLineIterator();
	
	/**
	 * Simply the filename
	 * @return
	 */
	public String getFilename();
	
	/**
	 * Close a reader
	 */
	public void closeFile();
	
}
