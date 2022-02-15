package fr.erias.spc.rcphtml.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import org.json.JSONObject;

import fr.erias.spc.rcphtml.handler.IHTMLextracted;
import fr.erias.spc.rcphtml.sections.Util;

/**
 * Writes the output to a JsonFile
 * 
 * @author Sebastien Cossin
 *
 */
public class OutputWriterJson implements IOutputWriter {

	private BufferedWriter w;
	private boolean needComma = false;
	
	public OutputWriterJson(File outputFile) throws IOException {
		deleteFileifExists(outputFile);
		this.w = new BufferedWriter(new PrintWriter(new FileOutputStream(outputFile, true)));
		w.write('['); // beginning of the JSON Array
	}

	private void deleteFileifExists(File outputFile) {
		if (outputFile.exists()) {
			System.out.println("Deleting existing file " + outputFile.getAbsolutePath());
			outputFile.delete();
		}
	}

	public void close() throws IOException {
		w.write(']'); // end of the JSON Array
		w.flush();
		w.close();
	}

	@Override
	public void write(IHTMLextracted html) throws IOException {
		if (this.needComma) w.write(',');
		JSONObject cisJson = new JSONObject();
		cisJson.put("cis", html.getCisCode());
		html.getExtractedSection().forEach((section, el) -> {
			cisJson.put(section.getSectionName(), Util.getText(el));
		});
		cisJson.write(w);
		this.needComma = true;
	}
}
