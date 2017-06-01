package de.uni_due.s3.evaluator.tree;

/**
 * This Class EInteger is a Terminal. 
 * 
 * It just inherits from EObject and implements the methods.
 * 
 * @UnderConstruction TODO implement abstract methods
 * @author dlux, frichtscheid, spobel
 */
public class EInteger extends EObject{

	private int value;

	/**
	 * Constructs an EInteger
	 * 
	 * @param value the value which should be saved in this EInteger
	 */
	public EInteger(int value) {
		this.value = value;
	}

	@Override
	protected String getPartialOpenMathXML() {
		return null;
	}
	
	@Override
	public String getSageSyntax() {
		return null;
	}

	@Override
	public String getRSyntax() {
		return null;
	}

	@Override
	public String getSymjaSyntax() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

	/**
	 * Returns the value of EInteger
	 * 
	 * @return an Integer 
	 */
	public int getValue() {
		return value;
	}
}
