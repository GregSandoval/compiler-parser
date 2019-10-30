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
  }

  public static class Main extends GrammarNode {
  }

  public static class BBlock extends GrammarNode {
  }

  public static class Vargroup extends GrammarNode {
  }

  public static class PPvarlist extends GrammarNode {
  }

  public static class Varlist extends GrammarNode {
  }

  public static class Varitem extends GrammarNode {
  }

  public static class Vardecl extends GrammarNode {
  }

  public static class Simplekind extends GrammarNode {
  }

  public static class BaseKind extends GrammarNode {
  }

  public static class Classid extends GrammarNode {
  }

  public static class Varspec extends GrammarNode {
  }

  public static class Varid extends GrammarNode {
  }

  public static class Arrspec extends GrammarNode {
  }

  public static class KKint extends GrammarNode {
  }

  public static class Deref_id extends GrammarNode {
  }

  public static class Deref extends GrammarNode {
  }

  public static class Varinit extends GrammarNode {
  }

  public static class BBexprs extends GrammarNode {
  }

  public static class Exprlist extends GrammarNode {
  }

  public static class Moreexprs extends GrammarNode {
  }

  public static class Classdecl extends GrammarNode {
  }

  public static class Classdef extends GrammarNode {
  }

  public static class BBClassitems extends GrammarNode {
  }

  public static class Classheader extends GrammarNode {
  }

  public static class Classmom extends GrammarNode {
  }

  public static class Classitems extends GrammarNode {
  }

  public static class Classgroup extends GrammarNode {
  }

  public static class Class_ctrl extends GrammarNode {
  }

  public static class Interfaces extends GrammarNode {
  }

  public static class Mddecls extends GrammarNode {
  }

  public static class Mdheader extends GrammarNode {
  }

  public static class Md_id extends GrammarNode {
  }

  public static class Fcndefs extends GrammarNode {
  }

  public static class Fcndef extends GrammarNode {
  }

  public static class Fcnheader extends GrammarNode {
  }

  public static class Fcnid extends GrammarNode {
  }

  public static class Retkind extends GrammarNode {
  }

  public static class PParmlist extends GrammarNode {
  }

  public static class Varspecs extends GrammarNode {
  }

  public static class More_varspecs extends GrammarNode {
  }

  public static class PPonly extends GrammarNode {
  }

  public static class Stmts extends GrammarNode {
  }

  public static class Stmt extends GrammarNode {
  }

  public static class Stasgn extends GrammarNode {
  }

  public static class Lval extends GrammarNode {
  }

  public static class Aref extends GrammarNode {
  }

  public static class KKexpr extends GrammarNode {
  }

  public static class Fcall extends GrammarNode {
  }

  public static class PPexprs extends GrammarNode {
  }

  public static class Stif extends GrammarNode {
  }

  public static class Elsepart extends GrammarNode {
  }

  public static class Stwhile extends GrammarNode {
  }

  public static class Stprint extends GrammarNode {
  }

  public static class Strtn extends GrammarNode {
  }

  public static class PPexpr extends GrammarNode {
  }

  public static class Expr extends GrammarNode {
  }

  public static class Expr_Tail extends GrammarNode {
  }

  public static class Rterm extends GrammarNode {
  }

  public static class Rterm_Tail extends GrammarNode {
  }

  public static class Term extends GrammarNode {
  }

  public static class Term_Tail extends GrammarNode {
  }

  public static class Fact extends GrammarNode {
  }

  public static class BaseLiteral extends GrammarNode {
  }

  public static class Addrof_id extends GrammarNode {
  }

  public static class Oprel extends GrammarNode {
  }

  public static class Lthan extends GrammarNode {
  }

  public static class Gthan extends GrammarNode {
  }

  public static class Opadd extends GrammarNode {
  }

  public static class Opmul extends GrammarNode {
  }

  public static class Epsilon extends GrammarNode {
  }

  public static void build() {
    new Pgm()
      .on(ProgramKeywordToken.class)
      .useRHS(ProgramKeywordToken::new, Vargroup::new, Fcndefs::new, Main::new);
    new Main()
      .on(MainKeywordToken.class)
      .useRHS(MainKeywordToken::new, BBlock::new);
    new BBlock()
      .on(LeftBrace.class)
      .useRHS(LeftBrace::new, Vargroup::new, Stmts::new, RightBrace::new);
    new Vargroup()
      .on(VarKeywordToken.class)
      .useRHS(VarKeywordToken::new, PPvarlist::new)
      .on()
      .useRHS(Epsilon::new);
    new PPvarlist()
      .on(LeftParen.class)
      .useRHS(LeftParen::new, Varlist::new, RightParen::new);
    new Varlist()
      .on()
      .useRHS(Varitem::new, SemiColon::new, Varlist::new)
      .on()
      .useRHS(Epsilon::new);
    new Varitem()
      .on()
      .useRHS(Vardecl::new)
      .on()
      .useRHS(Vardecl::new, Equal::new, Varinit::new)
      .on()
      .useRHS(Classdecl::new)
      .on()
      .useRHS(Classdef::new);
    new Vardecl()
      .on()
      .useRHS(Simplekind::new, Varspec::new);
    new Simplekind()
      .on()
      .useRHS(BaseKind::new)
      .on()
      .useRHS(Classid::new);
    new BaseKind()
      .on()
      .useRHS(IntegerKeywordToken::new)
      .on()
      .useRHS(FloatKeywordToken::new)
      .on()
      .useRHS(StringKeywordToken::new);
    new Classid()
      .on()
      .useRHS(IdentifierToken::getSentinel);
    new Varspec()
      .on()
      .useRHS(Varid::new)
      .on()
      .useRHS(Arrspec::new)
      .on()
      .useRHS(Deref_id::new);
    new Varid()
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken::getSentinel);
    new Arrspec()
      .on()
      .useRHS(Varid::new, KKint::new);
    new KKint()
      .on()
      .useRHS(LeftBracket::new, IntegerToken::getSentinel, RightBracket::new);
    new Deref_id()
      .on(Asterisk.class)
      .useRHS(Deref::new, IdentifierToken::getSentinel);
    new Deref()
      .on(Asterisk.class)
      .useRHS(Asterisk::new);


    new Varinit()
      .on()
      .useRHS(Expr::new)
      .on()
      .useRHS(BBexprs::new);
    new BBexprs()
      .on()
      .useRHS(LeftBrace::new, Exprlist::new, RightBrace::new)
      .on()
      .useRHS(LeftBrace::new, RightBrace::new);
    new Exprlist()
      .on()
      .useRHS(Expr::new, Moreexprs::new);
    new Moreexprs()
      .on()
      .useRHS(Comma::new, Exprlist::new)
      .on()
      .useRHS(Epsilon::new);


    new Classdecl()
      .on()
      .useRHS(ClassKeywordToken::new, Classid::new);
    new Classdef()
      .on()
      .useRHS(Classheader::new, BBClassitems::new)
      .on()
      .useRHS(Classheader::new, IfKeywordToken::new, BBClassitems::new);
    new BBClassitems()
      .on()
      .useRHS(LeftBrace::new, Classitems::new, RightBrace::new);
    new Classheader()
      .on()
      .useRHS(Classdecl::new, Classmom::new, Interfaces::new);
    new Classmom()
      .on()
      .useRHS(Colon::new, Classid::new)
      .on()
      .useRHS(Epsilon::new);
    new Classitems()
      .on()
      .useRHS(Classgroup::new, Classitems::new)
      .on()
      .useRHS(Epsilon::new);
    new Classgroup()
      .on()
      .useRHS(Class_ctrl::new)
      .on()
      .useRHS(Vargroup::new)
      .on()
      .useRHS(Mddecls::new);
    new Class_ctrl()
      .on()
      .useRHS(Colon::new, IdentifierToken::getSentinel);
    new Interfaces()
      .on()
      .useRHS(Plus::new, Classid::new, Interfaces::new)
      .on()
      .useRHS(Epsilon::new);


    new Mddecls()
      .on()
      .useRHS(Mdheader::new, Mddecls::new)
      .on()
      .useRHS(Epsilon::new);
    new Mdheader()
      .on()
      .useRHS(FunctionKeywordToken::new, Md_id::new, PParmlist::new, Retkind::new);
    new Md_id()
      .on()
      .useRHS(Classid::new, Colon::new, Fcnid::new);

    new Fcndefs()
      .on()
      .useRHS(Fcndef::new, Fcndefs::new)
      .on()
      .useRHS(Epsilon::new);
    new Fcndef()
      .on()
      .useRHS(Fcnheader::new, BBlock::new);
    new Fcnheader()
      .on()
      .useRHS(FunctionKeywordToken::new, Fcnid::new, PParmlist::new, Retkind::new);
    new Fcnid()
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken::getSentinel);
    new Retkind()
      .on()
      .useRHS(BaseKind::new);
    new PParmlist()
      .on()
      .useRHS(LeftParen::new, Varspecs::new, RightParen::new)
      .on()
      .useRHS(PPonly::new);
    new Varspecs()
      .on()
      .useRHS(Varspec::new, More_varspecs::new);
    new More_varspecs()
      .on()
      .useRHS(Comma::new, Varspecs::new)
      .on()
      .useRHS(Epsilon::new);
    new PPonly()
      .on()
      .useRHS(LeftParen::new, RightParen::new);


    new Stmts()
      .on()
      .useRHS(Stmt::new, SemiColon::new, Stmts::new)
      .on()
      .useRHS(Epsilon::new);
    new Stmt()
      .on()
      .useRHS(Stasgn::new)
      .on()
      .useRHS(Fcall::new)
      .on()
      .useRHS(Stif::new)
      .on()
      .useRHS(Stwhile::new)
      .on()
      .useRHS(Stprint::new)
      .on()
      .useRHS(Strtn::new);


    new Stasgn()
      .on()
      .useRHS(Lval::new, Equal::new, Expr::new);
    new Lval()
      .on(IdentifierToken.class)
      .useRHS(Varid::new)
      .on(IdentifierToken.class)
      .useRHS(Aref::new)
      .on(Asterisk.class)
      .useRHS(Deref_id::new);
    new Aref()
      .on(IdentifierToken.class)
      .useRHS(Varid::new, KKexpr::new);
    new KKexpr()
      .on()
      .useRHS(LeftBracket::new, Expr::new, RightBracket::new);


    new Fcall()
      .on(IdentifierToken.class)
      .useRHS(Fcnid::new, PPexprs::new);
    new PPexprs()
      .on()
      .useRHS(LeftParen::new, Exprlist::new, RightParen::new)
      .on()
      .useRHS(PPonly::new);


    new Stif()
      .on()
      .useRHS(IfKeywordToken::new, PPexpr::new, BBlock::new, Elsepart::new);
    new Elsepart()
      .on()
      .useRHS(ElseIfKeywordToken::new, PPexpr::new, BBlock::new, Elsepart::new)
      .on()
      .useRHS(ElseKeywordToken::new, BBlock::new)
      .on()
      .useRHS(Epsilon::new);


    new Stwhile()
      .on()
      .useRHS(WhileKeywordToken::new, PPexpr::new, BBlock::new);
    new Stprint()
      .on()
      .useRHS(PrintKeywordToken::new, PPexprs::new);


    new Strtn()
      .on()
      .useRHS(ReturnKeywordToken::new, Expr::new)
      .on()
      .useRHS(ReturnKeywordToken::new);


    new PPexpr()
      .on(LeftParen.class)
      .useRHS(LeftParen::new, Expr::new, RightParen::new);
    new Expr()
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Rterm::new, Expr_Tail::new);
    new Expr_Tail()
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class)
      .useRHS(Oprel::new, Rterm::new, Expr_Tail::new)
      .on(RightParen.class)
      .useRHS();
    new Rterm()
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Term::new, Rterm_Tail::new);
    new Rterm_Tail()
      .on(Plus.class, Minus.class)
      .useRHS(Opadd::new, Term::new, Rterm_Tail::new)
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class, RightParen.class)
      .useRHS();
    new Term()
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Fact::new, Term_Tail::new);
    new Term_Tail()
      .on(Asterisk.class, ForwardSlash.class, Caret.class)
      .useRHS(Opmul::new, Fact::new, Term_Tail::new)
      .on(EqualEqual.class, NotEqual.class, LessThan.class, LessThanOrEqual.class, GreaterThanOrEqual.class, GreaterThan.class, RightParen.class, Plus.class, Minus.class)
      .useRHS();
    new Fact()
      .on(IntegerToken.class, FloatToken.class, StringToken.class)
      .useRHS(BaseLiteral::new)
      .on(IdentifierToken.class, Asterisk.class)
      .useRHS(Lval::new)
      .on(Ampersand.class)
      .useRHS(Addrof_id::new)
      .on(IdentifierToken.class)
      .useRHS(Fcall::new)
      .on(LeftParen.class)
      .useRHS(PPexpr::new);
    new BaseLiteral()
      .on(IntegerToken.class)
      .useRHS(IntegerToken::getSentinel)
      .on(FloatToken.class)
      .useRHS(FloatToken::getSentinel)
      .on(StringToken.class)
      .useRHS(StringToken::getSentinel);
    new Addrof_id()
      .on(Ampersand.class)
      .useRHS(Ampersand::new, IdentifierToken::getSentinel);
    new Oprel()
      .on(EqualEqual.class)
      .useRHS(EqualEqual::new)
      .on(NotEqual.class)
      .useRHS(NotEqual::new)
      .on(LessThan.class)
      .useRHS(Lthan::new)
      .on(LessThanOrEqual.class)
      .useRHS(LessThanOrEqual::new)
      .on(GreaterThanOrEqual.class)
      .useRHS(GreaterThanOrEqual::new)
      .on(GreaterThan.class)
      .useRHS(Gthan::new);
    new Lthan()
      .on(LessThan.class)
      .useRHS(LessThan::new);
    new Gthan()
      .on(GreaterThan.class)
      .useRHS(GreaterThan::new);
    new Opadd()
      .on(Plus.class)
      .useRHS(Plus::new)
      .on(Minus.class)
      .useRHS(Minus::new);
    new Opmul()
      .on(Asterisk.class)
      .useRHS(Asterisk::new)
      .on(ForwardSlash.class)
      .useRHS(ForwardSlash::new)
      .on(Caret.class)
      .useRHS(Caret::new);
  }
}
