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
//		functions.put("abs", 		new Object[]{new Abs(), "arith1", "abs"});
		//functions.put("modulus",	new Object[]{new Modulus(), ""});
		
		//Rounding
//		functions.put("ceil", 	new Object[]{new Ceiling(), "rounding1", "ceiling"});

		//Relationen
//		functions.put("lessthan",			new Object[]{new LessThan(), 			"relation1", "lt"});
//		functions.put("lessthanorequal", 	new Object[]{new LessThanOrEqual(), 	"relation1", "leq"});
//		functions.put("greaterthan", 		new Object[]{new GreaterThan(), 		"relation1", "gt"});
//		functions.put("greaterthanorequal", new Object[]{new GreaterThanOrEqual(), 	"relation1", "geq"});
//		functions.put("equal", 				new Object []{new Equal(), 				"relation1", "eq"});
//		functions.put("notequal", 			new Object[]{new NotEqual(), 			"relation1", "neq"});
		
		//Logic
//		functions.put("booleanor", 			new Object[]{new BooleanOr(), "logic1", "or"});
//		functions.put("booleanand", 		new Object[]{new BooleanAnd(), "logic1", "and"});
		
		
		//Trigonometrie
//		functions.put("sin", 	new Object[]{new Sin(), 	"transc1", "sin"});
//		functions.put("cos", 	new Object[]{new Cos(), 	"transc1", "cos"});
//		functions.put("tan", 	new Object[]{new Tan(), 	"transc1", "tan"});
//		functions.put("asin", 	new Object[]{new ArcSin(), 	"transc1", "arcsin"});
//		functions.put("acos", 	new Object[]{new ArcCos(), 	"transc1", "arccos"});
//		functions.put("atan", 	new Object[]{new ArcTan(), 	"transc1", "arctan"});
//		functions.put("atan2", 	new Object[]{new ArcTan2(), "transc2", "arctan"}); 
		
		
		//Polynome
//		functions.put("deg", new Object[]{new Degree(), "polynomial1", "degree"});
//		functions.put("dependsOn", new Object[]{new DependsOn(), "polynomialJACK", "dependsOn"});
//		functions.put("derive", new Object[]{new Derive(), "polynomialJACK", "derive"});
//		functions.put("equalsExpr", new Object[]{new EqualsExpr(), "polynomialJACK", "equalsExpr"});
//		functions.put("equalscplx", new Object[]{new EqualsCplx(), "polynomialJACK", "evalcplx"});
//		functions.put("evaleq", new Object[]{new EvalEq(), "polynomialJACK", "evalEq"});
//		functions.put("evalpolynomial", new Object[]{new EvalPolynomial(), "polynomialJACK", "evalPolynomial"});
//		functions.put("evalPolynomialCplx", new Object[]{new EvalPolynomialCplx(), "polynomialJACK", "evalPolynomialCplx"});
//		functions.put("evalterm2", new Object[]{new EvalTerm2(), "polynomialJACK", "evalterm2"});
//		functions.put("expand", new Object[]{new Expand(), "polynomial1", "expand"});
//		functions.put("factorOf", new Object[]{new FactorOf(), "polynomialJACK", "factorOf"});
//		functions.put("integrate", new Object[]{new Integrate(), "polynomialJACK", "integrate"});
//		functions.put("isLinearlyIndependent", new Object[]{new IsLinearlyIndependent(), "polynomialJACK", "isLinearlyIndependent"});
//		functions.put("isPolynomial", new Object[]{new IsPolynomial(), "polynomialJACK", "isPolynomial"});
//		functions.put("numberOfVariables", new Object[]{new NumberOfVariables(), "polynomialJACK", "numberOfVariables"});
		
		//Lineare Algebra
//		functions.put("vector", new Object[]{new Vector(), "linalg2", "vector"});
//		functions.put("matrix", new Object[]{new Matrix(), "linalg2", "matrix"});
//		functions.put("matrixrow", new Object[]{new MatrixRow(), "linalg2", "matrixrow"});
//		functions.put("equalBasis", new Object[]{new EqualBasis(), "linalgJACK", "equalBasis"});
//		functions.put("randomMatrixEigenvalue", new Object[]{new RandomMatrixEigenvalue(), "linalgJACK", "randomMatrixEigenvalue"});
//		functions.put("randomMatrixRank", new Object[]{new RandomMatrixRank(), "linalgJACK", "randomMatrixRank"});
		
		//List
//		functions.put("set", new Object[]{new Set(), "list1", "list"});
//		functions.put("chooseFromComplement", new Object[]{new ChooseFromComplement(), "listJACK", "chooseFromComplement"})
		
		//CAS
//		functions.put("evaluateInR", new Object[]{new EvaluateInR(), "casJACK", "evaluateInR"});
//		functions.put("evaluateInSage", new Object[]{new EvaluateInSage(), "casJACK", "evaluateInSage"});
//		functions.put("evaluateInSymja", new Object[]{new EvaluateInSymja(), "casJACK", "evaluateInSymja"});
		
		//String
//		functions.put("charAt", new Object[]{new CharAt(), "stringJACK", "chatAt"});
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
