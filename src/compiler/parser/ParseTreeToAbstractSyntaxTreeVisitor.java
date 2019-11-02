package compiler.parser;

import compiler.a5.grammar.GrammarNodeVisitor;
import compiler.lexer.token.EOFToken;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.SymbolToken.*;
import compiler.lexer.token.Token;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;
import static compiler.lexer.token.OperatorToken.*;

public class ParseTreeToAbstractSyntaxTreeVisitor implements TokenVisitor, GrammarNodeVisitor {
  @Override
  public void visit(LeftParen lhs) {
  }

  @Override
  public void visit(RightParen lhs) {
  }

  @Override
  public void visit(IntegerToken token) {
  }

  @Override
  public void visit(Plus token) {
  }

  @Override
  public void visit(PPexpr ppexpr) {
  }

  @Override
  public void visit(Expr tree) {
  }

  @Override
  public void visit(Expr_Tail tree) {
  }

  @Override
  public void visit(BaseLiteral tree) {
  }

  @Override
  public void visit(Fact tree) {
  }

  @Override
  public void visit(Term tree) {
  }

  @Override
  public void visit(Term_Tail tree) {

  }

  @Override
  public void visit(Rterm tree) {

  }

  @Override
  public void visit(Rterm_Tail tree) {

  }

  @Override
  public void visit(EOFToken tree) {

  }

  @Override
  public void visit(Opadd tree) {
  }

  @Override
  public void visit(Opmul tree) {

  }

  private void hoist(AbstractGrammarNode tree) {
    for (var child : tree.children) {
      if (child instanceof Token) {
        child.parent = tree.parent;
        tree.parent.children.set(tree.parent.children.indexOf(tree), child);
      }
    }
  }
}
