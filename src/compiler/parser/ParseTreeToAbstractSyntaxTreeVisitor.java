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
  public void visit(Minus token) {
  }

  @Override
  public void visit(PPexpr ppexpr) {
    hoist(ppexpr);
  }

  @Override
  public void visit(ForwardSlash forwardSlash) {
    hoist(forwardSlash);
  }

  @Override
  public void visit(Asterisk asterisk) {
    hoist(asterisk);
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
        while (--tokenIndex != 0 && !tree.children.isEmpty())
          token.children.addFirst(tree.children.pop());

        while (!tree.children.isEmpty())
          token.children.addLast(tree.children.pop());

        return;
      }
    }
  }
}
