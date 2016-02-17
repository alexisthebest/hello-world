package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import net.sf.saxon.TransformerFactoryImpl;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class TestClass {

	final String OUTPUT_DIR ="C:/Users/Alex/Documents/xslt";
	
	@Test
	public void test() throws DocumentException, UnsupportedEncodingException, FileNotFoundException, TransformerException {
		TransformerFactory transformerFactory = new TransformerFactoryImpl();
		
		SAXReader reader = new SAXReader();
		Document doc = reader.read(getClass().getResourceAsStream("../greeting.xml"));
		Source xml = new StreamSource(new ByteArrayInputStream(doc.asXML().getBytes("UTF-8")));
		Source xsl = new StreamSource(getClass().getResourceAsStream("../greeting.xsl"));
		
		File index = new File(OUTPUT_DIR, "index.xhtml");
		
		Transformer transformer = transformerFactory.newTransformer(xsl);
		transformer.setParameter("outputdir", OUTPUT_DIR);
		transformer.transform(xml, new StreamResult(new FileOutputStream(index)));
	}
}
