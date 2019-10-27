package compiler.parser;

public class ParserBuilder {

  public ParserBuilderLastStep setStartSymbol(GrammarRule startSymbol) {
    return new ParserBuilderLastStep(startSymbol);
  }

  public static class ParserBuilderLastStep {
    private GrammarRule startSymbol;

    private ParserBuilderLastStep(GrammarRule startSymbol) {
      this.startSymbol = startSymbol;
    }

    public Parser createParser() {
      return new Parser(startSymbol);
    }
  }
}
