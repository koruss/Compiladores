package Core.Visitors;

import Triangle.AbstractSyntaxTrees.Visitor;
import java.io.FileWriter;
import java.io.IOException;
import Triangle.AbstractSyntaxTrees.AnyTypeDenoter;
import Triangle.AbstractSyntaxTrees.ArrayExpression;
import Triangle.AbstractSyntaxTrees.ArrayTypeDenoter;
import Triangle.AbstractSyntaxTrees.AssignCommand;
import Triangle.AbstractSyntaxTrees.BinaryExpression;
import Triangle.AbstractSyntaxTrees.BinaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.BoolTypeDenoter;
import Triangle.AbstractSyntaxTrees.CallCommand;
import Triangle.AbstractSyntaxTrees.CallExpression;
import Triangle.AbstractSyntaxTrees.Case;
import Triangle.AbstractSyntaxTrees.CharTypeDenoter;
import Triangle.AbstractSyntaxTrees.CharacterExpression;
import Triangle.AbstractSyntaxTrees.CharacterLiteral;
import Triangle.AbstractSyntaxTrees.ConstActualParameter;
import Triangle.AbstractSyntaxTrees.ConstDeclaration;
import Triangle.AbstractSyntaxTrees.ConstFormalParameter;
import Triangle.AbstractSyntaxTrees.DoUntilCommand;
import Triangle.AbstractSyntaxTrees.DoWhileCommand;
import Triangle.AbstractSyntaxTrees.DotVname;
import Triangle.AbstractSyntaxTrees.EmptyActualParameterSequence;
import Triangle.AbstractSyntaxTrees.EmptyCommand;
import Triangle.AbstractSyntaxTrees.EmptyExpression;
import Triangle.AbstractSyntaxTrees.EmptyFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.ErrorTypeDenoter;
import Triangle.AbstractSyntaxTrees.FuncActualParameter;
import Triangle.AbstractSyntaxTrees.FuncDeclaration;
import Triangle.AbstractSyntaxTrees.FuncFormalParameter;
import Triangle.AbstractSyntaxTrees.Identifier;
import Triangle.AbstractSyntaxTrees.IfCommand;
import Triangle.AbstractSyntaxTrees.IfExpression;
import Triangle.AbstractSyntaxTrees.InVarDecl;
import Triangle.AbstractSyntaxTrees.IntTypeDenoter;
import Triangle.AbstractSyntaxTrees.IntegerExpression;
import Triangle.AbstractSyntaxTrees.IntegerLiteral;
import Triangle.AbstractSyntaxTrees.LetCommand;
import Triangle.AbstractSyntaxTrees.LetExpression;
import Triangle.AbstractSyntaxTrees.LocalDeclaration;
import Triangle.AbstractSyntaxTrees.MultipleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleArrayAggregate;
import Triangle.AbstractSyntaxTrees.MultipleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.MultipleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.MultipleProcDeclaration;
import Triangle.AbstractSyntaxTrees.MultipleRecordAggregate;
import Triangle.AbstractSyntaxTrees.Operator;
import Triangle.AbstractSyntaxTrees.ProcActualParameter;
import Triangle.AbstractSyntaxTrees.ProcDeclaration;
import Triangle.AbstractSyntaxTrees.ProcFormalParameter;
import Triangle.AbstractSyntaxTrees.Program;
import Triangle.AbstractSyntaxTrees.RangeVarDecl;
import Triangle.AbstractSyntaxTrees.RecordExpression;
import Triangle.AbstractSyntaxTrees.RecordTypeDenoter;
import Triangle.AbstractSyntaxTrees.RecursiveDeclaration;
import Triangle.AbstractSyntaxTrees.RepeatForRangeCommand;
import Triangle.AbstractSyntaxTrees.RepeatForRangeUntilCommand;
import Triangle.AbstractSyntaxTrees.RepeatForRangeWhileCommand;
import Triangle.AbstractSyntaxTrees.RepeatInCommand;
import Triangle.AbstractSyntaxTrees.SequentialCommand;
import Triangle.AbstractSyntaxTrees.SequentialDeclaration;
import Triangle.AbstractSyntaxTrees.SimpleTypeDenoter;
import Triangle.AbstractSyntaxTrees.SimpleVname;
import Triangle.AbstractSyntaxTrees.SingleActualParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleArrayAggregate;
import Triangle.AbstractSyntaxTrees.SingleFieldTypeDenoter;
import Triangle.AbstractSyntaxTrees.SingleFormalParameterSequence;
import Triangle.AbstractSyntaxTrees.SingleRecordAggregate;
import Triangle.AbstractSyntaxTrees.SubscriptVname;
import Triangle.AbstractSyntaxTrees.TypeDeclaration;
import Triangle.AbstractSyntaxTrees.UnaryExpression;
import Triangle.AbstractSyntaxTrees.UnaryOperatorDeclaration;
import Triangle.AbstractSyntaxTrees.UntilCommand;
import Triangle.AbstractSyntaxTrees.VarActualParameter;
import Triangle.AbstractSyntaxTrees.VarDeclaration;
import Triangle.AbstractSyntaxTrees.VarExpDeclaration;
import Triangle.AbstractSyntaxTrees.VarFormalParameter;
import Triangle.AbstractSyntaxTrees.Visitor;
import Triangle.AbstractSyntaxTrees.VnameExpression;
import Triangle.AbstractSyntaxTrees.WhileCommand;
/**
 *
 * @author Andres
 */
