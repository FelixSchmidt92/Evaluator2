package de.uni_due.s3.evaluator2.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsTuple extends TestFunctionAbstract {

	HashMap<String, OMOBJ> exMap = new HashMap<>();

	@Test
	public void integrationTestMatrix1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(-3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(matrix(matrixrow(1,2)))", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix3() throws EvaluatorException, OpenMathException {
		exMap.put("A", ExpressionParser.parse("tuple(vector(1,2),1,2)", null, null));
		OMOBJ omobj = ExpressionParser.parse("isTuple([var=A])", exMap, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isTuple(tuple())", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
