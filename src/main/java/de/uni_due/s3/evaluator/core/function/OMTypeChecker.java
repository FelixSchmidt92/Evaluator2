package de.uni_due.s3.evaluator.core.function;

import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;

public class OMTypeChecker {

	public static boolean isOMA(Object obj){
		return obj instanceof OMA;
	}
	
	public static boolean isOMAWithSymbol(Object obj, String symbol){
		try{
			if ( ((OMS) ((OMA)obj).getOmel().get(0) ).getName().equals(symbol) )
			return true;
		}catch(Exception e){
			return false;
		}
		
		return false;
	}
	
	public static boolean isOMS(Object obj){
		return obj instanceof OMS;
	}
	
	public static boolean isOMF(Object obj){
		return obj instanceof OMF;
	}
	public static boolean isOMSTR(Object obj){
		return obj instanceof OMSTR;
	}
	public static boolean isOMI(Object obj){
		return obj instanceof OMI;
	}
}
