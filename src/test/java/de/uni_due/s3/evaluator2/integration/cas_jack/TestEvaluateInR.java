package de.uni_due.s3.evaluator2.integration.cas_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluateInR extends TestIntegration  {
	
	@Test
	public void testEvaluateInRInteger() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9')", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(9));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRFloat() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9.123')", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		OMOBJ expected = new OMOBJ();
		expected.setOMF(OMCreator.createOMF(9.123));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRString() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('\"This\"')", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("This"));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRBool() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('TRUE')", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRList() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('list(1, 2, 3, 4)')", null, null);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		OMOBJ expected = new OMOBJ();
		ArrayList<Object> omel = new ArrayList<>();
		
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		omel.add(OMCreator.createOMI(3));
		omel.add(OMCreator.createOMI(4));
		
		expected.setOMA(OMCreator.createOMA(OMSymbol.LIST1_LIST, omel));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRWithExerciseVariables() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[var=a]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		assertEquals(OMCreator.createOMI(0),actual.getOMI());
	}
	
	@Test
	public void testEvaluateInRWithFillInVariables() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[pos=1]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		
		assertEquals(OMCreator.createOMI(0),actual.getOMI());
	}
	
	@Test
	public void testEvaluateInRWithFillInVariablesAsVector() throws EvaluatorException, OpenMathException {
		fillInVariableMap.put(20, ExpressionParser.parse("vector(1,2)", exerciseVariableMap, fillInVariableMap));
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[pos=20]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		OMOBJ expected = new OMOBJ();
		ArrayList<Object> omel = new ArrayList<>();
		
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		
		expected.setOMA(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, omel));
		
		assertEquals(expected, actual);
	}
	
	@Test(expected = CasEvaluationException.class)
	public void testEvaluateInRWrongSyntax() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('wrong input')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
	}
	
}
