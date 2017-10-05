package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.core.visitor.OMToPaletteVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements the Math.pow function for functions Example: 2^3 = 8 or Usage:
 * power(2,3) = 8
 * 
 * @author frichtscheid, spobel
 *
 */
public class Power extends BinaryFunction {

	public Power() {
		super(OMSPriority.getPriority(OMSymbol.ARITH1_POWER));
	}

	/**
	 * Expects two arguments either of type OMI or OMF
	 * 
	 * @return OMI or OMF
	 * @throws FunctionInvalidArgumentTypeException
	 * @throws OpenMathException
	 * @throws EvaluatorException 
	 */
	@Override
	protected Object execute(List<Object> arguments) throws OpenMathException, EvaluatorException {
		try {
			Double b = getDoubleSyntax(arguments.get(0));
			Double e = getDoubleSyntax(arguments.get(1));
			return OMCreator.createOMIOMF(Math.pow(b, e));
		} catch (FunctionInvalidArgumentTypeException e) {
			if (OMTypeChecker.isOMV(arguments.get(0)) || OMTypeChecker.isOMV(arguments.get(1))
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(0), OMSymbol.SYMBOLIC_EXPRESSION)
					|| OMTypeChecker.isOMAWithSymbol(arguments.get(1), OMSymbol.SYMBOLIC_EXPRESSION)) {
				return OMCreator.createOMA(OMSymbol.ARITH1_POWER, arguments);
			}
			throw new FunctionInvalidArgumentTypeException(this, "integer, float, double");
		}
	}
	
	@Override
	public Object generatePalette(List<Object> arguments) throws FunctionNotImplementedException {
		List<Object> args = new ArrayList<Object>();
		switch(arguments.size()) {
		case 2:{
			args.add(OMToPaletteVisitor.visit(arguments.get(0)));
			args.add(OMToPaletteVisitor.visit(arguments.get(1)));
			break;
		}
		case 1: {
			args.add(OMToPaletteVisitor.visit(arguments.get(0)));
			args.add(OMSymbol.EDITOR1_INPUT_BOX);
			break;
		}
		 default: {
			 args.add(OMSymbol.EDITOR1_INPUT_BOX);
			 args.add(OMSymbol.EDITOR1_INPUT_BOX);
		 }
		}
		
		return OMCreator.createOMA(OMSymbol.ARITH1_ROOT, args);
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
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		return "(" + getSageSyntax(arguments.get(0)) + ")^(" + getSageSyntax(arguments.get(1)) + ")";
	}
	
	@Override
	public String getPartialLatexSyntax(List<Object> arguments)
			throws FunctionException, NoRepresentationAvailableException {
		
		return "{"+arguments.get(0)+"}^{" + arguments.get(1)+"}";
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException {
		return getStringSyntax(arguments.get(0)) + "^" + getStringSyntax(arguments.get(1));
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException {
		return  "(" + getRSyntax(arguments.get(0)) + "^" + getRSyntax(arguments.get(1)) + ")";
	}
}
