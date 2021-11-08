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
public class VarDeclarationInferred extends VarDeclaration {

	public VarDeclarationInferred(Identifier iAST, Expression eAST,
			SourcePosition thePosition) {
		super(iAST, null, thePosition);
		E = eAST;
	}

	@Override
	public Object visit(Visitor v, Object o) {
		return v.visitVarDeclarationInferred(this, o);
	}
	public Expression E;
}
