package de.uni_due.s3.evaluator.tree;

import de.uni_due.s3.evaluator.tree.EObject;

/**
 * This Class EInteger is a Terminal. 
 * 
 * It just inherits from EObject and implements the methods.
 * 
 * @UnderConstruction TODO implement abstract methods
 * @author dlux, frichtscheid, spobel
 */
public class EString extends EObject{

	private String value;

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
	 * Returns the value of EString
	 * 
	 * @return a String
	 */
	public String getValue() {
		return value;
	}
}
