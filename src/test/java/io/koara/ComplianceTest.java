package io.koara;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ComplianceTest {

	private String module;
	private String testcase;
	
	public ComplianceTest(String module, String testcase) {
  		this.module = module;
  		this.testcase = testcase;
    }
	
	@Parameters(name= "{0}: {1}")
	public static Iterable<Object[]> data() {
		List<Object[]> modules = new ArrayList<Object[]>();
		for(File module : new File("testsuite/input").listFiles()) {
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
		Koara koara = new Koara(new FileInputStream("testsuite/input/" + module + "/" + testcase + ".kd"));
		String output = readFile("testsuite/output/html5/" + module + "/" + testcase + ".htm");
		ASTDocument document = koara.Document();
		Html5Renderer renderer = new Html5Renderer();
		document.jjtAccept(renderer, null);
		assertEquals(output, renderer.getOutput());
	}
	
	@Test
	public void testKoaraToXml() throws Exception {
		Koara koara = new Koara(new FileInputStream("testsuite/input/" + module + "/" + testcase + ".kd"));
		String output = readFile("testsuite/output/xml/" + module + "/" + testcase + ".xml");
		ASTDocument document = koara.Document();
		XmlRenderer renderer = new XmlRenderer();
		document.jjtAccept(renderer, null);
		assertEquals(output, renderer.getOutput());
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