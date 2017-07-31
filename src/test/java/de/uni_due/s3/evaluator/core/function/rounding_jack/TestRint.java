package de.uni_due.s3.evaluator.core.function.rounding_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

import de.uni_due.s3.evaluator.OMExecutor;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRint extends TestFunctionAbstract {

	Function func = new Rint();

	@Test
	public void testRintInfintiy() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(Double.POSITIVE_INFINITY));

		assertEquals(OMCreator.createOMF(Double.POSITIVE_INFINITY), func.evaluate(args));
	}

	@Test
	public void testRintNaN() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(Double.NaN));

		assertEquals(OMCreator.createOMF(Double.NaN), func.evaluate(args));
	}

	@Test
	public void testRintZero() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMF(0));

		assertEquals(OMCreator.createOMI(0), func.evaluate(args));
	}

	@Test
	public void testRintRandomDoubles() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args;
		for (int i = 0; i < 1000; i++) {
			double t = new Random().nextDouble() * new Random().nextInt(100); // generate a double between 0 and 100
			args = new ArrayList<>();
			args.add(OMCreator.createOMF(t));

			assertEquals(OMCreator.createOMI((int) Math.rint(t)), func.evaluate(args));
		}
	}

	@Test
	public void testRintCaseIntegrationNumber() throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ tree = ExpressionParser.parse("rint(22.2)", null, null);
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(22));
		OMOBJ actual = OMExecutor.execute(tree); 
				
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRintCaseIntegrationText() throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		OMOBJ tree = ExpressionParser.parse("rint('22.2')", null, null);
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(22));
		OMOBJ actual = OMExecutor.execute(tree); 
				
		assertEquals(expected, actual);
	}
}
