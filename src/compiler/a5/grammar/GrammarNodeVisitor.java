package compiler.a5.grammar;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;

public interface GrammarNodeVisitor {
  public default void visit(Pgm node){
    throw new RuntimeException("Not implemented: visit(Pgm node); this: " + this);
  }

  public default void visit(Main node){
    throw new RuntimeException("Not implemented: visit(Main node); this: " + this);
  }

  public default void visit(BBlock node){
    throw new RuntimeException("Not implemented: visit(BBlock node); this: " + this);
  }

  public default void visit(Vargroup node){
    throw new RuntimeException("Not implemented: visit(Vargroup node); this: " + this);
  }

  public default void visit(PPvarlist node){
    throw new RuntimeException("Not implemented: visit(PPvarlist node); this: " + this);
  }

  public default void visit(Varlist node){
    throw new RuntimeException("Not implemented: visit(Varlist node); this: " + this);
  }

  public default void visit(Varitem node){
    throw new RuntimeException("Not implemented: visit(Varitem node); this: " + this);
  }

  public default void visit(Vardecl node){
    throw new RuntimeException("Not implemented: visit(Vardecl node); this: " + this);
  }

  public default void visit(Simplekind node){
    throw new RuntimeException("Not implemented: visit(Simplekind node); this: " + this);
  }

  public default void visit(BaseKind node){
    throw new RuntimeException("Not implemented: visit(BaseKind node); this: " + this);
  }

  public default void visit(Classid node){
    throw new RuntimeException("Not implemented: visit(Classid node); this: " + this);
  }

  public default void visit(Varspec node){
    throw new RuntimeException("Not implemented: visit(Varspec node); this: " + this);
  }

  public default void visit(Varid node){
    throw new RuntimeException("Not implemented: visit(Varid node); this: " + this);
  }

  public default void visit(Arrspec node){
    throw new RuntimeException("Not implemented: visit(Arrspec node); this: " + this);
  }

  public default void visit(KKint node){
    throw new RuntimeException("Not implemented: visit(KKint node); this: " + this);
  }

  public default void visit(Deref_id node){
    throw new RuntimeException("Not implemented: visit(Deref_id node); this: " + this);
  }

  public default void visit(Deref node){
    throw new RuntimeException("Not implemented: visit(Deref node); this: " + this);
  }

  public default void visit(Varinit node){
    throw new RuntimeException("Not implemented: visit(Varinit node); this: " + this);
  }

  public default void visit(BBexprs node){
    throw new RuntimeException("Not implemented: visit(BBexprs node); this: " + this);
  }

  public default void visit(Exprlist node){
    throw new RuntimeException("Not implemented: visit(Exprlist node); this: " + this);
  }

  public default void visit(Moreexprs node){
    throw new RuntimeException("Not implemented: visit(Moreexprs node); this: " + this);
  }

  public default void visit(Classdecl node){
    throw new RuntimeException("Not implemented: visit(Classdecl node); this: " + this);
  }

  public default void visit(Classdef node){
    throw new RuntimeException("Not implemented: visit(Classdef node); this: " + this);
  }

  public default void visit(BBClassitems node){
    throw new RuntimeException("Not implemented: visit(BBClassitems node); this: " + this);
  }

  public default void visit(Classheader node){
    throw new RuntimeException("Not implemented: visit(Classheader node); this: " + this);
  }

  public default void visit(Classmom node){
    throw new RuntimeException("Not implemented: visit(Classmom node); this: " + this);
  }

  public default void visit(Classitems node){
    throw new RuntimeException("Not implemented: visit(Classitems node); this: " + this);
  }

  public default void visit(Classgroup node){
    throw new RuntimeException("Not implemented: visit(Classgroup node); this: " + this);
  }

  public default void visit(Class_ctrl node){
    throw new RuntimeException("Not implemented: visit(Class_ctrl node); this: " + this);
  }

  public default void visit(Interfaces node){
    throw new RuntimeException("Not implemented: visit(Interfaces node); this: " + this);
  }

  public default void visit(Mddecls node){
    throw new RuntimeException("Not implemented: visit(Mddecls node); this: " + this);
  }

  public default void visit(Mdheader node){
    throw new RuntimeException("Not implemented: visit(Mdheader node); this: " + this);
  }

  public default void visit(Md_id node){
    throw new RuntimeException("Not implemented: visit(Md_id node); this: " + this);
  }

  public default void visit(Fcndefs node){
    throw new RuntimeException("Not implemented: visit(Fcndefs node); this: " + this);
  }

  public default void visit(Fcndef node){
    throw new RuntimeException("Not implemented: visit(Fcndef node); this: " + this);
  }

