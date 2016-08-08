package io.koara;

public class XmlRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	private int level;
	private String declarationTag;
	private boolean hardWrap;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		if(declarationTag != null) {
		    out.append(declarationTag + "\n");
		}
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
	public Object visit(ASTBlockQuote node, Object data) {
		level++;
		out.append(indent() + "<blockquote");
		if(node.children != null && node.children.length > 0) {
			out.append(">\n");
			level++;
			node.childrenAccept(this, data);
			level--;
			out.append(indent() + "</blockquote>\n");
			level--;
		} else {
			out.append(" />\n");
		}
		return null;
	}
	
	@Override
	public Object visit(ASTCodeBlock node, Object data) {
		level++;
		out.append(indent() + "<codeblock");
		if(node.getLanguage() != null) {
			out.append(" language=\"" + escape(node.getLanguage()) + "\"");
		}
		if(node.value != null && node.value.toString().length() > 0) {
			out.append(">");
			level++;
			out.append(escape(node.value.toString()));
			level--;
			out.append("</codeblock>\n");
		} else {
			out.append(" />\n");
		}
		level--;
		return null;
	}
	
	
	@Override
	public Object visit(ASTLink node, Object data) {
		out.append(indent() + "<link url=\"" + escapeUrl(node.value.toString()) + "\">\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</link>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTImage node, Object data) {
		out.append(indent() + "<image url=\"" + escapeUrl(node.value.toString()) + "\">\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</image>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTEm node, Object data) {
		out.append(indent() + "<em>\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</em>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTStrong node, Object data) {
		out.append(indent() + "<strong>\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</strong>\n");
		return null;
	}
	
	@Override
	public Object visit(ASTCode node, Object data) {
		out.append(indent() + "<code>\n");
		level++;
		node.childrenAccept(this, data);
		level--;
		out.append(indent() + "</code>\n");
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
		boolean hard = hardWrap || node.isExplicit();
		out.append(indent() + "<linebreak explicit=\"" + hard + "\"/>\n");
		return null;
	}
	
	public String escapeUrl(String text) {
		return text.replaceAll(" ", "%20")
				.replaceAll("\"", "%22")
				.replaceAll("`", "%60")
				.replaceAll("<", "%3C")
				.replaceAll(">", "%3E")
				.replaceAll("\\[", "%5B")
				.replaceAll("\\]", "%5D")
				.replaceAll("\\\\", "%5C");
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
	
	public void setHardWrap(boolean hardWrap) {
		this.hardWrap = hardWrap;
	}
	
	public void setDeclarationTag(String declarationTag) {
		this.declarationTag = declarationTag;
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}
