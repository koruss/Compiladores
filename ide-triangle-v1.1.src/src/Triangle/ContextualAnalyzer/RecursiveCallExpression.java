/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.Visitor;

/**
 *
 * @author Andres
 */

public class RecursiveCallExpression extends RecursiveCall{

    private CallExpression callExpression;

    public RecursiveCallExpression(IdentificationTable p_recIdTable, CallExpression callExpression) {
        super(p_recIdTable);
        this.callExpression = callExpression;
    }

    @Override
    public void visitRecursiveCall(Visitor v, Object o) {
        callExpression.visit(v,o);
    }

    @Override
    public Identifier getProcFuncIdentifier() {
        return this.callExpression.I;
    }
	
}
