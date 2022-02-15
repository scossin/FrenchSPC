package fr.erias.spc.rcppdf.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.json.JSONObject;

import fr.erias.spc.rcphtml.sections.SPCsections;

/**
 * Writes the output to a JsonFile
 * 
 * @author Sebastien Cossin
 *
 */
public class OutputWriterJson implements ITxtOutputWriter {

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
	public void write(Map<SPCsections, String> extractedSections, String numCode) throws IOException {
		if (this.needComma) w.write(',');
		JSONObject cisJson = new JSONObject();
		cisJson.put("numCode",numCode);
		extractedSections.forEach((section, string) -> {
			cisJson.put(section.getSectionName(), string);
		});
		cisJson.write(w);
		this.needComma = true;
	}
}
