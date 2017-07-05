package de.uni_due.s3.evaluator.core.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.JAXBOpenMath.OMUtils.OMCreator;
import de.uni_due.s3.JAXBOpenMath.openmath.OMF;
import de.uni_due.s3.JAXBOpenMath.openmath.OMI;
import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.JAXBOpenMath.openmath.OMS;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Times;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestTimes {
	
	@Test
	public void testTimesInteger(){
		Times func = new Times();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("12", result.getValue());
	}
	
	@Test
	public void testTimesFloat(){
		Times func = new Times();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		args.add(OMCreator.createOMF(5.8));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(14.5), result.getDec());
	}
	
	@Test
	public void testTimesMixed(){
		Times func = new Times();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1000));
		args.add(OMCreator.createOMF(4.892));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("4892", result.getValue());
	}
	
	@Test
	public void testTimesIntegration(){
		OMOBJ omobj = ExpressionParser.parse("10*5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("50", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("times(10,-17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-170", result.getOMI().getValue());
	}
	
	@Test
	public void testTimesSageSyntax(){
		Times func = (Times)OMSFunctionDictionary.getInstance().getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("times"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("times(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		assertEquals("5 * 10", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("1.0 * 1.0",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testMinusWithWrongArguments(){
		OMOBJ omobj = ExpressionParser.parse("10*'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("times('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}
