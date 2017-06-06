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
	
	/**
	 * Constructs an EBoolean
	 * 
	 * @param value the value to be saved in EBoolean
	 */
	public EBoolean(boolean value) {
		this.value = value;
	}

	@Override
	protected String getPartialOpenMathXML() {
		return this.toString();
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
		return Boolean.toString(this.getValue());
	}

	/**
	 * Returns the value of EBoolean
	 * 
	 * @return true or false as Boolean
	 */
	public boolean getValue(){
		return this.value;
	}
}
