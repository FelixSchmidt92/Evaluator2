package de.uni_due.s3.evaluator2.exceptions.parserruntime;

/**
 * This exception class signals, that the specified ExerciseVariable cannot be parsed for OpenMath
 * 
 * @author spobel
 *
 */
public class ErroneousFillInVariableRuntimeException extends RuntimeException {

	public ErroneousFillInVariableRuntimeException(Integer variableName) {
		super("FillInVariable with name " + variableName + " is erroneous.");
	}

	private static final long serialVersionUID = 1L;

}
