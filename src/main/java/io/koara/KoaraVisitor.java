/* Generated By:JavaCC: Do not edit this line. KoaraVisitor.java Version 6.0_1 */
package io.koara;

public interface KoaraVisitor
{
  public Object visit(SimpleNode node, Object data);
  public Object visit(ASTDocument node, Object data);
  public Object visit(ASTHeading node, Object data);
  public Object visit(ASTBlockquote node, Object data);
  public Object visit(ASTList node, Object data);
  public Object visit(ASTListItem node, Object data);
  public Object visit(ASTCodeBlock node, Object data);
  public Object visit(ASTParagraph node, Object data);
  public Object visit(ASTImage node, Object data);
  public Object visit(ASTLink node, Object data);
  public Object visit(ASTText node, Object data);
  public Object visit(ASTStrong node, Object data);
  public Object visit(ASTEm node, Object data);
  public Object visit(ASTCode node, Object data);
  public Object visit(ASTLineBreak node, Object data);
}
/* JavaCC - OriginalChecksum=392076de85d12d42090f3fc238fb7cd8 (do not edit this line) */
