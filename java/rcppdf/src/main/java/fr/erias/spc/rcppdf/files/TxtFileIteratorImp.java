package fr.erias.spc.rcppdf.files;

import java.io.File;
import java.util.Iterator;

/**
 * Iterates over text files in a folder
 * 
 * @author Sebastien Cossin
 *
 */
public class TxtFileIteratorImp implements ITxtFileIterator {

	private File[] rcpFiles;
	
	public TxtFileIteratorImp(File inputFolder) {
		checkFolderExists(inputFolder);
		this.rcpFiles = inputFolder.listFiles();
	}
	
	public static void checkFolderExists(File folder) {
		if (!folder.isDirectory()) {
			throw new NullPointerException("folder" + folder + "doesn't exist");
		}
		return;
	}
	
	@Override
	public Iterator<File> getFileIterator() {
		// TODO Auto-generated method stub
		return new RCPiterator(this.rcpFiles);
	}
}

class RCPiterator implements Iterator<File> {

	private File[] rcpFiles;
	private int i = 0;
	
	public RCPiterator(File[] rcpFiles) {
		this.rcpFiles = rcpFiles;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return i < rcpFiles.length;
	}

	@Override
	public File next() {
		File rcpFile = rcpFiles[i];
		i+=1;
		return(rcpFile);
	}
}
