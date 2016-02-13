package io.koara;

public class ASTBlockElement extends SimpleNode {

	public ASTBlockElement(int i) {
		super(i);
	}
	
	public boolean isNested() {
		return !(parent instanceof ASTDocument);
	}
	
	
	public boolean isFirstChild() {
		return ((SimpleNode) this.parent).children[0] == this;
	}
	
	public boolean isSingleChild() {
		return ((SimpleNode) this.parent).children.length == 1;
	}
	
	public Node next() {
		Node[] nodes = ((SimpleNode) this.parent).children;
		for(int i=0; i<nodes.length - 1; i++) {
			if(nodes[i] == this) {
				return nodes[i + 1];
			}
		}
		return null;
	}

}
