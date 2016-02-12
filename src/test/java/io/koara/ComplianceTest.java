package io.koara;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ComplianceTest {

	private static final String TESTSUITE_FOLDER = "src/test/testsuite";
	
	private String module;
	private String testcase;
	
	public ComplianceTest(String module, String testcase) {
  		this.module = module;
  		this.testcase = testcase;
    }
	
	@Parameters(name= "{0}: {1}")
	public static Iterable<Object[]> data() {
		List<Object[]> modules = new ArrayList<Object[]>();
		for(File module : new File(TESTSUITE_FOLDER + "/input").listFiles()) {
			if(module.isDirectory()) {		
				for(File testcase : module.listFiles()) {
					modules.add(new Object[]{module.getName(), testcase.getName().substring(0, testcase.getName().length() - 3)});
				}
			}
		}
		return modules;
	}

	@Test
	public void testKoaraToHtml5() throws Exception {
		Koara koara = new Koara(new FileInputStream(TESTSUITE_FOLDER + "/input/" + module + "/" + testcase + ".kd"));
		String output = readFile(TESTSUITE_FOLDER + "/output/html5/" + module + "/" + testcase + ".htm");
		ASTDocument document = koara.Document();
		Html5Renderer renderer = new Html5Renderer();
		document.jjtAccept(renderer, null);
		assertEquals(output, renderer.getOutput());
	}
	
	@Test
	public void testKoaraToXml() throws Exception {
		Koara koara = new Koara(new FileInputStream(TESTSUITE_FOLDER + "/input/" + module + "/" + testcase + ".kd"));
		String output = readFile(TESTSUITE_FOLDER + "/output/xml/" + module + "/" + testcase + ".xml");
		ASTDocument document = koara.Document();
		XmlRenderer renderer = new XmlRenderer();
		document.jjtAccept(renderer, null);
		assertEquals(output, renderer.getOutput());
	}
	
	
	@Test
	public void testKoaraToKoara() throws Exception {
		Koara koara = new Koara(new FileInputStream(TESTSUITE_FOLDER + "/input/" + module + "/" + testcase + ".kd"));
		if(new File(TESTSUITE_FOLDER + "/output/koara/" + module + "/" + testcase + ".kd").exists()) {
			String output = readFile(TESTSUITE_FOLDER + "/output/koara/" + module + "/" + testcase + ".kd");
			ASTDocument document = koara.Document();
			KoaraRenderer koaraRenderer = new KoaraRenderer();
			document.jjtAccept(koaraRenderer, null);
			assertEquals(output, koaraRenderer.getOutput());
			
			// make sure the new 'formatted' koara code renders the small HTML output.
//			String html5expected = readFile(TESTSUITE_FOLDER + "/output/html5/" + module + "/" + testcase + ".htm");
//			koara = new Koara(new StringReader(output));
//			document = koara.Document();
//			Html5Renderer html5Renderer = new Html5Renderer();
//			document.jjtAccept(html5Renderer, null);
//			assertEquals(html5expected, html5Renderer.getOutput());
		}
	}
	
	private String readFile(String path) throws IOException {
		BufferedReader reader = null;
		try {
			StringBuffer fileData = new StringBuffer();
			reader = new BufferedReader(new FileReader(path));
			char[] buf = new char[1024];
	        int numRead=0;
	        while((numRead=reader.read(buf)) != -1){
	            String readData = String.valueOf(buf, 0, numRead);
	            fileData.append(readData);
	        }
	        return fileData.toString();
		} finally {
			reader.close();
		}
    }
	
	
	
}