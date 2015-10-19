/* Koara.java */
/* Generated By:JJTree&JavaCC: Do not edit this line. Koara.java */
package io.koara;

import java.util.Arrays;

public class Koara/*@bgen(jjtree)*/implements KoaraTreeConstants, KoaraConstants {/*@bgen(jjtree)*/
  protected JJTKoaraState jjtree = new JJTKoaraState();
        private int currentBlockLevel = 0;

  private boolean blockAhead() {
    if(getToken(1).kind == EOL) {
      Token t;
      int i = 2;
      int eol = 0;
              do {
              do {
             t = getToken(i++);
                 if(t.kind == EOL && currentBlockLevel > 0 && ++eol > 2) { return false; }
                 if(t.kind == GT) {
                   if(t.beginColumn == 1 && currentBlockLevel > 0)  {
                           return false;
                   }
                  }
              } while (t.kind == GT || t.kind == SPACE || t.kind == TAB);
      } while(t.kind == EOL);

      return (t.kind != EOF) && (t.beginColumn > ((currentBlockLevel * 4) - 2));
    }
    return false;
  }


  private boolean headingAhead(int offset) {
    if (getToken(offset).kind == EQ) {
      int heading = 1;
      for(int i=(offset + 1);;i++) {
        if(getToken(i).kind != EQ) { return true; }
        if(++heading > 6) { return false;}
      }
    }
    return false;
  }

        private boolean listItemAhead(boolean ordered) {
      if(getToken(1).kind == EOL) {
        for(int i=2, eol=1;;i++) {
        Token t = getToken(i);

        if(t.kind == EOL && ++eol > 2) {
                return false;
        } else if(t.kind != SPACE && t.kind != TAB && t.kind != EOL){
            if(ordered) {
                      return (t.kind == DIGITS && getToken(i+1).kind == DOT);
                  }
                  return (t.kind == DASH);
        }
    }
      }
  return false;
}

  private boolean textAhead() {
    int i = skip(1, SPACE, TAB);
    if(getToken(i).kind == EOL && getToken(i+1).kind != EOL && getToken(i+1).kind != EOF) {
      i = skip(i+1, SPACE, TAB);

          i = skip(i, SPACE, TAB, GT);
          return getToken(i).kind != EOL
            && getToken(i).kind != DASH
                && !(getToken(i).kind == DIGITS && getToken(i+1).kind == DOT)
                && !(getToken(i).kind == BACKTICK && getToken(i+1).kind == BACKTICK && getToken(i+2).kind == BACKTICK)
                && !headingAhead(i);

    }
    return false;
  }

  private boolean nextAfterSpace(Integer... tokens) {
    int i = skip(1, SPACE, TAB);
    return Arrays.asList(tokens).contains(getToken(i).kind);
  }

  private int skip(int offset, Integer... tokens) {
    for(int i=offset;;i++) {
      Token t = getToken(i);
      if(t.kind == EOF || !Arrays.asList(tokens).contains(t.kind)) { return i; }
    }
  }

