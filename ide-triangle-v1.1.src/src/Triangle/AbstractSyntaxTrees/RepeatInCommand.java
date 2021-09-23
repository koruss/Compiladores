package Triangle.AbstractSyntaxTrees;

import Triangle.SyntacticAnalyzer.SourcePosition;

/**
 *
 * @author Andres
 */
public class RepeatInCommand extends Command{
    
    public InVarDecl inVar;
    public Command C;
    
    public RepeatInCommand(InVarDecl _inVar, Command _cmd, SourcePosition thePosition){
        super(thePosition);
        inVar = _inVar;
        C = _cmd;
    }
    
    public Object visit(Visitor v, Object o) {
        return v.visitRepeatInCommand(this, o);
    }
}
