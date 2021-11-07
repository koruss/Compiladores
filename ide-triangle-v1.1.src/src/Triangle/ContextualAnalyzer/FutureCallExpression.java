/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.Expression;
import Triangle.AbstractSyntaxTrees.TypeDenoter;

/**
 *
 * @author Andres
 */
public class FutureCallExpression {
    private TypeDenoter typeDenoterToCheck;
    private Expression E;

    public FutureCallExpression(TypeDenoter p_typeDenoter, Expression e) {
        this.typeDenoterToCheck = p_typeDenoter;
        E = e;
    }

    public TypeDenoter getTypeDenoterToCheck() {
        return typeDenoterToCheck;
    }

    public void setTypeDenoterToCheck(TypeDenoter typeDenoterToCheck) {
        this.typeDenoterToCheck = typeDenoterToCheck;
    }

    public Expression getE() {
        return E;
    }

    public void setE(Expression e) {
        E = e;
    }
}
