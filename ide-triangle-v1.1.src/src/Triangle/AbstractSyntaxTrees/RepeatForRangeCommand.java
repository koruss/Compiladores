package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class RepeatForRangeCommand extends Command {

	public RangeVarDecl rangeVar; // The range declaration required to iterate.
	public Expression E; // This is the expression that halts the entire loop command.
	public Command C; // The standard command contained in the loop.

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
