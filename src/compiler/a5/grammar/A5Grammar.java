package compiler.a5.grammar;

import compiler.lexer.token.FloatToken;
import compiler.lexer.token.IdentifierToken;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.KeywordToken.*;
import compiler.lexer.token.StringToken;
import compiler.parser.GrammarNode;

import static compiler.lexer.token.OperatorToken.*;
import static compiler.lexer.token.SymbolToken.*;

public class A5Grammar {
  public static class Pgm extends GrammarNode {
    public Pgm() {
      super("Pgm");
    }
  }

  public static class Main extends GrammarNode {
    public Main() {
      super("Main");
    }
  }

  public static class BBlock extends GrammarNode {
    public BBlock() {
      super("BBlock");
    }
  }

  public static class Vargroup extends GrammarNode {
    public Vargroup() {
      super("Vargroup");
    }
  }

  public static class PPvarlist extends GrammarNode {
    public PPvarlist() {
      super("PPvarlist");
    }
  }

  public static class Varlist extends GrammarNode {
    public Varlist() {
      super("Varlist");
    }
  }

  public static class Varitem extends GrammarNode {
    public Varitem() {
      super("Varitem");
    }
  }

  public static class Vardecl extends GrammarNode {
    public Vardecl() {
      super("Vardecl");
    }
  }

  public static class Simplekind extends GrammarNode {
    public Simplekind() {
      super("Simplekind");
    }
  }

  public static class BaseKind extends GrammarNode {
    public BaseKind() {
      super("BaseKind");
    }
  }

  public static class Classid extends GrammarNode {
    public Classid() {
      super("Classid");
    }
  }

  public static class Varspec extends GrammarNode {
    public Varspec() {
      super("Varspec");
    }
  }

  public static class Varid extends GrammarNode {
    public Varid() {
      super("Varid");
    }
  }

  public static class Arrspec extends GrammarNode {
    public Arrspec() {
      super("Arrspec");
    }
  }

  public static class KKint extends GrammarNode {
    public KKint() {
      super("KKint");
    }
  }

  public static class Deref_id extends GrammarNode {
    public Deref_id() {
      super("Deref_id");
    }
  }

  public static class Deref extends GrammarNode {
    public Deref() {
      super("Deref");
    }
  }

  public static class Varinit extends GrammarNode {
    public Varinit() {
      super("Varinit");
    }
  }

  public static class BBexprs extends GrammarNode {
    public BBexprs() {
      super("BBexprs");
    }
  }

  public static class Exprlist extends GrammarNode {
    public Exprlist() {
      super("Exprlist");
    }
  }

  public static class Moreexprs extends GrammarNode {
    public Moreexprs() {
      super("Moreexprs");
    }
  }

  public static class Classdecl extends GrammarNode {
    public Classdecl() {
      super("Classdecl");
    }
  }

  public static class Classdef extends GrammarNode {
    public Classdef() {
      super("Classdef");
    }
  }

  public static class BBClassitems extends GrammarNode {
    public BBClassitems() {
      super("BBClassitems");
    }
  }

  public static class Classheader extends GrammarNode {
    public Classheader() {
      super("Classheader");
    }
  }

  public static class Classmom extends GrammarNode {
    public Classmom() {
      super("Classmom");
    }
  }

  public static class Classitems extends GrammarNode {
    public Classitems() {
      super("Classitems");
    }
  }

  public static class Classgroup extends GrammarNode {
    public Classgroup() {
      super("Classgroup");
    }
  }

  public static class Class_ctrl extends GrammarNode {
    public Class_ctrl() {
      super("Class_ctrl");
    }
  }

  public static class Interfaces extends GrammarNode {
    public Interfaces() {
      super("Interfaces");
    }
  }

  public static class Mddecls extends GrammarNode {
    public Mddecls() {
      super("Mddecls");
    }
  }

  public static class Mdheader extends GrammarNode {
    public Mdheader() {
      super("Mdheader");
    }
  }

  public static class Md_id extends GrammarNode {
    public Md_id() {
      super("Md_id");
    }
  }

  public static class Fcndefs extends GrammarNode {
    public Fcndefs() {
      super("Fcndefs");
    }
  }

  public static class Fcndef extends GrammarNode {
    public Fcndef() {
      super("Fcndef");
    }
  }

  public static class Fcnheader extends GrammarNode {
    public Fcnheader() {
      super("Fcnheader");
    }
  }

  public static class Fcnid extends GrammarNode {
    public Fcnid() {
      super("Fcnid");
    }
  }

  public static class Retkind extends GrammarNode {
    public Retkind() {
      super("Retkind");
    }
  }

