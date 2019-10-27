package compiler.parser;

import compiler.lexer.token.Token;

import java.util.*;

public final class GrammarRule extends AbstractGrammarRule {
  private final Map<Class<? extends Token>, List<AbstractGrammarRule>> rules;
  private String name;

  public GrammarRule(String name) {
    this.name = name;
    this.rules = new HashMap<>();
  }

  public List<AbstractGrammarRule> getRHS(Class<? extends Token> token) {
    return this.rules.get(token);
  }

  @Override
  public String toString() {
    return name;
  }

  @SafeVarargs
  public final RuleBuilder on(Class<? extends Token>... first) {
    return new RuleBuilder(first);
  }

  public class RuleBuilder {
    private Class<? extends Token>[] firstItems;

    @SafeVarargs
    private RuleBuilder(Class<? extends Token>... first) {
      this.firstItems = first;
    }

    public GrammarRule useRHS(AbstractGrammarRule... rest) {
      final var rhs = new ArrayList<AbstractGrammarRule>();
      rhs.addAll(Arrays.asList(rest));

      for (var token : firstItems) {
        GrammarRule.this.rules.put(token, rhs);
      }

      return GrammarRule.this;
    }
  }

}
