package de.uni_due.s3.evaluator2.r;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPDouble;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.RList;

public class RToOMOBJVisitor {

	
	public  RToOMOBJVisitor() {
		
	}
	
	public String visit(REXP rexp) {
		try {
			
			if(rexp.isList()) {
				//Its a List
				return visitList(rexp);
				
			}else if (rexp.length() != 1) {
				//its a Vector or a Matrix
				
				if (rexp._attr() != null) {
					//Matrix??
				}else {
					//Combine Function or a Vector
					return visitVector(rexp);
				}
				
				
				System.out.println("not impl");
				
			}else if (rexp.isNumeric()) {
				//Its Double or Integer
				return visitIntDouble(rexp);
				
			}else if (rexp.isString()) {
				//Its String
				return visitString(rexp);
				
			}else if (rexp.isLogical()) {
				//Its True or False
				 return visitBoolean(rexp);
				 
			}else if (rexp.isComplex()) {
				System.out.println("not impl");
				
			}else if (rexp.isSymbol()) {
				System.out.println("not impl");
				
			}else {
				System.out.println(rexp);
			}
		} catch (REXPMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String visitIntDouble(REXP rexp) throws REXPMismatchException {
		Double result = rexp.asDouble();
		
		if (result == Math.floor(result)) {
			return "<OMI>" + result.intValue() + "</OMI>";
		}else {
			return "<OMF dec=\"" + result + "\">";
		}
	}
	
	
	private String visitString(REXP rexp) throws REXPMismatchException  {
		String result = rexp.asString();
		return "<OMSTR>" + result + "</OMSTR>";
	}
	
	
	
	private String visitList(REXP rexp) throws REXPMismatchException {
		String omList = "<OMA><OMS cd=\"list1\" name=\"list\">";
		RList list = rexp.asList();
		
		for (int i = 0; i < list.size(); i++) {
			omList += visit((REXP) list.get(i));
		}
		
		omList += "</OMA>";
		return omList;
		
	}
	
	private String visitBoolean(REXP rexp) throws REXPMismatchException {
		String bool = rexp.asString();
		if (bool.equals("TRUE")) {
			return "<OMS cd=\"logic1\" name=\"true\">"; 
					
		} else {
			return "<OMS cd=\"logic1\" name=\"false\">"; 
		}
		
	}
	
	private String visitVector(REXP rexp) throws REXPMismatchException {
		RList results = rexp.asList();
		String omList = "<OMA><OMS cd=\"linalg2\" name=\"vector\">";
		
		for (int i = 0; i < results.size(); i++) {
			omList += visit((REXP) results.get(i));
		}
		
		omList += "</OMA>";
		return omList;
	}
}
