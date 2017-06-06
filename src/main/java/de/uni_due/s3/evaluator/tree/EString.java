package de.uni_due.s3.evaluator.tree;

import de.uni_due.s3.evaluator.tree.EObject;

/**
 * This Class EString is a Terminal. 
 * 
 * It just inherits from EObject and implements the methods.
 * 
 * @UnderConstruction TODO implement abstract methods
 * @author dlux, frichtscheid, spobel
 */
public class EString extends EObject{

	private String value;

	/**
	 * Construct an EString
	 * 
	 * @param value the String value, which will be saved
	 */
	public EString(String value) {
		this.value = value;
	}

	@Override
	protected String getPartialOpenMathXML() {
		return "<OMSTR>" + this.toString() + "</OMSTR>";
	}
	
	@Override
	public String getSageSyntax() {
		return this.toString();
	}

	@Override
	public String getRSyntax() {
		return this.toString();
	}

	@Override
	public String getSymjaSyntax() {
		return this.toString();
	}

	@Override
	public String toString() {
		return this.getValue();
	}
	
	
	/**
	 * Returns the value of EString
	 * 
	 * @return a String
	 */
	public String getValue() {
		return this.value;
	}
}
