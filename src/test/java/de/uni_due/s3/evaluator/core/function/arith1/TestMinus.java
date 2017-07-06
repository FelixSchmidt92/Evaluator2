package de.uni_due.s3.evaluator.core.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.openmath.omutils.OMCreator;
import org.openmath.omutils.OpenMathException;
import org.openmath.openmath.OMF;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMOBJ;
import org.openmath.openmath.OMS;
import org.openmath.openmath.OMSTR;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Minus;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestMinus {
	
	private OMI omi = OMCreator.createOMI(1);
	private OMF omf = OMCreator.createOMF(2.5);
	private OMSTR omstr = OMCreator.createOMSTR("test");
	
	@Test
	public void testMinusInteger() throws FunctionException{
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omi);
		args.add(omi);
		OMI result = (OMI) func.evaluate(args);
		assertEquals("0", result.getValue());
	}
	
	@Test
	public void testMinusFloat() throws FunctionException{
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(OMCreator.createOMF(5.8));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-3.3), result.getDec());
	}
	
	@Test
	public void testMinusMixed() throws FunctionException{
		Minus func = new Minus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(omi);
		args.add(omf);
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-1.5), result.getDec());
	}
	
	@Test
	public void testMinusIntegration() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("10-5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("minus(10,17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
	@Test
	public void testMinusSageSyntax() throws FunctionArgumentNumberException, NoRepresentationAvailableException, CasException{
		Minus func = (Minus)OMSFunctionDictionary.getInstance().getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("minus"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("minus(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		assertEquals("5 - 10", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("1.0 - 1.0",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testMinusWithWrongArguments() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("10-'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("minus('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}
