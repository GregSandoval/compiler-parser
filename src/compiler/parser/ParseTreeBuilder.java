package compiler.parser;

import compiler.lexer.token.EOFToken;
import compiler.lexer.token.Token;

import java.util.List;

public class ParseTreeBuilder {
  private AbstractGrammarNode root;

  public ParseTreeBuilderBuildStep setStartSymbol(GrammarNode startSymbol) {
    return tokens -> {
      final var EOF = new EOFToken();
      this.root = new ParseTreeSentinel();
      this.root.children.push(EOF);
      this.root.children.push(startSymbol);
      this.root.children.forEach(node -> node.parent = this.root);

      new ParserBuilder()
        .setStartSymbol(startSymbol)
        .setEOF(EOF)
        .onGrammarRuleApplication(this::AttachToTree)
        .createParser()
        .parse(tokens);

      return root;
    };
  }

  private void AttachToTree(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
    if (top instanceof Token && !(top instanceof EOFToken)) {
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

  private static class ParseTreeSentinel extends GrammarNode {
  }
}
