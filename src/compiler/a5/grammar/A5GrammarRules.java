package compiler.a5.grammar;

import compiler.lexer.token.*;
import compiler.lexer.token.KeywordToken.*;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;
import static compiler.lexer.token.OperatorToken.*;
import static compiler.lexer.token.SymbolToken.*;

public class A5GrammarRules {

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
      .on(
        FunctionKeywordToken.class,
        MainKeywordToken.class,
        Asterisk.class,
        IdentifierToken.class,
        IfKeywordToken.class,
        WhileKeywordToken.class,
        PrintKeywordToken.class,
        ReturnKeywordToken.class,
        RightBrace.class,
        Colon.class,
        VarKeywordToken.class
      )
      .useRHS(Epsilon::new);
    new PPvarlist()
      .on(LeftParen.class)
      .useRHS(LeftParen::new, Varlist::new, RightParen::new);
    new Varlist()
      .on(
        IntegerKeywordToken.class,
        FloatKeywordToken.class,
        StringKeywordToken.class,
        IdentifierToken.class,
        ClassKeywordToken.class
      )
      .useRHS(Varitem::new, SemiColon::new, Varlist::new)
      .on(RightParen.class)
      .useRHS(Epsilon::new);
    new Varitem()
      .on(
        IntegerKeywordToken.class,
        FloatKeywordToken.class,
        StringKeywordToken.class,
        IdentifierToken.class
      )
      .useRHS(Vardecl::new, Varitem_Suffix::new)
      .on(ClassKeywordToken.class)
      .useRHS(Classdef::new);
    new Varitem_Suffix()
      .on(Equal.class)
      .useRHS(Equal::new, Varinit::new)
      .on(SemiColon.class)
      .useRHS(Epsilon::new);
    new Vardecl()
      .on(
        IntegerKeywordToken.class,
        FloatKeywordToken.class,
        StringKeywordToken.class,
        IdentifierToken.class
      )
      .useRHS(Simplekind::new, Varspec::new);
    new Simplekind()
      .on(
        IntegerKeywordToken.class,
        FloatKeywordToken.class,
        StringKeywordToken.class
      )
      .useRHS(BaseKind::new)
      .on(IdentifierToken.class)
      .useRHS(Classid::new);
    new BaseKind()
      .on(IntegerKeywordToken.class)
      .useRHS(IntegerKeywordToken::new)
      .on(FloatKeywordToken.class)
      .useRHS(FloatKeywordToken::new)
      .on(StringKeywordToken.class)
      .useRHS(StringKeywordToken::new);
    new Classid()
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken::getSentinel);
    new Varspec()
      .on(IdentifierToken.class)
      .useRHS(Varid::new, Arrspec::new)
      .on(Asterisk.class)
      .useRHS(Deref_id::new);
    new Varid()
      .on(IdentifierToken.class)
      .useRHS(IdentifierToken::getSentinel);
    new Arrspec()
      .on(LeftBracket.class)
      .useRHS(KKint::new)
      .on(
        Equal.class,
        SemiColon.class,
        Comma.class,
        RightParen.class
      )
      .useRHS(Epsilon::new);
    new KKint()
      .on(LeftBracket.class)
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
      .on(FunctionKeywordToken.class)
      .useRHS(Fcndef::new, Fcndefs::new)
      .on(MainKeywordToken.class)
      .useRHS(Epsilon::new);
    new Fcndef()
      .on(FunctionKeywordToken.class)
      .useRHS(Fcnheader::new, BBlock::new);
    new Fcnheader()
      .on(FunctionKeywordToken.class)
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
      .on(IdentifierToken.class, Asterisk.class)
      .useRHS(Stasgn::new)
      .on(IdentifierToken.class)
      .useRHS(Fcall::new)
      .on(IfKeywordToken.class)
      .useRHS(Stif::new)
      .on(WhileKeywordToken.class)
      .useRHS(Stwhile::new)
      .on(PrintKeywordToken.class)
      .useRHS(Stprint::new)
      .on(ReturnKeywordToken.class)
      .useRHS(Strtn::new);


    new Stasgn()
      .on(IdentifierToken.class, Asterisk.class)
      .useRHS(Lval::new, Equal::new, Expr::new);
    new Lval()
      .on(IdentifierToken.class)
      .useRHS(Varid::new, Lval_Tail::new)
      .on(Asterisk.class)
      .useRHS(Deref_id::new);
    new Lval_Tail()
      .on(LeftBracket.class)
      .useRHS(KKexpr::new)
      .on(Equal.class, SemiColon.class, Comma.class, RightBracket.class, RightParen.class)
      .useRHS(Epsilon::new);
    new KKexpr()
      .on(LeftBracket.class)
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
      .on(IfKeywordToken.class)
      .useRHS(IfKeywordToken::new, PPexpr::new, BBlock::new, Elsepart::new);
    new Elsepart()
      .on()
      .useRHS(ElseIfKeywordToken::new, PPexpr::new, BBlock::new, Elsepart::new)
      .on()
      .useRHS(ElseKeywordToken::new, BBlock::new)
      .on()
      .useRHS(Epsilon::new);


    new Stwhile()
      .on(WhileKeywordToken.class)
      .useRHS(WhileKeywordToken::new, PPexpr::new, BBlock::new);
    new Stprint()
      .on(PrintKeywordToken.class)
      .useRHS(PrintKeywordToken::new, PPexprs::new);
    new Strtn()
      .on(ReturnKeywordToken.class)
      .useRHS(ReturnKeywordToken::new, Strtn_Tail::new);
    new Strtn_Tail()
      .on(IntegerToken.class, FloatToken.class, StringToken.class, IdentifierToken.class, Asterisk.class, Ampersand.class, LeftParen.class)
      .useRHS(Expr::new)
      .on(SemiColon.class)
      .useRHS(Epsilon::new);


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
