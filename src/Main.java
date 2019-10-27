import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.AlexHydrator;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.IntegerToken;
import compiler.lexer.token.Token;
import compiler.parser.TokenGrammarRule;

import java.util.stream.Collectors;

public class Main {
  private static final String testTokens = """
(Tok: 10 lin= 1,1 str = "prog")
(Tok: 11 lin= 1,6 str = "main")
(Tok: 33 lin= 1,11 str = "{")
(Tok: 23 lin= 2,5 str = "print")
(Tok: 53 lin= 2,10 str = "!=")
(Tok: 37 lin= 2,12 str = "(")
(Tok: 5 lin= 2,14 str = "Input legs> ")
(Tok: 38 lin= 2,29 str = ")")
(Tok: 7 lin= 2,30 str = ";")
(Tok: 2 lin= 3,5 str = "var")
(Tok: 2 lin= 3,9 str = "a")
(Tok: 45 lin= 3,11 str = "=")
(Tok: 22 lin= 3,13 str = "input")
(Tok: 37 lin= 3,18 str = "(")
(Tok: 16 lin= 3,20 str = "int")
(Tok: 38 lin= 3,24 str = ")")
(Tok: 7 lin= 3,25 str = ";")
(Tok: 2 lin= 4,5 str = "var")
(Tok: 2 lin= 4,9 str = "b")
(Tok: 45 lin= 4,11 str = "=")
(Tok: 22 lin= 4,13 str = "input")
(Tok: 37 lin= 4,18 str = "(")
(Tok: 16 lin= 4,20 str = "int")
(Tok: 38 lin= 4,24 str = ")")
(Tok: 7 lin= 4,25 str = ";")
(Tok: 23 lin= 5,5 str = "print")
(Tok: 37 lin= 5,10 str = "(")
(Tok: 5 lin= 5,12 str = "Hypotenuse= ")
(Tok: 6 lin= 5,26 str = ",")
(Tok: 37 lin= 5,28 str = "(")
(Tok: 2 lin= 5,30 str = "a")
(Tok: 41 lin= 5,32 str = "*")
(Tok: 2 lin= 5,34 str = "a")
(Tok: 47 lin= 5,36 str = "+")
(Tok: 2 lin= 5,38 str = "b")
(Tok: 41 lin= 5,40 str = "*")
(Tok: 2 lin= 5,42 str = "b")
(Tok: 38 lin= 5,44 str = ")")
(Tok: 42 lin= 5,46 str = "^")
(Tok: 4 lin= 5,48 str = "0.5" flo= 0.5)
(Tok: 38 lin= 5,52 str = ")")
(Tok: 7 lin= 5,53 str = ";")
(Tok: 34 lin= 6,1 str = "}")
(Tok: 13 lin= 8,1 str = "class")
(Tok: 2 lin= 8,7 str = "testing")
(Tok: 33 lin= 8,15 str = "{")
(Tok: 12 lin= 10,5 str = "fcn")
(Tok: 37 lin= 10,8 str = "(")
(Tok: 38 lin= 10,9 str = ")")
(Tok: 33 lin= 10,10 str = "{")
(Tok: 23 lin= 11,9 str = "print")
(Tok: 37 lin= 11,14 str = "(")
(Tok: 5 lin= 11,15 str = "Something to print")
(Tok: 38 lin= 11,35 str = ")")
(Tok: 7 lin= 11,36 str = ";")
(Tok: 2 lin= 12,9 str = "var")
(Tok: 2 lin= 12,13 str = "somevar")
(Tok: 45 lin= 12,21 str = "=")
(Tok: 3 lin= 12,23 str = "3" int= 3))
(Tok: 7 lin= 12,24 str = ";")
(Tok: 2 lin= 13,9 str = "var")
(Tok: 2 lin= 13,13 str = "someothervar")
(Tok: 45 lin= 13,26 str = "=")
(Tok: 3 lin= 13,28 str = "4" int= 4))
(Tok: 7 lin= 13,29 str = ";")
(Tok: 23 lin= 14,9 str = "print")
(Tok: 37 lin= 14,14 str = "(")
(Tok: 3 lin= 14,15 str = "3" int= 3))
(Tok: 47 lin= 14,17 str = "+")
(Tok: 3 lin= 14,19 str = "4" int= 4))
(Tok: 38 lin= 14,20 str = ")")
(Tok: 7 lin= 14,21 str = ";")
(Tok: 23 lin= 15,9 str = "print")
(Tok: 37 lin= 15,14 str = "(")
(Tok: 2 lin= 15,15 str = "j")
(Tok: 38 lin= 15,16 str = ")")
(Tok: 34 lin= 16,5 str = "}")
(Tok: 34 lin= 18,1 str = "}")
(Tok: 56 lin= 20,1 str = "<<")
(Tok: 57 lin= 20,4 str = ">>")
(Tok: 31 lin= 20,7 str = "<")
(Tok: 32 lin= 20,9 str = ">")
(Tok: 53 lin= 20,11 str = "!=")
(Tok: 0 lin= 20,13 str = "")
""";

  public static void main(String[] args) {
    final var lexer = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var tokens = new AlexHydrator(lexer).hydrate(testTokens);

    final var dehyrdated = tokens.stream()
      .map(Token::toString)
      .collect(Collectors.joining("\n"));

    final var test = new TokenGrammarRule(token -> token instanceof IntegerToken);


    final var a = new TestToken();
    final var b = new TestGarmmarRule();
    final var c = new OtherGrammarRule();

    System.out.println(a.equals(a));
    System.out.println(a.equals(b));
    System.out.println(a.equals(c));

    System.out.println();

    System.out.println(b.equals(a));
    System.out.println(b.equals(b));
    System.out.println(b.equals(c));

    System.out.println();

    System.out.println(c.equals(a));
    System.out.println(c.equals(b));
    System.out.println(c.equals(c));
  }
}