  public static class PParmlist extends GrammarNode {
    public PParmlist() {
      super("PParmlist");
    }
  }

  public static class Varspecs extends GrammarNode {
    public Varspecs() {
      super("Varspecs");
    }
  }

  public static class More_varspecs extends GrammarNode {
    public More_varspecs() {
      super("More_varspecs");
    }
  }

  public static class PPonly extends GrammarNode {
    public PPonly() {
      super("PPonly");
    }
  }

  public static class Stmts extends GrammarNode {
    public Stmts() {
      super("Stmts");
    }
  }

  public static class Stmt extends GrammarNode {
    public Stmt() {
      super("Stmt");
    }
  }

  public static class Stasgn extends GrammarNode {
    public Stasgn() {
      super("Stasgn");
    }
  }

  public static class Lval extends GrammarNode {
    public Lval() {
      super("Lval");
    }
  }

  public static class Aref extends GrammarNode {
    public Aref() {
      super("Aref");
    }
  }

  public static class KKexpr extends GrammarNode {
    public KKexpr() {
      super("KKexpr");
    }
  }

  public static class Fcall extends GrammarNode {
    public Fcall() {
      super("Fcall");
    }
  }

  public static class PPexprs extends GrammarNode {
    public PPexprs() {
      super("PPexprs");
    }
  }

  public static class Stif extends GrammarNode {
    public Stif() {
      super("Stif");
    }
  }

  public static class Elsepart extends GrammarNode {
    public Elsepart() {
      super("Elsepart");
    }
  }

  public static class Stwhile extends GrammarNode {
    public Stwhile() {
      super("Stwhile");
    }
  }

  public static class Stprint extends GrammarNode {
    public Stprint() {
      super("Stprint");
    }
  }

  public static class Strtn extends GrammarNode {
    public Strtn() {
      super("Strtn");
    }
  }

  public static class PPexpr extends GrammarNode {
    public PPexpr() {
      super("PPexpr");
    }
  }

  public static class Expr extends GrammarNode {
    public Expr() {
      super("Expr");
    }
  }

  public static class Expr_Tail extends GrammarNode {
    public Expr_Tail() {
      super("Expr_Tail");
    }
  }

  public static class Rterm extends GrammarNode {
    public Rterm() {
      super("Rterm");
    }
  }

  public static class Rterm_Tail extends GrammarNode {
    public Rterm_Tail() {
      super("Rterm_Tail");
    }
  }

  public static class Term extends GrammarNode {
    public Term() {
      super("Term");
    }
  }

  public static class Term_Tail extends GrammarNode {
    public Term_Tail() {
      super("Term_Tail");
    }
  }

  public static class Fact extends GrammarNode {
    public Fact() {
      super("Fact");
    }
  }

  public static class BaseLiteral extends GrammarNode {
    public BaseLiteral() {
      super("BaseLiteral");
    }
  }

  public static class Addrof_id extends GrammarNode {
    public Addrof_id() {
      super("Addrof_id");
    }
  }

  public static class Oprel extends GrammarNode {
    public Oprel() {
      super("Oprel");
    }
  }

  public static class Lthan extends GrammarNode {
    public Lthan() {
      super("Lthan");
    }
  }

  public static class Gthan extends GrammarNode {
    public Gthan() {
      super("Gthan");
    }
  }

  public static class Opadd extends GrammarNode {
    public Opadd() {
      super("Opadd");
    }
  }

  public static class Opmul extends GrammarNode {
    public Opmul() {
      super("Opmul");
    }
  }

  public static class Epsilon extends GrammarNode {
    public Epsilon() {
      super("Epsilon");
    }
  }

