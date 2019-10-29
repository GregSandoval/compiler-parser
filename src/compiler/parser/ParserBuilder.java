package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class ParserBuilder {

  public ParserBuilderLastStep setStartSymbol(GrammarRule startSymbol) {
    return new ParserBuilderLastStep(startSymbol);
  }

  public static class ParserBuilderLastStep {
    private GrammarRule startSymbol;
    private BiConsumer<LinkedList<AbstractGrammarRule>, Token> beforeRuleApplication = (abstractGrammarRule, token) -> {
    };
    private BiConsumer<AbstractGrammarRule, Token> onUnexpectedToken = (abstractGrammarRule, token) -> {
    };
    private BiConsumer<AbstractGrammarRule, Token> onUnknownGrammarRule = (abstractGrammarRule, token) -> {
    };
    private BiConsumer<AbstractGrammarRule, Token> onPredictionNotFoundError = (abstractGrammarRule, token) -> {
    };
    private TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> onGrammarRuleApplication = (abstractGrammarRule, token, abstractGrammarRules) -> {
    };

    private ParserBuilderLastStep(GrammarRule startSymbol) {
      this.startSymbol = startSymbol;
    }

    public ParserBuilderLastStep beforeRuleApplication(BiConsumer<LinkedList<AbstractGrammarRule>, Token> beforeRuleApplication) {
      this.beforeRuleApplication = this.beforeRuleApplication.andThen(beforeRuleApplication);
      return this;
    }

    public ParserBuilderLastStep onUnexpectedToken(BiConsumer<AbstractGrammarRule, Token> onUnexpectedToken) {
      this.onUnexpectedToken = this.onUnexpectedToken.andThen(onUnexpectedToken);
      return this;
    }

    public ParserBuilderLastStep onUnknownGrammarRule(BiConsumer<AbstractGrammarRule, Token> onUnknownGrammarRule) {
      this.onUnknownGrammarRule = this.onUnknownGrammarRule.andThen(onUnknownGrammarRule);
      return this;
    }

    public ParserBuilderLastStep onPredictionNotFoundError(BiConsumer<AbstractGrammarRule, Token> onPredictionNotFoundError) {
      this.onPredictionNotFoundError = this.onPredictionNotFoundError.andThen(onPredictionNotFoundError);
      return this;
    }

    public ParserBuilderLastStep onGrammarRuleApplication(TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> onGrammarRuleApplication) {
      this.onGrammarRuleApplication = this.onGrammarRuleApplication.andThen(onGrammarRuleApplication);
      return this;
    }

    public Parser createParser() {
      return new Parser(
        startSymbol,
        beforeRuleApplication,
        onUnexpectedToken,
        onUnknownGrammarRule,
        onPredictionNotFoundError,
        onGrammarRuleApplication
      );
    }

  }
}
