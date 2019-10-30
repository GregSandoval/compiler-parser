package compiler.parser;

import compiler.lexer.token.Token;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GrammarNode extends AbstractGrammarNode {
  private static final Map<Class<? extends GrammarNode>, Map<Class<? extends Token>, List<Supplier<AbstractGrammarNode>>>> LLTable = new HashMap<>();

  public GrammarNode() {
    if (!LLTable.containsKey(this.getClass())) {
      LLTable.put(this.getClass(), new HashMap<>());
    }
  }

  public List<AbstractGrammarNode> getRHS(Class<? extends Token> token) {
    return LLTable
      .get(this.getClass())
      .getOrDefault(token, Collections.emptyList())
      .stream()
      .map(Supplier::get)
      .collect(Collectors.toUnmodifiableList());
  }

  @Override
  public String toString() {
    return this.getClass().getSimpleName();
  }

  @SafeVarargs
  public final RuleBuilderSecondStep on(Class<? extends Token>... first) {
    return new RuleBuilderSecondStep(first);
  }

  public class RuleBuilderFirstStep {
    @SafeVarargs
    public final RuleBuilderSecondStep on(Class<? extends Token>... first) {
      return new RuleBuilderSecondStep(first);
    }
  }

  public class RuleBuilderSecondStep {
    private Class<? extends Token>[] firstItems;

    @SafeVarargs
    private RuleBuilderSecondStep(Class<? extends Token>... first) {
      this.firstItems = first;
    }

    @SafeVarargs
    public final RuleBuilderFirstStep useRHS(Supplier<AbstractGrammarNode>... rest) {
      final var rhs = new ArrayList<>(Arrays.asList(rest));

      for (var token : firstItems) {
        LLTable.get(GrammarNode.this.getClass()).put(token, rhs);
      }

      return new RuleBuilderFirstStep();
    }
  }

}
