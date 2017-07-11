package de.uni_due.s3.evaluator.exceptions.parser;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedExerciseVariableRuntimeException;

public class UndefinedExerciseVariableException extends EvaluatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3163649617908472677L;

	public UndefinedExerciseVariableException(UndefinedExerciseVariableRuntimeException e) {
		super(e.getMessage());
		// TODO Auto-generated constructor stub
	}

}
