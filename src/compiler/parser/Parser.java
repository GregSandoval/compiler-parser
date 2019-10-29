package compiler.parser;

import compiler.lexer.token.Token;
import compiler.utils.TriConsumer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

public class Parser {
  private GrammarRule startSymbol;
  private BiConsumer<LinkedList<AbstractGrammarRule>, Token> beforeRuleApplication;
  private TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> afterRuleApplication;
  private BiConsumer<AbstractGrammarRule, Token> onUnexpectedToken;
  private BiConsumer<AbstractGrammarRule, Token> onUnknownGrammarRule;
  private BiConsumer<AbstractGrammarRule, Token> onPredictionNotFoundError;
  private TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> onGrammarRuleApplication;

  public Parser(
    GrammarRule startSymbol,
    BiConsumer<LinkedList<AbstractGrammarRule>, Token> beforeRuleApplication,
    TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> afterRuleApplication,
    BiConsumer<AbstractGrammarRule, Token> onUnexpectedToken,
    BiConsumer<AbstractGrammarRule, Token> onUnknownGrammarRule,
    BiConsumer<AbstractGrammarRule, Token> onPredictionNotFoundError,
    TriConsumer<AbstractGrammarRule, Token, List<AbstractGrammarRule>> onGrammarRuleApplication
  ) {
    this.startSymbol = startSymbol;
    this.beforeRuleApplication = beforeRuleApplication;
    this.afterRuleApplication = afterRuleApplication;
    this.onUnexpectedToken = onUnexpectedToken;
    this.onUnknownGrammarRule = onUnknownGrammarRule;
    this.onPredictionNotFoundError = onPredictionNotFoundError;
    this.onGrammarRuleApplication = onGrammarRuleApplication;
  }

  public void parse(LinkedList<Token> tokens) throws Exception {
    final var stack = new LinkedList<AbstractGrammarRule>();
    stack.add(this.startSymbol);

    while (!tokens.isEmpty() && !stack.isEmpty()) {
      beforeRuleApplication.accept(stack, tokens.peek());
      final Token token = tokens.peek();
      final AbstractGrammarRule top = stack.pop();

      if (top instanceof Token) {
        if (token.getClass() == top.getClass()) {
          tokens.pop();
          afterRuleApplication.accept(top, token, Collections.emptyList());
          continue;
        }
        onUnexpectedToken.accept(top, token);
        return;
      }

      if (!(top instanceof GrammarRule)) {
        onUnknownGrammarRule.accept(top, token);
        return;
      }

      final var rhs = ((GrammarRule) top).getRHS(token.getClass());

      if (rhs == null) {
        onPredictionNotFoundError.accept(top, token);
        return;
      }

      onGrammarRuleApplication.accept(top, token, rhs);
      for (int i = rhs.size() - 1; i >= 0; i--) {
        stack.push(rhs.get(i));
      }
    }

    if (!tokens.isEmpty() || !stack.isEmpty()) {
      throw new Exception("Failed to parse input; Expected tokens but recieved EOF");
    }
  }

}
