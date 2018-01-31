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

public class TestIsNTuple extends TestFunctionAbstract {

	HashMap<String, OMOBJ> exMap = new HashMap<>();

	@Test
	public void integrationTestMatrix1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNTuple(-3,1)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNTuple(matrix(matrixrow(1,2)),2)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix3() throws EvaluatorException, OpenMathException {
		exMap.put("A", ExpressionParser.parse("tuple(vector(1,2),1,2)", null, null));
		OMOBJ omobj = ExpressionParser.parse("isNTuple([var=A],3)", exMap, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}

	@Test
	public void integrationTestMatrix4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isNTuple(tuple(),0)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
