package fr.erias.spc.rcphtml.files;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Iterates over HTML files
 * 
 * @author Sebastien Cossin
 *
 */
public class HTMLfileIteratorImp implements IHTMLfileIterator {

	private File[] rcpFiles;
	
	public HTMLfileIteratorImp(File inputFolder) {
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
	public Iterator<IHTMLFile> getFileIterator() {
		// TODO Auto-generated method stub
		return new RCPiterator(this.rcpFiles);
	}
}

class RCPiterator implements Iterator<IHTMLFile> {

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
	public IHTMLFile next() {
		File rcpFile = rcpFiles[i];
		i+=1;
		try {
			return new HTMLFile(rcpFile) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
