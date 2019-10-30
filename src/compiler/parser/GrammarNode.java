package compiler.parser;

import compiler.lexer.token.Token;

import java.util.*;

public class GrammarNode extends AbstractGrammarNode {
  private static final Map<Class<? extends Token>, List<Class<? extends AbstractGrammarNode>>> rules = new HashMap<>();
  private String name;

  public GrammarNode(String name) {
    this.name = name;
  }

  public List<Class<? extends AbstractGrammarNode>> getRHS(Class<? extends Token> token) {
    return rules.get(token);
  }

  @Override
  public String toString() {
    return name;
  }

  @SafeVarargs
  public static RuleBuilderSecondStep on(Class<? extends Token>... first) {
    return new RuleBuilderSecondStep(first);
  }

  public static class RuleBuilderFirstStep {
    @SafeVarargs
    public final RuleBuilderSecondStep on(Class<? extends Token>... first) {
      return new RuleBuilderSecondStep(first);
    }
  }

  public static class RuleBuilderSecondStep {
    private Class<? extends Token>[] firstItems;

    @SafeVarargs
    private RuleBuilderSecondStep(Class<? extends Token>... first) {
      this.firstItems = first;
    }

    @SafeVarargs
    public final RuleBuilderFirstStep useRHS(Class<? extends AbstractGrammarNode>... rest) {
      final var rhs = new ArrayList<>(Arrays.asList(rest));

      for (var token : firstItems) {
        GrammarNode.rules.put(token, rhs);
      }

      return new RuleBuilderFirstStep();
    }
  }

}
