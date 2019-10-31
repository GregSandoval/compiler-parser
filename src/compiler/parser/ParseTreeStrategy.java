package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.List;

public class ParseTreeStrategy implements TriConsumer<AbstractGrammarNode, Token, List<AbstractGrammarNode>> {
  private AbstractGrammarNode root;

  @Override
  public void accept(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
    if (root == null) {
      root = top;
    }

    if (top instanceof Token) {
      top.parent.children.set(top.parent.children.indexOf(top), token);
    }

    if (!(top instanceof Token)) {
      top.children.addAll(rhs);
      rhs.forEach(node -> node.parent = top);
    }
  }

  public AbstractGrammarNode getRoot() {
    return root;
  }
}
