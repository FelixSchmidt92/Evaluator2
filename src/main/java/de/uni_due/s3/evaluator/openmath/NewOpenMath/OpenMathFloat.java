package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This is a Terminal
 * It saves ONLY a float (double) value
 * 
 * @author dlux
 */
public class OpenMathFloat extends OpenMathObject<Double>{

	public OpenMathFloat(double value) {
		this.value = value;
	}
	@Override
	protected String getPartialXML() {
		return "<OMF dec=\"" + value + "\"/>";
	}

}
