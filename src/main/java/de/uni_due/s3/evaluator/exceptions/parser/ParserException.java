package de.uni_due.s3.evaluator.exceptions.parser;

import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;

public class ParserException extends EvaluatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1240445747209609498L;

	public ParserException(ParserRuntimeException e) {
		super(e.getMessage());
		// TODO Auto-generated constructor stub
	}

}
