package de.uni_due.s3.evaluator.openmath;

public class OpenMathVariable extends OpenMathObject {

	private String name;

	public String getName() {
		return name;
	}

	@Override
	protected String getPartialXML() {
		return "<OMV name=\"" + name + "\"/>";
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		OpenMathVariable other = (OpenMathVariable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
