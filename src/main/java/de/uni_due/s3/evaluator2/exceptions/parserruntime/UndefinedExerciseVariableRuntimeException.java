package de.uni_due.s3.evaluator2.exceptions.parserruntime;

/**
 * This exception class signals, that the specified ExerciseVariable does not
 * exists and can't be retrieved.
 * 
 * @author frichtscheid
 *
 */
public class UndefinedExerciseVariableRuntimeException extends RuntimeException {

	public UndefinedExerciseVariableRuntimeException(String variableName) {
		super("There is no exerciseVariable with name " + variableName);
	}

	private static final long serialVersionUID = 1L;

}
