package compiler.parser;

import compiler.lexer.token.Token;

import java.util.*;

public final class GrammarRule extends AbstractGrammarRule {
  private final Map<Class<? extends Token>, List<AbstractGrammarRule>> rules;

  public GrammarRule() {
    this.rules = new HashMap<>();
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

    public GrammarRule useRHS(AbstractGrammarRule first, AbstractGrammarRule... rest) {
      final var rhs = new ArrayList<AbstractGrammarRule>();
      rhs.add(first);
      rhs.addAll(Arrays.asList(rest));

      for (var token : firstItems) {
        GrammarRule.this.rules.put(token, rhs);
      }

      return GrammarRule.this;
    }
  }

}
