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

public class TestIsMNMatrix extends TestFunctionAbstract {
	
	HashMap<String, OMOBJ> exMap = new HashMap<>();

	@Test
	public void integrationTestMNMatrix1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMNMatrix(-3,1,1)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMNMatrix2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMNMatrix(matrix(matrixrow(1,2)),1,2)", null, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMNMatrix3() throws EvaluatorException, OpenMathException {
		exMap.put("A", ExpressionParser.parse("matrix(matrixrow(1,2),matrixrow(1,2))", null, null));
		OMOBJ omobj = ExpressionParser.parse("isMNMatrix([var=A],2,2)", exMap, null);
		assertEquals(OMSymbol.LOGIC1_TRUE, OMToResultVisitor.getInstance().visit(omobj));
	}
	
	@Test
	public void integrationTestMNMatrix4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("isMNMatrix(matrix(),2,2)", null, null);
		assertEquals(OMSymbol.LOGIC1_FALSE, OMToResultVisitor.getInstance().visit(omobj));
	}
}
