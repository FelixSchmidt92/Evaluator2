package de.uni_due.s3.evaluator2.core.function.editor1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Creates a Tab for a palette from mathdox.
 * @author frichtscheid
 *
 */
public class PaletteTab extends ConstructorFunction{

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Latex-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no R-representation for function " + this.getClass().getSimpleName());
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no Sage-representation for function " + this.getClass().getSimpleName());
	}

	/**
	 *  Expects the result of paletterow as argument
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		if(!OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.EDITOR1_PALETTE_ROW)) {
			throw new FunctionInvalidArgumentTypeException("paletterow");
		}
		
		return OMCreator.createOMA(OMSymbol.EDITOR1_PALETTE_TAB, arguments);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}
	
	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no List-representation for function " + this.getClass().getSimpleName());
	}
	
}