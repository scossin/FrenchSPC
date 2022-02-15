package fr.erias.spc.extractpdf.extractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class PDFextractor implements IPDFextractor {

	@Override
	public String extractPDFcontent(File pdfFile) throws IOException {
		BodyContentHandler handler = new BodyContentHandler(-1);
		FileInputStream stream = new FileInputStream(pdfFile);
		Metadata metadata = new Metadata();
		AutoDetectParser parser = new AutoDetectParser();
		try {
			parser.parse(stream, handler, metadata);
		} catch (SAXException | TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return(handler.toString().trim());
	}
}
