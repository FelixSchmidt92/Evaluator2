package de.uni_due.s3.evaluator.parser;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;

/**
 * This is the default ErrorHandler, if the Parser cannot match to  any Token or 
 * cannot recognize a Token completely.<p>
 * 
 * This Class is the ErrorHandler for the EvaluatorParser
 * 
 * @author dlux
 */
public class ParserErrorStrategy extends BailErrorStrategy{

	/**
	 * After a wrong Token-Parsing this method is called to recover from this state.
	 * Usually it should not be called, because Errors are thrown beforehand
	 * 
	 * @throws a ParserException
	 */
	@Override
	public void recover(Parser recognizer,  RecognitionException e){
		throw new ParserRuntimeException("RecoverException in ParserErrorStrategy", e);
	}
	
	/**
	 * This method is called by a NoViableAltException and should be catched 
	 * beforehand by sync
	 * 
	 * Examples, where this method is called:   ' / 2',  '(()))', ';', '?', '#'
	 * 
	 * @throws a ParserException
	 */
	@Override
	protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
		String msg = "Parsing-Error at Character: " + getTokenErrorDisplay(e.getOffendingToken()) 
				   + " and Position: " + e.getOffendingToken().getCharPositionInLine();
		throw new ParserRuntimeException(msg , e);
	}
	
	/**
	 * This method is called when the Parser tries to recover AFTER an unrecognizable Token
	 * 
	 * Examples, where this method is called: 'w', 'a', 
	 * 
	 * @throws a ParserException
	 */
	@Override
	public Token recoverInline(Parser recognizer) throws RecognitionException {
		throw new ParserRuntimeException("Could not recognize Tokens before Position: " + recognizer.getCurrentToken().getCharPositionInLine());
	}

	/**
	 * This method is called by every Token. Here it checks if the current Token 
	 * is the expected Token from Parser. If not an Exception is thrown.
	 * 
	 * Examples, where an Exception should be thrown: '3aa', '[var=a]b', '22.11gt'
	 * 
	 * @throws a ParserException
	 */
	@Override
	public void sync(Parser recognizer) {
		int typeNum = recognizer.getCurrentToken().getType(); // Type Number
		
		if(!recognizer.getExpectedTokens().contains(typeNum)){ // if not in Expected Tokens
			String msg = "Error at Token: '" + recognizer.getCurrentToken().getText()
					   + "', at Character Position: " + recognizer.getCurrentToken().getCharPositionInLine();
			throw new ParserRuntimeException(msg);
		}
	}
}
