package colla.kernel.util;

import org.junit.*;
import static org.junit.Assert.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.Attributes2Impl;

/**
 * The class <code>SAXReaderTest</code> contains tests for the class <code>{@link SAXReader}</code>.
 *
 * @generatedBy CodePro at 10/09/13 18:26
 * @author joaovq
 * @version $Revision: 1.0 $
 */
public class SAXReaderTest {
	/**
	 * Run the SAXReader() constructor test.
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testSAXReader_1()
		throws Exception {
		SAXReader result = new SAXReader();
		assertNotNull(result);
		// add additional test code here
	}

	/**
	 * Run the void endDocument() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testEndDocument_1()
		throws Exception {
		SAXReader fixture = new SAXReader();

		fixture.endDocument();

		// add additional test code here
	}

	/**
	 * Run the String getIPfromXML() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetIPfromXML_1()
		throws Exception {
		SAXReader fixture = new SAXReader();

		String result = fixture.getIPfromXML();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the Integer getPortNumberFromXML() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testGetPortNumberFromXML_1()
		throws Exception {
		SAXReader fixture = new SAXReader();

		Integer result = fixture.getPortNumberFromXML();

		// add additional test code here
		assertEquals(null, result);
	}

	/**
	 * Run the void parse(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = org.xml.sax.SAXParseException.class)
	public void testParse_1()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String arquivo = "";

		fixture.parse(arquivo);

		// add additional test code here
	}

	/**
	 * Run the void parse(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = org.xml.sax.SAXParseException.class)
	public void testParse_2()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String arquivo = "";

		fixture.parse(arquivo);

		// add additional test code here
	}

	/**
	 * Run the void parse(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = org.xml.sax.SAXParseException.class)
	public void testParse_3()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String arquivo = "";

		fixture.parse(arquivo);

		// add additional test code here
	}

	/**
	 * Run the void parse(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = org.xml.sax.SAXParseException.class)
	public void testParse_4()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String arquivo = "";

		fixture.parse(arquivo);

		// add additional test code here
	}

	/**
	 * Run the void parse(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test(expected = org.xml.sax.SAXParseException.class)
	public void testParse_5()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String arquivo = "";

		fixture.parse(arquivo);

		// add additional test code here
	}

	/**
	 * Run the void startDocument() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartDocument_1()
		throws Exception {
		SAXReader fixture = new SAXReader();

		fixture.startDocument();

		// add additional test code here
	}

	/**
	 * Run the void startElement(String,String,String,Attributes) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartElement_1()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String uri = "";
		String localName = "";
		String tag = "";
		Attributes atributos = new Attributes2Impl();

		fixture.startElement(uri, localName, tag, atributos);

		// add additional test code here
	}

	/**
	 * Run the void startElement(String,String,String,Attributes) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartElement_2()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String uri = "a";
		String localName = "a";
		String tag = "";
		Attributes atributos = new Attributes2Impl();

		fixture.startElement(uri, localName, tag, atributos);

		// add additional test code here
	}

	/**
	 * Run the void startElement(String,String,String,Attributes) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartElement_3()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String uri = "a";
		String localName = "a";
		String tag = "";
		Attributes atributos = new Attributes2Impl();

		fixture.startElement(uri, localName, tag, atributos);

		// add additional test code here
	}

	/**
	 * Run the void startElement(String,String,String,Attributes) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartElement_4()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String uri = "";
		String localName = "";
		String tag = "";
		Attributes atributos = new Attributes2Impl();

		fixture.startElement(uri, localName, tag, atributos);

		// add additional test code here
	}

	/**
	 * Run the void startElement(String,String,String,Attributes) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Test
	public void testStartElement_5()
		throws Exception {
		SAXReader fixture = new SAXReader();
		String uri = "";
		String localName = "";
		String tag = "";
		Attributes atributos = new Attributes2Impl();

		fixture.startElement(uri, localName, tag, atributos);

		// add additional test code here
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 10/09/13 18:26
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(SAXReaderTest.class);
	}
}