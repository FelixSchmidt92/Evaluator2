package de.uni_due.s3.evaluator2.core.function.arith1;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.BinaryFunction;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implements plus operation for numbers Example: 3.56 + 4 = 7.56
 * 
 * NOTE: This is an n-ary function!
 * 
 * @author frichtscheid, spobel, dlux
 *
 */
public class Plus extends BinaryFunction {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double rightValue = null;
		Double result = 0d;
		for (int i = 0; i < arguments.size(); i++) {
				rightValue = getDoubleSyntax(arguments.get(i));
				result = result + rightValue;
		}
		return OMCreator.createOMIOMF(result);
	}
	
	
	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		Double rightValue = null;
		Double result = 0d;
		List<Integer> failures = new ArrayList<>();
		for (int i = 0; i < arguments.size(); i++) {
			try {
				rightValue = getDoubleSyntax(arguments.get(i));
				result = result + rightValue;
			} catch (FunctionInvalidArgumentTypeException e) {
				failures.add(i);
			}
		}
		ArrayList<Object> newArgs = new ArrayList<>();
		
		if(result != 0 || failures.size() == 1) {
			newArgs.add(OMCreator.createOMIOMF(result)); // add evaluated result
		}
		for (int faili : failures) {
			// Failures are Beforehand Type-checked.
			newArgs.add(arguments.get(faili));
		}

		return OMCreator.createOMA(OMSymbol.ARITH1_PLUS, newArgs);
	}

	@Override
	protected int minArgs() {
		return 2;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String sage = "(" + getSageSyntax(arguments.get(0)) + ")";
		
		for(int i = 1; i < arguments.size(); i++) {
			sage = "( " + sage + " + (" + getSageSyntax(arguments.get(i)) + ") )";
		}
		
		return "(( " + sage + " ))";
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String latex = getLatexSyntax(arguments.get(0));
		
		for(int i = 1; i < arguments.size(); i++) {
			latex += " + " + getLatexSyntax(arguments.get(i));
		}
		
		return latex;
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String string = getLatexSyntax(arguments.get(0));
		
		for(int i = 1; i < arguments.size(); i++) {
			string += "+" + getLatexSyntax(arguments.get(i));
		}
		
		return string;
	}
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String r = "(" + getRSyntax(arguments.get(0)) + ")";
		
		for(int i = 1; i < arguments.size(); i++) {
			r = "( " + r + " + (" + getRSyntax(arguments.get(i)) + ") )";
		}
		
		return "(( " + r + " ))";
	}

}