  static {
    Pgm
      .on(ProgramKeywordToken.class)
      .useRHS(ProgramKeywordToken.class, Vargroup.class, Fcndefs.class, Main.class);
    Main
      .on(MainKeywordToken.class)
      .useRHS(MainKeywordToken.class, BBlock.class);
    BBlock
      .on(LeftBrace.class)
      .useRHS(LeftBrace.class, Vargroup.class, Stmts.class, RightBrace.class);
    Vargroup
      .on(VarKeywordToken.class)
      .useRHS(VarKeywordToken.class, PPvarlist.class)
      .on()
      .useRHS(Epsilon.class);
    PPvarlist
      .on(LeftParen.class)
      .useRHS(LeftParen.class, Varlist.class, RightParen.class);
    Varlist
      .on()
      .useRHS(Varitem.class, SemiColon.class, Varlist.class)
      .on()
      .useRHS(Epsilon.class);
    Varitem
      .on()
      .useRHS(Vardecl.class)
      .on()
      .useRHS(Vardecl.class, Equal.class, Varinit.class)
      .on()
      .useRHS(Classdecl.class)
      .on()
      .useRHS(Classdef.class);
    Vardecl
      .on()
      .useRHS(Simplekind.class, Varspec.class);
    Simplekind
      .on()
      .useRHS(BaseKind.class)
      .on()
      .useRHS(Classid.class);
    BaseKind
      .on()
      .useRHS(IntegerKeywordToken.class)
      .on()
      .useRHS(FloatKeywordToken.class)
      .on()
      .useRHS(StringKeywordToken.class);
    Classid
      .on()
      .useRHS(IdentifierToken.class);
    Varspec
      .on()
      .useRHS(Varid.class)
      .on()
      .useRHS(Arrspec.class)
      .on()
      .useRHS(Deref_id.class);
    Varid
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken.class);
    Arrspec
      .on()
      .useRHS(Varid.class, KKint.class);
    KKint
      .on()
      .useRHS(LeftBracket.class, IntegerToken.class, RightBracket.class);
    Deref_id
      .on(Asterisk.class)
      .useRHS(Deref.class, IdentifierToken.class);
    Deref
      .on(Asterisk.class)
      .useRHS(Asterisk.class);


    Varinit
      .on()
      .useRHS(Expr.class)
      .on()
      .useRHS(BBexprs.class);
    BBexprs
      .on()
      .useRHS(LeftBrace.class, Exprlist.class, RightBrace.class)
      .on()
      .useRHS(LeftBrace.class, RightBrace.class);
    Exprlist
      .on()
      .useRHS(Expr.class, Moreexprs.class);
    Moreexprs
      .on()
      .useRHS(Comma.class, Exprlist.class)
      .on()
      .useRHS(Epsilon.class);


    Classdecl
      .on()
      .useRHS(ClassKeywordToken.class, Classid.class);
    Classdef
      .on()
      .useRHS(Classheader.class, BBClassitems.class)
      .on()
      .useRHS(Classheader.class, IfKeywordToken.class, BBClassitems.class);
    BBClassitems
      .on()
      .useRHS(LeftBrace.class, Classitems.class, RightBrace.class);
    Classheader
      .on()
      .useRHS(Classdecl.class, Classmom.class, Interfaces.class);
    Classmom
      .on()
      .useRHS(Colon.class, Classid.class)
      .on()
      .useRHS(Epsilon.class);
    Classitems
      .on()
      .useRHS(Classgroup.class, Classitems.class)
      .on()
      .useRHS(Epsilon.class);
    Classgroup
      .on()
      .useRHS(Class_ctrl.class)
      .on()
      .useRHS(Vargroup.class)
      .on()
      .useRHS(Mddecls.class);
    Class_ctrl
      .on()
      .useRHS(Colon.class, IdentifierToken.class);
    Interfaces
      .on()
      .useRHS(Plus.class, Classid.class, Interfaces.class)
      .on()
      .useRHS(Epsilon.class);


    Mddecls
      .on()
      .useRHS(Mdheader.class, Mddecls.class)
      .on()
      .useRHS(Epsilon.class);
    Mdheader
      .on()
      .useRHS(FunctionKeywordToken.class, Md_id.class, PParmlist.class, Retkind.class);
    Md_id
      .on()
      .useRHS(Classid.class, Colon.class, Fcnid.class);

