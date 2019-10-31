package compiler.parser;

import compiler.lexer.token.EOFToken;
import compiler.lexer.token.Token;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;

public class AbstractSyntaxTreeBuilder {
  public static AbstractGrammarNode tranform(AbstractGrammarNode tree) {
    if (tree == null) {
      return null;
    }

    if (tree instanceof Token) {

      if (tree instanceof EOFToken) {
        tree.parent.children.remove(tree);
        return tree;
      }
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
        return tree;
      }

      if (tree instanceof Rterm) {
        if (tree.children.size() == 1) {
          final var value = tree.children.get(0);
          tree.parent.children.set(tree.parent.children.indexOf(tree), value);
          return tree;
        }
      }

      if (tree instanceof Opadd) {
        final var value = tree.children.get(0);
        tree.parent.children.set(tree.parent.children.indexOf(tree), value);
        return tree;
      }

      if (tree instanceof Opmul) {
        final var value = tree.children.get(0);
        tree.parent.children.set(tree.parent.children.indexOf(tree), value);
        return tree;
      }

      if (tree instanceof Fact) {
        final var value = tree.children.get(0);
        tree.parent.children.set(tree.parent.children.indexOf(tree), value);
        return tree;
      }

      if (tree instanceof BaseLiteral) {
        final var value = tree.children.get(0);
        tree.parent.children.set(tree.parent.children.indexOf(tree), value);
        return tree;
      }

      if (tree instanceof Term) {
        if (tree.children.size() == 1) {
          final var value = tree.children.get(0);
          tree.parent.children.set(tree.parent.children.indexOf(tree), value);
          return tree;
        }

        final var lvalue = tree.children.get(0);
        final var operator = tree.children.get(1);
        tree.parent.children.set(tree.parent.children.indexOf(tree), operator);
        operator.children.addFirst(lvalue);
      }

      if (tree instanceof Term_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
          return tree;
        } else {
          final var operator = tree.children.get(0);
          final var value = tree.children.get(1);
          tree.parent.children.set(tree.parent.children.indexOf(tree), operator);
          operator.children.addLast(value);
          return tree;
        }
      }

      if (tree instanceof Rterm_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
          return tree;
        }
      }

      if (tree instanceof Expr_Tail) {
        if (tree.children.isEmpty()) {
          tree.parent.children.remove(tree);
          return tree;
        }
      }
    }

    return tree;
  }
}
