package de.uni_due.s3.evaluator.core.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openmath.omutils.OMCreator;
import org.openmath.openmath.OMF;
import org.openmath.openmath.OMI;
import org.openmath.openmath.OMOBJ;
import org.openmath.openmath.OMS;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.arith1.Modulus;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoSageRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestModulus {
	
	
	@Test
	public void testModulusInteger() throws FunctionException{
		Modulus func = new Modulus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(5));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("2", result.getValue());
	}
	
	@Test
	public void testModulusFloat() throws FunctionException{
		Modulus func = new Modulus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(3.5));
		args.add(OMCreator.createOMF(3.0));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(0.5), result.getDec());
	}
	
	@Test
	public void testModulusMixed() throws FunctionException{
		Modulus func = new Modulus();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(10));
		args.add(OMCreator.createOMF(2.7));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-1.5), result.getDec());
	}
	
	@Test
	public void testModulusIntegration() throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException{
		OMOBJ omobj = ExpressionParser.parse("10%5", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("0", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("modulus(3,-2)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("1", result.getOMI().getValue());
	}
	
	@Test
	public void testModulusSageSyntax() throws OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException{
		Modulus func = (Modulus)OMSFunctionDictionary.getInstance().getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("minus"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("modulus(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		assertEquals("5 % 10", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("1.0 % 1.0",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testMinusWithWrongArguments() throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException{
		OMOBJ omobj = ExpressionParser.parse("10%'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("modulus('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}
