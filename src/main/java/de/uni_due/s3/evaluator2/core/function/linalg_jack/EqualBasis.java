package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.InvalidResultTypeException;
import de.uni_due.s3.evaluator2.sage.Sage;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This function Checks if the Two given Sets of Vectors have the same Basis.
 * 
 * 
 * If they have true will be returned, false otherwise.
 * 
 * @author dlux
 *
 */
public class EqualBasis extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {

		List<Object> list1 = getListSyntax(arguments.get(0));
		List<Object> list2 = getListSyntax(arguments.get(1));
		getIntegerSyntax(arguments.get(2)); //throws exception if not
		
		for (Object vector : list1) {
			if (!OMTypeChecker.isOMAWithSymbol(vector, OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, "The sets can only contain Vectors for this Function");
			}
		}

		for (Object vector : list2) {
			if (!OMTypeChecker.isOMAWithSymbol(vector, OMSymbol.LINALG2_VECTOR)) {
				throw new FunctionInvalidArgumentException(this, "The sets can only contain Vectors for this Function");
			}
		}

		Object result = Sage.evaluateInCAS(getPartialSageSyntax(arguments));

		if (!(OMTypeChecker.isOMS(result)
				&& (((OMS) result).equals(OMSymbol.LOGIC1_TRUE) || ((OMS) result).equals(OMSymbol.LOGIC1_FALSE)))) {
			throw new InvalidResultTypeException(this, "true, false");
		}
		return result;
	}

	@Override
	protected int minArgs() {
		return 3;
	}

	@Override
	protected int maxArgs() {
		return 3;
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException {
		StringBuilder preVector = new StringBuilder();
		List<String> vList1 = new ArrayList<>();
		List<Object> vectorList1 = getListSyntax(arguments.get(0));
		int i = 1;
		for (Object vector1 : vectorList1) {
			preVector.append("v");
			preVector.append(i);
			preVector.append(" = ");
			preVector.append(getSageSyntax(vector1));
			preVector.append(";");
			vList1.add("v" + i);
			i++;
		}
		List<String> vList2 = new ArrayList<>();
		List<Object> vectorList2 = getListSyntax(arguments.get(1));
		int j = 1;
		for (Object vector2 : vectorList2) {
			preVector.append("w");
			preVector.append(j);
			preVector.append(" = ");
			preVector.append(getSageSyntax(vector2));
			preVector.append(";");
			vList2.add("w" + j);
			j++;
		}
		StringBuilder request = new StringBuilder();
		request.append(preVector.toString());
		request.append("V=RR^" + getSageSyntax(arguments.get(2)) + ";");
		request.append("U=V.span([" + String.join(",", vList1) + "]);");
		request.append("W=V.span([" + String.join(",", vList2) + "]);");
		request.append("U==W");
		return request.toString();
	}
}
