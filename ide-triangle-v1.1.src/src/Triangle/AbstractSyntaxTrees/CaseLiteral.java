/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.AbstractSyntaxTrees;

/**
 *
 * @author Andres
 * NO tiene que ser visitado porque IntegerLiteral y CharacterLiteral ya son 
 * visitados por defecto.
 */
public class CaseLiteral{
	public Object CaL; //Case-Literal

	public CaseLiteral(IntegerLiteral IL){
		CaL = IL;
	}

	public CaseLiteral(CharacterLiteral CL){
		CaL = CL;
	}
}
