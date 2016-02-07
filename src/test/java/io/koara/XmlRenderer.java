package io.koara;

public class XmlRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		if(node.children != null && node.children.length > 0) {
			out.append("<document>\n");
			node.childrenAccept(this, data);
			out.append("</document>");
		} else {
			out.append("<document />");
		}
		return null;
	}
	
	@Override
	public Object visit(ASTHeading node, Object data) {
		level++;
		out.append(indent() + "<heading level=\"" + node.value + "\"");
		if(node.children != null && node.children.length > 0) {
			out.append(">\n");
			level++;
			node.childrenAccept(this, data);
			level--;
			out.append(indent() + "</heading>\n");
		} else {
			out.append(" />\n");
		}
		
		
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		level++;
		out.append(indent() + "<paragraph>\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</paragraph>\n");
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTList node, Object data) {
		level++;
		out.append(indent() + "<list ordered=\"" + node.isOrdered() + "\">\n");
		node.childrenAccept(this, data);
		out.append(indent() + "</list>\n");
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTListItem node, Object data) {
		level++;
		out.append(indent() + "<listitem");
		if(node.getNumber() != null) {
			out.append(" number=\"" + node.getNumber() + "\"");
		}
		if(node.children != null && node.children.length > 0) {
			out.append(">\n");
			node.childrenAccept(this, data);
			out.append(indent() + "</listitem>\n");
		} else {
			out.append(" />\n");
		}
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTText node, Object data) {
		out.append(indent() + "<text>");
		out.append(escape(node.value.toString()));
		out.append("</text>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTLineBreak node, Object data) {
		out.append(indent() + "<linebreak />\n");
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
	
	public String escape(String text) {
		return text.replaceAll("&", "&amp;")
				.replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;")
				.replaceAll("\"", "&quot;");
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}
