package compiler.a5.hyrdrator;


import compiler.lexer.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;

/*
  (Tok: 10 lin= 1,1 str = "prog")
  (Tok: 11 lin= 1,6 str = "main")
  (Tok: 33 lin= 1,11 str = "{")
  (Tok: 3 lin= 14,15 str = "3" int= 3))
  (Tok: 4 lin= 5,48 str = "0.5" flo= 0.5)
 */
public final class A5Hydrator {
  private final String pattern =
    "\\(Tok: (\\d+) lin= (\\d+),(\\d+) str = \"([^\"]*)\"(?: (?:int|flo)= ([^)]+))?";
  private final Pattern lexer = Pattern.compile(pattern);
  private final Function<String, Token> tokenResolver;

  private A5Hydrator(Function<String, Token> tokenResolver) {
    this.tokenResolver = tokenResolver;
  }

  public List<Token> hydrate(String serializedTokenStream) {
    final var matcher = lexer.matcher(serializedTokenStream);
    final var tokens = new ArrayList<Token>();

    while (matcher.find()) {
      final var id = matcher.group(1);
      final var lineNumber = matcher.group(2);
      final var columnNumber = matcher.group(3);
      final var stringValue = matcher.group(4);
      final var numericValue = matcher.group(5);
      final var token = tokenResolver.apply(id);
    }
    return tokens;
  }


}
