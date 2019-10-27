import compiler.a5.grammar.A5Grammar;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.AlexHydrator;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.parser.ParserBuilder;

import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {
  private static final String testTokens = """
(Tok: 3 lin= 1,1 str = "(")
(Tok: 3 lin= 1,1 str = "3" int= 3))
(Tok: 47 lin= 1,3 str = "+")
(Tok: 3 lin= 1,5 str = "3" int= 3))
(Tok: 47 lin= 1,3 str = "*")
(Tok: 3 lin= 1,5 str = "3" int= 3))
(Tok: 47 lin= 1,3 str = "/")
(Tok: 3 lin= 1,5 str = "3" int= 3))
(Tok: 3 lin= 1,1 str = ")")
(Tok: 0 lin= 1,10 str = "")
""";

  private static final String testInput = """
  (3.0 + 1 * (4.3 / 3))
""";

  public static void main(String[] args) {
    final var lexer = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var tokens = lexer.analyze(testInput);
    final var parser = new ParserBuilder()
      .setStartSymbol(A5Grammar.PPexpr)
      .createParser();

    tokens.remove(tokens.size() - 1);
    final var tokenStream = new LinkedList<Token>(tokens);
    parser.parse(tokenStream);

    final var dehyrdated = tokens.stream()
      .map(Token::toString)
      .collect(Collectors.joining("\n"));

    System.out.println(dehyrdated);
  }
}
