package io.koara;

public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		node.childrenAccept(this, data);
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		node.childrenAccept(this, data);
		out.append("\n\n");
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append("\n");
		return null;
	}
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(escape(node.value.toString()));
		return null;
	}
	
	private String escape(String text) {
		return text.replaceAll("\\[", "\\\\[")
				.replaceAll("\\]", "\\\\]")
				.replaceFirst("\\=", "\\\\=");
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}