/* Generated By:JJTree: Do not edit this line. ASTImage.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.koara;

public
class ASTImage extends SimpleNode {
  public ASTImage(int id) {
    super(id);
  }

  public ASTImage(Koara p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(KoaraVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=75d5c2862ca6050d7a5936be7afc5b1f (do not edit this line) */
