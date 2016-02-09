package io.koara;

import java.util.Stack;

public class KoaraRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level = -1;
	private Stack<Integer> listSequence = new Stack<Integer>();
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		node.childrenAccept(this, data);
		return null;
	}
	
	@Override
	public Object visit(ASTHeading node, Object data) {
		int level = Integer.parseInt(node.value.toString());
		for(int i=0; i<level; i++) {
			out.append("=");
		}
		if(node.children != null && node.children.length > 0) {
			out.append(" ");
			node.childrenAccept(this, data);
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
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		boolean ordered = ((ASTList) node.parent).isOrdered();
		if(ordered) {
			Integer seq = (node.getNumber() != null ? node.getNumber() : listSequence.peek() + 1);
			out.append(indent() + seq + ". ");
		} else {			
		  out.append(indent() + "- ");
		}
		level++;
		super.visit(node, data);
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		if(node.parent instanceof ASTListItem) {
			if(node.parent.jjtGetChild(0) != node) {
				out.append(indent());
			}
		} else {
			out.append(indent());
		}
		
		
		node.childrenAccept(this, data);
		out.append("\n");
		
		if(node.parent instanceof ASTListItem) {
			if(node.parent.jjtGetNumChildren() > 1 && !(node.parent.jjtGetChild(1) instanceof ASTList)) {
				out.append("\n");
			}
		} else {
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
	public Object visit(ASTText node, Object data) {
		out.append(escape(node.value.toString()));
		return null;
	}
	
	public String escape(String text) {
		return text.replaceAll("\\[", "\\\\[")
				.replaceAll("\\]", "\\\\]")
				.replaceFirst("\\=", "\\\\=");
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