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

public class TestIsMatrix extends TestFunctionAbstract {
	
	HashMap<String, OMOBJ> exMap = new HashMap<>();

	@Test
	public void integrationTestMatrix1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMatrix(-3)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMatrix2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMatrix(matrix(matrixrow(1,2)))", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMatrix3() throws EvaluatorException, OpenMathException {
		exMap.put("A", ExpressionParser.parse("matrix(matrixrow(1,2),matrixrow(1,2))", null, null));
		OMOBJ omobj = ExpressionParser.parse("isMatrix([var=A])", exMap, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMatrix4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMatrix(matrix())", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
