/* Generated By:JavaCC: Do not edit this line. KoaraVisitor.java Version 6.0_1 */
package io.koara;

public interface KoaraVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTDocument node, Object data);
  public Object visit(ASTParagraph node, Object data);
  public Object visit(ASTText node, Object data);
  public Object visit(ASTLineBreak node, Object data);
}
/* JavaCC - OriginalChecksum=fabed19df97803d62ba721f097e0cf94 (do not edit this line) */
