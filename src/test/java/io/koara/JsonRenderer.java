package io.koara;

public class JsonRenderer extends KoaraDefaultVisitor {

	private StringBuffer out;
	
	@Override
	public Object visit(ASTDocument node, Object data) {
		out = new StringBuffer();
		node.childrenAccept(this, data);
		return null;
	}
	
	public String getOutput() {
		return out.toString().trim();
	}
	
}
