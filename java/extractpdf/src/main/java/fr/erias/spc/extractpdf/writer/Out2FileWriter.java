package fr.erias.spc.extractpdf.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import fr.erias.spc.extractpdf.utils.Utils;

public class Out2FileWriter implements IOutWriter {

	private File outputFolder;

	public Out2FileWriter(File outputFolder) {
		Utils.checkFolderExists(outputFolder);
		this.outputFolder = outputFolder;
	}

	@Override
	public void write(File pdfFile, String content) {
		// TODO Auto-generated method stub
		File outputFile = getOutputFileName(pdfFile);
		PrintWriter out;
		try {
			out = new PrintWriter(outputFile);
			out.println();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File getOutputFileName(File pdfFile) {
		String txtFileName = pdfFile.getName().replace(".pdf", ".txt"); // outputFile name has the same name
		File outputFile = new File(this.outputFolder.getAbsolutePath() + "/" + txtFileName);
		return(outputFile);
	}

	@Override
	public boolean fileAlreadyExtracted(File pdfFile) {
		// TODO Auto-generated method stub
		File outputFile = getOutputFileName(pdfFile);
		return outputFile.exists();
	}
}
