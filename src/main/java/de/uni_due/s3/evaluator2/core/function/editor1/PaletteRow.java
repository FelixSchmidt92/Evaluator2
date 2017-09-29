package de.uni_due.s3.evaluator2.core.function.editor1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class PaletteRow extends ConstructorFunction{

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
	 * Expects a list of OMObjects
	 */
	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		
		List<Object> paletteArguments = new ArrayList<Object>(arguments.size());
		//get the representation for the palette for each OMA
		for (Object arg : arguments) {
			if(OMTypeChecker.isOMA(arg)) {
				OMA argAsOMA = (OMA) arg;
				List<Object> argsArguments = argAsOMA.getOmel();
				OMS argsOms = (OMS) argsArguments.remove(0);
				Function func = OMSFunctionDictionary.getInstance().getFunction(argsOms);
				paletteArguments.add(func.generatePalette(argsArguments));
			}else {
				throw new FunctionInvalidArgumentException(this, "arguments need to be functions");
			}
		}
		return OMCreator.createOMA(OMSymbol.EDITOR1_PALETTE_ROW, paletteArguments);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}
	
	@Override
	public boolean argumentsShouldBeEvaluated() {
		return false;
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		throw new NoRepresentationAvailableException(
				"There is no List-representation for function " + this.getClass().getSimpleName());
	}
	
}