package io.koara;

import java.io.FileInputStream;

import org.junit.Test;

public class LearningTest {

	@Test
	public void test() throws Exception {
		Koara koara = new Koara(new FileInputStream("input.kd"));
		ASTDocument document = koara.Document();
		Html5Renderer renderer = new Html5Renderer();
		
		
		
		document.jjtAccept(renderer, null);
		
		System.out.println("\n\n\n");
		
		System.err.println(renderer.getOutput());
	}
	
}
