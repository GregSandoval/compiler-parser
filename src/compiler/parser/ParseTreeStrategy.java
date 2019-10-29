package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class ParseTreeStrategy implements
  TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>>,
  BiConsumer<LinkedList<AbstractGrammarRule>, Token> {
  private AbstractGrammarRule root;
  private AbstractGrammarRule currentNode;

  @Override
  public void accept(AbstractGrammarRule top, Token token, List<AbstractGrammarRule> rhs) {
    for (final var child : rhs) {
      final var clone = new GrammarRule(child.toString());
      currentNode.children.add(clone);
    }
  }

  @Override
  public void accept(LinkedList<AbstractGrammarRule> stack, Token token) {
    final var top = stack.peek();

    if (top instanceof Token || top == null) {
      return;
    }

    currentNode = new GrammarRule(top.toString());

    if (root == null) {
      root = currentNode;
    }
  }

  public AbstractGrammarRule getRoot() {
    return root;
  }
}
