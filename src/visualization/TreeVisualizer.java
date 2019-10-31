package visualization;

import compiler.lexer.token.EOFToken;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarNode;
import visualization.dotfile.Digraph;

import java.io.IOException;

public class TreeVisualizer {
  public static void toImage(AbstractGrammarNode tree, String name) throws IOException {
    final var graph = new Digraph("testing");
    buildGraph(graph, tree, 0);
    graph.generate(name + ".dot");

    new ProcessBuilder("dot", "-Tpng", name + ".dot", "-o", name + ".png")
      .start();

    System.out.println("Generated parse tree image, file name: graph.png");
  }

  private static int buildGraph(Digraph graph, AbstractGrammarNode current, int id) {
    final var parentID = id;

    if (current == null) {
      return id;
    }

    graph.addNode("" + id, formatWithValue(current));
    for (final var child : current.children) {
      final var childID = ++id;
      id = buildGraph(graph, child, childID);
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
