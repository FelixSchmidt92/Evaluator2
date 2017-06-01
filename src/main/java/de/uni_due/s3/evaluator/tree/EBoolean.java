package de.uni_due.s3.evaluator.tree;

/**
 * This Class EBoolean is a Terminal. 
 * 
 * It just inherits from EObject and implements the methods.
 * 
 * @UnderConstruction TODO implement abstract methods
 * @author dlux, frichtscheid, spobel
 */
public class EBoolean extends EObject{

	private boolean value;
	
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
	 * Returns the value of EBoolean
	 * 
	 * @return true or false as Boolean
	 */
	public boolean getValue(){
		return value;
	}
}
