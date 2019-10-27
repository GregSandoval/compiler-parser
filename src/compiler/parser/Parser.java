package compiler.parser;

import compiler.lexer.token.Token;

import java.util.LinkedList;

public class Parser {
  private GrammarRule startSymbol;

  public Parser(GrammarRule startSymbol) {
    this.startSymbol = startSymbol;
  }

  public void parse(LinkedList<Token> tokens) {
    final var stack = new LinkedList<AbstractGrammarRule>();
    stack.add(this.startSymbol);

    while (!tokens.isEmpty() && !stack.isEmpty()) {
      final Token token = tokens.peek();
      System.out.println("Current Token: " + token);
      final AbstractGrammarRule top = stack.pop();

      if (top instanceof Token) {
        if (token.getClass() == top.getClass()) {
          System.out.println("Applying Rule: Popping: " + top);
          System.out.println("Stack: " + stack);
          System.out.println();
          tokens.pop();
          continue;
        }

        System.out.println("Expected: " + top + " but found: " + token);
        System.out.println("Stack: " + stack);
        System.out.println();
        return;
      }

      if (!(top instanceof GrammarRule)) {
        System.out.println("Grammar contains rule not in Grammar hierarchy: " + top);
        System.out.println("Stack: " + stack);
        System.out.println();
        return;
      }

      final var rhs = ((GrammarRule) top).getRHS(token.getClass());

      if (rhs == null) {
        System.out.println("User error; Rule: " + top + " has no entry for " + token);
        System.out.println("Stack: " + stack);
        System.out.println();
        return;
      }

      System.out.println("Applying Rule: " + top + " -> " + rhs.toString());
      for (int i = rhs.size() - 1; i >= 0; i--) {
        stack.push(rhs.get(i));
      }
      System.out.println("Stack: " + stack);
      System.out.println();
    }

    if (tokens.isEmpty() && stack.isEmpty()) {
      System.out.println("Parsed input success!");
    } else {
      System.out.println("Parsed failure!");
    }
  }

}
