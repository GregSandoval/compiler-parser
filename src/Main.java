import compiler.a5.grammar.A5Grammar;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarNode;
import compiler.parser.ParseTreeBuilder;
import compiler.parser.ParserBuilder;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
  private static final String testInput = """
  (2 + 2 + 3)
""";

  public static void main(String[] args) throws Exception {
    final var lexer = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer();

    final var tokens = lexer.analyze(testInput);
    tokens.remove(tokens.size() - 1);

    new ParserBuilder()
      .setStartSymbol(A5Grammar.PPexpr)
      .beforeRuleApplication(Main::logBeforeState)
      .onUnexpectedToken(Main::logUnexpectedToken)
      .onUnknownGrammarRule(Main::logUnknownGrammarRule)
      .onPredictionNotFoundError(Main::logPredictionNotFound)
      .onGrammarRuleApplication(Main::logGrammarRuleApplication)
      .createParser()
      .parse(tokens);

    final var parseTree = new ParseTreeBuilder()
      .setStartSymbol(A5Grammar.PPexpr)
      .build(tokens);

    Graph graph = new SingleGraph("Some graph", false, true);
    buildGraph(graph, parseTree, 0, 0);
    for (Node node : graph) {
      node.addAttribute("ui.label", node.getId());
    }
    graph.display();
    System.out.println(parseTree);
  }

  public static void buildGraph(Graph graph, AbstractGrammarNode current, int id, int depth) {
    if (current == null) {
      return;
    }

    for (final var child : current.children) {
      graph.addEdge("" + (++id), format(current), format(child));
      buildGraph(graph, child, id, ++depth);
    }
  }

  public static void logBeforeState(LinkedList<AbstractGrammarNode> stack, Token token) {
    System.out.println("Stack: " + format(stack));
  }

  public static void logGrammarRuleApplication(AbstractGrammarNode top, Token token, List<AbstractGrammarNode> rhs) {
    System.out.println("Move : " + format(top) + "(" + format(token) + ") => " + format(rhs) + "\n");
  }


  public static void logUnexpectedToken(AbstractGrammarNode top, Token token) {
    System.out.println("Expected: " + format(top) + " but found: " + format(token));
  }

  public static void logUnknownGrammarRule(AbstractGrammarNode top, Token token) {
    System.out.println("\n");
    System.out.println("Grammar contains rule not in Grammar hierarchy: " + format(top));
  }

  public static void logPredictionNotFound(AbstractGrammarNode top, Token token) {
    System.out.println("\n");
    System.out.println("User error; Rule: " + format(top) + " has no entry for " + format(token));
  }

  public static String format(List<AbstractGrammarNode> rules) {
    var strings = rules.stream()
      .map(Main::format)
      .collect(Collectors.toList());
    return "[" + String.join(", ", strings) + "]";
  }

  public static String format(AbstractGrammarNode rule) {
    return rule instanceof Token ? rule.getClass().getSimpleName() : rule.toString();
  }

}
