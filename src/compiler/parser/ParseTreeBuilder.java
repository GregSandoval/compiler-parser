package compiler.parser;

import compiler.lexer.token.Token;

import java.util.List;

public class ParseTreeBuilder {
  public ParseTreeBuilderLastStep setStartSymbol(GrammarNode startSymbol) {
    return new ParseTreeBuilderLastStep(startSymbol);
  }

  public static class ParseTreeBuilderLastStep {
    private GrammarNode startSymbol;

    private ParseTreeBuilderLastStep(GrammarNode startSymbol) {
      this.startSymbol = startSymbol;
    }

    public AbstractGrammarNode build(List<Token> tokens) throws Exception {
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
