import compiler.a5.grammar.A5Grammar;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarRule;
import compiler.parser.ParserBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

  public static void main(String[] args) throws Exception {
    final var lexer = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var tokens = lexer.analyze(testInput);
    tokens.remove(tokens.size() - 1);
    final var tokenStream = new LinkedList<>(tokens);

    new ParserBuilder()
      .setStartSymbol(A5Grammar.PPexpr)
      .beforeRuleApplication(Main::logBeforeState)
      .afterRuleApplication(Main::logTokenPopping)
      .onUnexpectedToken(Main::logUnexpectedToken)
      .onUnknownGrammarRule(Main::logUnknownGrammarRule)
      .onPredictionNotFoundError(Main::logPredictionNotFound)
      .onGrammarRuleApplication(Main::logGrammarRuleApplication)
      .createParser()
      .parse(tokenStream);


    final var dehyrdated = tokens.stream()
      .map(Token::toString)
      .collect(Collectors.joining("\n"));

    System.out.println(dehyrdated);
  }

  public static void logBeforeState(LinkedList<AbstractGrammarRule> stack, Token token) {
    System.out.println("Stack:         " + format(stack));
  }

  public static void logTokenPopping(AbstractGrammarRule top, Token token, List<AbstractGrammarRule> rhs) {
    System.out.println("Applying Rule: Popping: " + top);
  }

  public static void logUnexpectedToken(AbstractGrammarRule top, Token token) {
    System.out.println("Expected: " + top + " but found: " + token);
  }

  public static void logUnknownGrammarRule(AbstractGrammarRule top, Token token) {
    System.out.println("Grammar contains rule not in Grammar hierarchy: " + top);
  }

  public static void logPredictionNotFound(AbstractGrammarRule top, Token token) {
    System.out.println("User error; Rule: " + top + " has no entry for " + token);
  }

  public static void logGrammarRuleApplication(AbstractGrammarRule top, Token token, List<AbstractGrammarRule> rhs) {
    System.out.println("Move:          " + top + "(" + token.getClass().getSimpleName() + ") => " + format(rhs));
  }

  public static String format(List<AbstractGrammarRule> rules) {
    var strings = new ArrayList<String>();
    for (final var rule : rules) {
      if (rule instanceof Token) {
        strings.add(rule.getClass().getSimpleName());
      } else {
        strings.add(rule.toString());
      }
    }
    return "[" + String.join(",", strings) + "]";
  }


}
