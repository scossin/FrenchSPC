package fr.erias.spc.extractpdf.utils;

import java.io.File;
import java.io.FilenameFilter;

public class Utils {
	
	public static void checkFolderExists(File folder) {
		if (!folder.isDirectory()) {
			throw new NullPointerException("folder" + folder + "doesn't exist");
		}
		return;
	}
	
	public static FilenameFilter pdfFilter = new FilenameFilter() {
		@Override
		public boolean accept(File dir, String name) {
			// TODO Auto-generated method stub
			return name.endsWith(".pdf");
		}
	};
}
