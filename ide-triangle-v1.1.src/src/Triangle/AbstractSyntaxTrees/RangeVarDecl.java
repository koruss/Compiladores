// Class that functions as the range declaration in the for token.
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author Andres
 */
public class RangeVarDecl {
    public Identifier I;
    public Expression E;
    
    public RangeVarDecl(Identifier _ident, Expression _exp){
        I = _ident;
        E = _exp;
    }
}
