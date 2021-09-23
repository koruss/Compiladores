package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;
/**
 *
 * @author Andres
 */
public class RepeatForRangeWhileCommand extends Command{
    public RangeVarDecl rangeVar;
    public Expression E1;
    public Command C;
    public Expression E2;
    
    public RepeatForRangeWhileCommand(RangeVarDecl _range, Expression _exp1, 
            Command _cmd, Expression _exp2, SourcePosition thePosition){
        super(thePosition);
        rangeVar = _range;
        E1 = _exp1;
        C = _cmd;
        E2 = _exp2;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitRepeatForRangeWhileCommand(this, o);
    }
}
