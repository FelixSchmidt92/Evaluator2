package de.uni_due.s3.evaluator.core.function.string_jack;

import java.util.List;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function is not registered in OMSEvaluatorSyntaxDictionary.
 * 
 * This is a Terminal like Vector or Matrix, but not callable by the Evaluator.
 * The ParserTree creates a custom JackString in OMA.
 * 
 * This class translates a String with variables in it (which are NOT an Expression)
 * into a concatening String with the specific Syntax.
 * 
 * 
 * 
 * @author dlux
 *
 */
public class TextValueWithVariables extends Function {

	@Override
	protected Object execute(List<Object> arguments) throws FunctionException, CasEvaluationException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTVALUEWITHVARIABLES, arguments);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	
	/**
	 * Here we want a Concatination of Strings and OMA'S in Sage Syntax
	 * 
	 * e.g 'var(x) + [pos=1].det()'
	 * 
	 * if pos=1 is a Matrix: [[1,2],[3,4]]
	 * should return: "var(x) + [[1,2],[3,4]].det()"
	 * 
	 */
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws FunctionInvalidNumberOfArgumentsException,
			NoRepresentationAvailableException, FunctionInvalidArgumentTypeException {
		String temp = "";
		
		for (Object obj : arguments) {
			if (obj instanceof OMSTR) {
				//removing ' at beginning and end of OMSTR, because for Sage-Commands
				temp += ((OMSTR) obj).getContent();
			}else {
				temp += getSageSyntax(obj);
			}
		}
		return temp;
	}

}
