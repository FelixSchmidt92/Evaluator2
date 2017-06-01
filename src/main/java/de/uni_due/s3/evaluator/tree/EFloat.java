package de.uni_due.s3.evaluator.tree;

/**
 * This Class EFloat is a Terminal. 
 * 
 * It just inherits from EObject and implements the methods.
 * 
 * @UnderConstruction TODO implement abstract methods
 * @author dlux, frichtscheid, spobel
 */
public class EFloat extends EObject {

	private double value;

	
	/**
	 * Constructs an EFloat
	 * 
	 * @param value the value which will be saved in EFLoat
	 */
	public EFloat(double value) {
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
	 * Returns the value of EFloat
	 * 
	 * @return a Double Value
	 */
	public double getValue() {
		return value;
	}
}
