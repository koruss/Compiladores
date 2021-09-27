package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 * 
 * Implementaciones posibles:
 * - Objects para los Case-Literals
 * - como 6-8 constructores
 * - Hacer una clase llamada CaseLiteral y meter todo ahí. Se usó esta.
 */
public class Case extends AST {

    public Command C;
    public CaseLiteral CaL1, CaL2;

	public Case(Command _cmd, CaseLiteral _cal1, SourcePosition thePosition) {
		super(thePosition);
		C = _cmd;
		CaL1 = _cal1;
		CaL2 = null;
	}

	public Case(Command _cmd, CaseLiteral _cal1, CaseLiteral _cal2,
		SourcePosition thePosition) {
		super(thePosition);
		C = _cmd;
		CaL1 = _cal1;
		CaL2 = _cal2;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitCase(this, o);
	}

}
