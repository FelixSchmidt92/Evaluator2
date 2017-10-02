package de.uni_due.s3.evaluator2.core.function.editor1;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Creates a Tab for a palette from mathdox.
 * @author frichtscheid
 *
 */
public class PaletteTab extends Function{

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
}