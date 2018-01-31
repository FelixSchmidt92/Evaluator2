package de.uni_due.s3.evaluator2.function.linalg_jack;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.linalg_jack.RandomMatrixEigenvalue;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixEigenvalue extends TestFunctionAbstract {

	Function func = new RandomMatrixEigenvalue();

	static OMOBJ omobj1 = null;
	static OMOBJ omobj2 = null;
	static OMOBJ omobj3 = null;

	@BeforeClass
	public static void init() throws EvaluatorException, OpenMathException {
		omobj3 = ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null);
		omobj2 = ExpressionParser.parse("matrix(matrixrow(1,0), matrixrow(0,1))", null, null);
		omobj1 = ExpressionParser.parse("matrix(matrixrow(1))", null, null);
	}

	@Test
	public void testRandomMatrixEigenValueWithIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 1, '[1]', '[1]')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(obj);

		assertEquals(omobj1, OMCreator.createOMOBJ(result));
	}

	@Test
	public void testRandomMatrixEigenValueCaseIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 2, '[1,1]', '[1,1]')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(obj);

		assertEquals(omobj2, OMCreator.createOMOBJ(result));
	}

	@Test
	public void testRandomMatrixEigenValueCaseIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', '[1,1,1]')", null, null);
		Object result = OMToResultVisitor.getInstance().visit(obj);

		assertEquals(omobj3, OMCreator.createOMOBJ(result));
	}

	@Test(expected = CasEvaluationException.class)
	public void testRandomMatrixEigenValueCaseIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', 3, '[1,1,1]', 12)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(obj);

		assertEquals(omobj3, OMCreator.createOMOBJ(result));
	}

	@Test
	public void testRandomMatrixEigenValueCaseIntegration5() throws EvaluatorException, OpenMathException {
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue(qq(), 3, list(1,1,1), list(1,1,1))", null, null);
		Object result = OMToResultVisitor.getInstance().visit(obj);

		assertEquals(omobj3, OMCreator.createOMOBJ(result));
	}
}