    Fcndefs
      .on()
      .useRHS(Fcndef.class, Fcndefs.class)
      .on()
      .useRHS(Epsilon.class);
    Fcndef
      .on()
      .useRHS(Fcnheader.class, BBlock.class);
    Fcnheader
      .on()
      .useRHS(FunctionKeywordToken.class, Fcnid.class, PParmlist.class, Retkind.class);
    Fcnid
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken.class);
    Retkind
      .on()
      .useRHS(BaseKind.class);
    PParmlist
      .on()
      .useRHS(LeftParen.class, Varspecs.class, RightParen.class)
      .on()
      .useRHS(PPonly.class);
    Varspecs
      .on()
      .useRHS(Varspec.class, More_varspecs.class);
    More_varspecs
      .on()
      .useRHS(Comma.class, Varspecs.class)
      .on()
      .useRHS(Epsilon.class);
    PPonly
      .on()
      .useRHS(LeftParen.class, RightParen.class);


    Stmts
      .on()
      .useRHS(Stmt.class, SemiColon.class, Stmts.class)
      .on()
      .useRHS(Epsilon.class);
    Stmt
      .on()
      .useRHS(Stasgn.class)
      .on()
      .useRHS(Fcall.class)
      .on()
      .useRHS(Stif.class)
      .on()
      .useRHS(Stwhile.class)
      .on()
      .useRHS(Stprint.class)
      .on()
      .useRHS(Strtn.class);


    Stasgn
      .on()
      .useRHS(Lval.class, Equal.class, Expr.class);
    Lval
      .on(IdentifierToken.class)
      .useRHS(Varid.class)
      .on(IdentifierToken.class)
      .useRHS(Aref.class)
      .on(Asterisk.class)
      .useRHS(Deref_id.class);
    Aref
      .on(IdentifierToken.class)
      .useRHS(Varid.class, KKexpr.class);
    KKexpr
      .on()
      .useRHS(LeftBracket.class, Expr.class, RightBracket.class);


    Fcall
      .on(IdentifierToken.class)
      .useRHS(Fcnid.class, PPexprs.class);
    PPexprs
      .on()
      .useRHS(LeftParen.class, Exprlist.class, RightParen.class)
      .on()
      .useRHS(PPonly.class);


    Stif
      .on()
      .useRHS(IfKeywordToken.class, PPexpr.class, BBlock.class, Elsepart.class);
    Elsepart
      .on()
      .useRHS(ElseIfKeywordToken.class, PPexpr.class, BBlock.class, Elsepart.class)
      .on()
      .useRHS(ElseKeywordToken.class, BBlock.class)
      .on()
      .useRHS(Epsilon.class);


    Stwhile
      .on()
      .useRHS(WhileKeywordToken.class, PPexpr.class, BBlock.class);
    Stprint
      .on()
      .useRHS(PrintKeywordToken.class, PPexprs.class);


    Strtn
      .on()
      .useRHS(ReturnKeywordToken.class, Expr.class)
      .on()
      .useRHS(ReturnKeywordToken.class);


    PPexpr
      .on(LeftParen.class)
      .useRHS(LeftParen.class, Expr.class, RightParen.class);
    Expr
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Rterm.class, Expr_Tail.class);
    Expr_Tail
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class)
      .useRHS(Oprel.class, Rterm.class, Expr_Tail.class)
      .on(RightParen.class)
      .useRHS();
    Rterm
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Term.class, Rterm_Tail.class);
    Rterm_Tail
      .on(Plus.class, Minus.class)
      .useRHS(Opadd.class, Term.class, Rterm_Tail.class)
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class, RightParen.class)
      .useRHS();
    Term
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Fact.class, Term_Tail.class);
    Term_Tail
      .on(Asterisk.class, ForwardSlash.class, Caret.class)
      .useRHS(Opmul.class, Fact.class, Term_Tail.class)
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class, RightParen.class, Plus.class, Minus.class)
      .useRHS();
    Fact
      .on(IntegerToken.class, FloatToken.class, StringToken.class)
      .useRHS(BaseLiteral.class)
      .on(IdentifierToken.class, Asterisk.class)
      .useRHS(Lval.class)
      .on(Ampersand.class)
      .useRHS(Addrof_id.class)
      .on(IdentifierToken.class)
      .useRHS(Fcall.class)
      .on(LeftParen.class)
      .useRHS(PPexpr.class);
    BaseLiteral
      .on(IntegerToken.class)
      .useRHS(IntegerToken.class)
      .on(FloatToken.class)
      .useRHS(FloatToken.class)
      .on(StringToken.class)
      .useRHS(StringToken.class);
    Addrof_id
      .on(Ampersand.class)
      .useRHS(Ampersand.class, IdentifierToken.class);
    Oprel
      .on(EqualEqual.class)
      .useRHS(EqualEqual.class)
      .on(NotEqual.class)
      .useRHS(NotEqual.class)
      .on(LessThan.class)
      .useRHS(Lthan.class)
      .on(LessThanOrEqual.class)
      .useRHS(LessThanOrEqual.class)
      .on(GreaterThanOrEqual.class)
      .useRHS(GreaterThanOrEqual.class)
      .on(GreaterThan.class)
      .useRHS(Gthan.class);
    Lthan
      .on(LessThan.class)
      .useRHS(LessThan.class);
    Gthan
      .on(GreaterThan.class)
      .useRHS(GreaterThan.class);
    Opadd
      .on(Plus.class)
      .useRHS(Plus.class)
      .on(Minus.class)
      .useRHS(Minus.class);
    Opmul
      .on(Asterisk.class)
      .useRHS(Asterisk.class)
      .on(ForwardSlash.class)
      .useRHS(ForwardSlash.class)
      .on(Caret.class)
      .useRHS(Caret.class);
  }
}
