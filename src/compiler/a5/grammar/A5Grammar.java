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
  private static final GrammarRule Pgm = new GrammarRule();
  private static final GrammarRule Main = new GrammarRule();
  private static final GrammarRule BBlock = new GrammarRule();

  private static final GrammarRule Vargroup = new GrammarRule();
  private static final GrammarRule PPvarlist = new GrammarRule();
  private static final GrammarRule Varlist = new GrammarRule();
  private static final GrammarRule Varitem = new GrammarRule();
  private static final GrammarRule Vardecl = new GrammarRule();
  private static final GrammarRule Simplekind = new GrammarRule();
  private static final GrammarRule BaseKind = new GrammarRule();
  private static final GrammarRule Classid = new GrammarRule();
  private static final GrammarRule Varspec = new GrammarRule();
  private static final GrammarRule Varid = new GrammarRule();
  private static final GrammarRule Arrspec = new GrammarRule();
  private static final GrammarRule KKint = new GrammarRule();
  private static final GrammarRule Deref_id = new GrammarRule();
  private static final GrammarRule Deref = new GrammarRule();

  private static final GrammarRule Varinit = new GrammarRule();
  private static final GrammarRule BBexprs = new GrammarRule();
  private static final GrammarRule Exprlist = new GrammarRule();
  private static final GrammarRule Moreexprs = new GrammarRule();

  private static final GrammarRule Classdecl = new GrammarRule();
  private static final GrammarRule Classdef = new GrammarRule();
  private static final GrammarRule BBClassitems = new GrammarRule();
  private static final GrammarRule Classheader = new GrammarRule();
  private static final GrammarRule Classmom = new GrammarRule();
  private static final GrammarRule Classitems = new GrammarRule();
  private static final GrammarRule Classgroup = new GrammarRule();
  private static final GrammarRule Class_ctrl = new GrammarRule();
  private static final GrammarRule Interfaces = new GrammarRule();

  private static final GrammarRule Mddecls = new GrammarRule();
  private static final GrammarRule Mdheader = new GrammarRule();
  private static final GrammarRule Md_id = new GrammarRule();

  private static final GrammarRule Fcndefs = new GrammarRule();
  private static final GrammarRule Fcndef = new GrammarRule();
  private static final GrammarRule Fcnheader = new GrammarRule();
  private static final GrammarRule Fcnid = new GrammarRule();
  private static final GrammarRule Retkind = new GrammarRule();
  private static final GrammarRule PParmlist = new GrammarRule();
  private static final GrammarRule Varspecs = new GrammarRule();
  private static final GrammarRule More_varspecs = new GrammarRule();
  private static final GrammarRule PPonly = new GrammarRule();

  private static final GrammarRule Stmts = new GrammarRule();
  private static final GrammarRule Stmt = new GrammarRule();
  private static final GrammarRule Stasgn = new GrammarRule();
  private static final GrammarRule Lval = new GrammarRule();
  private static final GrammarRule Aref = new GrammarRule();
  private static final GrammarRule KKexpr = new GrammarRule();

  private static final GrammarRule Fcall = new GrammarRule();
  private static final GrammarRule PPexprs = new GrammarRule();

  private static final GrammarRule Stif = new GrammarRule();
  private static final GrammarRule Elsepart = new GrammarRule();

  private static final GrammarRule Stwhile = new GrammarRule();
  private static final GrammarRule Stprint = new GrammarRule();

  private static final GrammarRule Strtn = new GrammarRule();

  private static final GrammarRule PPexpr = new GrammarRule();
  private static final GrammarRule Expr = new GrammarRule();
  private static final GrammarRule Rterm = new GrammarRule();
  private static final GrammarRule Term = new GrammarRule();
  private static final GrammarRule Fact = new GrammarRule();
  private static final GrammarRule BaseLiteral = new GrammarRule();
  private static final GrammarRule Addrof_id = new GrammarRule();
  private static final GrammarRule Oprel = new GrammarRule();
  private static final GrammarRule Lthan = new GrammarRule();
  private static final GrammarRule Gthan = new GrammarRule();
  private static final GrammarRule Opadd = new GrammarRule();
  private static final GrammarRule Opmul = new GrammarRule();

  private static final GrammarRule Epsilon = new GrammarRule();

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
      .useRHS(new LeftBracket(), new IntegerToken(""), new RightBracket());
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
      .on()
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
      .useRHS(Expr, Oprel, Rterm)
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Rterm);
    Rterm
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Rterm, Opadd, Term)
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Term);
    Term
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Term, Opmul, Fact)
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Fact);
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
      .useRHS(new IntegerToken(""))
      .on(FloatToken.class)
      .useRHS(new FloatToken(""))
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
