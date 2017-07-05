package de.uni_due.s3.evaluator;

import java.util.HashMap;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

/**
 * TODO
 * 
 * @author dlux,frichtscheid,spobel
 *
 */
public class Evaluator {

	private HashMap<String, OMOBJ> exerciseVariableMap;
	private HashMap<Integer, OMOBJ> fillInVariableMap;

	public Evaluator() {

	}

	/**
	 * Evaluates an expression, like "1+3" or "sin(cos(1))"
	 * 
	 * @param expression
	 *            see requirements F2.*
	 * @return the result of the evaluated expression as OpenMath-Object (JAXB)
	 * @throws FunctionException, ParserException 
	 * @throws OMObjectNotSupportedException 
	 * @throws OMOBJChildNotSupportedException 
	 */
	public OMOBJ evaluate(String expression) throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException, ParserException {
		OMOBJ omobj = ExpressionParser.parse(expression, exerciseVariableMap, fillInVariableMap);
		return OMExecutor.execute(omobj);
	}

	/**
	 * Evaluates an OpenMath-Object. Example for an Object: <OMOBJ> <OMA>
	 * <OMS cd=arith1 name="plus" /> <OMI>1</OMI> <OMI>3</OMI> </OMA> </OMOBJ>
	 * 
	 * @param omobj
	 * @return the evaluated OpenMath-Object
	 * @throws FunctionException 
	 * @throws OMObjectNotSupportedException 
	 * @throws OMOBJChildNotSupportedException 
	 */
	public OMOBJ evaluate(OMOBJ omobj) throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException {
		return OMExecutor.execute(omobj);
	}

	/**
	 * Adds a FillInVariable to the evaluator
	 * 
	 * @param name
	 *            the name, mainly something like 1,2 or 3 etc, but can be every
	 *            integer value
	 * @param omobj
	 *            the value of the variable as OpenMath-Object
	 */
	public void addFillInVariable(int name, OMOBJ omobj) {
		fillInVariableMap.put(name, omobj);
	}

	/**
	 * Adds an ExerciseVariable to the evaluator
	 * 
	 * @param name
	 *            the name, something like a, b or c etc, but can be every kind
	 *            of string
	 * @param omobj
	 *            the value of the variable as OpenMath-Object
	 */
	public void addExerciseVariable(String name, OMOBJ omobj) {
		exerciseVariableMap.put(name, omobj);
	}
}
