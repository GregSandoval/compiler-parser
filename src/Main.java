import compiler.a5.grammar.A5GrammarNonTerminals;
import compiler.a5.grammar.A5GrammarRules;
import compiler.a5.lexicon.A5LexiconDFA;
import compiler.lexer.LexerBuilder;
import compiler.parser.AbstractGrammarNode;
import compiler.parser.AbstractSyntaxTreeBuilder;
import compiler.parser.GrammarNode;
import compiler.parser.ParseTreeBuilder;
import visualization.TreeVisualizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) throws Exception {
    final var settings = processArgs(args);

    final var tokens = new LexerBuilder()
      .setStartState(A5LexiconDFA.START)
      .createLexer()
      .analyze(settings.inputText);

    A5GrammarRules.build();

    final var tree = new ParseTreeBuilder()
      .setStartSymbol(new A5GrammarNonTerminals.Pgm())
      .build(tokens);

    TreeVisualizer.toImage(tree, settings.pstOut);
    AbstractSyntaxTreeBuilder.fromParseTree(tree);
    TreeVisualizer.toImage(tree, settings.astOut);


    System.out.println();
    validateAST(tree);
  }

  private static ParserSettings processArgs(String[] args) throws IOException {
    final var settings = new ParserSettings();
    for (final var arg : args) {
      final var split = arg.split("=");

      if (split.length != 2)
        continue;

      final var key = split[0];
      final var value = split[1];
      switch (key) {
        case "--pst-name" -> settings.pstOut = value;
        case "--ast-name" -> settings.astOut = value;
        case "--file" -> settings.inputText = Files.readString(Path.of(value));
      }
    }

    if (settings.inputText == null) {
      try (final var scanner = new Scanner(System.in).useDelimiter(Pattern.compile("$"))) {
        settings.inputText = scanner.hasNext() ? scanner.next() : "";
      }
    }
    return settings;
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

  private static class ParserSettings {
    private String pstOut = "pst";
    private String astOut = "ast";
    private String inputText = null;
  }
}
