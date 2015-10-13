package io.koara;

public class Html5Renderer extends KoaraDefaultVisitor {

	private StringBuffer out;

	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		return super.visit(node, data);
	}
		
	@Override
	public Object visit(ASTParagraph node, Object data) {
		out.append("<p>");
		super.visit(node, data);
		out.append("</p>\n\n");
		return null;
	}
		
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(escape(node.value.toString()));
		return null;
	}
	
	public String escape(String text) {
		return text.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;")
				.replaceAll("\"", "&quot;");
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append("<br>\n");
		return super.visit(node, data);
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}