package compiler.parser;

import compiler.lexer.token.Token;

import java.util.List;

public class ParseTreeBuilder {
  private AbstractGrammarNode root;

  public ParseTreeBuilderBuildStep setStartSymbol(GrammarNode startSymbol) {
    return tokens -> {
      new ParserBuilder()
        .setStartSymbol(startSymbol)
        .onGrammarRuleApplication(this::AttachToTree)
        .createParser()
        .parse(tokens);
      return root;
    };
  }

  private void AttachToTree(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
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

  public interface ParseTreeBuilderBuildStep {
    AbstractGrammarNode build(List<Token> tokens) throws Exception;
  }
}
