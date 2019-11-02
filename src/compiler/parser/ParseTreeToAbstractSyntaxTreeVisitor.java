package compiler.parser;

import compiler.a5.grammar.GrammarNodeVisitor;
import compiler.lexer.token.EOFToken;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.SymbolToken;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;

public class ParseTreeToAbstractSyntaxTreeVisitor implements TokenVisitor, GrammarNodeVisitor {
  @Override
  public void visit(SymbolToken.LeftParen lhs) {

  }

  @Override
  public void visit(SymbolToken.RightParen lhs) {
  }

  @Override
  public void visit(IntegerToken token) {

  }

  @Override
  public void visit(PPexpr ppexpr) {
    final var lparen = ppexpr.children.get(0);
    final var expr = ppexpr.children.get(1);
    final var rparen = ppexpr.children.get(2);
    ppexpr.parent.children.set(ppexpr.parent.children.indexOf(ppexpr), expr);
    expr.children.addFirst(lparen);
    expr.children.addLast(rparen);
    expr.children.forEach(child -> child.parent = expr);
    expr.parent = ppexpr.parent;
    ppexpr.parent = null;
    ppexpr.children = null;
  }

  @Override
  public void visit(Expr tree) {
    final var lparen = tree.children.get(0);
    tree.children.remove(lparen);
    tree.parent.children.set(tree.parent.children.indexOf(tree), lparen);
    lparen.parent = tree.parent;
    lparen.children.addAll(tree.children);
    tree.children.forEach(child -> child.parent = lparen);
    tree.parent = null;
    tree.children = null;
  }

  @Override
  public void visit(Expr_Tail tree) {
    if (tree.children.isEmpty()) {
      tree.parent.children.remove(tree);
      tree.parent = null;
      tree.children = null;
    }

  }

  @Override
  public void visit(BaseLiteral tree) {
    final var value = tree.children.get(0);
    tree.parent.children.set(tree.parent.children.indexOf(tree), value);
    value.parent = tree.parent;
    tree.parent = null;
    tree.children = null;
  }

  @Override
  public void visit(Fact tree) {
    final var value = tree.children.get(0);
    tree.parent.children.set(tree.parent.children.indexOf(tree), value);
    value.parent = tree.parent;
    tree.parent = null;
    tree.children = null;
  }

  @Override
  public void visit(Term tree) {
    if (tree.children.size() == 1) {
      final var value = tree.children.get(0);
      tree.parent.children.set(tree.parent.children.indexOf(tree), value);
      value.parent = tree.parent;
      tree.parent = null;
      tree.children = null;
    }
  }

  @Override
  public void visit(Term_Tail tree) {
    if (tree.children.isEmpty()) {
      tree.parent.children.remove(tree);
      tree.parent = null;
      tree.children = null;
    }
  }

  @Override
  public void visit(Rterm tree) {
    if (tree.children.size() == 1) {
      final var value = tree.children.get(0);
      tree.parent.children.set(tree.parent.children.indexOf(tree), value);
      value.parent = tree.parent;
      tree.parent = null;
      tree.children = null;
    }
  }

  @Override
  public void visit(Rterm_Tail tree) {
    if (tree.children.isEmpty()) {
      tree.parent.children.remove(tree);
      tree.parent = null;
      tree.children = null;
    }
  }

  @Override
  public void visit(EOFToken tree) {
    tree.parent.children.remove(tree);
    tree.parent = null;
    tree.children = null;
  }

  @Override
  public void visit(Opadd tree) {
    final var value = tree.children.get(0);
    tree.parent.children.set(tree.parent.children.indexOf(tree), value);
    value.parent = tree.parent;
    tree.parent = null;
    tree.children = null;
  }

  @Override
  public void visit(Opmul tree) {
    final var value = tree.children.get(0);
    tree.parent.children.set(tree.parent.children.indexOf(tree), value);
    value.parent = tree.parent;
    tree.parent = null;
    tree.children = null;
  }
}
