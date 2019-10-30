package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class ParseTreeStrategy implements
  TriConsumer<AbstractGrammarNode, Token, List<AbstractGrammarNode>>,
  BiConsumer<LinkedList<AbstractGrammarNode>, Token> {
  private AbstractGrammarNode root;
  private AbstractGrammarNode currentNode;

  @Override
  public void accept(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
    for (final var child : rhs) {
      final var clone = new GrammarNode(child.toString());
      currentNode.children.add(clone);
    }
  }

  @Override
  public void accept(LinkedList<AbstractGrammarNode> stack, Token token) {
    final var top = stack.peek();

    if (top instanceof Token || top == null) {
      return;
    }

    currentNode = new GrammarNode(top.toString());

    if (root == null) {
      root = currentNode;
    }
  }

  public AbstractGrammarNode getRoot() {
    return root;
  }
}
