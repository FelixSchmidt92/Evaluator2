package de.uni_due.s3.evaluator2.core.function.linalg_jack;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixEigenvalue extends TestFunctionAbstract {

	Function func = new RandomMatrixEigenvalue();

	static OMOBJ omobj1 = null;
	static OMOBJ omobj2 = null;
	static OMOBJ omobj3 = null;

	@BeforeClass
	public static void init() throws OpenMathException, EvaluatorException {
		omobj3 = ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null);
		omobj2 = ExpressionParser.parse("matrix(matrixrow(1,0), matrixrow(0,1))", null, null);
		omobj1 = ExpressionParser.parse("matrix(matrixrow(1))", null, null);
	}

	@Test
	public void testRandomMatrixEigenValueWithIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 1, '[1]', '[1]')", null, null);
		obj = OMExecutor.execute(obj);

		assertEquals(omobj1, obj);
	}

	@Test
	public void testRandomMatrixEigenValueCaseIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 2, '[1,1]', '[1,1]')", null, null);
		obj = OMExecutor.execute(obj);

		assertEquals(omobj2, obj);
	}

	@Test
	public void testRandomMatrixEigenValueCaseIntegration3() throws OpenMathException, EvaluatorException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', '[1,1,1]')", null, null);
		obj = OMExecutor.execute(obj);

		assertEquals(omobj3, obj);
	}

	@Test(expected = FunctionInvalidArgumentException.class)
	public void testRandomMatrixEigenValueCaseIntegration4() throws OpenMathException, EvaluatorException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', 12)", null, null);
		obj = OMExecutor.execute(obj);

		assertEquals(omobj3, obj);
	}

}
