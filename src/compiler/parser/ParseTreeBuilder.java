package compiler.parser;

import compiler.lexer.token.Token;

import java.util.List;

public class ParseTreeBuilder {
  public ParseTreeBuilderLastStep setStartSymbol(GrammarRule startSymbol) {
    return new ParseTreeBuilderLastStep(startSymbol);
  }

  public static class ParseTreeBuilderLastStep {
    private GrammarRule startSymbol;

    private ParseTreeBuilderLastStep(GrammarRule startSymbol) {
      this.startSymbol = startSymbol;
    }

    public AbstractGrammarRule build(List<Token> tokens) throws Exception {
      final var builderStrategy = new ParseTreeStrategy();
      new ParserBuilder()
        .setStartSymbol(this.startSymbol)
        .beforeRuleApplication(builderStrategy)
        .onGrammarRuleApplication(builderStrategy)
        .createParser()
        .parse(tokens);
      return builderStrategy.getRoot();
    }
  }
}
