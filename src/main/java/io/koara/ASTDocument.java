/* Generated By:JJTree: Do not edit this line. ASTDocument.java Version 6.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package io.koara;

public
class ASTDocument extends SimpleNode {
  public ASTDocument(int id) {
    super(id);
  }

  public ASTDocument(Koara p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(KoaraVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=a3956fe9ac4d202dd5ebd9e38d55c995 (do not edit this line) */