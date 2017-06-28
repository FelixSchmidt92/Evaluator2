package de.uni_due.s3.openmath;

public class SageVisitor extends Visitor {

	@Override
	public String visit(OMA oma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(OMS oms) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(OMOBJ omobj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(OMI omi) {
		return omi.getValue();
	}

	@Override
	public String visit(OMF omf) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
