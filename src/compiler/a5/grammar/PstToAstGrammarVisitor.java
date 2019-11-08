package compiler.a5.grammar;

import static compiler.a5.grammar.A5GrammarNonTerminals.*;
import static compiler.parser.PstToAstHelpers.hoist;

public class PstToAstGrammarVisitor implements GrammarNodeVisitor {

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
  public void visit(Expr_Tail tree) {
    hoist(tree);
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
  public void visit(PPexpr ppexpr) {
    hoist(ppexpr);
  }

}
