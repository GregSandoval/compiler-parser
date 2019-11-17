import compiler.a5.grammar.A5GrammarNonTerminals;
import compiler.a5.grammar.A5GrammarRules;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.parser.AbstractGrammarNode;
import compiler.parser.AbstractSyntaxTreeBuilder;
import compiler.parser.GrammarNode;
import compiler.parser.ParseTreeBuilder;
import visualization.TreeVisualizer;

public class Main {
  private static final String testInput = """
  prog

  var (
    class Person : Animal + Human {
      : public var (
          string name = "greg";
      )

      : private var (
          string ssn = "lol wut?";
      )

      fcn Person:getName(a, b, c[10], d, *e) string
    };
  )

  main {
      var (
        float test = 4;
        string a = "";
      )

      print( "Hypotenuse= ", ( a * a + b * b ) ^ 0.5, "", 2 + 3, a );

      a = 12;
      b = john;
      c = hello(1, 2, 3, 4);
      d = dave[6 + 4];
      e = *mike;

      java(a, b);

      if(a != ""){
        print(a);
      } elseif (a != "nice") {
        print("Elseif!");
      } elseif (a != "why not") {
        print("Elseif!");
      } else{
        print("Else!");
      };

      while(a != false){
        print("Looping!");
      };

      return 1 + 1;
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


    System.out.println();
    validateAST(tree);
  }

  public static void validateAST(AbstractGrammarNode tree) {
    if (tree == null) {
      return;
    }
    if (tree instanceof GrammarNode) {
      System.out.println("Grammar node found in AST: " + tree);
    }
    for (final var child : tree.children) {
      validateAST(child);
    }
  }

}
