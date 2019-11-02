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
    hoist(tree);
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
    for (var token : tree.children) {
      if (token instanceof Token) {
        var tokenIndex = tree.parent.children.indexOf(tree);

        // Replace token's parent
        token.parent = tree.parent;

        // Replace rule with token
        tree.parent.children.set(tokenIndex, token);

        // Remove token from rule's children
        tree.children.remove(token);

        // Set tree's children to point to token as new parent
        tree.children.forEach(child -> child.parent = token);

        // Add rule's children to the token;
        while (--tokenIndex != 0)
          token.children.addFirst(tree.children.pop());

        while (!tree.children.isEmpty())
          token.children.addLast(tree.children.pop());

        return;
      }
    }
  }
}
