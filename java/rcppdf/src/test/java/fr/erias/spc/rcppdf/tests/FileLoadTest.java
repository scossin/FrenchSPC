package fr.erias.spc.rcppdf.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import fr.erias.spc.rcppdf.InvalidFilenameException;
import fr.erias.spc.rcppdf.files.ITxtFile;
import fr.erias.spc.rcppdf.files.TxtFileImp;

public class FileLoadTest {
	
	public static ITxtFile get128916File() throws IOException, InvalidFilenameException {
		String filename = Thread.currentThread().getContextClassLoader().getResource("anx_128916_fr.txt").getFile();
		File file = new File(filename);
		ITxtFile txtFile = new TxtFileImp(file);
		return(txtFile);
	}
	
	@Test
	public void TxtFileLoadTest() throws IOException, InvalidFilenameException {
		String filename = Thread.currentThread().getContextClassLoader().getResource("anx_128916_fr.txt").getFile();
		File file = new File(filename);
		ITxtFile txtFile = new TxtFileImp(file);
		assertTrue(txtFile.getNumCode().equals("128916"));
		Iterator<String> iter = txtFile.getLineIterator();
		int countLine = 0;
		while(iter.hasNext()) {
			iter.next();
			countLine += 1;
		}
		assertEquals(countLine, 2159);
	}
}
