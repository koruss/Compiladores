/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.Visitor;

/**
 *
 * @author Andres
 */
public abstract class RecursiveCall {

	private IdentificationTable recIdTable;

	private int level;

	public RecursiveCall(IdentificationTable p_recIdTable) {
		setRecIdTable(p_recIdTable);
		this.level = p_recIdTable.getLevel();
	}

	public abstract void visitRecursiveCall(Visitor v, Object o);

	public abstract Identifier getProcFuncIdentifier();

	// Getters & Setters.
	public IdentificationTable getRecIdTable() {
		return recIdTable;
	}

	public void setRecIdTable(IdentificationTable recIdTable) {
		this.recIdTable = recIdTable;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