  public default void visit(Fcnheader node){
    throw new RuntimeException("Not implemented: visit(Fcnheader node); this: " + this);
  }

  public default void visit(Fcnid node){
    throw new RuntimeException("Not implemented: visit(Fcnid node); this: " + this);
  }

  public default void visit(Retkind node){
    throw new RuntimeException("Not implemented: visit(Retkind node); this: " + this);
  }

  public default void visit(PParmlist node){
    throw new RuntimeException("Not implemented: visit(PParmlist node); this: " + this);
  }

  public default void visit(Varspecs node){
    throw new RuntimeException("Not implemented: visit(Varspecs node); this: " + this);
  }

  public default void visit(More_varspecs node){
    throw new RuntimeException("Not implemented: visit(More_varspecs node); this: " + this);
  }

  public default void visit(PPonly node){
    throw new RuntimeException("Not implemented: visit(PPonly node); this: " + this);
  }

  public default void visit(Stmts node){
    throw new RuntimeException("Not implemented: visit(Stmts node); this: " + this);
  }

  public default void visit(Stmt node){
    throw new RuntimeException("Not implemented: visit(Stmt node); this: " + this);
  }

  public default void visit(Stasgn node){
    throw new RuntimeException("Not implemented: visit(Stasgn node); this: " + this);
  }

  public default void visit(Lval node){
    throw new RuntimeException("Not implemented: visit(Lval node); this: " + this);
  }

  public default void visit(Aref node){
    throw new RuntimeException("Not implemented: visit(Aref node); this: " + this);
  }

  public default void visit(KKexpr node){
    throw new RuntimeException("Not implemented: visit(KKexpr node); this: " + this);
  }

  public default void visit(Fcall node){
    throw new RuntimeException("Not implemented: visit(Fcall node); this: " + this);
  }

  public default void visit(PPexprs node){
    throw new RuntimeException("Not implemented: visit(PPexprs node); this: " + this);
  }

  public default void visit(Stif node){
    throw new RuntimeException("Not implemented: visit(Stif node); this: " + this);
  }

  public default void visit(Elsepart node){
    throw new RuntimeException("Not implemented: visit(Elsepart node); this: " + this);
  }

  public default void visit(Stwhile node){
    throw new RuntimeException("Not implemented: visit(Stwhile node); this: " + this);
  }

  public default void visit(Stprint node){
    throw new RuntimeException("Not implemented: visit(Stprint node); this: " + this);
  }

  public default void visit(Strtn node){
    throw new RuntimeException("Not implemented: visit(Strtn node); this: " + this);
  }

  public default void visit(PPexpr node){
    throw new RuntimeException("Not implemented: visit(PPexpr node); this: " + this);
  }

  public default void visit(Expr node){
    throw new RuntimeException("Not implemented: visit(Expr node); this: " + this);
  }

  public default void visit(Expr_Tail node){
    throw new RuntimeException("Not implemented: visit(Expr_Tail node); this: " + this);
  }

  public default void visit(Rterm node){
    throw new RuntimeException("Not implemented: visit(Rterm node); this: " + this);
  }

  public default void visit(Rterm_Tail node){
    throw new RuntimeException("Not implemented: visit(Rterm_Tail node); this: " + this);
  }

  public default void visit(Term node){
    throw new RuntimeException("Not implemented: visit(Term node); this: " + this);
  }

  public default void visit(Term_Tail node){
    throw new RuntimeException("Not implemented: visit(Term_Tail node); this: " + this);
  }

  public default void visit(Fact node){
    throw new RuntimeException("Not implemented: visit(Fact node); this: " + this);
  }

  public default void visit(BaseLiteral node){
    throw new RuntimeException("Not implemented: visit(BaseLiteral node); this: " + this);
  }

  public default void visit(Addrof_id node){
    throw new RuntimeException("Not implemented: visit(Addrof_id node); this: " + this);
  }

  public default void visit(Oprel node){
    throw new RuntimeException("Not implemented: visit(Oprel node); this: " + this);
  }

  public default void visit(Lthan node){
    throw new RuntimeException("Not implemented: visit(Lthan node); this: " + this);
  }

  public default void visit(Gthan node){
    throw new RuntimeException("Not implemented: visit(Gthan node); this: " + this);
  }

  public default void visit(Opadd node){
    throw new RuntimeException("Not implemented: visit(Opadd node); this: " + this);
  }

  public default void visit(Opmul node){
    throw new RuntimeException("Not implemented: visit(Opmul node); this: " + this);
  }

  public default void visit(Epsilon node){
    throw new RuntimeException("Not implemented: visit(Epsilon node); this: " + this);
  }
}
