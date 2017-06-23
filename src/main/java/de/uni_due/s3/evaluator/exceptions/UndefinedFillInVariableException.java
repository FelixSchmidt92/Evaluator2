package de.uni_due.s3.evaluator.exceptions;

/**
 * This exception class signals, that a specified FillInVariable does not exists and can't be retrieved
 * 
 * @author frichtscheid
 *
 */
public class UndefinedFillInVariableException extends ParserException{

	public UndefinedFillInVariableException(int number) {
		super("There is no FillIn variable at position "+number);
		// TODO Auto-generated constructor stub
	}


	private static final long serialVersionUID = 1L;

}
