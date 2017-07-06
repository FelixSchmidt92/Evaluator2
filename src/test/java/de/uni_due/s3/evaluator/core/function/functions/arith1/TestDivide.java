package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openmath.omutils.OMCreator;
import org.openmath.omutils.OpenMathException;
import org.openmath.openmath.OMF;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMOBJ;
import org.openmath.openmath.OMS;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Divide;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Times;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestDivide {
	
	@Test
	public void testDivideInteger() throws FunctionException{
		Divide func = new Divide();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(0.75), result.getDec());
	}
	
	@Test
	public void testDivideFloat() throws FunctionException{
		Divide func = new Divide();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.6));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(2.5/5.6), result.getDec());
	}
	
	@Test
	public void testDivideMixed() throws FunctionException{
		Divide func = new Divide();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(2.00));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("500", result.getValue());
	}
	
	@Test
	public void testDivideIntegration() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("10/5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("2", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("divide(1,-4)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals(new Double(-0.25), result.getOMF().getDec());
	}
	
	@Test
	public void testDivideSageSyntax() throws FunctionArgumentNumberException, NoRepresentationAvailableException, CasException{
		Divide func = (Divide)OMSFunctionDictionary.getInstance().getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("times"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("divide(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		assertEquals("5 / 10", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("1.0 / 1.0",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testMinusWithWrongArguments() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("10/'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("divide('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}
