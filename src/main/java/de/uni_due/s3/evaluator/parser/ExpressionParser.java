package de.uni_due.s3.evaluator.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator.core.OMVisitor;
import de.uni_due.s3.evaluator.exceptions.ParserException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMOBJ;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;
import de.uni_due.s3.openmath.OMV;

/**
 * This class contains only one Function 'parse' to parse the expression to the 
 * TODO OpenMath-Tree and evaluate this tree by using OMVisitor.
 * 
 * Use this class instead of the EvaluatorLexar and EvaluatorParser for parsing expressions.
 * This method also detects Errors (with LexarErrorStrategy and ParserErrorStrategy) which the 
 * parser cannot detect by itself (because it is not that strict).
 * 
 * @author dlux
 */
public class ExpressionParser {

	/**
	 * This Function returns the """TODO evaluated OpenMath-Tree"""-Structure of the given String.
	 * The Tree returned from this function is evaluated and build by the parser.
	 * 
	 *  
	 * @param expression the String which is in 'Evaluator-Language'.
	 * @return TODO an evaluated standardized OpenMath-Tree as in JAXB from the given String.
	 * @throws ParserException if the given String is null or not parsable.
	 * 
	 * @UnderConstruction  FIXME dlux returns an OMOBJ as Object or an OMA, OMI, OMV??
	 */
	public static OMOBJ parse(String expression,Map<String,OMOBJ> exerciseVariableMap, Map<Integer,OMOBJ> fillInVariableMap) {
		if (expression == null){
			// empty String passed to this function
			throw new ParserException("Expression passed to this Parser is null!");
		}
		
		
		Reader input = new StringReader(expression);
		CharStream cstream = null;
		try {
			cstream = CharStreams.fromReader(input);
		} catch (IOException e) {
			//Some weird String given to the Reader 
			String cause = "IOException thrown by Reader creating an CharStream."
					+ "The expression passed to the Reader: " + expression;
			throw new ParserException(cause, e); 
		}
		
		
		EvaluatorLexer evaluatorLexer = new EvaluatorLexer(cstream);
		
		//set default ErrorListener
		evaluatorLexer.removeErrorListeners(); 
		evaluatorLexer.addErrorListener(new LexerErrorStrategy());
		
		CommonTokenStream commonTokenStream = new CommonTokenStream(evaluatorLexer);
		EvaluatorParser evaluatorParser = new EvaluatorParser(commonTokenStream);
		
		//set default ErrorStrategy
		evaluatorParser.setErrorHandler(new ParserErrorStrategy()); 
		
		ParseTree tree = evaluatorParser.expression();
		
		// Putting the parsed OpenMath-Tree in OMOBJ and visit OMVisitor for Evaluation
		Object omobjElementsTree = new ExpressionToOpenMathVisitor(exerciseVariableMap,fillInVariableMap).visit(tree);

		
		// Convert Tree to OMOBJ, evaluate it and again convert the evaluated
		// tree to OMOBJ
		return convertToOmobj(omobjElementsTree);
	}
	
	/**
	 * This method just gets an unknown OpenMathObject and wraps it into an OMOBJ.
	 * Only add Objects which an OMOBJ can store in here otherwise null will be returned.
	 * 
	 * @param omElement The Object which should be wrapped into OMOBJ (has to be one of the JAXB-Classes)
	 * @return an OMOBJ containing the omOnknown at the correct spot
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
