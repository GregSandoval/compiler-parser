package compiler.a5.grammar;

import compiler.lexer.token.FloatToken;
import compiler.lexer.token.IdentifierToken;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.KeywordToken.*;
import compiler.lexer.token.StringToken;
import compiler.parser.GrammarRule;

import static compiler.lexer.token.OperatorToken.*;
import static compiler.lexer.token.SymbolToken.*;

public class A5Grammar {
  public static final GrammarRule Pgm = new GrammarRule("Pgm");
  public static final GrammarRule Main = new GrammarRule("Main");
  public static final GrammarRule BBlock = new GrammarRule("BBlock");

  public static final GrammarRule Vargroup = new GrammarRule("Vargroup");
  public static final GrammarRule PPvarlist = new GrammarRule("PPvarlist");
  public static final GrammarRule Varlist = new GrammarRule("Varlist");
  public static final GrammarRule Varitem = new GrammarRule("Varitem");
  public static final GrammarRule Vardecl = new GrammarRule("Vardecl");
  public static final GrammarRule Simplekind = new GrammarRule("Simplekind");
  public static final GrammarRule BaseKind = new GrammarRule("BaseKind");
  public static final GrammarRule Classid = new GrammarRule("Classid");
  public static final GrammarRule Varspec = new GrammarRule("Varspec");
  public static final GrammarRule Varid = new GrammarRule("Varid");
  public static final GrammarRule Arrspec = new GrammarRule("Arrspec");
  public static final GrammarRule KKint = new GrammarRule("KKint");
  public static final GrammarRule Deref_id = new GrammarRule("Deref_id");
  public static final GrammarRule Deref = new GrammarRule("Deref");

  public static final GrammarRule Varinit = new GrammarRule("Varinit");
  public static final GrammarRule BBexprs = new GrammarRule("BBexprs");
  public static final GrammarRule Exprlist = new GrammarRule("Exprlist");
  public static final GrammarRule Moreexprs = new GrammarRule("Moreexprs");

  public static final GrammarRule Classdecl = new GrammarRule("Classdecl");
  public static final GrammarRule Classdef = new GrammarRule("Classdef");
  public static final GrammarRule BBClassitems = new GrammarRule("BBClassitems");
  public static final GrammarRule Classheader = new GrammarRule("Classheader");
  public static final GrammarRule Classmom = new GrammarRule("Classmom");
  public static final GrammarRule Classitems = new GrammarRule("Classitems");
  public static final GrammarRule Classgroup = new GrammarRule("Classgroup");
  public static final GrammarRule Class_ctrl = new GrammarRule("Class_ctrl");
  public static final GrammarRule Interfaces = new GrammarRule("Interfaces");

  public static final GrammarRule Mddecls = new GrammarRule("Mddecls");
  public static final GrammarRule Mdheader = new GrammarRule("Mdheader");
  public static final GrammarRule Md_id = new GrammarRule("Md_id");

  public static final GrammarRule Fcndefs = new GrammarRule("Fcndefs");
  public static final GrammarRule Fcndef = new GrammarRule("Fcndef");
  public static final GrammarRule Fcnheader = new GrammarRule("Fcnheader");
  public static final GrammarRule Fcnid = new GrammarRule("Fcnid");
  public static final GrammarRule Retkind = new GrammarRule("Retkind");
  public static final GrammarRule PParmlist = new GrammarRule("PParmlist");
  public static final GrammarRule Varspecs = new GrammarRule("Varspecs");
  public static final GrammarRule More_varspecs = new GrammarRule("More_varspecs");
  public static final GrammarRule PPonly = new GrammarRule("PPonly");

  public static final GrammarRule Stmts = new GrammarRule("Stmts");
  public static final GrammarRule Stmt = new GrammarRule("Stmt");
  public static final GrammarRule Stasgn = new GrammarRule("Stasgn");
  public static final GrammarRule Lval = new GrammarRule("Lval");
  public static final GrammarRule Aref = new GrammarRule("Aref");
  public static final GrammarRule KKexpr = new GrammarRule("KKexpr");

  public static final GrammarRule Fcall = new GrammarRule("Fcall");
  public static final GrammarRule PPexprs = new GrammarRule("PPexprs");

  public static final GrammarRule Stif = new GrammarRule("Stif");
  public static final GrammarRule Elsepart = new GrammarRule("Elsepart");

  public static final GrammarRule Stwhile = new GrammarRule("Stwhile");
  public static final GrammarRule Stprint = new GrammarRule("Stprint");

  public static final GrammarRule Strtn = new GrammarRule("Strtn");

  public static final GrammarRule PPexpr = new GrammarRule("PPexpr");
  public static final GrammarRule Expr = new GrammarRule("Expr");
  public static final GrammarRule Expr_Tail = new GrammarRule("Expr_Tail");
  public static final GrammarRule Rterm = new GrammarRule("Rterm");
  public static final GrammarRule Rterm_Tail = new GrammarRule("Rterm_Tail");
  public static final GrammarRule Term = new GrammarRule("Term");
  public static final GrammarRule Term_Tail = new GrammarRule("Term_Tail");
  public static final GrammarRule Fact = new GrammarRule("Fact");
  public static final GrammarRule BaseLiteral = new GrammarRule("BaseLiteral");
  public static final GrammarRule Addrof_id = new GrammarRule("Addrof_id");
  public static final GrammarRule Oprel = new GrammarRule("Oprel");
  public static final GrammarRule Lthan = new GrammarRule("Lthan");
  public static final GrammarRule Gthan = new GrammarRule("Gthan");
  public static final GrammarRule Opadd = new GrammarRule("Opadd");
  public static final GrammarRule Opmul = new GrammarRule("Opmul");

