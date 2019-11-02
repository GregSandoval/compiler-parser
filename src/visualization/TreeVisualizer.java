package visualization;

import compiler.lexer.token.EOFToken;
import compiler.lexer.token.Token;
import compiler.parser.AbstractGrammarNode;
import visualization.dotfile.Digraph;

import java.io.IOException;

public class TreeVisualizer {
  private static String NULL_NODE = "NULL_NODE";

  public static void toImage(AbstractGrammarNode tree, String name) throws IOException {
    final var graph = new Digraph("testing");

    graph.addNode(NULL_NODE, "NULL_NODE");

    buildGraph(graph, tree);

    graph.link(tree.UUID, NULL_NODE);
    graph.link(NULL_NODE, tree.UUID);
    graph.generate(name + ".dot");

    new ProcessBuilder("dot", "-Tpng", name + ".dot", "-o", name + ".png")
      .start();

    System.out.println("Generated parse tree image, file name: " + name + ".png");
  }

  private static void buildGraph(Digraph graph, AbstractGrammarNode current) {
    if (current == null) {
      return;
    }

    if (graph.exists(current.UUID)) {
      throw new RuntimeException("Cycle exists, Node: " + current);
    }

    graph.addNode(current.UUID, formatWithValue(current));

    for (final var child : current.children) {
      buildGraph(graph, child);
      graph.link(current.UUID, child.UUID);

      if (child.parent == null) {
        graph.link(child.UUID, NULL_NODE);
        continue;
      }

      if (!graph.exists(child.parent.UUID)) {
        graph.addNode(child.parent.UUID, formatWithValue(child.parent) + "[Parent] (Error: Missing)");
        graph.link(child.UUID, child.parent.UUID);
      } else {
        graph.link(child.UUID, child.parent.UUID);
      }
    }
  }

  private static String formatWithValue(AbstractGrammarNode rule) {
    if (rule instanceof EOFToken)
      return "EOF";

    if (rule instanceof Token)
      return ((Token) rule).getValue();

    return rule.toString();
  }
}
