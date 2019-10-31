package compiler.parser;

public class AbstractSyntaxTreeBuilder {
  public AbstractGrammarNode build(AbstractGrammarNode tree) {
    return new GrammarNode();
  }
}
