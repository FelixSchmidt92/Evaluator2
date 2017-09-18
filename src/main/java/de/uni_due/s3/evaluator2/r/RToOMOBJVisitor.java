package de.uni_due.s3.evaluator2.r;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPMismatchException;

public class RToOMOBJVisitor {

	
	public  RToOMOBJVisitor() {
		
	}
	
	public String visit(REXP rexp) {
		
			((REXPDouble) rexp).isList();

		
		return null;
	}
	
}
