package compiler.a5.grammar;

import compiler.lexer.token.*;
import compiler.parser.TokenVisitor;

import static compiler.parser.PstToAstHelpers.hoist;

public class PstToAstTokenVisitor implements TokenVisitor {

  @Override
  public void visit(SymbolToken.ForwardSlash forwardSlash) {
    hoist(forwardSlash);
  }

  @Override
  public void visit(OperatorToken.Asterisk asterisk) {
    hoist(asterisk);
  }

}
