package de.uni_due.s3.evaluator.parser;

import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;


/**
 * This class is the default ErrorListener for the Lexer<p>
 * 
 * It is the ONLY ErrorListener for EvaluatorLexer and throws by syntaxError 
 * an ParserException.
 * 
 * @author dlux
 */
public class LexerErrorStrategy extends ConsoleErrorListener{

	/**
	 * Synatax Error caused by  not recognizable Characters
	 * 
	 * Example: '#1', '?[var=a]', '"', '&',
	 * 
	 * @throws a ParserException
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		throw new ParserRuntimeException("Error at Position: " + charPositionInLine + ", " + msg);
	}
}
