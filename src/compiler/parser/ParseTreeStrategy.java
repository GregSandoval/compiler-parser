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
  private Token currentToken;

  @Override
  public void accept(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
    if (currentNode instanceof Token && currentToken != null) {
      currentNode.parent.children.set(currentNode.parent.children.indexOf(currentNode), token);
    } else {
      currentNode.children.addAll(rhs);
      rhs.forEach(node -> node.parent = currentNode);
    }
  }

  @Override
  public void accept(LinkedList<AbstractGrammarNode> stack, Token token) {
    var top = stack.peek();

    currentNode = top;
    currentToken = top instanceof Token ? token : null;

    if (root == null) {
      root = currentNode;
    }
  }

  public AbstractGrammarNode getRoot() {
    return root;
  }
}
