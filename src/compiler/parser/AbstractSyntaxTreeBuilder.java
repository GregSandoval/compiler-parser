package compiler.parser;

import compiler.lexer.token.EOFToken;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;

public class AbstractSyntaxTreeBuilder {
  public static AbstractGrammarNode tranform(AbstractGrammarNode tree) {
    if (tree == null) {
      return null;
    }

    if (tree instanceof EOFToken) {
      tree.parent.children.remove(tree);
    }

    if (tree instanceof GrammarNode) {
      tree.children.forEach(AbstractSyntaxTreeBuilder::tranform);

      if (tree instanceof PPexpr) {
        final var lparen = tree.children.get(0);
        final var expr = tree.children.get(1);
        final var rparen = tree.children.get(2);
        tree.parent.children.set(tree.parent.children.indexOf(tree), expr);
        expr.children.addFirst(lparen);
        expr.children.addLast(rparen);
      }

      if (tree instanceof Term_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
        }
      }

      if (tree instanceof Rterm_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
        }
      }

      if (tree instanceof Expr_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
        }
      }
    }

    return tree;
  }
}
