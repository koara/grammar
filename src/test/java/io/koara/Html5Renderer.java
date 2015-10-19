package io.koara;

import java.util.Stack;

public class Html5Renderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level;
	private Stack<Integer> listSequence = new Stack<Integer>();

	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		node.childrenAccept(this, data);
		return null;
	}
	
	@Override
	public Object visit(ASTHeading node, Object data) {
		out.append(indent() + "<h" + node.value + ">");
		super.visit(node, data);
		out.append("</h" + node.value + ">\n");
		if(!node.isNested()) { out.append("\n"); }
		return null;
	}
	
	@Override
	public Object visit(ASTList node, Object data) {
		listSequence.push(0);
		String tag = node.isOrdered() ? "ol" : "ul";
		out.append(indent() + "<" + tag + ">\n");
		level++;
		super.visit(node, data);
		level--;
		out.append(indent() + "</" + tag + ">\n");
		if(!node.isNested()) { out.append("\n"); }
		listSequence.pop();
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		Integer seq = listSequence.peek() + 1;		
		listSequence.set(listSequence.size() - 1, listSequence.peek() + 1);
		out.append(indent() + "<li");
		if(node.getNumber() != null && (seq != node.getNumber())) {
			out.append(" value=\"" + node.getNumber() + "\"");
			listSequence.set(listSequence.size() - 1, node.getNumber());
		}
		out.append(">");
		if(node.children != null) {
			if(node.children.length > 1 || !(node.children[0] instanceof ASTParagraph)) { out.append("\n"); }
			level++;
			super.visit(node, data);
			level--;
			if(node.children.length > 1 || !(node.children[0] instanceof ASTParagraph)) { out.append(indent()); }
		}
		out.append("</li>\n");
		return null;
	}
		
	@Override
	public Object visit(ASTParagraph node, Object data) {
		if(node.isNested() && (node.parent instanceof ASTListItem) && node.isSingleChild()) {
			super.visit(node, data);
		} else {
			out.append(indent() + "<p>");
			super.visit(node, data);
			out.append("</p>\n");
			if(!node.isNested()) { out.append("\n"); }
		}
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
		out.append("<br>\n" + indent());
		return super.visit(node, data);
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