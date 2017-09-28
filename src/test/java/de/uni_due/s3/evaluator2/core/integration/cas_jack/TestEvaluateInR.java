package de.uni_due.s3.evaluator2.core.integration.cas_jack;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.integration.TestIntegration;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.r.TestR;
import de.uni_due.s3.evaluator2.sage.TestSage;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestEvaluateInR extends TestIntegration  {
	
	@Test
	public void testEvaluateInRInteger() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9')", null, null);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(9));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRFloat() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('9.123')", null, null);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		OMOBJ expected = new OMOBJ();
		expected.setOMF(OMCreator.createOMF(9.123));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRString() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('\"This\"')", null, null);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("This"));
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRBool() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('TRUE')", null, null);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		OMOBJ expected = new OMOBJ();
		expected.setOMS(OMSymbol.LOGIC1_TRUE);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testEvaluateInRList() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('list(1, 2, 3, 4)')", null, null);
		OMOBJ actual = OMExecutor.execute(omobj);
		
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
	public void testEvaluateInRWithExerciseVariables() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[var=a]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		assertEquals(OMCreator.createOMI(0),actual.getOMI());
	}
	
	@Test
	public void testEvaluateInRWithFillInVariables() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[pos=1]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		assertEquals(OMCreator.createOMI(0),actual.getOMI());
	}
	
	@Test
	public void testEvaluateInRWithFillInVariablesAsVector() throws OpenMathException, EvaluatorException {
		fillInVariableMap.put(20, ExpressionParser.parse("vector(1,1)", exerciseVariableMap, fillInVariableMap));
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('[pos=20]')",exerciseVariableMap, fillInVariableMap);
		OMOBJ actual = OMExecutor.execute(omobj);
		
		assertEquals(OMCreator.createOMI(0),actual.getOMI());
	}
	
	@Test(expected = CasEvaluationException.class)
	public void testEvaluateInRWrongSyntax() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("evaluateInR('wrong input')", null, null);
		OMExecutor.execute(omobj);
	}
	
}