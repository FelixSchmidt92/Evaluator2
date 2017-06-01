package de.uni_due.s3.evaluator.tree.function;

import java.util.HashMap;


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
 * @UnderConstruction TODO implement getFunction
 * @author dlux, frichtscheid, spobel
 */
public class FunctionFactory {

	private static FunctionFactory itself = null;
	private HashMap<String, EFunction> functions = new HashMap<>();
	
	/**
	 * private: Singleton Pattern!
	 * 
	 * Here you can add (register) the Functions which are
	 * available for the Evaluator in the HashMap.
	 * 
	 * The Constructor only adds Functions to the HashMap.
	 * There is nothing else to do here.
	 */
	private FunctionFactory(){
		
		functions.put("example", /*new Example()*/null);
		
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
	 * This method returns the specific EFunction for the EApplication.
	 * 
	 * @UnderConstruction TODO
	 * @param name the name (key)(in lower case) of this Function
	 * @return the specific EFunction 
	 */
	public EFunction getFunction(String name){
		return null;
	}
	
	
}
