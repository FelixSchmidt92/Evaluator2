package de.uni_due.s3.evaluator2.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.BinaryFunction;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements openmath logic and. Example true && true => true
 * 
 * If the first argument is False, it directly returns False, ignoring the
 * second argument
 * 
 * @author spobel, dlux
 *
 */
public class And extends BinaryFunction {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if (!getBooleanSyntax(OMToResultVisitor.getInstance().visit(arguments.get(0)))) {
			return OMSymbol.LOGIC1_FALSE;
		} else {
			if (getBooleanSyntax(OMToResultVisitor.getInstance().visit(arguments.get(1)))) {
				return OMSymbol.LOGIC1_TRUE;
			} else {
				return OMSymbol.LOGIC1_FALSE;
			}
		}
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return 2;
	}

	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getSageSyntax(arguments.get(0)) + " & " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return getLatexSyntax(arguments.get(0)) + "\\mbox{and}" + getLatexSyntax(arguments.get(1));
	}
}
