package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class RepeatForRangeCommand extends Command {

	public RangeVarDecl rangeVar;
	public Expression E;
	public Command C;

	public RepeatForRangeCommand(RangeVarDecl _range, Expression _exp, Command _cmd, SourcePosition thePosition) {
		super(thePosition);
		rangeVar = _range;
		E = _exp;
		C = _cmd;
	}

	public Object visit(Visitor v, Object o) {
		return v.visitRepeatForRangeCommand(this, o);
	}
}
