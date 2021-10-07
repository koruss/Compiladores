/*
 * @(#)Scanner.java                        2.1 2003/10/07
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
package Triangle.SyntacticAnalyzer;

import Triangle.HTMLGenerator.HTMLWriter;

public final class Scanner {

	private SourceFile sourceFile;
	private boolean debug;

	private char currentChar;
	private StringBuffer currentSpelling;
	private boolean currentlyScanningToken;

	//Extension to support HTML file generation.
	private String cmntLine = ""; // Line in which the comment is being stored
	private HTMLWriter html;

	private boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	private boolean isDigit(char c) {
		return (c >= '0' && c <= '9');
	}

// isOperator returns true iff the given character is an operator character.
	private boolean isOperator(char c) {
		return (c == '+' || c == '-' || c == '*' || c == '/'
			|| c == '=' || c == '<' || c == '>' || c == '\\'
			|| c == '&' || c == '@' || c == '%' || c == '^'
			|| c == '?');
	}

///////////////////////////////////////////////////////////////////////////////
	public Scanner(SourceFile source) {
		sourceFile = source;
		currentChar = sourceFile.getSource();
		html = new HTMLWriter();
		debug = false;
	}

	//Extension to support HTML file generation.
	public Scanner(SourceFile source, String _fileSrc) {
		sourceFile = source;
		html = new HTMLWriter(_fileSrc);
		currentChar = sourceFile.getSource();
		debug = false;
	}

	public void enableDebugging() {
		debug = true;
	}

	// takeIt appends the current character to the current token, and gets
	// the next character from the source program.
	private void takeIt() {
		if (currentlyScanningToken) {
			currentSpelling.append(currentChar);
		}
		currentChar = sourceFile.getSource();
	}

	// scanSeparator skips a single separator.
	private void scanSeparator() {
		switch (currentChar) {
			case '!': {
				takeIt();
				while ((currentChar != SourceFile.EOL) && (currentChar != SourceFile.EOT)) {
					cmntLine += currentChar;
					takeIt();
				}
				if (currentChar == SourceFile.EOL) {
					cmntLine += currentChar;
					takeIt();
				}
				html.writeComment(cmntLine);
			}
			break;

			case ' ':
			case '\n':
			case '\r':
			case '\t':
				html.writeSpacingChar(currentChar);
				takeIt();
				break;
		}
	}

	private int scanToken() {

		switch (currentChar) {

			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'g':
			case 'h':
			case 'i':
			case 'j':
			case 'k':
			case 'l':
			case 'm':
			case 'n':
			case 'o':
			case 'p':
			case 'q':
			case 'r':
			case 's':
			case 't':
			case 'u':
			case 'v':
			case 'w':
			case 'x':
			case 'y':
			case 'z':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'E':
			case 'F':
			case 'G':
			case 'H':
			case 'I':
			case 'J':
			case 'K':
			case 'L':
			case 'M':
			case 'N':
			case 'O':
			case 'P':
			case 'Q':
			case 'R':
			case 'S':
			case 'T':
			case 'U':
			case 'V':
			case 'W':
			case 'X':
			case 'Y':
			case 'Z':
				takeIt();
				while (isLetter(currentChar) || isDigit(currentChar)) {
					takeIt();
				}
				return Token.IDENTIFIER;

			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				takeIt();
				while (isDigit(currentChar)) {
					takeIt();
				}
				return Token.INTLITERAL;

			case '+':
			case '-':
			case '*':
			case '/':
			case '=':
			case '<':
			case '>':
			case '\\':
			case '&':
			case '@':
			case '%':
			case '^':
			case '?':
				takeIt();
				while (isOperator(currentChar)) {
					takeIt();
				}
				return Token.OPERATOR;

			case '\'':
				takeIt();
				takeIt(); // the quoted character
				if (currentChar == '\'') {
					takeIt();
					return Token.CHARLITERAL;
				} else {
					return Token.ERROR;
				}

			case '.':
				takeIt();
				if(currentChar == '.'){
					takeIt();
					return Token.DOUBLE_DOT;
				}
				return Token.DOT;


			case '|':
				takeIt();
				return Token.VERTICAL_BAR;

			case ':':
				takeIt();
				if (currentChar == '=') {
					takeIt();
					return Token.BECOMES;
				} else {
					return Token.COLON;
				}

			case ';':
				takeIt();
				return Token.SEMICOLON;

			case ',':
				takeIt();
				return Token.COMMA;

			case '~':
				takeIt();
				return Token.IS;

			case '(':
				takeIt();
				return Token.LPAREN;

			case ')':
				takeIt();
				return Token.RPAREN;

			case '[':
				takeIt();
				return Token.LBRACKET;

			case ']':
				takeIt();
				return Token.RBRACKET;

			case '{':
				takeIt();
				return Token.LCURLY;

			case '}':
				takeIt();
				return Token.RCURLY;

			case SourceFile.EOT:
				return Token.EOT;

			default:
				takeIt();
				return Token.ERROR;
		}
	}

	public void finishScan(){
		html.writeFile();
	}

	public Token scan() {
		Token tok;
		SourcePosition pos;
		int kind;

		currentlyScanningToken = false;
		while (currentChar == '!'
			|| currentChar == ' '
			|| currentChar == '\n'
			|| currentChar == '\r'
			|| currentChar == '\t') {
			scanSeparator();
		}

		currentlyScanningToken = true;
		currentSpelling = new StringBuffer("");
		pos = new SourcePosition();
		pos.start = sourceFile.getCurrentLine();

		kind = scanToken();

		pos.finish = sourceFile.getCurrentLine();
		tok = new Token(kind, currentSpelling.toString(), pos);

		//Extension to support HTML file generation.
		if (tok.kind >= 4 && tok.kind <= 30) {
			html.writeReservedWord(tok.spelling);
		} else if (tok.kind <= 1) {
			html.writeLiteral(tok.spelling);
		} else {
			html.writeWord(tok.spelling);
		}

		if (debug) {
			System.out.println(tok);
		}
		return tok;
	}

}
