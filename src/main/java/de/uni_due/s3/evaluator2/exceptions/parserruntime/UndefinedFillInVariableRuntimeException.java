package de.uni_due.s3.evaluator2.exceptions.parserruntime;

/**
 * This exception class signals, that a specified FillInVariable does not exists
 * and can't be retrieved
 * 
 * @author frichtscheid
 *
 */
public class UndefinedFillInVariableRuntimeException extends RuntimeException {

	public UndefinedFillInVariableRuntimeException(String varposVal) {
		super("There is no FillIn variable at position " + varposVal);
	}

	private static final long serialVersionUID = 1L;

}
