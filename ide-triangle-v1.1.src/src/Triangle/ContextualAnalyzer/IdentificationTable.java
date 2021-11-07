/*
 * @(#)IdentificationTable.java                2.1 2003/10/07
 *
 * Copyright (C) 1999, 2003 D.A. Watt and D.F. Brown
 * Dept. of Computing Science, University of Glasgow, Glasgow G12 8QQ Scotland
 * and School of Computer and Math Sciences, The Robert Gordon University,
 * St. Andrew Street, Aberdeen AB25 1HG, Scotland.
 * All rights reserved.
 *
 * This software is provided free for educational use only. It may
 * not be used for commercial purposes without the prior written permission
 * of the authors.
 */
package Triangle.ContextualAnalyzer;

import Triangle.AbstractSyntaxTrees.Declaration;
import Triangle.AbstractSyntaxTrees.Identifier;
import java.util.ArrayList;

public final class IdentificationTable {

	private int level;
	private int recursiveDepth;
	private IdEntry latest;
	public ArrayList<RecursiveCall> recursiveCalls;
	public ArrayList<FutureCallExpression> futureCallExpressions;

	public IdentificationTable() {
		level = recursiveDepth = 0;
		latest = null;
		recursiveCalls = new ArrayList<>();
		futureCallExpressions = new ArrayList<>();
	}

	public IdentificationTable(IdentificationTable oldIdTable) {
		this.level = oldIdTable.level;
		this.latest = oldIdTable.latest;
		this.futureCallExpressions = oldIdTable.futureCallExpressions;
		//this.recLevel = oldIdTable.recLevel;
		//this.pendingCalls = oldIdTable.pendingCalls;
	}

	// Opens a new level in the identification table, 1 higher than the
	// current topmost level.
	public void openScope() {
		level++;
	}

	// Closes the topmost level in the identification table, discarding
	// all entries belonging to that level.
	public void closeScope() {

		IdEntry entry, local;

		// Presumably, idTable.level > 0.
		entry = this.latest;
		while (entry.level == this.level) {
			local = entry;
			entry = local.previous;
		}
		this.level--;
		this.latest = entry;
	}

	// Closes a declaration's scope on top of another declaration's scope
	// This only happens inside a LocalDeclaration.
	public void closeLocalScope() {
		IdEntry entry = this.latest, local = entry, localEntry;

		// There are two declarations in this scope, local needs to be "rewound"
		// to the first declaration.
		while (entry.level == this.level) {
			local = entry;
			local.level = local.level - 2;
			entry = local.previous;
		}

		// Get all the entries that belong to the local variable.
		while (entry.level == this.level - 1) {
			localEntry = entry;
			entry = localEntry.previous;
		}
		local.previous = entry;

		//Finally, commit the scope level.
		this.level = level - 2;
	}

	// Adds a level to the recursion depth.
	public void openRecursiveScope() {
		recursiveDepth++;
	}

	// Removes a level from the recursion depth.
	public void closeRecursiveScope() {
		recursiveDepth--;
	}

	// Makes a new entry in the identification table for the given identifier
	// and attribute. The new entry belongs to the current level.
	// duplicated is set to to true iff there is already an entry for the
	// same identifier at the current level.
	public void enter(String id, Declaration attr) {

		IdEntry entry = this.latest;
		boolean present = false, searching = true;

		// Check for duplicate entry ...
		while (searching) {
			if (entry == null || entry.level < this.level) {
				searching = false;
			} else if (entry.id.equals(id)) {
				present = true;
				searching = false;
			} else {
				entry = entry.previous;
			}
		}

		attr.duplicated = present;
		// Add new entry ...
		entry = new IdEntry(id, attr, this.level, this.latest);
		this.latest = entry;
	}

	// Finds an entry for the given identifier in the identification table,
	// if any. If there are several entries for that identifier, finds the
	// entry at the highest level, in accordance with the scope rules.
	// Returns null iff no entry is found.
	// otherwise returns the attribute field of the entry found.
	public Declaration retrieve(String id) {

		IdEntry entry;
		Declaration attr = null;
		boolean present = false, searching = true;

		entry = this.latest;
		while (searching) {
			if (entry == null) {
				searching = false;
			} else if (entry.id.equals(id)) {
				present = true;
				searching = false;
				attr = entry.attr;
			} else {
				entry = entry.previous;
			}
		}

		return attr;
	}

	public void addRecursiveCall(RecursiveCall pendingCall) {
		recursiveCalls.add(pendingCall);
	}

	public void addFutureCallExp(FutureCallExpression ast) {
		this.futureCallExpressions.add(ast);
	}

	public ArrayList<RecursiveCall> checkRecursiveCalls(Identifier pfId) {

		ArrayList<RecursiveCall> toVisit = new ArrayList<>();
		//(When the program access this method,it does it in the level of the routine's body, so it's needed to subtract 1
		//to get the level of its declaration)
		int declLevel = level - 1;

		for (RecursiveCall c : recursiveCalls) //Check if the call's level is deeper than the level of the declaration.
		{
			if (c.getLevel() > declLevel && c.getProcFuncIdentifier().equals(pfId)) {
				toVisit.add(c);
			}
		}

		recursiveCalls.removeAll(toVisit);

		return toVisit;
	}

	//Getters and Setters
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getRecursiveDepth() {
		return recursiveDepth;
	}

	public void setRecursiveDepth(int recursiveDepth) {
		this.recursiveDepth = recursiveDepth;
	}

	public IdEntry getLatest() {
		return latest;
	}

	public void setLatest(IdEntry latest) {
		this.latest = latest;
	}

}