public class XMLVisitor implements Visitor{
	
	public String fileName, fileBuffer;
	public int indentationLevel = 0;

	public static String xmlOpener = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";

	public XMLVisitor(){
		fileBuffer = xmlOpener;
	}

	public XMLVisitor(String _fname){
		// Get the absolute filename without the ".tri", and change it to ".xml".
		fileName = (_fname.substring(0, _fname.length() - 4)) + ".xml";
		// Add the metadata and head elements to the html buffer.
		fileBuffer = xmlOpener;
	}

	public void writeFile(Program programAST) {
		try {
			// Write the file.
			programAST.visit(this, null);
			FileWriter xmlFile = new FileWriter(fileName);
			xmlFile.write(fileBuffer);
			xmlFile.close();
			// Clear the buffer.
			fileBuffer = "";
		} catch (IOException e) {
			System.err.println("ERROR: An error has occurred while creating the XML document file.");
			e.printStackTrace();
		}
	}

	public String getIndentation(){
		String msg = "";
		for(int i = 0; i<indentationLevel;i++)
			msg += "\t";	

		return msg;
	}

	public void writeToBuffer(String tag, String separator, Boolean isIndent){
		if(isIndent)
			fileBuffer += getIndentation() + tag + separator;
		else
			fileBuffer +=  tag + separator;
	}


