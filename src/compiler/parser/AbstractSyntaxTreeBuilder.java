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

    if (tree instanceof GrammarNode) {
      ((GrammarNode) tree).accept(visitor);
    }

    if (tree instanceof Token) {
      ((Token) tree).accept(visitor);
    }
  }

}
