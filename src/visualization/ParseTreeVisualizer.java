package visualization;

import compiler.lexer.token.EOFToken;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarNode;
import visualization.dotfile.Digraph;

import java.io.IOException;

public class ParseTreeVisualizer {
  public static void toImage(AbstractGrammarNode tree) throws IOException {
    final var digraph = new Digraph("testing");
    addNodes(digraph, tree, 0);
    digraph.generate("graph.dot");

    new ProcessBuilder("dot", "-Tpng", "graph.dot", "-o", "graph.png")
      .start();

    System.out.println("Generated parse tree image, file name: graph.png");
  }

  private static int addNodes(Digraph graph, AbstractGrammarNode current, int id) {
    if (current == null) {
      return id;
    }
    graph.addNode("" + id, formatWithValue(current));
    final var parentID = id;
    for (final var child : current.children) {
      final var childID = ++id;
      id = addNodes(graph, child, childID);
      graph.link("" + parentID, "" + childID);
    }
    return id;
  }

  private static String formatWithValue(AbstractGrammarNode rule) {
    if (rule instanceof EOFToken)
      return "EOF";

    if (rule instanceof Token)
      return ((Token) rule).getValue();

    return rule.toString();
  }
}
