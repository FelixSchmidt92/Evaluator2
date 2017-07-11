package de.uni_due.s3.evaluator.exceptions.parser;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.UndefinedFillInVariableRuntimeException;

public class UndefinedFillInVariableException extends EvaluatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3523089020334909078L;

	public UndefinedFillInVariableException(UndefinedFillInVariableRuntimeException e) {
		super(e.getMessage());
		// TODO Auto-generated constructor stub
	}

}
