package compiler.parser;

import compiler.lexer.token.Token;


public class AbstractSyntaxTreeBuilder {

  public static void fromParseTree(AbstractGrammarNode tree) {
    final var visitor = new ParseTreeToAbstractSyntaxTreeVisitor();
    postordervisit(tree, visitor);
  }

  public static void postordervisit(AbstractGrammarNode tree, ParseTreeToAbstractSyntaxTreeVisitor visitor) {
    if (tree == null) {
      return;
    }

    for (var child : tree.children) {
      postordervisit(child, visitor);
    }

    trim(tree);
    contract(tree);
    if (tree instanceof GrammarNode) {
      ((GrammarNode) tree).accept(visitor);
    }

    if (tree instanceof Token) {
      ((Token) tree).accept(visitor);
    }
  }

  private static void trim(AbstractGrammarNode tree) {
    tree
      .children
      .removeIf(child -> child instanceof GrammarNode && child.children.isEmpty());
  }

  private static void contract(AbstractGrammarNode tree) {
    if (tree instanceof GrammarNode && tree.children.size() == 1) {
      final var child = tree.children.get(0);
      child.parent = tree.parent;
      tree.parent.children.set(tree.parent.children.indexOf(tree), child);
      // IMPORTANT DO NOT REMOVE
      tree.children.clear();
    }
  }

}
