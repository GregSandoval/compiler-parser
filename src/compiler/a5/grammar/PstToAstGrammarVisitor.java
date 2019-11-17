package compiler.a5.grammar;

import compiler.lexer.token.SymbolToken;
import compiler.parser.GrammarNode;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;
import static compiler.parser.PstToAstHelpers.*;

public class PstToAstGrammarVisitor implements GrammarNodeVisitor {
  @Override
  public void visit(Expr tree) {
    if (tree.children.size() == 2) {
      final var left = tree.children.pop();
      final var right = tree.children.peek();
      hoist(tree);
      left.parent = right;
      right.children.addFirst(left);
    }
  }

  @Override
  public void visit(Expr_Tail tree) {
    hoist(tree);
  }

  @Override
  public void visit(Term term) {
    if (term.children.size() == 2) {
      final var lvalue = term.children.get(0);
      final var operator = term.children.get(1);
      term.children.remove(lvalue);
      lvalue.parent = operator;
      operator.children.addFirst(lvalue);
    }
    hoist(term);
  }

  @Override
  public void visit(Term_Tail tree) {
    if (tree.children.size() == 3) {
      final var tail = tree.children.get(2);
      final var tailLValue = tree.children.get(1);
      tailLValue.parent = tail;
      tail.children.addFirst(tailLValue);
      tree.children.remove(tailLValue);
    }
    hoist(tree);
  }

  @Override
  public void visit(Rterm tree) {
    if (tree.children.size() == 2) {
      final var operator = tree.children.get(1);
      final var lvalue = tree.children.get(0);
      lvalue.parent = operator;
      tree.children.remove(lvalue);
      operator.children.addFirst(lvalue);
    }
    hoist(tree);
  }

  @Override
  public void visit(Rterm_Tail tree) {
    if (tree.children.size() == 3) {
      final var tail = tree.children.get(2);
      final var tailLValue = tree.children.get(1);
      tailLValue.parent = tail;
      tail.children.addFirst(tailLValue);
      tree.children.remove(tailLValue);
    }
    hoist(tree);
  }

  @Override
  public void visit(PPexpr ppexpr) {
    hoist(ppexpr);
  }

  @Override
  public void visit(Classmom node) {

  }

  @Override
  public void visit(GrammarNode node) {

  }

  @Override
  public void visit(Pgm node) {

  }

  @Override
  public void visit(Main node) {
    hoist(node);
  }

  @Override
  public void visit(BBlock node) {
    hoist(node);
  }

  @Override
  public void visit(Vargroup node) {
    hoist(node);
  }

  @Override
  public void visit(PPvarlist node) {
    node.children.removeIf(child -> child instanceof SymbolToken.RightParen);
    if (node.children.size() == 2) {
      final var varlist = node.children.get(1);
      node.children.remove(varlist);
      varlist.parent = null;

      varlist.children.forEach(node.children::addLast);
      varlist.children.forEach(child -> child.parent = node);
    }
    hoist(node);
  }

  @Override
  public void visit(Varlist node) {
    node.children.removeIf(child -> child instanceof SymbolToken.SemiColon);
    if (node.children.size() == 2) {
      final var otherVarList = node.children.get(1);
      node.children.remove(otherVarList);
      otherVarList.children.forEach(node.children::addLast);
      otherVarList.children.forEach(child -> child.parent = node);
      return;
    }
  }

  @Override
  public void visit(Varitem node) {
    reverseHoist(node);
  }

  @Override
  public void visit(Varitem_Suffix node) {
    hoist(node);
  }

  @Override
  public void visit(Vardecl node) {
    hoist(node);
  }

  @Override
  public void visit(Simplekind node) {

  }

  @Override
  public void visit(BaseKind node) {

  }

  @Override
  public void visit(Varspec node) {

  }

  @Override
  public void visit(Varid node) {

  }

  @Override
  public void visit(Arrspec node) {

  }

  @Override
  public void visit(KKint node) {

  }

  @Override
  public void visit(Deref_id node) {

  }

  @Override
  public void visit(Deref node) {

  }

  @Override
  public void visit(Varinit node) {

  }

  @Override
  public void visit(BBexprs node) {

  }

  @Override
  public void visit(Exprlist node) {
    reverseHoist(node);
  }

  @Override
  public void visit(Moreexprs node) {
    hoist(node);
  }

  @Override
  public void visit(Classdecl node) {

  }

  @Override
  public void visit(Classdef node) {
    hoist(node);
  }

  @Override
  public void visit(Classdef_Suffix node) {

  }

  @Override
  public void visit(BBClassitems node) {
    node.children.removeIf(child -> child instanceof SymbolToken.RightBrace);
    rightContraction(node);
    hoist(node);
  }

  @Override
  public void visit(Classitems node) {
    if(!node.children.isEmpty())
      rightContraction(node);
  }

  @Override
  public void visit(Classgroup node) {

  }

  @Override
  public void visit(Class_ctrl node) {
    hoist(node);
  }

  @Override
  public void visit(Interfaces node) {

  }

  @Override
  public void visit(Mddecls node) {

  }

  @Override
  public void visit(Mdheader node) {

  }

  @Override
  public void visit(Md_id node) {

  }

  @Override
  public void visit(Fcndefs node) {

  }

  @Override
  public void visit(Fcndef node) {

  }

  @Override
  public void visit(Fcnheader node) {

  }

  @Override
  public void visit(Fcnid node) {

  }

  @Override
  public void visit(Retkind node) {

  }

  @Override
  public void visit(PParmlist node) {

  }

  @Override
  public void visit(Varspecs node) {

  }

  @Override
  public void visit(More_varspecs node) {

  }

  @Override
  public void visit(PPonly node) {

  }

  @Override
  public void visit(Stmts node) {

  }

  @Override
  public void visit(Stmt node) {

  }

  @Override
  public void visit(StasgnOrFcall node) {

  }

  @Override
  public void visit(StasgnOrFcall_Suffix node) {

  }

  @Override
  public void visit(Stasgn_Suffix node) {

  }

  @Override
  public void visit(Lval_Suffix node) {

  }

  @Override
  public void visit(Lval node) {

  }

  @Override
  public void visit(LvalOrFcall node) {

  }

  @Override
  public void visit(LvalOrFcall_Suffix node) {

  }

  @Override
  public void visit(Lval_Tail node) {

  }

  @Override
  public void visit(KKexpr node) {

  }

  @Override
  public void visit(Fcall node) {

  }

  @Override
  public void visit(PPexprs node) {
    hoist(node);
  }

  @Override
  public void visit(Stif node) {

  }

  @Override
  public void visit(Elsepart node) {

  }

  @Override
  public void visit(Stwhile node) {

  }

  @Override
  public void visit(Stprint node) {
    hoist(node);
  }

  @Override
  public void visit(Strtn node) {

  }

  @Override
  public void visit(Strtn_Suffix node) {

  }

  @Override
  public void visit(Fact node) {

  }

  @Override
  public void visit(BaseLiteral node) {

  }

  @Override
  public void visit(Addrof_id node) {

  }

  @Override
  public void visit(Oprel node) {

  }

  @Override
  public void visit(Lthan node) {

  }

  @Override
  public void visit(Gthan node) {

  }

  @Override
  public void visit(Opadd node) {

  }

  @Override
  public void visit(Opmul node) {

  }

  @Override
  public void visit(Epsilon node) {

  }

  @Override
  public void visit(Classheader node) {
    hoist(node);
  }

  @Override
  public void visit(Classid node) {

  }

}
