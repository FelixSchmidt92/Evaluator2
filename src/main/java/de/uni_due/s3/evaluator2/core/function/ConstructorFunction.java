package de.uni_due.s3.evaluator2.core.function;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;

/**
 * ConstructorFunction is an abstract Class where Constructors can be executed with
 * their ArgumentList. All Classes which inherit this Class <b>have to</b>
 * implement minArgs, maxArgs and the execute method(where the logic should be).
 * 
 * If you want to add a new Function please refer to the OpenMath Content
 * Dictionaries (www.openmath.org/cd/) and add the new Function to the
 * corresponding Package. If the Function is not listed in OpenMath, use the
 * *_jack package (then this Function is a jack-specific one, '*' can be linalg,
 * string etc..).
 * 
 * Getting a ConstructorFunction can be achieved with the OMSFunctionDictionary. The
 * OMSFunctionDictionary-Class will contain all available Functions for this
 * Evaluator. By Adding a new Function make this Function also available at
 * OMSFunctionDictionary.
 * 
 * Some getPartial... - functions are abstract and have to be implemented!.
 * 
 * @author dlux, frichtscheid, spobel
 */
public abstract class ConstructorFunction extends Function {

	@Override
	public abstract String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException;

	@Override
	public abstract String getPartialRSyntax(List<Object> arguments) throws EvaluatorException;

	@Override
	public abstract String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException;

}
