package de.uni_due.s3.evaluator2.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This interface contains all entries of partialMethods (which are used in the
 * getSyntaxRepresentationForFunction-method in every visitor!)
 * 
 * All methods defined by this interface, need to be implemented in every
 * function!
 * 
 * Adding a new method in this interface guarantees that every function has this
 * function implemented and this method can be called!
 * 
 * By adding a new Visitor also add the partial function in this Interface.
 * 
 * @author dlux
 *
 */
public interface IPartialMethods {

	/** Operation Visitor (to count or calculate..) */
	// protected final Integer countNodes(Object omElement) throws

	// EvaluatorException, OpenMathException

	// protected Object getPartialOpenMathElementResult(List<Object> arguments)

	// throws EvaluatorException, OpenMathException;

	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	/** Primitive Data Type Visitor functions */
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public List<?> getPartialListSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	/** Translator of the OpenMathObject into a Syntax Visitor functions */
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException;

	// protected final Set<OMV> getVariablesAsOMVSet(Object omElement) throws
	// EvaluatorException, OpenMathException;

}
