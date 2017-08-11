package de.uni_due.s3.evaluator2.exceptions.representation;

/*
 * Will be thrown when there is no OpenMath representation for Sage
 */
public class NoSageRepresentationAvailableException extends NoRepresentationAvailableException{

	public NoSageRepresentationAvailableException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}
