package de.uni_due.s3.evaluator2.core.function.list1;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * Implementation of List. This is a Terminal so it returns itself!
 * 
 * @author dlux
 *
 */
public class List extends Function {

	@Override
	protected Object execute(java.util.List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, arguments);
	}

	@Override
	protected int minArgs() {
		return 0;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialLatexSyntax(java.util.List<Object> arguments)
			throws EvaluatorException {
		String args ="";
		for(Object arg:arguments) {
			args += getLatexSyntax(arg)+",";
		}
		args = args.substring(0, args.length()-1);
		return "\\left\\{"+args+"\\right\\}";
	}
	
	@Override
	public String getPartialRSyntax(java.util.List<Object> arguments) throws EvaluatorException {
		
		String list = "list(";
		for (Object arg : arguments) {
			list += getSageSyntax(arg) + ", ";
		}
		list = list.substring(0, list.length() - 2); // Removing ", "
		
		return list;
	}

	@Override
	public java.util.List<Object> getPartialListSyntax(java.util.List<Object> omel) throws EvaluatorException {
		return omel;
	}

	@Override
	public String getPartialSageSyntax(java.util.List<Object> arguments) throws EvaluatorException {
		String set = "[";
		for (Object arg : arguments) {
			set += getSageSyntax(arg) + ", ";
		}
		set = set.substring(0, set.length() - 2); // Removing ", "
	
		return set + "]";
	}
}
