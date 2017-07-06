package de.uni_due.s3.evaluator.core.function.arith1;

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
import de.uni_due.s3.evaluator.core.function.functions.arith1.Abs;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestAbs {
	private Abs func = new Abs();
	@Test
	public void testAbsInteger() throws FunctionException{
	
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(-3));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("3", result.getValue());
	}
	
	@Test
	public void testAbsFloat() throws FunctionException{
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(-2.5));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(2.5), result.getDec());
	}
	
	@Test
	public void testAbsIntegration() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("abs(-13)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("13", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("abs(10)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("10", result.getOMI().getValue());
	}
	
	@Test
	public void testAbsSageSyntax() throws  FunctionArgumentNumberException, NoRepresentationAvailableException, CasException{
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("abs(5)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);

		assertEquals("abs(5)", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		assertEquals("abs(1.0)",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testAbsWithWrongArguments() throws FunctionException, OpenMathException{
		OMOBJ omobj = ExpressionParser.parse("abs('test')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("abs('test')", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}
