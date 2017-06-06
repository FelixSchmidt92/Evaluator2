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
		return "<OMF>" + this.toString() + "</OMF>";
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
		return Double.toString(this.getValue());
	}

	/**
	 * Returns the value of EFloat
	 * 
	 * @return a Double Value
	 */
	public double getValue() {
		return this.value;
	}
}
