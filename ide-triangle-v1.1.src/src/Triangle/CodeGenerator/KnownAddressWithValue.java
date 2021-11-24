/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.CodeGenerator;

/**
 *
 * @author Andres
 */
public class KnownAddressWithValue extends KnownAddress {

	public KnownAddressWithValue() {
		super();

	}

	public KnownAddressWithValue(int size, int level, int displacement, int value) {
		super(size, level, displacement);
		this.value = value;
	}

	public int value;

}
