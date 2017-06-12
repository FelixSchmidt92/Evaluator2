package de.uni_due.s3.evaluator.parser;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import de.uni_due.s3.evaluator.exceptions.ParserException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;

/**
 * This class contains only one Function 'parse' to parse the expression to the 
 * """TODO OMOBJ"""-Tree.
 * 
 * Use this class instead of the EvaluatorLexar and EvaluatorParser for parsing expressions.
 * This method also detects Errors (with LexarErrorStrategy and ParserErrorStrategy) which the 
 * parser cannot detect by itself (because it is not that strict).
 * 
 * @author dlux
 */
public class ExpressionParser {

	/**
	 * This Function returns the """TODO OMOBJ"""-Structure of the given String.
	 * The Tree returned from this function is not evaluated only build by the parser.
	 * 
	 *  
	 * @param expression the String which is in 'Evaluator-Language'.
	 * @return an """TODO OMOBJ"""-Tree from the given String.
	 * @throws ParserException if the given String is null or not parsable.
	 * 
	 * @UnderConstruction  FIXME dlux returns an OMOBJ as Object or an OMA, OMI, OMV??
	 */
	public static Object parse(String expression) {
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
		return new ExpressionToEvaluatorOpenMathVisitor().visit(tree);
	}
}
