package de.uni_due.s3.evaluator.openmath.NewOpenMath.Text;

/**
 * This Class is a Terminal
 * It saves ONLY a String value
 * 
 * @author dlux
 */
public class OpenMathString extends OpenMathText<String>{

	public OpenMathString(String value) {
		this.value = value;
	}

	@Override
	protected String getPartialXML() {
		return "<OMSTR>" + value + "</OMSTR>";
	}

}
