package io.koara;

import java.util.Stack;

/** BLOCKQUOTE 31, 60 */
public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private Stack<String> lead = new Stack<String>();
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		super.visit(node, null);
		return null;
	}
	
	@Override
	public Object visit(ASTHeading node, Object data) {
		for(int i=0; i < node.getLevel(); i++) {
			out.append("=");
		}
		if(node.hasChildren()) {
			out.append(" ");
			super.visit(node, data);
		}
		out.append("\n");
		out.append("\n");
		return null;
	}
	
	@Override
	public Object visit(ASTList node, Object data) {
		super.visit(node, data);
		if(node.isNested()) {
			lead.pop();
		}
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		out.append("- ");
		super.visit(node, data);
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		super.visit(node, data);
		
		if(node.next() instanceof ASTList) {
			lead.push("  ");
		}

		out.append("\n");
		lead();
		if(!node.isNested() || node.next() instanceof ASTParagraph) {
		out.append("\n");
		lead();	
		}
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		super.visit(node, data);
		out.append("\n");
		return null;
	}
	
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(node.value.toString()
				.replaceFirst("\\=", "\\\\=")
				.replaceFirst("\\-", "\\\\-")
				.replaceFirst("\\>", "\\\\>")
				.replaceAll("\\[", "\\\\[")
				.replaceAll("\\*", "\\\\*")
				.replaceAll("\\_", "\\\\_")
				.replaceAll("(\\d+)\\.", "\\\\$1\\.")
				.replaceAll("\\]", "\\\\]"));
		return null;
	}
	
	private void lead() {
		for(String l : lead) {
			out.append(l);
		}
	}
	
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}