// Class that functions as the range declaration in the for token.
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class RangeVarDecl extends Declaration{

	public Identifier I;
	public Expression E;

	public RangeVarDecl(Identifier _ident, Expression _exp, SourcePosition thisPosition) {
		super(thisPosition);
		I = _ident;
		E = _exp;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitRangeVarDecl(this, o);
	}
}