  final public ASTDocument Document() throws ParseException {/*@bgen(jjtree) Document */
  ASTDocument jjtn000 = new ASTDocument(JJTDOCUMENT);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      label_1:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case EOL:{
          ;
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          break label_1;
        }
        jj_consume_token(EOL);
      }
      WhiteSpace();
      if (jj_2_1(1)) {
        BlockElement();
        label_2:
        while (true) {
          if (blockAhead()) {
            ;
          } else {
            break label_2;
          }
          label_3:
          while (true) {
            jj_consume_token(EOL);
            WhiteSpace();
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case EOL:{
              ;
              break;
              }
            default:
              jj_la1[1] = jj_gen;
              break label_3;
            }
          }
          BlockElement();
        }
        label_4:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case EOL:{
            ;
            break;
            }
          default:
            jj_la1[2] = jj_gen;
            break label_4;
          }
          jj_consume_token(EOL);
        }
        WhiteSpace();
      } else {
        ;
      }
      jj_consume_token(0);
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
{if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
    throw new Error("Missing return statement in function");
  }

  final public void BlockElement() throws ParseException {currentBlockLevel++;
    if (headingAhead(1)) {
      Heading();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case DASH:{
        UnorderedList();
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        if (jj_2_2(2)) {
          OrderedList();
        } else if (jj_2_3(1)) {
          Paragraph();
        } else {
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
currentBlockLevel--;
  }

  final public void Heading() throws ParseException {/*@bgen(jjtree) Heading */
                           ASTHeading jjtn000 = new ASTHeading(JJTHEADING);
                           boolean jjtc000 = true;
                           jjtree.openNodeScope(jjtn000);Token t; int heading=0;
    try {
      label_5:
      while (true) {
        jj_consume_token(EQ);
heading++;
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case EQ:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_5;
        }
      }
      WhiteSpace();
      label_6:
      while (true) {
        if (jj_2_4(1)) {
          ;
        } else {
          break label_6;
        }
        if (jj_2_5(1)) {
          Text();
        } else {
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case ASTERISK:
          case BACKTICK:
          case LBRACK:
          case UNDERSCORE:{
            LooseChar();
            break;
            }
          default:
            jj_la1[5] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.jjtSetValue(heading);
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void UnorderedList() throws ParseException {/*@bgen(jjtree) List */
  ASTList jjtn000 = new ASTList(JJTLIST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      UnorderedListItem();
      label_7:
      while (true) {
        if (listItemAhead(false)) {
          ;
        } else {
          break label_7;
        }
        label_8:
        while (true) {
          jj_consume_token(EOL);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case EOL:{
            ;
            break;
            }
          default:
            jj_la1[6] = jj_gen;
            break label_8;
          }
        }
        WhiteSpace();
        UnorderedListItem();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void UnorderedListItem() throws ParseException {/*@bgen(jjtree) ListItem */
  ASTListItem jjtn000 = new ASTListItem(JJTLISTITEM);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      jj_consume_token(DASH);
      WhiteSpace();
      if (jj_2_6(1)) {
        BlockElement();
        label_9:
        while (true) {
          if (blockAhead()) {
            ;
          } else {
            break label_9;
          }
          label_10:
          while (true) {
            jj_consume_token(EOL);
            WhiteSpace();
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case EOL:{
              ;
              break;
              }
            default:
              jj_la1[7] = jj_gen;
              break label_10;
            }
          }
          BlockElement();
        }
      } else {
        ;
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void OrderedList() throws ParseException {/*@bgen(jjtree) List */
  ASTList jjtn000 = new ASTList(JJTLIST);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      OrderedListItem();
      label_11:
      while (true) {
        if (listItemAhead(true)) {
          ;
        } else {
          break label_11;
        }
        label_12:
        while (true) {
          jj_consume_token(EOL);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case EOL:{
            ;
            break;
            }
          default:
            jj_la1[8] = jj_gen;
            break label_12;
          }
        }
        WhiteSpace();
        OrderedListItem();
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.setOrdered(true);
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void OrderedListItem() throws ParseException {/*@bgen(jjtree) ListItem */
                                    ASTListItem jjtn000 = new ASTListItem(JJTLISTITEM);
                                    boolean jjtc000 = true;
                                    jjtree.openNodeScope(jjtn000);Token t;
    try {
      t = jj_consume_token(DIGITS);
      jj_consume_token(DOT);
      WhiteSpace();
      if (jj_2_7(1)) {
        BlockElement();
        label_13:
        while (true) {
          if (blockAhead()) {
            ;
          } else {
            break label_13;
          }
          label_14:
          while (true) {
            jj_consume_token(EOL);
            WhiteSpace();
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case EOL:{
              ;
              break;
              }
            default:
              jj_la1[9] = jj_gen;
              break label_14;
            }
          }
          BlockElement();
        }
      } else {
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.setNumber(Integer.valueOf(Integer.valueOf(t.image)));
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Paragraph() throws ParseException {/*@bgen(jjtree) Paragraph */
  ASTParagraph jjtn000 = new ASTParagraph(JJTPARAGRAPH);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Inline();
      label_15:
      while (true) {
        if (textAhead()) {
          ;
        } else {
          break label_15;
        }
        LineBreak();
        WhiteSpace();
        Inline();
      }
    } catch (Throwable jjte000) {
if (jjtc000) {
      jjtree.clearNodeScope(jjtn000);
      jjtc000 = false;
    } else {
      jjtree.popNode();
    }
    if (jjte000 instanceof RuntimeException) {
      {if (true) throw (RuntimeException)jjte000;}
    }
    if (jjte000 instanceof ParseException) {
      {if (true) throw (ParseException)jjte000;}
    }
    {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void Inline() throws ParseException {
    label_16:
    while (true) {
      if (jj_2_8(1)) {
        Text();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case ASTERISK:
        case BACKTICK:
        case LBRACK:
        case UNDERSCORE:{
          LooseChar();
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      if (jj_2_9(1)) {
        ;
      } else {
        break label_16;
      }
    }
  }

  final public void Text() throws ParseException {/*@bgen(jjtree) Text */
                     ASTText jjtn000 = new ASTText(JJTTEXT);
                     boolean jjtc000 = true;
                     jjtree.openNodeScope(jjtn000);Token t; StringBuffer s = new StringBuffer();
    try {
      label_17:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case BACKSLASH:{
          t = jj_consume_token(BACKSLASH);
s.append(t.image);
          break;
          }
        case CHAR_SEQUENCE:{
          t = jj_consume_token(CHAR_SEQUENCE);
s.append(t.image);
          break;
          }
        case DASH:{
          t = jj_consume_token(DASH);
s.append(t.image);
          break;
          }
        case DIGITS:{
          t = jj_consume_token(DIGITS);
s.append(t.image);
          break;
          }
        case DOT:{
          t = jj_consume_token(DOT);
s.append(t.image);
          break;
          }
        case EQ:{
          t = jj_consume_token(EQ);
s.append(t.image);
          break;
          }
        case ESCAPED_CHAR:{
          t = jj_consume_token(ESCAPED_CHAR);
s.append(t.image.substring(1));
          break;
          }
        case GT:{
          t = jj_consume_token(GT);
s.append(t.image);
          break;
          }
        case LPAREN:{
          t = jj_consume_token(LPAREN);
s.append(t.image);
          break;
          }
        case LT:{
          t = jj_consume_token(LT);
s.append(t.image);
          break;
          }
        case RBRACK:{
          t = jj_consume_token(RBRACK);
s.append(t.image);
          break;
          }
        case RPAREN:{
          t = jj_consume_token(RPAREN);
s.append(t.image);
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          if (!nextAfterSpace(EOL, EOF)) {
            switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
            case SPACE:{
              t = jj_consume_token(SPACE);
s.append(t.image);
              break;
              }
            case TAB:{
              t = jj_consume_token(TAB);
s.append("    ");
              break;
              }
            default:
              jj_la1[11] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
          } else {
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
        if (jj_2_10(1)) {
          ;
        } else {
          break label_17;
        }
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.jjtSetValue(s.toString());
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void LooseChar() throws ParseException {/*@bgen(jjtree) Text */
                          ASTText jjtn000 = new ASTText(JJTTEXT);
                          boolean jjtc000 = true;
                          jjtree.openNodeScope(jjtn000);Token t;
    try {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case ASTERISK:{
        t = jj_consume_token(ASTERISK);
        break;
        }
      case BACKTICK:{
        t = jj_consume_token(BACKTICK);
        break;
        }
      case LBRACK:{
        t = jj_consume_token(LBRACK);
        break;
        }
      case UNDERSCORE:{
        t = jj_consume_token(UNDERSCORE);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
jjtree.closeNodeScope(jjtn000, true);
    jjtc000 = false;
jjtn000.jjtSetValue(t.image);
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void LineBreak() throws ParseException {/*@bgen(jjtree) LineBreak */
  ASTLineBreak jjtn000 = new ASTLineBreak(JJTLINEBREAK);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      label_18:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case SPACE:
        case TAB:{
          ;
          break;
          }
        default:
          jj_la1[14] = jj_gen;
          break label_18;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case SPACE:{
          jj_consume_token(SPACE);
          break;
          }
        case TAB:{
          jj_consume_token(TAB);
          break;
          }
        default:
          jj_la1[15] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(EOL);
    } finally {
if (jjtc000) {
      jjtree.closeNodeScope(jjtn000, true);
    }
    }
  }

  final public void WhiteSpace() throws ParseException {
    label_19:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SPACE:
      case TAB:{
        ;
        break;
        }
      default:
        jj_la1[16] = jj_gen;
        break label_19;
      }
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SPACE:{
        jj_consume_token(SPACE);
        break;
        }
      case TAB:{
        jj_consume_token(TAB);
        break;
        }
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  private boolean jj_2_7(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  private boolean jj_2_8(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  private boolean jj_2_9(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  private boolean jj_2_10(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  private boolean jj_3_10()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) {
    jj_scanpos = xsp;
    if (jj_3R_31()) {
    jj_scanpos = xsp;
    if (jj_3R_32()) {
    jj_scanpos = xsp;
    if (jj_3R_33()) {
    jj_scanpos = xsp;
    if (jj_3R_34()) {
    jj_scanpos = xsp;
    if (jj_3R_35()) {
    jj_scanpos = xsp;
    if (jj_3R_36()) {
    jj_scanpos = xsp;
    if (jj_3R_37()) {
    jj_scanpos = xsp;
    jj_lookingAhead = true;
    jj_semLA = !nextAfterSpace(EOL, EOF);
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_38()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  private boolean jj_3R_24()
 {
    Token xsp;
    if (jj_3_10()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_10()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_25()
 {
    if (jj_3R_43()) return true;
    return false;
  }

  private boolean jj_3_9()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3R_25()) return true;
    }
    return false;
  }

  private boolean jj_3_8()
 {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3_6()
 {
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_42()
 {
    Token xsp;
    if (jj_3_9()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_9()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3R_22()
 {
    if (jj_3R_42()) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_41()
 {
    if (jj_scan_token(DIGITS)) return true;
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  private boolean jj_3R_21()
 {
    if (jj_3R_41()) return true;
    return false;
  }

  private boolean jj_3R_49()
 {
    if (jj_scan_token(DASH)) return true;
    return false;
  }

  private boolean jj_3R_47()
 {
    if (jj_3R_49()) return true;
    return false;
  }

  private boolean jj_3R_23()
 {
    if (jj_3R_43()) return true;
    return false;
  }

  private boolean jj_3_4()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_5()) {
    jj_scanpos = xsp;
    if (jj_3R_23()) return true;
    }
    return false;
  }

  private boolean jj_3_5()
 {
    if (jj_3R_24()) return true;
    return false;
  }

  private boolean jj_3R_48()
 {
    if (jj_scan_token(EQ)) return true;
    return false;
  }

  private boolean jj_3R_46()
 {
    Token xsp;
    if (jj_3R_48()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_48()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  private boolean jj_3_3()
 {
    if (jj_3R_22()) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_21()) return true;
    return false;
  }

  private boolean jj_3R_40()
 {
    if (jj_3R_47()) return true;
    return false;
  }

  private boolean jj_3R_39()
 {
    if (jj_3R_46()) return true;
    return false;
  }

  private boolean jj_3R_20()
 {
    Token xsp;
    xsp = jj_scanpos;
    jj_lookingAhead = true;
    jj_semLA = headingAhead(1);
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_39()) {
    jj_scanpos = xsp;
    if (jj_3R_40()) {
    jj_scanpos = xsp;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3_3()) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3R_44()
 {
    if (jj_scan_token(SPACE)) return true;
    return false;
  }

  private boolean jj_3R_45()
 {
    if (jj_scan_token(TAB)) return true;
    return false;
  }

  private boolean jj_3R_43()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(3)) {
    jj_scanpos = xsp;
    if (jj_scan_token(4)) {
    jj_scanpos = xsp;
    if (jj_scan_token(13)) {
    jj_scanpos = xsp;
    if (jj_scan_token(20)) return true;
    }
    }
    }
    return false;
  }

  private boolean jj_3_7()
 {
    if (jj_3R_20()) return true;
    return false;
  }

  private boolean jj_3R_38()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_44()) {
    jj_scanpos = xsp;
    if (jj_3R_45()) return true;
    }
    return false;
  }

  private boolean jj_3R_37()
 {
    if (jj_scan_token(RPAREN)) return true;
    return false;
  }

  private boolean jj_3R_36()
 {
    if (jj_scan_token(RBRACK)) return true;
    return false;
  }

  private boolean jj_3R_35()
 {
    if (jj_scan_token(LT)) return true;
    return false;
  }

  private boolean jj_3R_26()
 {
    if (jj_scan_token(BACKSLASH)) return true;
    return false;
  }

  private boolean jj_3R_34()
 {
    if (jj_scan_token(LPAREN)) return true;
    return false;
  }

  private boolean jj_3R_33()
 {
    if (jj_scan_token(GT)) return true;
    return false;
  }

  private boolean jj_3R_32()
 {
    if (jj_scan_token(ESCAPED_CHAR)) return true;
    return false;
  }

  private boolean jj_3R_31()
 {
    if (jj_scan_token(EQ)) return true;
    return false;
  }

  private boolean jj_3R_30()
 {
    if (jj_scan_token(DOT)) return true;
    return false;
  }

  private boolean jj_3R_29()
 {
    if (jj_scan_token(DIGITS)) return true;
    return false;
  }

  private boolean jj_3R_28()
 {
    if (jj_scan_token(DASH)) return true;
    return false;
  }

  private boolean jj_3R_27()
 {
    if (jj_scan_token(CHAR_SEQUENCE)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public KoaraTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  /** Whether we are looking ahead. */
  private boolean jj_lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x400,0x400,0x400,0x80,0x800,0x102018,0x400,0x400,0x400,0x400,0x102018,0xc0000,0x3dbe4,0x102018,0xc0000,0xc0000,0xc0000,0xc0000,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[10];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Koara(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Koara(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new KoaraTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Koara(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new KoaraTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Koara(KoaraTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(KoaraTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 18; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = jj_lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk_f() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[21];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 18; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 21; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 10; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
