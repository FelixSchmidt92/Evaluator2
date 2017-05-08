package de.uni_due.s3.evaluator.openmath;

/**
 * Represents an OpenMathObject
 */
public abstract class OpenMathObject {

	/**
	 * Counts the number of applications of a specified symbol in this OpenMath tree.
	 * 
	 * @param cd
	 * 			The content dictionary
	 * @param name
	 * 			The name of the symbol
	 * @return the number of applications
	 */
	public int getNumberOfApplications(String cd, String name) {
		return 0;
	}

	/**
	 * Returns the number of nodes of this OpenMath tree.
	 * 
	 * @return the number of Nodes
	 */
	public double getNumberOfNodes() {
		return 1;
	}
	
	protected abstract String getPartialXML();

	/**
	 * Returns the XML representation of this OpenMathObject
	 * 
	 * @return the XML string
	 */
	public String getXML() {
		return "<OMOBJ>" + getPartialXML() + "</OMOBJ>";
	}

	/**
	 * Checks whether this OpenMathObject is a fraction.
	 * 
	 * @return true or false depending on whether this OpenMathObject is a fraction
	 */
	public boolean isFraction() {
		return false;
	}

	@Override
	public String toString() {
		return getXML();
	}

}
