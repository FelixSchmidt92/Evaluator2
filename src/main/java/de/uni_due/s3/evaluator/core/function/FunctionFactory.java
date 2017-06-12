package de.uni_due.s3.evaluator.core.function;

import java.util.HashMap;

import de.uni_due.s3.evaluator.exceptions.FunctionDoesNotExistException;
import de.uni_due.s3.evaluator.core.function.arith1.*;


/**
 * In This Factory all available Functions are registered in a HashMap.
 * This Class is a Singleton, so it cannot be constructed manually.
 * To get the Object of this Class use getInsance.
 * 
 * 
 * <b>Convention:</b> The name of the Function is the same as 
 * the Class, only with lower case letters!
 * 
 * <b>Convention:</b> Names of Function have to be Unique, like the Java-Classes!
 * 
 * @author dlux, frichtscheid, spobel
 */
public class FunctionFactory {

	private static FunctionFactory itself = null;
	private HashMap<String, Object[]> functions = new HashMap<>();
	
	/**
	 * private: Singleton Pattern!
	 * 
	 * Here you can add (register) the Functions which are
	 * available for the Evaluator in the HashMap.
	 * 
	 * The Constructor only adds Functions to the HashMap.
	 * There is nothing else to do here.
	 * 
	 * Explanation:
	 * The key of this HashMap (First Parameter) defines the FunctionName 
	 * 
	 * The next Parameter is an Array of Objects:
	 * 
	 * First ArrayElement: 	The Object which implements this functionName (can also be null)
	 * 
	 * Second ArrayElement: The ContentDictionary (the Package where the functions can be found)
	 * 						Use the OpenMath-Standard and determine where the Function should be 
	 * 						allocated! Wrong allocation could result in errors by other Programs
	 * 						which rely on these Content-Dictionary
	 * 
	 * Third ArrayElement:	Like in Second ArrayElement refer to the OpenMath-Standard here as the Name! 
	 * 						But you can point with this Parameter if there is already an Implementation 
	 * 						of this function. (But it has still to be an OpenMath-Name!)
	 * 
	 */
	private FunctionFactory(){
		
	//  functions.put("myOwnPlus", 	new Object[]{null, "arith1", "plus"});  //redirects to key "plus"
		functions.put("plus", 		new Object[]{new Plus(), "arith1", "plus"});
		functions.put("minus", 		new Object[]{new Minus(), "arith1", "minus"});
		functions.put("times", 		new Object[]{new Times(), "arith1", "times"});
		functions.put("divide", 	new Object[]{new Divide(), "arith1", "divide"});
		
	}
	
	

	
	/**
	 * Singleton-Pattern, this Class can be only 
	 * initialized once. With this method you can get the 
	 * Object of FunctionFactory.
	 * 
	 * @return this Class as itself
	 */
	public static FunctionFactory getInstance(){
		if (itself == null){
			itself = new FunctionFactory();
		}
		return itself;
	}
	
	
	/**
	 * This method returns the specific Function for OMVisitor
	 * So it is used by OMVisitor
	 * 
	 * @param name the name (key)(in lower case) of this Function
	 * @return the specific Function 
	 * @throws FunctionDoesNotExistException if Function is not found in HashMap
	 */
	public Function getFunction(String name){
		if(functions.containsKey(name))
			return (Function)functions.get(name)[0];
		else
			 throw new FunctionDoesNotExistException(name);
	}
	
	
	/**
	 * This method returns the contentDictionary of the specific function. 
	 * It is mainly used by the expressionParser and the OMVisitor.
	 * 
	 * @param name the name (key)(in lower case) of this Function
	 * @return the specific contentDictionary as String
	 * @throws FunctionDoesNotExisExcetion if the function is not found in the HashMap. The evaluator user has to deal with this exception
	 */
	public String getFunctionContentDictionary(String name){
		if(functions.containsKey(name))
			return (String)functions.get(name)[1];
		else
		  throw new FunctionDoesNotExistException(name);
	}
	
	
	/**
	 * This method returns the name of the specific function 
	 * for ExpressionToEvaluatorOpenMathVisitor. It needs its name
	 * to generate the Tree (needed for OMS in parser/ExpressionToEvaluatorOpenMathVisitor)
	 * 
	 * @param name the name (key)(in lower case) of this Function
	 * @return the specific Name as String
	 * @throws FunctionDoesNotExistException if function is not found in HashMap.
	 */
	public String getFunctionName(String name){
		if(functions.containsKey(name))
			return (String)functions.get(name)[2];
		else
		  throw new FunctionDoesNotExistException(name);
	}
}
