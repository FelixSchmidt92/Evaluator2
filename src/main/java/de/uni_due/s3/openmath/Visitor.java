package de.uni_due.s3.openmath;

public abstract class Visitor {
	
	public abstract String visit(OMA oma);
	public abstract String visit(OMS oms);
	public abstract String visit(OMOBJ omobj);
	public abstract String visit(OMI omi);
	public abstract String visit(OMF omf);
}
