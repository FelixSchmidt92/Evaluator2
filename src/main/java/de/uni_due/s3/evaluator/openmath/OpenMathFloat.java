package de.uni_due.s3.evaluator.openmath;

public class OpenMathFloat extends OpenMathObject {

	private double value;

	@Override
	protected String getPartialXML() {
		return "<OMF dec=\"" + value + "\"/>";
	}

	public double getValue() {
		return value;
	}

	@Override
	public boolean isFloat() {
		return true;
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		OpenMathFloat other = (OpenMathFloat) obj;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}
	
}
