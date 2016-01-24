package io.koara;

public class XmlRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		out.append("<document>\n");
		node.childrenAccept(this, data);
		out.append("</document>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTParagraph node, Object data) {
		level++;
		out.append(indent() + "<paragraph>\n");
		node.childrenAccept(this, data);
		out.append(indent() + "</paragraph>\n");
		level--;
		return null;
	}
	
	@Override
	public Object visit(ASTText node, Object data) {
		level++;
		out.append(indent() + "<text>");
		out.append(node.value);
		out.append("</text>\n");
		level--;
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
