package de.uni_due.s3.evaluator.core.function.functions.arith1;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;


public class TestPower {

	private Power func = new Power();
	private List<Object> args;
	
	@Test
	public void testPowerWithInteger() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		
		OMI result = (OMI) func.evaluate(args);
		assertEquals("8", result.getValue());
	}
	
	@Test
	public void testPowerWithFloat() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.4));
		args.add(OMCreator.createOMF(3.9));
		
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(Math.pow(2.4, 3.9)),result.getDec());
	}
	
	@Test
	public void testPowerWithMixedArgs() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMF(3.5));
		
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(Math.pow(2, 3.5)), result.getDec());
	}
	
	@Test
	public void testPowerIntegration() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("pow(2,3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("8", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("pow(2,-2)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals(new Double(0.25),result.getOMF().getDec());
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testPowerWithInvalidArgumentType() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMSTR("2"));
		args.add(OMCreator.createOMV("test"));
		
		func.evaluate(args);
	}
	
	@Test
	public void testPowerSageSyntax() throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException{
		OMOBJ omobj = ExpressionParser.parse("pow(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		args.remove(0); //remove oms
		
		//assertEquals("power(5,10)", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(1.45));
		args.add(OMCreator.createOMF(3));
		assertEquals("power(1.45,3.0)",func.getPartialSageSyntax(args));
	}
	
	
}
