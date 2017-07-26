package de.uni_due.s3.evaluator.core.function.cas_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

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
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluateInSage extends TestFunctionAbstract {
	Function func = new EvaluateInSage();

	@Test
	public void testEvaluateInSageIntegration1() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		HashMap<String, OMOBJ> exer = new HashMap<>();
		HashMap<Integer, OMOBJ> fill = new HashMap<>();
		OMOBJ t1 = new OMOBJ();
		t1.setOMI(OMCreator.createOMI(55));
		exer.put("a", t1);

		OMOBJ t = ExpressionParser.parse("evaluateInSage('[var=a].imag()')", exer, fill);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(0), actual.getOMI());
	}
	
	@Test
	public void testEvaluateInSageIntegration2() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		HashMap<String, OMOBJ> exer = new HashMap<>();
		HashMap<Integer, OMOBJ> fill = new HashMap<>();
		OMOBJ t1 = new OMOBJ();
		t1.setOMI(OMCreator.createOMI(55));
		exer.put("a", t1);

		OMOBJ t = ExpressionParser.parse("evaluateInSage('[var=a].real()')", exer, fill);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(55), actual.getOMI());
	}
	
	@Test
	public void testEvaluateInSageIntegration3() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		HashMap<String, OMOBJ> exer = new HashMap<>();
		HashMap<Integer, OMOBJ> fill = new HashMap<>();
		OMOBJ t1 = new OMOBJ();
		t1.setOMI(OMCreator.createOMI(55));
		exer.put("a", t1);

		OMOBJ t = ExpressionParser.parse("evaluateInSage('s = [var=a].real(); s*([var=a]-53)')", exer, fill);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(110), actual.getOMI());
	}
	
	@Test
	public void testEvaluateInSageIntegration4() throws UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		HashMap<String, OMOBJ> exer = new HashMap<>();
		HashMap<Integer, OMOBJ> fill = new HashMap<>();
		OMOBJ t1 = new OMOBJ();
		t1.setOMI(OMCreator.createOMI(5));
		exer.put("a", t1);
		fill.put(2, t1);

		OMOBJ t = ExpressionParser.parse("evaluateInSage('s = [var=a].real(); s*[pos=2]')", exer, fill);
		OMOBJ actual = OMExecutor.execute(t);

		assertEquals(OMCreator.createOMI(25), actual.getOMI());
	}
}