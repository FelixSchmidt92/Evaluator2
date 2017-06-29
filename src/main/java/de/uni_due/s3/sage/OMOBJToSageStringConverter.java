package de.uni_due.s3.sage;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import de.uni_due.s3.JAXBOpenMath.openmath.OMA;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.JAXBOpenMath.openmath.OMSTR;
import de.uni_due.s3.JAXBOpenMath.openmath.OMV;

/**
 * This Class only converts a finite amount of OMOBJ-Objects into Sage-Syntax.
 * It covers a lot of non terminals and some basic polynoms for now.
 * 
 * It is constructed as easily as possible, to add more functionality in this
 * Converter. 
 * 
 * To Add a new Constant do this ->
 * 		1. Look up the representation of this Constant in OpenmMath (e.g. gamma in nums1) and Sage
 * 		2. Simply add it into OMSLookUp like this: "OMSLookUp.put(new KeyPair("nums1", "gamma"), "SAGE_RE_OF_GAMMA");"
 * 
 * To Add a new binary or unary Operator do this ->
 * 		Just add it into OMALookUp AND OMAMethodCall refer at OMAMethodCall to convertUnary 
 * 		or convertArith1 (Binary)
 * 
 * To Add a new OMA-Conversion do this ->
 * 		1. Write a new private method and look if you could use the buildString method for the missing conversion.
 * 		For Example for gcd in arith1 in OpenMath create a new method "convertGCD(OMA gcd)" you can call
 * 		the Function gcd in Sage as follows "gcd(Number, Number)". Make sure to write the method recursively, the 
 * 		Terminals in this OMA can be another OMAs or maybe another gcd. You can do so by simply call convert
 * 		on this Arguments, the rest is up to you how to code it ;)
 * 	
 * 		2. Add the method into the OMAMethodCall-HashMap and reference to it with a lambda (for better reading)
 * 		In this example, it should look like this:
 * 		"OMAMethodCall.put(new KeyPair("arith1", "gcd"), (OMA obj) -> { return convertGCD(obj);});"
 * 
 * @author dlux
 */
public class OMOBJToSageStringConverter {

	private Map<KeyPair, String> OMSLookUp = new HashMap<>();
	
	private Map<KeyPair, String> OMALookUp = new HashMap<>();
	private Map<KeyPair, Function<OMA, String>> OMAMethodCall = new HashMap<>();
	
	private static OMOBJToSageStringConverter itself = null;
	
	
	/**
	 * This Class is an Singleton
	 * @return this Class as Object itself
	 */
	public static OMOBJToSageStringConverter getInstance(){
		if(itself == null){
			itself = new OMOBJToSageStringConverter();
		}
		return itself;
	}
	
