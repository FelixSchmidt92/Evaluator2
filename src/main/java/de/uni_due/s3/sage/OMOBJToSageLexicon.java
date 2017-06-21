package de.uni_due.s3.sage;


import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;
import de.uni_due.s3.openmath.OMV;

/**
 * This Class is under Construction!
 * 
 * @author dlux
 * @UnderConstruction
 */
public class OMOBJToSageLexicon {

	
	public String convert(Object omUnknown){
		
		String result = null;
		
		if(omUnknown instanceof OMF){
			result = convertFloat((OMF) omUnknown);
		}
		else if(omUnknown instanceof OMI){
			result = convertInteger((OMI) omUnknown);
		}
		else if(omUnknown instanceof OMSTR){
			result = convertString((OMSTR) omUnknown);
		}
		else if(omUnknown instanceof OMV){
			result = convertVariable((OMV) omUnknown);
		}
		
		
		
		
		else if(omUnknown instanceof OMS){
			result = convertOMS((OMS) omUnknown);
			
			
			
			
			
			
		}


		else if(omUnknown instanceof OMA){
			result = convertOMA((OMA) omUnknown);
		}
		
		
		return result;
	}
	
	
	//float
	private String convertFloat(OMF float1){
		return "RealNumber("+ float1.getDec() +")";
	}
	
	
	//integer
	private String convertInteger(OMI integer){
		return "Integer("+ integer.getValue() +")";
	}
	
	
	//String
	private String convertString(OMSTR string){
		return "'"+ string.getContent() +"'";
	}
	
	
	//Sage Variablen Setzung mit var(y) = x 
	//variable
	//TODO VariablenSetzung vorm Befehl irgendwie!!!
	private String convertVariable(OMV variable){

		
		return variable.getName();
	}
	
	
	
	
	//Input/Output can be PI E "GAMMA" I and Infinity and Boolean
	private String convertOMS(OMS oms){
		
		if(oms.getCd().equals("logic1")){
			return convertBoolean(oms);
		}
		
		//>TODO<
		// Constant Numbers set as OMS in OMOBJ
		else if(oms.getCd().equals("nums1")){
			if(oms.getName().equals("pi")){
				return "pi";
			}
			else if(oms.getName().equals("e")){
				return "e";
			}
			else if(oms.getName().equals("gamma")){
				return "gamma";
			}
			else if(oms.getName().equals("i")){
				return "I";
			}
			else if(oms.getName().equals("infinity")){
				return "Infinity";
			}
		}
		
		//Terminal Not found
		throw new EvaluatorException("not parsable");
	}
	
	
	//TODO exact
	//boolean 
	private String convertBoolean(OMS bool){
		return bool.getName();
	}
	
	
	
	
	private String convertOMA(OMA oma){
		OMS oms = (OMS) oma.getOmel().get(0);
		convert(null);//TODO recursively generation!
		
		//>TODO<
		if(oms.getCd().equals("arith1")){
			if(oms.getName().equals("unary_minus")){
				return convertUnary(oma);
			}
			else if(oms.getName().equals("unary_plus")){
				return convertUnary(oma);
			}
			
		}
		
		
		
		return null;
	}
	
	
	
	//matrix
	private String convertMatrix(OMA matrix){
			return null;
		}
	
	//rowmatrix
	private String convertRowMatrix(OMA rowMatrix){
		return null;
	}
	
	//vector
	private String convertVector(OMA vector){
		return null;
	}
	
	//set
	private String convertSet(OMA set){
		return null;
	}
	
	//complex
	private String convertComplex(OMA complex){
		return null;
	}
	
	//list
	private String convertList(OMA list){
		return null;
	}
	
	//unary
	private String convertUnary(OMA unary){
		return null;
	}
	
	//polynom
	private String convertPolynom(OMA polynom){
		return null;
	}
	
	//rational
	private String convertRational(OMA rational){
		return null;
	}
	

}
