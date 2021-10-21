/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class InVarDecl extends Declaration{

	public Identifier I;
	public Expression E;

	public InVarDecl(Identifier _ident, Expression _exp, SourcePosition thisPosition) {
		super(thisPosition);
		I = _ident;
		E = _exp;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitInVarDecl(this, o);
	}
}
