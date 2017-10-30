package de.uni_due.s3.evaluator2.core.function.logic1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;

/**
 * Implements openmath logic or. Example true | false => true
 * 
 * If the first argument is True, it directly returns True, ignoring the second
 * argument
 * 
 * @author spobel, dlux
 */
public class Or extends BinaryFunction {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException {
		if (getBooleanSyntax(new OMToResultVisitor().visit(arguments.get(0)))) {
			return OMSymbol.LOGIC1_TRUE;
		} else {
			if (getBooleanSyntax(new OMToResultVisitor().visit(arguments.get(1)))) {
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return getSageSyntax(arguments.get(0)) + " | " + getSageSyntax(arguments.get(1));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		return getLatexSyntax(arguments.get(0)) + "\\mbox{or}" + getLatexSyntax(arguments.get(1));
	}

	@Override
	public Object generatePalette(List<Object> arguments) throws FunctionNotImplementedException {
		return OMSymbol.LOGIC1_OR;
	}

}
