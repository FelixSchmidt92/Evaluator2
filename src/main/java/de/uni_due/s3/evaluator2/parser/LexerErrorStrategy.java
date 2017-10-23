package de.uni_due.s3.evaluator2.parser;

import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import de.uni_due.s3.evaluator2.exceptions.parserruntime.ParserRuntimeException;

/**
 * This class is the default ErrorListener for the Lexer
 * <p>
 * 
 * It is the ONLY ErrorListener for EvaluatorLexer and throws by syntaxError an
 * ParserException.
 * 
 * @author dlux
 */
public class LexerErrorStrategy extends ConsoleErrorListener {

	/**
	 * Syntax Error caused by not recognizable Characters
	 * 
	 * Example: '#1', '?[var=a]', '"', '&',
	 * 
	 * @throws a ParserRuntimeException
	 */
	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		/*
		 * NOTE: In Antlr4.7 exists an Bug, where a UTF-8 NUL-Element is given in msg.
		 * Replacing it here with ' ' The Evaluator (or Java) can handle this
		 * NUL-Element but adding it to a Database or something else can be dangerous.
		 * So replace this NUL here. (Work Around)
		 */
		String correctDisplayMessage = msg.replaceAll("\0", " ");
		throw new ParserRuntimeException("Error at Position: " + charPositionInLine + ", " + correctDisplayMessage);
	}
}