	//Visitor methods.
	// <editor-fold defaultstate="collapsed" desc=" Commands ">
	// Commands
	public Object visitAssignCommand(AssignCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<AssignCommand>", "\n", true);
		ast.V.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</AssignCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitCallCommand(CallCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<CallCommand>", "\n", true);
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeToBuffer("</CallCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitEmptyCommand(EmptyCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<EmptyCommand/>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitIfCommand(IfCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<IfCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeToBuffer("</IfCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitLetCommand(LetCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<LetCommand>", "\n", true);
		ast.D.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</LetCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSequentialCommand(SequentialCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SequentialCommand>", "\n", true);
		ast.C1.visit(this, null);
		ast.C2.visit(this, null);
		writeToBuffer("</SequentialCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitWhileCommand(WhileCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<WhileCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</WhileCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitUntilCommand(UntilCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<UntilCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</UntilCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitDoWhileCommand(DoWhileCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<DoWhileCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</DoWhileCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitDoUntilCommand(DoUntilCommand ast, Object o) {
		indentationLevel++;
		writeToBuffer("<DoUntilCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</DoUntilCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitRepeatForRangeCommand(RepeatForRangeCommand ast, Object o){
		indentationLevel++;
		writeToBuffer("<RepeatForRangeCommand>", "\n", true);
		ast.E.visit(this, null);
		ast.C.visit(this, null);
		ast.rangeVar.visit(this, null);
		writeToBuffer("</RepeatForRangeCommand>", "\n", true);
		indentationLevel--;

		return (null);
		
	}

	public Object visitRepeatForRangeWhileCommand(RepeatForRangeWhileCommand ast, Object o){
		indentationLevel++;
		writeToBuffer("<RepeatForRangeWhileCommand>", "\n", true);
		ast.C.visit(this, null);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.rangeVar.visit(this, null);
		writeToBuffer("</RepeatForRangeWhileCommand>", "\n", true);
		indentationLevel--;

		return (null);
		
	}

	public Object visitRepeatForRangeUntilCommand(RepeatForRangeUntilCommand ast, Object o){
		indentationLevel++;
		writeToBuffer("<RepeatForRangeUntilCommand>", "\n", true);
		ast.C.visit(this, null);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.rangeVar.visit(this, null);
		writeToBuffer("</RepeatForRangeUntilCommand>", "\n", true);
		indentationLevel--;

		return (null);
		
	}

	public Object visitRepeatInCommand(RepeatInCommand ast, Object o){
		indentationLevel++;
		writeToBuffer("<RepeatInCommand>", "\n", true);
		ast.C.visit(this, null);
		ast.inVar.visit(this, null);
		writeToBuffer("</RepeatInCommand>", "\n", true);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Expressions ">
	// Expressions
	public Object visitArrayExpression(ArrayExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ArrayExpression>", "\n", true);
		ast.AA.visit(this, null);
		writeToBuffer("</ArrayExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitBinaryExpression(BinaryExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<BinaryExpression>", "\n", true);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.O.visit(this, null);
		writeToBuffer("</BinaryExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitCallExpression(CallExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<CallExpression>", "\n", true);
		ast.I.visit(this, null);
		ast.APS.visit(this, null);
		writeToBuffer("</CallExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitCharacterExpression(CharacterExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<CharacterExpression>", "\n", true);
		ast.CL.visit(this, null);
		writeToBuffer("</CharacterExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitEmptyExpression(EmptyExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<EmptyExpression/>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitIfExpression(IfExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<IfExpression>", "\n", true);
		ast.E1.visit(this, null);
		ast.E2.visit(this, null);
		ast.E3.visit(this, null);
		writeToBuffer("</IfExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitIntegerExpression(IntegerExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<IntegerExpression>", "\n", true);
		ast.IL.visit(this, null);
		writeToBuffer("</IntegerExpression>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitLetExpression(LetExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<LetExpression>", "\n", true);
		ast.D.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</LetExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitRecordExpression(RecordExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<RecordExpression>", "\n", true);
		ast.RA.visit(this, null);
		writeToBuffer("<RecordExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitUnaryExpression(UnaryExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<UnaryExpression>", "\n", true);
		ast.E.visit(this, null);
		ast.O.visit(this, null);
		writeToBuffer("</UnaryExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitVnameExpression(VnameExpression ast, Object o) {
		indentationLevel++;
		writeToBuffer("<VnameExpression>", "\n", true);
		ast.V.visit(this, null);
		writeToBuffer("</VnameExpression>", "\n", true);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Declarations ">
	// Declarations
	public Object visitBinaryOperatorDeclaration(BinaryOperatorDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<BinaryOperatorDeclaration>", "\n", true);
		ast.ARG1.visit(this, null);
		ast.ARG2.visit(this, null);
		ast.O.visit(this, null);
		ast.RES.visit(this, null);
		writeToBuffer("</BinaryOperatorDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitConstDeclaration(ConstDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ConstOperatorDeclaration>", "\n", true);
		ast.E.visit(this, null);
		ast.I.visit(this, null);
		writeToBuffer("</ConstOperatorDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitFuncDeclaration(FuncDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<FuncDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		ast.FPS.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</FuncDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitProcDeclaration(ProcDeclaration ast, Object o) {

		indentationLevel++;
		writeToBuffer("<ProcDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.C.visit(this, null);
		writeToBuffer("</ProcDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSequentialDeclaration(SequentialDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SequentialDeclaration>", "\n", true);
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeToBuffer("</SequentialDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitTypeDeclaration(TypeDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<TypeDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</TypeDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitUnaryOperatorDeclaration(UnaryOperatorDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<UnaryOperatorDeclaration>", "\n", true);
		ast.ARG.visit(this, null);
		ast.O.visit(this, null);
		ast.RES.visit(this, null);
		writeToBuffer("</UnaryOperatorDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitVarDeclaration(VarDeclaration ast, Object o) {
		indentationLevel++;
		writeToBuffer("<VarDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</VarDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitRangeVarDecl(RangeVarDecl ast, Object o){
		indentationLevel++;
		writeToBuffer("<RangeVarDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</RangeVarDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitInVarDecl(InVarDecl ast, Object o){
		indentationLevel++;
		writeToBuffer("<InVarDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</InVarDeclaration>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitVarExpDeclaration(VarExpDeclaration ast, Object o){
		indentationLevel++;
		writeToBuffer("<VarExpDeclaration>", "\n", true);
		ast.I.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</VarExpDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitLocalDeclaration(LocalDeclaration ast,  Object o){
		indentationLevel++;
		writeToBuffer("<LocalDeclaration>", "\n", true);
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeToBuffer("</LocalDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}
        
        public Object visitRecursiveDeclaration(RecursiveDeclaration ast,  Object o){
		indentationLevel++;
		writeToBuffer("<RecursiveDeclaration>", "\n", true);
		ast.D.visit(this, null);
		writeToBuffer("</RecursiveDeclaration>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitMultipleProcDeclaration(MultipleProcDeclaration ast, Object o){
		indentationLevel++;
		writeToBuffer("<MultipleProcDeclaration>", "\n", true);
		ast.D1.visit(this, null);
		ast.D2.visit(this, null);
		writeToBuffer("</MultipleProcDeclaration>", "\n", true);
		indentationLevel--;

		return (null);
	}


	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Cases ">
	// Cases
	public Object visitCase(Case ast, Object o){
		indentationLevel++;
		writeToBuffer("<Case>", "\n", true);
		ast.C.visit(this, null);
		writeToBuffer("</Case>", "\n", true);
		indentationLevel--;

		return (null);
	}

	// </editor-fold>
	
	// <editor-fold defaultstate="collapsed" desc=" Aggregates ">
	// Array Aggregates
	public Object visitMultipleArrayAggregate(MultipleArrayAggregate ast, Object o) {
		indentationLevel++;
		writeToBuffer("<MultipleArrayAggregate>", "\n", true);
		ast.AA.visit(this, null);
		ast.E.visit(this, null);
		writeToBuffer("</MultipleArrayAggregate>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSingleArrayAggregate(SingleArrayAggregate ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SingleArrayAggregate>", "\n", true);
		ast.E.visit(this, null);
		writeToBuffer("</SingleArrayAggregate>", "\n", true);
		indentationLevel--;

		return (null);
	}

	// Record Aggregates
	public Object visitMultipleRecordAggregate(MultipleRecordAggregate ast, Object o) {
		indentationLevel++;
		writeToBuffer("<MultipleRecordAggregate>", "\n", true);
		ast.E.visit(this, null);
		ast.I.visit(this, null);
		ast.RA.visit(this, null);
		writeToBuffer("</MultipleRecordAggregate>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSingleRecordAggregate(SingleRecordAggregate ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SingleRecordAggregate>", "\n", true);
		ast.E.visit(this, null);
		ast.I.visit(this, null);
		writeToBuffer("</SingleRecordAggregate>", "\n", true);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Parameters ">
	// Formal Parameters
	public Object visitConstFormalParameter(ConstFormalParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ConstFormalParameter>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</ConstFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitFuncFormalParameter(FuncFormalParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<FuncFormalParameter>", "\n", true);
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</FuncFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitProcFormalParameter(ProcFormalParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ProcFormalParameter>", "\n", true);
		ast.I.visit(this, null);
		ast.FPS.visit(this, null);
		writeToBuffer("<ProcFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitVarFormalParameter(VarFormalParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<VarFormalParameter>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</VarFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitEmptyFormalParameterSequence(EmptyFormalParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<EmptyFormalParameter/>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitMultipleFormalParameterSequence(MultipleFormalParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<MultipleFormalParameter>", "\n", true);
		ast.FP.visit(this, null);
		ast.FPS.visit(this, null);
		writeToBuffer("</MultipleFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSingleFormalParameterSequence(SingleFormalParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SingleFormalParameter>", "\n", true);
		ast.FP.visit(this, null);
		writeToBuffer("</SingleFormalParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	// Actual Parameters
	public Object visitConstActualParameter(ConstActualParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ConstActualParameter>", "\n", true);
		ast.E.visit(this, null);
		writeToBuffer("</ConstActualParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitFuncActualParameter(FuncActualParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<FuncActualParameter>", "\n", true);
		ast.I.visit(this, null);
		writeToBuffer("</FuncActualParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitProcActualParameter(ProcActualParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ProcActualParameter>", "\n", true);
		ast.I.visit(this, null);
		writeToBuffer("</ProcActualParameter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitVarActualParameter(VarActualParameter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<VarActualParameter>","\n", true);
		ast.V.visit(this, null);
		writeToBuffer("</VarActualParameter>","\n", true);
		indentationLevel--;
		

		return (null);
	}

	public Object visitEmptyActualParameterSequence(EmptyActualParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ProcActualParameterSequence/>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitMultipleActualParameterSequence(MultipleActualParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<MultipleActualParameterSequence>", "\n", true);
		ast.AP.visit(this, null);
		ast.APS.visit(this, null);
		writeToBuffer("</MultipleActualParameterSequence>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSingleActualParameterSequence(SingleActualParameterSequence ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SingleActualParameterSequence>", "\n", true);
		ast.AP.visit(this, null);
		writeToBuffer("</SingleActualParameterSequence>", "\n", true);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Type Denoters ">
	// Type Denoters
	public Object visitAnyTypeDenoter(AnyTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<AnyTypeDenoter/>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitArrayTypeDenoter(ArrayTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ArrayTypeDenoter>", "\n", true);
		ast.IL.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</ArrayTypeDenoter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitBoolTypeDenoter(BoolTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<BoolTypeDenoter>", "\n", true);
		ast.visit(this, null);
		writeToBuffer("</BoolTypeDenoter>", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitCharTypeDenoter(CharTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<CharTypeDenoter/>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitErrorTypeDenoter(ErrorTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<ErrorTypeDenoter/>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSimpleTypeDenoter(SimpleTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SimpleTypeDenoter>", "\n", true);
		ast.I.visit(this, null);
		writeToBuffer("</SimpleTypeDenoter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitIntTypeDenoter(IntTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<IntTypeDenoter/>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitRecordTypeDenoter(RecordTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<RecordTypeDenoter>", "\n", true);
		ast.FT.visit(this, null);
		writeToBuffer("</RecordTypeDenoter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitMultipleFieldTypeDenoter(MultipleFieldTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<MultipleFieldTypeDenoter>", "\n", true);
		ast.FT.visit(this, null);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</MultipleFieldTypeDenoter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSingleFieldTypeDenoter(SingleFieldTypeDenoter ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SingleFieldTypeDenoter>", "\n", true);
		ast.I.visit(this, null);
		ast.T.visit(this, null);
		writeToBuffer("</SingleFieldTypeDenoter>", "\n", true);
		indentationLevel--;

		return (null);
	}

	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Literals, Identifiers and Operators ">
	// Literals, Identifiers and Operators
	public Object visitCharacterLiteral(CharacterLiteral ast, Object o) {
		indentationLevel++;
		writeToBuffer("<CharacterLiteral>", "", true);
		writeToBuffer(ast.spelling, "", false);
		writeToBuffer("</CharacterLiteral>", "\n", false);
		indentationLevel--;
		return (null);
	}

	public Object visitIdentifier(Identifier ast, Object o) {
		indentationLevel++;
		writeToBuffer("<Identifier>", "", true);
		writeToBuffer(ast.spelling,"", false);
		writeToBuffer("</Identifier>", "\n", false);
		indentationLevel--;

		return (null);
	}

	public Object visitIntegerLiteral(IntegerLiteral ast, Object o) {
		indentationLevel++;
		writeToBuffer("<IntegerLiteral>", "", true);
		writeToBuffer(ast.spelling, "", false);
		writeToBuffer("</IntegerLiteral>", "\n", false);
		indentationLevel--;
		return (null);
	}

	public Object visitOperator(Operator ast, Object o) {
		indentationLevel++;
		writeToBuffer("<Operator>", "", true);
		writeToBuffer(ast.spelling, "", false);
		writeToBuffer("<Operator/>", "\n", false);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Values or Variable Names ">
	// Value-or-variable names
	public Object visitDotVname(DotVname ast, Object o) {
		indentationLevel++;
		writeToBuffer("<DotVname>", "\n", true);
		ast.I.visit(this, null);
		ast.V.visit(this, null);
		writeToBuffer("</DotVname>", "\n", true);
		indentationLevel--;

		return (null);
	}

	public Object visitSimpleVname(SimpleVname ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SimpleVname>", "\n", true);
		ast.I.visit(this, null);
		writeToBuffer("</SimpleVname", "\n", true);
		indentationLevel--;
		return (null);
	}

	public Object visitSubscriptVname(SubscriptVname ast, Object o) {
		indentationLevel++;
		writeToBuffer("<SubscriptVname>", "\n", true);
		ast.E.visit(this, null);
		ast.V.visit(this, null);
		writeToBuffer("</SubscriptVname>", "\n", true);
		indentationLevel--;

		return (null);
	}
	// </editor-fold>

	// <editor-fold defaultstate="collapsed" desc=" Program ">
	// Programs
	public Object visitProgram(Program ast, Object o) {
		writeToBuffer("<Program>", "\n", true);
		ast.C.visit(this, null);
		writeToBuffer("</Program>", "\n", true);

		return (null);
	}
	// </editor-fold>
}
