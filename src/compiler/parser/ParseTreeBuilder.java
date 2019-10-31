package compiler.parser;

import compiler.lexer.token.Token;

import java.util.List;

public class ParseTreeBuilder {
  public ParseTreeBuilderBuildStep setStartSymbol(GrammarNode startSymbol) {
    return tokens -> {
      final var builderStrategy = new ParseTreeStrategy();
      new ParserBuilder()
        .setStartSymbol(startSymbol)
        .onGrammarRuleApplication(builderStrategy)
        .createParser()
        .parse(tokens);
      return builderStrategy.getRoot();
    };
  }

  public interface ParseTreeBuilderBuildStep {
    AbstractGrammarNode build(List<Token> tokens) throws Exception;
  }
}
