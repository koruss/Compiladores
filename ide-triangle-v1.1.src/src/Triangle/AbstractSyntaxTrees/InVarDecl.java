/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author Andres
 */
public class InVarDecl {
    public Identifier I;
    public Expression E;
    
    public InVarDecl(Identifier _ident, Expression _exp){
        I = _ident;
        E = _exp;
    }
}
