import compiler.a5.grammar.A5GrammarNonTerminals;
import compiler.a5.grammar.A5GrammarRules;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.parser.AbstractSyntaxTreeBuilder;
import compiler.parser.ParseTreeBuilder;
import visualization.TreeVisualizer;

public class Main {
  private static final String testInput = """
  prog

  var (
    class Person {
      : public
        var (
          string name = "greg";
        )
    };
  )

  main {
      var (
        float test = 4;
        string a = "";
      )
      print( "Input legs> " );
      print( "Hypotenuse= ", ( a * a + b * b ) ^ 0.5 );
    }
""";

  public static void main(String[] args) throws Exception {
    final var tokens = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer()
      .analyze(testInput);

    A5GrammarRules.build();

    final var tree = new ParseTreeBuilder()
      .setStartSymbol(new A5GrammarNonTerminals.Pgm())
      .build(tokens);

    TreeVisualizer.toImage(tree, "ParseTree");
    AbstractSyntaxTreeBuilder.fromParseTree(tree);
    TreeVisualizer.toImage(tree, "AbstractSyntaxTree");
  }

}
