package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class ParserBuilder {

  public ParserBuilderLastStep setStartSymbol(GrammarNode startSymbol) {
    return new ParserBuilderLastStep(startSymbol);
  }

  public static class ParserBuilderLastStep {
    private GrammarNode startSymbol;
    private BiConsumer<LinkedList<AbstractGrammarNode>, Token> beforeRuleApplication = (AbstractGrammarNode, token) -> {
    };
    private BiConsumer<AbstractGrammarNode, Token> onUnexpectedToken = (AbstractGrammarNode, token) -> {
    };
    private BiConsumer<AbstractGrammarNode, Token> onUnknownGrammarRule = (AbstractGrammarNode, token) -> {
    };
    private BiConsumer<AbstractGrammarNode, Token> onPredictionNotFoundError = (AbstractGrammarNode, token) -> {
    };
    private TriConsumer<AbstractGrammarNode, Token, List<AbstractGrammarNode>> onGrammarRuleApplication = (AbstractGrammarNode, token, AbstractGrammarNodes) -> {
    };

    private ParserBuilderLastStep(GrammarNode startSymbol) {
      this.startSymbol = startSymbol;
    }

    public ParserBuilderLastStep beforeRuleApplication(BiConsumer<LinkedList<AbstractGrammarNode>, Token> beforeRuleApplication) {
      this.beforeRuleApplication = this.beforeRuleApplication.andThen(beforeRuleApplication);
      return this;
    }

    public ParserBuilderLastStep onUnexpectedToken(BiConsumer<AbstractGrammarNode, Token> onUnexpectedToken) {
      this.onUnexpectedToken = this.onUnexpectedToken.andThen(onUnexpectedToken);
      return this;
    }

    public ParserBuilderLastStep onUnknownGrammarRule(BiConsumer<AbstractGrammarNode, Token> onUnknownGrammarRule) {
      this.onUnknownGrammarRule = this.onUnknownGrammarRule.andThen(onUnknownGrammarRule);
      return this;
    }

    public ParserBuilderLastStep onPredictionNotFoundError(BiConsumer<AbstractGrammarNode, Token> onPredictionNotFoundError) {
      this.onPredictionNotFoundError = this.onPredictionNotFoundError.andThen(onPredictionNotFoundError);
      return this;
    }

    public ParserBuilderLastStep onGrammarRuleApplication(TriConsumer<AbstractGrammarNode, Token, List<AbstractGrammarNode>> onGrammarRuleApplication) {
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
