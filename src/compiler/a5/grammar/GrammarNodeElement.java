package compiler.a5.grammar;

public interface GrammarNodeElement {
  public void accept(GrammarNodeVisitor visitor);
}
