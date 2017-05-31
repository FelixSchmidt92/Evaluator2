package de.uni_due.s3.evaluator.openmath;


public class OpenMathInteger extends OpenMathObject {

	private int value;

	@Override
	protected String getPartialXML() {
		return "<OMI>" + value + "</OMI>";
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public boolean isInteger() {
		return true;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + value;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OpenMathInteger other = (OpenMathInteger) obj;
		if (value != other.value)
			return false;
		return true;
	}

}
