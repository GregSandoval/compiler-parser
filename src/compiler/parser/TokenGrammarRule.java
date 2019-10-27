package compiler.parser;

import compiler.lexer.token.Token;

public final class TokenGrammarRule extends AbstractGrammarRule {
  private Class<? extends Token> tokenClass;

  public TokenGrammarRule(Class<? extends Token> tokenClass) {
    this.tokenClass = tokenClass;
  }

}
