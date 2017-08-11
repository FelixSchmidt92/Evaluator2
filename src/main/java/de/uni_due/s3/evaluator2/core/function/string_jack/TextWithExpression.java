package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;

/**
 * This Function is not registered in OMSEvaluatorSyntaxDictionary.
 * 
 * This is a Terminal like Vector or Matrix, but not callable by the Evaluator.
 * 
 * With this Function we save input String to evaluator for use with String
 * functions, if there is a String, which is parsable in an Expression like OMI
 * OMA etc.
 * 
 * @author spobel
 *
 */
public class TextWithExpression extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException {
		return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTWITHEXPRESSION, arguments);
	}
	
	@Override
	protected boolean keepOriginalTextValue() {
		return true;
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
	public String getPartialSageSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		return getSageSyntax(arguments.get(1));
	}

}
