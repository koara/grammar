package io.koara;

public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuilder out;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuilder();
		return super.visit(node, data);
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		super.visit(node, data);
		out.append("\n");
		out.append("\n");	
		return null;
	}
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(node.value.toString()
				.replaceFirst("\\=", "\\\\=")
				.replaceFirst("\\-", "\\\\-")
				.replaceAll("\\[", "\\\\[")
				.replaceAll("(\\d+)\\.", "\\\\$1\\.")
				.replaceAll("\\]", "\\\\]"));
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append("\n");
		return null;
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}