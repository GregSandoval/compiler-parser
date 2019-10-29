import compiler.a5.grammar.A5Grammar;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarRule;
import compiler.parser.ParserBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  private static final String testInput = """
  ( ( 3.0 + 1 * ( 4.3 / 3 ) ) + 3 * ( 23 + 4 ))
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
      .onUnexpectedToken(Main::logUnexpectedToken)
      .onUnknownGrammarRule(Main::logUnknownGrammarRule)
      .onPredictionNotFoundError(Main::logPredictionNotFound)
      .onGrammarRuleApplication(Main::logGrammarRuleApplication)
      .createParser()
      .parse(tokenStream);
  }

  public static void logBeforeState(LinkedList<AbstractGrammarRule> stack, Token token) {
    System.out.println("Stack: " + format(stack));
  }

  public static void logGrammarRuleApplication(AbstractGrammarRule top, Token token, List<AbstractGrammarRule> rhs) {
    System.out.println("Move : " + format(top) + "(" + format(token) + ") => " + format(rhs) + "\n");
  }


  public static void logUnexpectedToken(AbstractGrammarRule top, Token token) {
    System.out.println("Expected: " + format(top) + " but found: " + format(token));
  }

  public static void logUnknownGrammarRule(AbstractGrammarRule top, Token token) {
    System.out.println("\n");
    System.out.println("Grammar contains rule not in Grammar hierarchy: " + format(top));
  }

  public static void logPredictionNotFound(AbstractGrammarRule top, Token token) {
    System.out.println("\n");
    System.out.println("User error; Rule: " + format(top) + " has no entry for " + format(token));
  }

  public static String format(List<AbstractGrammarRule> rules) {
    var strings = rules.stream()
      .map(Main::format)
      .collect(Collectors.toList());
    return "[" + String.join(", ", strings) + "]";
  }

  public static String format(AbstractGrammarRule rule) {
    return rule instanceof Token ? rule.getClass().getSimpleName() : rule.toString();
  }

}
