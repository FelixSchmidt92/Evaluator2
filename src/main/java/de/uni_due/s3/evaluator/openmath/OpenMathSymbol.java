package de.uni_due.s3.evaluator.openmath;


public class OpenMathSymbol extends OpenMathObject {

	private String cd;
	private String name;

	public OpenMathSymbol() {
	}

	public String getCd() {
		return cd;
	}

	public String getName() {
		return name;
	}

	@Override
	public int getNumberOfApplications(String cd, String name) {
		if (this.cd.equals(cd) && this.name.equals(name)) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	protected String getPartialXML() {
		return "<OMS cd=\"" + cd + "\" name=\"" + name + "\"/>";
	}

	@Override
	public boolean isFraction() {
		return cd.equals("nums1") && name.equals("rational") || cd.equals("arith1") && name.equals("divide");
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cd == null) ? 0 : cd.hashCode());
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
		OpenMathSymbol other = (OpenMathSymbol) obj;
		if (cd == null) {
			if (other.cd != null)
				return false;
		} else if (!cd.equals(other.cd))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public void setName(String name) {
		this.name = name;
	}

}
