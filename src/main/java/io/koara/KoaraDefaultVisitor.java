/* Generated By:JavaCC: Do not edit this line. KoaraDefaultVisitor.java Version 6.0_1 */
package io.koara;

public class KoaraDefaultVisitor implements KoaraVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTDocument node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTHeading node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTList node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTListItem node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTParagraph node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTImage node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTLink node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTText node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTLineBreak node, Object data){
    return defaultVisit(node, data);
  }
}
/* JavaCC - OriginalChecksum=5f2de49e6cdfef207fc1e0051ea88a5a (do not edit this line) */
