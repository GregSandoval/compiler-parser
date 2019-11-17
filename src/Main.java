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

  // Classes (with single inheritance and interfaces)
  var (
    class Person : Animal + Human {

      // Public scope variables
      : public var (
          string name = "greg";
          int age = 27;
      )

      // Private scope variables
      : private var (
          string ssn = "lol wut?";
          string phoneNumber = "911";
      )

      // Default scope variables
      var (
          string nickname = "Package private maybe?";
      )

      // Methods
      fcn Person:getName() string
      fcn Person:setName(name) string
      fcn Person:setRandomStuff(a, b, c[10], d, *e) string
    };
  )

  main {
      // Local Variables
      var (
        float test = 4;
        string a;
        string b;
        string c;
        string d;
        string e;
      )

      // Statements
      // Assignment Statement
      a = 12;
      b = john;
      c = hello(1, 2, 3, 4);
      d = dave[6 + 4];
      e = *mike;

      // Function Call
      java(a, b);

      // If statement
      if(a != ""){
        print(a);
      } elseif (a != "nice") {
        print("Elseif!");
      } elseif (a != "why not") {
        print("Elseif!");
      } else{
        print("Else!");
      };

      // While Statement
      while(a != false){
        print("Looping!");
      };

      // Print Statement
      print( "Hypotenuse= ", ( a * a + b * b ) ^ 0.5, "", 2 + 3, a );

      // Return Statement
      return 1 + 1;
      // End of statements


      // Expressions

      // Relations
      // Less than
      a = (2 < 3);
      // Less than or equal
      a = (2 <= 3);
      // Greater than
      a = (2 > 3);
      // Greater than or equal
      a = (2 >= 3);
      // Equality
      a = (2 == 2);
      // Inequality
      a = (2 != 2);

      // Plus minus
      a = 2 + 3 - 4;

      // Multiplication/division
      a = 2 * 2 / 5;


      // Everything together!
      a = 2 * a() + (b(a[0], *a, &k)) / &f;
      return 2;
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
