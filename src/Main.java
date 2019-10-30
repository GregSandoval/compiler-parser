import compiler.a5.grammar.A5Grammar;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarNode;
import compiler.parser.ParseTreeBuilder;
import compiler.parser.ParserBuilder;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.gexf.SmartXMLWriter;
import org.graphstream.ui.layout.HierarchicalLayout;

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

    A5Grammar.build();
    new ParserBuilder()
      .setStartSymbol(new A5Grammar.PPexpr())
      .beforeRuleApplication(Main::logBeforeState)
      .onUnexpectedToken(Main::logUnexpectedToken)
      .onUnknownGrammarRule(Main::logUnknownGrammarRule)
      .onPredictionNotFoundError(Main::logPredictionNotFound)
      .onGrammarRuleApplication(Main::logGrammarRuleApplication)
      .createParser()
      .parse(tokens);

    final var parseTree = new ParseTreeBuilder()
      .setStartSymbol(new A5Grammar.PPexpr())
      .build(tokens);

    final var graph = new SingleGraph("Some graph", true, true);
    addNodes(graph, parseTree, 0);
    final var viewer = graph.display(false);
    viewer.enableAutoLayout(new HierarchicalLayout());
    System.out.println(parseTree);
  }

  public static int addNodes(Graph graph, AbstractGrammarNode current, int id) {
    if (current == null) {
      return id;
    }
    graph.addNode("" + id).addAttribute("ui.label", format(current));
    final var parentID = id;
    for (final var child : current.children) {
      final var childID = ++id;
      id = addNodes(graph, child, childID);
      graph.addEdge("edge:" + childID, "" + parentID, "" + childID);
    }
    return id;
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
