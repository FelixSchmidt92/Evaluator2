package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsTuple extends TestFunctionAbstract {

	HashMap<String, OMOBJ> exMap = new HashMap<>();

	@Test
	public void integrationTestMatrix1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(-3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, new OMToResultVisitor().execute(omobj).getOMS());
	}

	@Test
	public void integrationTestMatrix2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(matrix(matrixrow(1,2)))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, new OMToResultVisitor().execute(omobj).getOMS());
	}

	@Test
	public void integrationTestMatrix3() throws OpenMathException, EvaluatorException {
		exMap.put("A", ExpressionParser.parse("tuple(vector(1,2),1,2)", null, null));
		OMOBJ omobj = ExpressionParser.parse("isTuple([var=A])", exMap, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, new OMToResultVisitor().execute(omobj).getOMS());
	}

	@Test
	public void integrationTestMatrix4() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(tuple())", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, new OMToResultVisitor().execute(omobj).getOMS());
	}
}
