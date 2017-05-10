package de.uni_due.s3.evaluator.openmath;

public class OpenMathString extends OpenMathObject {

	private String value;

	@Override
	protected String getPartialXML() {
		// TODO &-Zeichen nicht in bereits vorhandenen Escape-Sequenzen ersetzen
		String string = value.replace("&", "&amp;");
		string = string.replace("<", "&lt;");
		string = string.replace(">", "&gt;");
		string = string.replace("\"", "&quot;");
		string = string.replace("\'", "&apos;");
		return "<OMSTR>" + string + "</OMSTR>";
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		OpenMathString other = (OpenMathString) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
