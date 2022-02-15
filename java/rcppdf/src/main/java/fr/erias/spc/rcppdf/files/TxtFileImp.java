package fr.erias.spc.rcppdf.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.erias.spc.rcppdf.InvalidFilenameException;

/**
 * Iterates over each line of the file
 * 
 * @author Sebastien Cossin
 */
public class TxtFileImp implements ITxtFile, Iterator<String> {

	private static Pattern pattern = Pattern.compile("[0-9]+");
	private String numCode;
	private BufferedReader br;
	private String nextLine = null;
	private File txtFile;
	
	public TxtFileImp(File txtFile) throws InvalidFilenameException, IOException {
		this.txtFile = txtFile;
		extractNumCode(txtFile.getName());
		this.br = new BufferedReader(new FileReader(txtFile));
		this.nextLine = br.readLine();
	}
	
	private void extractNumCode(String filename) throws InvalidFilenameException {
		Matcher matcher = pattern.matcher(filename);
		if (matcher.find()) {
			this.numCode = matcher.group(0).trim();
			return;
		}
		throw new InvalidFilenameException("Fail to extract numCode in " + filename);
	}
	
	@Override
	public void closeFile() {
		try {
			this.closeReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String getNumCode() {
		return this.numCode;
	}

	@Override
	public Iterator<String> getLineIterator() {
		// TODO Auto-generated method stub
		return this;
	}
	
	public void closeReader() throws IOException {
		br.close();
	}
	
	@Override
	public boolean hasNext() {
		return nextLine != null;
	}

	@Override
	public String next() {
		String currentLine = nextLine;
		try {
			this.nextLine = br.readLine();
			if (this.nextLine == null) {
				br.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentLine;
	}
	
	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return txtFile.getName();
	}
}

