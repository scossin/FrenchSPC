package fr.erias.spc.extractpdf.writer;

import java.io.File;

public class ConsoleWriter implements IOutWriter {

	@Override
	public void write(File pdfFile, String content) {
		System.out.println(content);
	}

	@Override
	public boolean fileAlreadyExtracted(File pdfFile) {
		// TODO Auto-generated method stub
		return false;
	}
}
