package io.koara;

import java.util.Stack;

public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level;
	private Stack<Integer> listSequence;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		listSequence = new Stack<Integer>();
		level = -1;
		node.childrenAccept(this, data);
		return null;
	}
	
	@Override
	public Object visit(ASTHeading node, Object data) {
		Integer headingLevel = Integer.parseInt(node.value.toString());
		for(int i=0; i<headingLevel; i++) {
			out.append("=");
		}
		if(node.children != null && node.children.length > 0) {
			out.append(" ");
			super.visit(node, data);
		}
		out.append("\n\n");
		return null;
	}
	
	@Override
	public Object visit(ASTList node, Object data) {
		listSequence.push(0);
		level++;
		super.visit(node, data);
		level--;
		listSequence.pop();
		out.append("\n");
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		boolean ordered = ((ASTList) node.parent).isOrdered();
		Integer seq = (node.getNumber() != null) ? node.getNumber() : listSequence.peek() + 1;
		out.append(indent() + (ordered ? seq + "." : "-"));
		if(node.children != null && node.children.length > 0) {
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
		if(node.parent.jjtGetChild(0) != node) {
			out.append(indent());
		}
		node.childrenAccept(this, data);
		out.append("\n");
		if(!node.isSingleChild()) {
			out.append("\n");
		} 
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append("\n" + indent());
		return null;
	}
	
	@Override
	public Object visit(ASTLink node, Object data) {
		out.append("[");
		node.childrenAccept(this, data);
		out.append("]");
		if(node.value.toString() != "") {
			out.append("(");
			out.append(escapeUrl(node.value.toString()));
			out.append(")");
		}
		return null;
	}
	
	@Override
	public Object visit(ASTImage node, Object data) {
		out.append("[image: ");
		node.childrenAccept(this, data);
		out.append("]");
		if(node.value.toString() != "") {
			out.append("(");
			out.append(escapeUrl(node.value.toString()));
			out.append(")");
		}
		return null;
	}
	
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(escape(node.value.toString()));
		return null;
	}
	
	private String escapeUrl(String text) {
		return text.replaceAll("\\[", "\\\\[")
				.replaceAll("\\]", "\\\\]")
				.replaceAll("\\(", "\\\\(")
				.replaceAll("\\)", "\\\\)");
	}
	
	private String escape(String text) {
		return text.replaceAll("\\[", "\\\\[")
				.replaceAll("\\]", "\\\\]")
				.replaceAll("\\(", "\\\\(") 			// TODO: can be removed?
				.replaceAll("\\)", "\\\\)") 			// TODO: can be removed?
				.replaceAll("\\:", "\\\\:")
				.replaceAll("\\-", "\\\\-") 			// TODO: only first?
				.replaceAll("\\*", "\\\\*")
				.replaceAll("\\_", "\\\\_")
				.replaceAll("(\\d)\\.", "\\\\$1\\\\.") 	// TODO: not necessary in resource
				.replaceFirst("\\=", "\\\\="); 			// TODO: not necessary in resource
	}
	
	public String indent() {
		if(level > 0) {
			int repeat = level * 2;
		    final char[] buf = new char[repeat];
			for (int i = repeat - 1; i >= 0; i--) {
			 buf[i] = ' ';
			} 
			return new String(buf);
		}
		return "";
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}