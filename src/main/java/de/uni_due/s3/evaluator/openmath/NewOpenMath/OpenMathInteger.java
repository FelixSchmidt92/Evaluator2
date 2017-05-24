package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This Class is a Terminal
 * It saves ONLY an Integer value
 * 
 * @author dlux
 */
public class OpenMathInteger extends OpenMathTerminal<Integer>{

	public OpenMathInteger(int value) {
		this.value = value;
	}

	@Override
	protected String getPartialXML(){
		return "<OMI>" + value + "</OMI>";
	}

}
