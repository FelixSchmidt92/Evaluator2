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

public class ExpressionParser {

	public static Object parse(String expression) {
		Reader input = new StringReader(expression);
		CharStream cstream = null;
		try {
			cstream = CharStreams.fromReader(input);
		} catch (IOException e) {
			throw new ParserException("IOException at CharStream", e); //TODO dlux Exception verfeinern
		}
		EvaluatorLexer evaluatorLexer = new EvaluatorLexer(cstream);
		
		evaluatorLexer.removeErrorListeners(); 	//default ErrorListener
		evaluatorLexer.addErrorListener(new LexerErrorStrategy());
		
		CommonTokenStream commonTokenStream = new CommonTokenStream(evaluatorLexer);
		EvaluatorParser evaluatorParser = new EvaluatorParser(commonTokenStream);
		
		evaluatorParser.setErrorHandler(new ParserErrorStrategy()); // default ErrorStrategy
		
		ParseTree tree = evaluatorParser.expression();
		ExpressionToEvaluatorOpenMathVisitor visitor = new ExpressionToEvaluatorOpenMathVisitor();
		return visitor.visit(tree);
	}
}
