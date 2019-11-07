package compiler.parser;

import compiler.a5.grammar.GrammarNodeVisitor;
import compiler.lexer.token.*;
import compiler.lexer.token.SymbolToken.*;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;
import static compiler.lexer.token.OperatorToken.*;

public class ParseTreeToAbstractSyntaxTreeVisitor implements TokenVisitor, GrammarNodeVisitor {
  public void visit(KeywordToken.ProgramKeywordToken token) {

  }

  public void visit(Vargroup token) {

  }

  public void visit(Fcndefs token) {

  }

  public void visit(KeywordToken.MainKeywordToken token){

  }

  public void visit(Pgm token){

  }

  public void visit(Main token){

  }

  public void visit(BBlock token){

  }

  public void visit(RightBrace token){

  }

  public void visit(LeftBrace token){

  }

  public void visit(Stmts token){

  }

  @Override
  public void visit(LeftParen lhs) {
  }

  @Override
  public void visit(LessThan lhs) {
  }

  @Override
  public void visit(Lthan lhs) {
  }

  @Override
  public void visit(Oprel lhs) {

  }

  @Override
  public void visit(RightParen lhs) {
  }

  @Override
  public void visit(IntegerToken token) {
  }

  @Override
  public void visit(Plus token) {
  }

  @Override
  public void visit(Minus token) {
  }

  @Override
  public void visit(PPexpr ppexpr) {
    hoist(ppexpr);
  }

  @Override
  public void visit(ForwardSlash forwardSlash) {
    hoist(forwardSlash);
  }

  @Override
  public void visit(Asterisk asterisk) {
    hoist(asterisk);
  }

  @Override
  public void visit(Expr tree) {
    if (tree.children.size() == 2) {
      final var left = tree.children.pop();
      final var right = tree.children.peek();
      hoist(tree);
      left.parent = right;
      right.children.addFirst(left);
    }
  }

  @Override
  public void visit(IdentifierToken identifier) {
  }

  @Override
  public void visit(Fcnid identifier) {
  }

  @Override
  public void visit(PPexprs identifier) {
  }

  @Override
  public void visit(Fcall identifier) {
  }

  @Override
  public void visit(Expr_Tail tree) {
    hoist(tree);
  }

  @Override
  public void visit(BaseLiteral tree) {
  }

  @Override
  public void visit(Fact tree) {
  }

  @Override
  public void visit(Term term) {
    if (term.children.size() == 2) {
      final var lvalue = term.children.get(0);
      final var operator = term.children.get(1);
      term.children.remove(lvalue);
      lvalue.parent = operator;
      operator.children.addFirst(lvalue);
    }
    hoist(term);
  }

  @Override
  public void visit(Term_Tail tree) {
    if (tree.children.size() == 3) {
      final var tail = tree.children.get(2);
      final var tailLValue = tree.children.get(1);
      tailLValue.parent = tail;
      tail.children.addFirst(tailLValue);
      tree.children.remove(tailLValue);
    }
    hoist(tree);
  }

  @Override
  public void visit(Rterm tree) {
    if (tree.children.size() == 2) {
      final var operator = tree.children.get(1);
      final var lvalue = tree.children.get(0);
      lvalue.parent = operator;
      tree.children.remove(lvalue);
      operator.children.addFirst(lvalue);
    }
    hoist(tree);
  }

  @Override
  public void visit(Rterm_Tail tree) {
    if (tree.children.size() == 3) {
      final var tail = tree.children.get(2);
      final var tailLValue = tree.children.get(1);
      tailLValue.parent = tail;
      tail.children.addFirst(tailLValue);
      tree.children.remove(tailLValue);
    }
    hoist(tree);
  }

  @Override
  public void visit(EOFToken tree) {
  }

  @Override
  public void visit(Opadd tree) {
  }

  @Override
  public void visit(Opmul tree) {

  }

  private void hoist(AbstractGrammarNode tree) {
    for (var token : tree.children) {
      if (token instanceof Token) {
        final var parent = tree.parent;
        var tokenIndex = parent.children.indexOf(tree);

        // Replace token's parent
        token.parent = parent;

        // Replace rule with token
        parent.children.set(tokenIndex, token);

        // Remove token from rule's children
        tree.children.remove(token);

        // Set tree's children to point to token as new parent
        tree.children.forEach(child -> child.parent = token);

        // Add rule's children to the token;
        while (--tokenIndex != 0 && !tree.children.isEmpty())
          token.children.addFirst(tree.children.pop());

        while (!tree.children.isEmpty())
          token.children.addLast(tree.children.pop());

        return;
      }
    }
  }
}
