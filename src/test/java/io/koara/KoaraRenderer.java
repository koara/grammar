package io.koara;

import java.util.Stack;

public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuilder out;
	private int level;
	private Stack<Integer> listSequence = new Stack<Integer>();
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuilder();
		level = 0;
		return super.visit(node, data);
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
		
		out.append("\n");
		
		Node next = node.next();
		if(next instanceof ASTList && ((ASTList) next).isOrdered() == node.isOrdered()) {
			out.append("\n");
		}
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		if(node.isOrdered()) {
			out.append(indent() + (node.getNumber() != null ? node.getNumber() : listSequence.peek()) + ".");		
		} else {
			out.append(indent() + "-");
		}
		if(node.hasChildren()) {
			out.append(" ");
			level++;
			super.visit(node, data);
			level--;
		} else {
			out.append("\n");
		}
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		if(!(node.parent instanceof ASTListItem && node.isFirstChild())) {
			out.append(indent());
		}
		super.visit(node, data);
		out.append("\n");
		if(!node.isLastElement() && !(node.parent instanceof ASTListItem && node.isFirstChild() && node.parent.jjtGetChild(1) instanceof ASTList)) {
			out.append("\n");
		}

		return null;
	}
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(node.value.toString()
				.replaceFirst("\\=", "\\\\=")
				.replaceFirst("\\-", "\\\\-")
				.replaceAll("\\[", "\\\\[")
				.replaceAll("\\*", "\\\\*")
				.replaceAll("(\\d+)\\.", "\\\\$1\\.")
				.replaceAll("\\]", "\\\\]"));
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append("\n");
		out.append(indent());
		return null;
	}
	
	public String indent() {
		int repeat = level * 2;
	    final char[] buf = new char[repeat];
		for (int i = repeat - 1; i >= 0; i--) {
		 buf[i] = ' ';
		} 
		return new String(buf);
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}