  public static final GrammarRule Epsilon = new GrammarRule("Epsilon");

  static {
    Pgm
      .on(ProgramKeywordToken.class)
      .useRHS(new ProgramKeywordToken(), Vargroup, Fcndefs, Main);
    Main
      .on(MainKeywordToken.class)
      .useRHS(new MainKeywordToken(), BBlock);
    BBlock
      .on(LeftBrace.class)
      .useRHS(new LeftBrace(), Vargroup, Stmts, new RightBrace());


    Vargroup
      .on(VarKeywordToken.class)
      .useRHS(new VarKeywordToken(), PPvarlist)
      .on()
      .useRHS(Epsilon);
    PPvarlist
      .on(LeftParen.class)
      .useRHS(new LeftParen(), Varlist, new RightParen());
    Varlist
      .on()
      .useRHS(Varitem, new SemiColon(), Varlist)
      .on()
      .useRHS(Epsilon);
    Varitem
      .on()
      .useRHS(Vardecl)
      .on()
      .useRHS(Vardecl, new Equal(), Varinit)
      .on()
      .useRHS(Classdecl)
      .on()
      .useRHS(Classdef);
    Vardecl
      .on()
      .useRHS(Simplekind, Varspec);
    Simplekind
      .on()
      .useRHS(BaseKind)
      .on()
      .useRHS(Classid);
    BaseKind
      .on()
      .useRHS(new IntegerKeywordToken())
      .on()
      .useRHS(new FloatKeywordToken())
      .on()
      .useRHS(new StringKeywordToken());
    Classid
      .on()
      .useRHS(new IdentifierToken(""));
    Varspec
      .on()
      .useRHS(Varid)
      .on()
      .useRHS(Arrspec)
      .on()
      .useRHS(Deref_id);
    Varid
      .on(IdentifierToken.class)
      .useRHS(new IdentifierToken(""));
    Arrspec
      .on()
      .useRHS(Varid, KKint);
    KKint
      .on()
      .useRHS(new LeftBracket(), new IntegerToken("0"), new RightBracket());
    Deref_id
      .on(Asterisk.class)
      .useRHS(Deref, new IdentifierToken(""));
    Deref
      .on(Asterisk.class)
      .useRHS(new Asterisk());


    Varinit
      .on()
      .useRHS(Expr)
      .on()
      .useRHS(BBexprs);
    BBexprs
      .on()
      .useRHS(new LeftBrace(), Exprlist, new RightBrace())
      .on()
      .useRHS(new LeftBrace(), new RightBrace());
    Exprlist
      .on()
      .useRHS(Expr, Moreexprs);
    Moreexprs
      .on()
      .useRHS(new Comma(), Exprlist)
      .on()
      .useRHS(Epsilon);


    Classdecl
      .on()
      .useRHS(new ClassKeywordToken(), Classid);
    Classdef
      .on()
      .useRHS(Classheader, BBClassitems)
      .on()
      .useRHS(Classheader, new IfKeywordToken(), BBClassitems);
    BBClassitems
      .on()
      .useRHS(new LeftBrace(), Classitems, new RightBrace());
    Classheader
      .on()
      .useRHS(Classdecl, Classmom, Interfaces);
    Classmom
      .on()
      .useRHS(new Colon(), Classid)
      .on()
      .useRHS(Epsilon);
    Classitems
      .on()
      .useRHS(Classgroup, Classitems)
      .on()
      .useRHS(Epsilon);
    Classgroup
      .on()
      .useRHS(Class_ctrl)
      .on()
      .useRHS(Vargroup)
      .on()
      .useRHS(Mddecls);
    Class_ctrl
      .on()
      .useRHS(new Colon(), new IdentifierToken(""));
    Interfaces
      .on()
      .useRHS(new Plus(), Classid, Interfaces)
      .on()
      .useRHS(Epsilon);


    Mddecls
      .on()
      .useRHS(Mdheader, Mddecls)
      .on()
      .useRHS(Epsilon);
    Mdheader
      .on()
      .useRHS(new FunctionKeywordToken(), Md_id, PParmlist, Retkind);
    Md_id
      .on()
      .useRHS(Classid, new Colon(), Fcnid);

    Fcndefs
      .on()
      .useRHS(Fcndef, Fcndefs)
      .on()
      .useRHS(Epsilon);
    Fcndef
      .on()
      .useRHS(Fcnheader, BBlock);
    Fcnheader
      .on()
      .useRHS(new FunctionKeywordToken(), Fcnid, PParmlist, Retkind);
    Fcnid
      .on(IdentifierToken.class)
      .useRHS(new IdentifierToken(""));
    Retkind
      .on()
      .useRHS(BaseKind);
    PParmlist
      .on()
      .useRHS(new LeftParen(), Varspecs, new RightParen())
      .on()
      .useRHS(PPonly);
    Varspecs
      .on()
      .useRHS(Varspec, More_varspecs);
    More_varspecs
      .on()
      .useRHS(new Comma(), Varspecs)
      .on()
      .useRHS(Epsilon);
    PPonly
      .on()
      .useRHS(new LeftParen(), new RightParen());


    Stmts
      .on()
      .useRHS(Stmt, new SemiColon(), Stmts)
      .on()
      .useRHS(Epsilon);
    Stmt
      .on()
      .useRHS(Stasgn)
      .on()
      .useRHS(Fcall)
      .on()
      .useRHS(Stif)
      .on()
      .useRHS(Stwhile)
      .on()
      .useRHS(Stprint)
      .on()
      .useRHS(Strtn);


    Stasgn
      .on()
      .useRHS(Lval, new Equal(), Expr);
    Lval
      .on(IdentifierToken.class)
      .useRHS(Varid)
      .on(IdentifierToken.class)
      .useRHS(Aref)
      .on(Asterisk.class)
      .useRHS(Deref_id);
    Aref
      .on(IdentifierToken.class)
      .useRHS(Varid, KKexpr);
    KKexpr
      .on()
      .useRHS(new LeftBracket(), Expr, new RightBracket());


    Fcall
      .on(IdentifierToken.class)
      .useRHS(Fcnid, PPexprs);
    PPexprs
      .on()
      .useRHS(new LeftParen(), Exprlist, new RightParen())
      .on()
      .useRHS(PPonly);


    Stif
      .on()
      .useRHS(new IfKeywordToken(), PPexpr, BBlock, Elsepart);
    Elsepart
      .on()
      .useRHS(new ElseIfKeywordToken(), PPexpr, BBlock, Elsepart)
      .on()
      .useRHS(new ElseKeywordToken(), BBlock)
      .on()
      .useRHS(Epsilon);


    Stwhile
      .on()
      .useRHS(new WhileKeywordToken(), PPexpr, BBlock);
    Stprint
      .on()
      .useRHS(new PrintKeywordToken(), PPexprs);


    Strtn
      .on()
      .useRHS(new ReturnKeywordToken(), Expr)
      .on()
      .useRHS(new ReturnKeywordToken());


    PPexpr
      .on(LeftParen.class)
      .useRHS(new LeftParen(), Expr, new RightParen());
    Expr
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Rterm, Expr_Tail);
    Expr_Tail
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class)
      .useRHS(Oprel, Rterm, Expr_Tail)
      .on(RightParen.class, Asterisk.class)
      .useRHS();
    Rterm
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Term, Rterm_Tail);
    Rterm_Tail
      .on(Plus.class, Minus.class)
      .useRHS(Opadd, Term, Rterm_Tail)
      .on(RightParen.class, Asterisk.class)
      .useRHS();
    Term
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Fact, Term_Tail);
    Term_Tail
      .on(Asterisk.class, ForwardSlash.class, Caret.class)
      .useRHS(Opmul, Fact, Term_Tail)
      .on(RightParen.class, Plus.class, IntegerToken.class)
      .useRHS();
    Fact
      .on(IntegerToken.class, FloatToken.class, StringToken.class)
      .useRHS(BaseLiteral)
      .on(IdentifierToken.class, Asterisk.class)
      .useRHS(Lval)
      .on(Ampersand.class)
      .useRHS(Addrof_id)
      .on(IdentifierToken.class)
      .useRHS(Fcall)
      .on(LeftParen.class)
      .useRHS(PPexpr);
    BaseLiteral
      .on(IntegerToken.class)
      .useRHS(new IntegerToken("0"))
      .on(FloatToken.class)
      .useRHS(new FloatToken("0"))
      .on(StringToken.class)
      .useRHS(new StringToken(""));
    Addrof_id
      .on(Ampersand.class)
      .useRHS(new Ampersand(), new IdentifierToken(""));
    Oprel
      .on(EqualEqual.class)
      .useRHS(new EqualEqual())
      .on(NotEqual.class)
      .useRHS(new NotEqual())
      .on(LessThan.class)
      .useRHS(Lthan)
      .on(LessThanOrEqual.class)
      .useRHS(new LessThanOrEqual())
      .on(GreaterThanOrEqual.class)
      .useRHS(new GreaterThanOrEqual())
      .on(GreaterThan.class)
      .useRHS(Gthan);
    Lthan
      .on(LessThan.class)
      .useRHS(new LessThan());
    Gthan
      .on(GreaterThan.class)
      .useRHS(new GreaterThan());
    Opadd
      .on(Plus.class)
      .useRHS(new Plus())
      .on(Minus.class)
      .useRHS(new Minus());
    Opmul
      .on(Asterisk.class)
      .useRHS(new Asterisk())
      .on(ForwardSlash.class)
      .useRHS(new ForwardSlash())
      .on(Caret.class)
      .useRHS(new Caret());
  }
}
