package de.uni_due.s3.evaluator.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * This class contains only one Function 'parse' to parse the expression to the 
 * 
 * Use this class instead of the EvaluatorLexar and EvaluatorParser for parsing expressions.
 * This method also detects Errors (with LexarErrorStrategy and ParserErrorStrategy) which the 
 * parser cannot detect by itself (because it is not that strict).
 * 
 * @author dlux
 */
public class ExpressionParser {

	/**
	 * This Function returns the OpenMath-Tree-Structure of the given String.
	 * The Tree returned from this function is NOT evaluated, only build by the parser.
	 * 
	 *  
	 * @param expression the String which is in 'Evaluator-Language'.
	 * @return a standardized OpenMath-Tree as in JAXB from the given String.
	 * @throws UndefinedFillInVariableException 
	 * @throws UndefinedExerciseVariableException 
	 * @throws ParserException 
	 * @throws FunctionNotImplementedException 
	 * @throws ParserRuntimeException if the given String is null or not parsable.
	 */
	public static OMOBJ parse(String expression, HashMap<String,OMOBJ> exerciseVariableMap, HashMap<Integer, OMOBJ> fillInVariableMap) throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, FunctionNotImplementedException {
		try {
			ParseTree tree = createParseTree(expression);
			
			// Putting the parsed OpenMath-Tree in OMOBJ and visit OMVisitor for Evaluation
			Object omobjElementsTree = new ExpressionToOpenMathVisitor(exerciseVariableMap,fillInVariableMap).visit(tree);
			
			// Convert Tree to OMOBJ, evaluate it and again convert the evaluated
			// tree to OMOBJ
			return convertToOmobj(omobjElementsTree);
		} catch (UndefinedFillInVariableRuntimeException e) {
			throw new UndefinedFillInVariableException(e.getMessage());
		} catch (UndefinedExerciseVariableRuntimeException e) {
			throw new UndefinedExerciseVariableException(e.getMessage());
		} catch (ParserRuntimeException e) {
			throw new ParserException(e.getMessage());
		} catch (FunctionNotImplementedRuntimeException e) {
			throw new FunctionNotImplementedException(e.getMessage());
		}
		
	}
	
	/**
	 * This Function generates a ParseTree from the given String expression.
	 * 
	 * This Tree is visit-able with the ExpressionToOpenMathVisitor.
	 * 
	 *  (it is currently only used by ExpressionToOpenMathVisitor 
	 *   for visitText(especially for Expressions in text))
	 *   
	 * @param expression the String which is in 'Evaluator-Language'.
	 * @return the ParseTree, which is generated form the ParseTree 
	 */
	protected static ParseTree createParseTree(String expression){
		if (expression == null){
			// empty String passed to this function
			throw new ParserRuntimeException("Expression passed to this Parser is null!");
		}
		
		
		Reader input = new StringReader(expression);
		CharStream cstream = null;
		try {
			cstream = CharStreams.fromReader(input);
		} catch (IOException e) {
			//Some weird String given to the Reader 
			String cause = "IOException thrown by Reader creating an CharStream."
					+ "The expression passed to the Reader: " + expression;
			throw new ParserRuntimeException(cause, e); 
		}
		
		
		EvaluatorLexer evaluatorLexer = new EvaluatorLexer(cstream);
		
		//set default ErrorListener
		evaluatorLexer.removeErrorListeners(); 
		evaluatorLexer.addErrorListener(new LexerErrorStrategy());
		
		CommonTokenStream commonTokenStream = new CommonTokenStream(evaluatorLexer);
		EvaluatorParser evaluatorParser = new EvaluatorParser(commonTokenStream);
		
		//set default ErrorStrategy
		evaluatorParser.setErrorHandler(new ParserErrorStrategy()); 
		
		return evaluatorParser.expression();
	}
	
	
	/**
	 * This method just gets an unknown OpenMathObject and wraps it into an OMOBJ.
	 * Only add Objects which an OMOBJ can store in here otherwise null will be returned.
	 * 
	 * @param omElement The Object which should be wrapped into OMOBJ (has to be one of the JAXB-Classes)
	 * @return an OMOBJ containing the omElement at the correct spot
	 */
	private static OMOBJ convertToOmobj(Object omElement){
		OMOBJ omobj = new OMOBJ();
		
		switch (omElement.getClass().getSimpleName()){
		case "OMF":
			omobj.setOMF((OMF) omElement);
			break;
			
		case "OMI":
			omobj.setOMI((OMI) omElement);
			break;
			
		case "OMS":
			omobj.setOMS((OMS) omElement);
			break;
			
		case "OMSTR":
			omobj.setOMSTR((OMSTR) omElement);
			break;
			
		case "OMV":
			omobj.setOMV((OMV) omElement);
			break;
			
		case "OMA":
			omobj.setOMA((OMA) omElement);
			break;
			
		default :
			omobj = null;
		}
		return omobj;
	}
}
