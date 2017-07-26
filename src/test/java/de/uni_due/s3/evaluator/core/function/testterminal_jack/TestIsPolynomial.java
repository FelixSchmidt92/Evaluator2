package de.uni_due.s3.evaluator.core.function.testterminal_jack;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsPolynomial extends TestFunctionAbstract{

	Function func = new IsPolynomial();
	
	@Test
	public void testIsPolynomial() throws UndefinedFillInVariableException, UndefinedExerciseVariableException,
			ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ t = ExpressionParser.parse("isPolynomial('a*2+ c', 'c')", null, null);
		//TODO ARITH1* has to support OMV's
		
		OMOBJ actual = OMExecutor.execute(t);
		
		
		System.out.println(actual);
	}
}
