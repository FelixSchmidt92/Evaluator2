package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This is a Terminal
 * It saves ONLY a String of a variable
 * 
 * @author dlux
 */
public class OpenMathVariable extends OpenMathObject<String>{

	public OpenMathVariable(String value) {
		this.value = value;
	}

	@Override
	protected String getPartialXML() {
		return "<OMV name=\"" + value +"\"/>";
	}
	

}
