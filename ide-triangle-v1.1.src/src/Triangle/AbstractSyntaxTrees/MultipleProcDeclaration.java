package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class MultipleProcDeclaration extends Declaration {

	public MultipleProcDeclaration(Declaration d1AST, Declaration d2AST,
		SourcePosition thePosition) {
		super(thePosition);
		D1 = d1AST;
		D2 = d2AST;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitMultipleProcDeclaration(this, o);
	}

	public Declaration D1, D2;
}
