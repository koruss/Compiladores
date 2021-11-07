/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.Visitor;

/**
 *
 * @author Andres
 */
public class RecursiveCallCommand extends RecursiveCall{
    private CallCommand callCommand;

    public RecursiveCallCommand(IdentificationTable p_recIdTable, CallCommand callCommand) {
        super(p_recIdTable);
        this.callCommand = callCommand;
    }

    @Override
    public void visitRecursiveCall(Visitor v, Object o) {
        callCommand.visit(v, o);
    }

    @Override
    public Identifier getProcFuncIdentifier() {
        return this.callCommand.I;
    }	
}
