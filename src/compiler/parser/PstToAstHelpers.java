package compiler.parser;

import compiler.lexer.token.*;

public class PstToAstHelpers {

  public static void hoist(AbstractGrammarNode tree) {
    for (var token : tree.children) {
      if (token instanceof Token) {
        final var parent = tree.parent;
        var tokenIndex = parent.children.indexOf(tree);

        // Replace token's parent
        token.parent = parent;

        // Replace rule with token
        parent.children.set(tokenIndex, token);

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
