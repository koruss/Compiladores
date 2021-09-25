package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class VarExpDeclaration extends Declaration{

	public Identifier I;
	public Expression E;

	public VarExpDeclaration(Identifier _ident, Expression _exp, SourcePosition thisPosition){
		super(thisPosition);
		I = _ident;
		E = _exp;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitVarExpDeclaration(this, o);
	}
	
}
