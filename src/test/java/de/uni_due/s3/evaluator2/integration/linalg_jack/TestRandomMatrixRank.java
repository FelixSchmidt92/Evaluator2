package de.uni_due.s3.evaluator2.integration.linalg_jack;

import java.util.HashMap;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixRank extends TestIntegration {

	static HashMap<Integer, OMOBJ> randomMatrixRankFillInVariableMap = new HashMap<>();
	static HashMap<String, OMOBJ> randomMatrixRankExerciseVariableMap = new HashMap<>();

	@BeforeClass
	public static void beforeTest() {
		try {
			randomMatrixRankFillInVariableMap.put(1, OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));

			randomMatrixRankExerciseVariableMap.put("a", OMConverter.toObject("<OMOBJ><OMI>3</OMI></OMOBJ>"));
		} catch (JAXBException e) {
			throw new RuntimeException("Erzeugung der OpenMath exercise Variablen für TestIntegration fehlgeschlagen",
					e);
		}
	}

	@Test
	public void testRandomMatrixEigenValue1() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('QQ', '3', '2', '2')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test
	public void testRandomMatrixEigenValue2() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('QQ', '3', '3', '1')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test
	public void testRandomMatrixEigenValue3() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('ZZ', '2', '2', '1', '2')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test
	public void testRandomMatrixEigenValueWithInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('QQ', '[pos=1]', '2', '2')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test
	public void testRandomMatrixEigenValueWithVariables() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('QQ', '[var=a]', '2', '2')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test(expected = ParserException.class)
	public void testRandomMatrixEigenValueWithONECharacter() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank(ab, ab, ab, ab)", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test(expected = CasEvaluationException.class)
	public void testRandomMatrixEigenValueWithEmptyStringArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('', '', '', '')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomMatrixEigenValueWithEmptyArgument() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank()", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test(expected = UndefinedExerciseVariableException.class)
	public void testRandomMatrixEigenValueWithoutExerciseVariable() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('[var=j]', '[var=j]', '[var=j]', '[var=j]')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}

	@Test(expected = UndefinedFillInVariableException.class)
	public void testRandomMatrixEigenValueWithoutInput() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("randomMatrixRank('[pos=42]', '[pos=42]', '[pos=42]', '[pos=42]')", randomMatrixRankExerciseVariableMap, randomMatrixRankFillInVariableMap);
	}
}
