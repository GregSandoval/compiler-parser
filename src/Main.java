import compiler.a5.grammar.A5GrammarNonTerminals;
import compiler.a5.grammar.A5GrammarRules;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.parser.ParseTreeBuilder;
import visualization.ParseTreeVisualizer;

public class Main {
  private static final String testInput = """
      (11)
""";

  public static void main(String[] args) throws Exception {
    final var tokens = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer()
      .analyze(testInput);

    A5GrammarRules.build();

    final var tree = new ParseTreeBuilder()
      .setStartSymbol(new A5GrammarNonTerminals.PPexpr())
      .build(tokens);

    ParseTreeVisualizer.toImage(tree);
  }

}