	/**
	 *	This is the Constructor for this Class. Here are the HashMaps -defined
	 */
	private OMOBJToSageStringConverter(){
		
		
		
		//FIXME hier auch ein OMSDictionary !?
		/*
		 * OMS Terminal-Declaration, just add here new Terminals, nothing else to do elsewhere
		 */
		OMSLookUp.put(new KeyPair("logic1", "true"), "True");
		OMSLookUp.put(new KeyPair("logic1", "false"), "False");
		
		OMSLookUp.put(new KeyPair("nums1", "pi"), "pi");
		OMSLookUp.put(new KeyPair("nums1", "e"), "e");
		OMSLookUp.put(new KeyPair("nums1", "NaN"), "NaN");
		OMSLookUp.put(new KeyPair("nums1", "i"), "I");
		OMSLookUp.put(new KeyPair("nums1", "infinity"), "Infinity");
		
		
		/*
		 * The LookUp Table for convertUnary and convertArith1 (Binary) 
		 */
		//OMA UnaryOperator
		OMALookUp.put(new KeyPair("arith1", "unary_plus"), "+");
		OMALookUp.put(new KeyPair("arith1", "unary_minus"), "-");
		OMALookUp.put(new KeyPair("logic1", "not"), "not ");
		
		//OMA Arith1 and Rational (Binary Operator)
		OMALookUp.put(new KeyPair("arith1", "plus"), " + ");
		OMALookUp.put(new KeyPair("arith1", "minus"), " - ");
		OMALookUp.put(new KeyPair("arith1", "times"), " * ");
		OMALookUp.put(new KeyPair("arith1", "divide"), " / ");
		OMALookUp.put(new KeyPair("arith1", "power"), " ^ ");
		OMALookUp.put(new KeyPair("nums1", "rational"), " / ");
		
		
		
		
		/*
		 * Call Mapping of OMAMethodCall
		 * 
		 * Here it is defined which cd and name belongs to which method
		 * The upper part are for the Unary and Binary-Conversion. The Entries below for Matrix, etc...
		 */
		OMAMethodCall.put(new KeyPair("arith1", "unary_minus"), (OMA obj) -> { return convertUnary(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "unary_plus"), (OMA obj) -> { return convertUnary(obj);});
		OMAMethodCall.put(new KeyPair("logic1", "not"), (OMA obj) -> { return convertUnary(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "plus"), (OMA obj) -> { return convertArith1(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "minus"), (OMA obj) -> { return convertArith1(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "times"), (OMA obj) -> { return convertArith1(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "divide"), (OMA obj) -> { return convertArith1(obj);});
		OMAMethodCall.put(new KeyPair("arith1", "power"), (OMA obj) -> { return convertArith1(obj);});
		OMAMethodCall.put(new KeyPair("nums1", "rational"), (OMA obj) -> { return convertArith1(obj);});
		
		
		
		
		OMAMethodCall.put(new KeyPair("set1", "set"), (OMA obj) -> { return convertSet(obj);});
		OMAMethodCall.put(new KeyPair("list1", "list"), (OMA obj) -> { return convertList(obj);});
		OMAMethodCall.put(new KeyPair("linalg2", "vector"), (OMA obj) -> { return convertVector(obj);});
		OMAMethodCall.put(new KeyPair("linalg2", "matrixrow"), (OMA obj) -> { return convertRowMatrix(obj);});
		OMAMethodCall.put(new KeyPair("linalg2", "matrix"), (OMA obj) -> { return convertMatrix(obj);});
	}
	
	/**
	 * This method calls the convert-Method for Object. 
	 * It just extracts the containing OM* in OMOBJ.
	 * 
	 * @param omobj the OMOBJ-"Container"
	 * @return the String representation of this OMOBJ in Sage-Syntax
	 */
	public String convert(OMOBJ omobj){
		if (omobj.getOMF() != null){
			return convert(omobj.getOMF());
		}
		if (omobj.getOMI() != null){
			return convert(omobj.getOMI());
		}
		if (omobj.getOMSTR() != null){
			return convert(omobj.getOMSTR());
		}
		if (omobj.getOMV() != null){
			return convert(omobj.getOMV());
		}
		if (omobj.getOMS() != null){
			return convert(omobj.getOMS());
		}
		if (omobj.getOMA() != null){
			return convert(omobj.getOMA());
		}
		
		throw new SagePhrasebookException("Unrecognizable OM* in OMOBJ. Could not parse OMOBJ into Sage-Syntax");
		
	}
	
	/**
	 * This method converts an OMF, OMA, OMI, OMSTR, OMV, OMS or an OMA into Sage-Syntax
	 * It delegates the specific OM*-Object to the specific Function.
	 * 
	 * OMF, OMI, OMSTR, OMV and OMS are Terminals. They cannot contain more than 1 Parameter,
	 * so these can be implemented directly (OMS can have constant variables, as I, Pi, E, or Gamma).
	 * 
	 * 
	 * @param omUnknown The OM*-Object which this function should convert to Sage-Syntax
	 * @return a String in Sage-Syntax
	 * @throws SagePhrasebookException
	 */
	public String convert(Object omUnknown){
		String result = null;
		
		switch (omUnknown.getClass().getSimpleName()){
		//Terminals Implementation can be found in this Class
		case "OMF":
			result = convertFloat((OMF) omUnknown); break;
			
		case "OMI":
			result = convertInteger((OMI) omUnknown); break;
			
		case "OMSTR":
			result = convertString((OMSTR) omUnknown); break;
			
		case "OMV":
			result = convertVariable((OMV) omUnknown); break;
			
		case "OMS":
			result = convertOMS((OMS) omUnknown); break;
			
			
		//a more Complex Expression (e.g Rational, Polynom or Matrix)
		case "OMA":
			result = convertOMA((OMA) omUnknown); break;
			
		}
		
		if (result == null){
			throw new SagePhrasebookException("Unrecognizable OM* to get Sage-Syntax");
		}
		return result;
	}
	
	
	/**
	 * Direct Implementation for Float to Sage.
	 * 
	 * @param float1 the OMF Float which should be represented in Sage
	 * @return a String in Sage-Syntax for this Float
	 */
	private String convertFloat(OMF float1){
		return "RealNumber("+ float1.getDec() +")";
	}
	
	
	/**
	 * Direct Implementation for Integer to Sage.
	 * 
	 * @param integer the OMI Integer which should be represented in Sage
	 * @return a String in Sage-Syntax for this Integer
	 */
	private String convertInteger(OMI integer){
		return "Integer("+ integer.getValue() +")";
	}
	
	
	/**
	 * Direct Implementation for String to Sage.
	 * 
	 * @param string the OMSTR String which should be represented in Sage
	 * @return a String in Sage-Syntax for this String (sounds weird)
	 */
	private String convertString(OMSTR string){
		return "'"+ string.getContent() +"'";
	}
	
	
	/**
	 * Direct Implementation for Variable to Sage.
	 * 
	 * Specifically in Sage the Variable has to be declared beforehand if you want
	 * to use it as regularly in math (e.g 2*x -3*y^2 ...).
	 * But it is also possible using var('StringArgument') to do it in-Function.
	 * The Output is the same and the Syntax looks like this: 2*var('x') - 3*var('y')^2 .
	 * 
	 * @param variable the OMV Variable which should be represented in Sage
	 * @return a String in Sage-Syntax for this Variable (returns itself)
	 */
	private String convertVariable(OMV variable){
		return "var('" + variable.getName() + "')";
	}
	
	
	
	
	/**
	 * Direct Implementation for OMS to Sage.
	 * 
	 * here are constant variables like True, PI, I, E, Infinity, Gamma, etc...
	 *
	 * Feel free to add more  if some essential Constant-Variables are missing
	 * in this Phrasebook which the Evaluator supports (look it also up in the OpenMath-Doc) 
	 * and the Phrasebook should translate. To add more constant variables
	 * look at Class Description!
	 * 
	 * @param oms the OMS which should be represented in Sage
	 * @return a String in Sage-Syntax for this specific OMS
	 */
	private String convertOMS(OMS oms){
		return OMSLookUp.get(new KeyPair(oms.getCd(), oms.getName()));
	}

	
	/************************************************************************/
	/******************OMA-SECTION NON-TERMINALS-MAIN-Call**********************/
	/************************************************************************/
	
	/**
	 * This method calls the Function which where specified in the HashMap before.
	 * 
	 * @param oma the OMA which should be converted into Sage-Syntax
	 * @return a String representation of the OMA in Sage-Syntax
	 */
	private String convertOMA(OMA oma){
		OMS oms = (OMS) oma.getOmel().get(0);
		return OMAMethodCall.get(new KeyPair(oms.getCd(), oms.getName())).apply(oma);
	}
	
	/************************************************************************/
	/*********************OMA-SECTION NON-TERMINALS**************************/
	/***************Below you can add more convert*-Methods******************/
	
	
	/**Converts unarys in OMALookUp into Sage*/
	private String convertUnary(OMA unary){
		String cd = ((OMS)unary.getOmel().get(0)).getCd();
		String name = ((OMS)unary.getOmel().get(0)).getName();
		
		return OMALookUp.get(new KeyPair(cd, name)) + convert(unary.getOmel().get(1));	
	}
	
	
	/**Converts binarys in OMALookUp into Sage. including simple Polynoms*/
	private String convertArith1(OMA arith1){
		String cd = ((OMS)arith1.getOmel().get(0)).getCd();
		String name = ((OMS)arith1.getOmel().get(0)).getName();
		
		return convert(arith1.getOmel().get(1)) + OMALookUp.get(new KeyPair(cd, name))
									+ convert(arith1.getOmel().get(2));	
	}
	
	/**Conversion of set using buildString*/
	private String convertSet(OMA set){	
		return buildString("{", set.getOmel(), "}", 1);
	}
	
	
	/**Conversion of list using buildString*/
	private String convertList(OMA list){
		return buildString("[", list.getOmel(), "]", 1);
	}
	
	
	/**Conversion of vector using buildString*/
	private String convertVector(OMA vector){
		return buildString("vector([", vector.getOmel(), "])", 1);
	}
	
	
	/**Conversion of rowmatrix using buildString*/
	private String convertRowMatrix(OMA rowMatrix){
		return buildString("matrix([[", rowMatrix.getOmel(), "]])", 1);
	}
	
	
	/**Conversion of matrix using convertList*/
	private String convertMatrix(OMA matrix){
		//convert inside of list
		List<String> matrixRows = new LinkedList<String>();
		for(int i = 1; i < matrix.getOmel().size(); i++){
			matrixRows.add(convertList((OMA)matrix.getOmel().get(i)));
		}
		//build matrix
		String builder = "matrix([";
		for(String row : matrixRows){
			builder += row + ", ";
		}
		builder = builder.substring(0, builder.length() -2 );
		builder += "])";
		
		return builder;
		}
	
	
	/**Add here new convert* methods*/
	
	
	/**
	 * Helper method to get the String representation of a List in one String separated by Comma.
	 * 
	 * @param pre the prefix
	 * @param omels The List of OM*-Objects
	 * @param post	the postfix 
	 * @param startPos the position where the List should start from the omel-list (usually 1)
	 * @return a String which represents this List as a Sage-Syntax-String
	 */
	private String buildString(String pre, List<Object> omels, String post, int startPos){
		if(startPos < omels.size()){
			return buildString(pre + convert(omels.get(startPos))+ ", ", omels, post, startPos + 1);
		}else{
			pre = pre.substring(0, pre.length()-2);
			return pre + post;
		}
	}

}
