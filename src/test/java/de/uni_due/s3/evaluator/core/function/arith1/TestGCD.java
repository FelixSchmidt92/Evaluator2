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
import de.uni_due.s3.evaluator.core.function.functions.arith1.GCD;
import de.uni_due.s3.evaluator.core.functionData.OMSEvaluatorSyntaxDictionary;
import de.uni_due.s3.evaluator.core.functionData.OMSFunctionDictionary;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestGCD {
	
	@Test
	public void testGcdInteger() throws FunctionException{
		GCD func = new GCD();
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("12", result.getValue());
	}
	
	
	@Test
	public void testGcdIntegration() throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException{
		OMOBJ omobj = ExpressionParser.parse("gcd(10,5)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("times(100,125)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("25", result.getOMI().getValue());
	}
	
	@Test
	public void testGcdSageSyntax() throws OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException{
		GCD func = (GCD)OMSFunctionDictionary.getInstance().getFunction(OMSEvaluatorSyntaxDictionary.getInstance().getOMS("GCD"));
		OMF omf = OMCreator.createOMF(1.0);
		OMI omi = OMCreator.createOMI(10);
		
		OMOBJ omobj = ExpressionParser.parse("gcd(5,10)", null, null);
		List<Object> args = omobj.getOMA().getOmel();
		OMS oms = (OMS)args.get(0);
		args.remove(0);
		
		assertEquals("gcd(5,10)", func.getPartialSageSyntax(args));
		args = new ArrayList<Object>(2);
		args.add(omf);
		args.add(omf);
		assertEquals("gcd(1.0,1.0)",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentException.class)
	public void testGcdWithWrongArguments() throws OMOBJChildNotSupportedException, OMObjectNotSupportedException, FunctionException{
		OMOBJ omobj = ExpressionParser.parse("gcd(2,'test')", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("5", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("gcd('test',17)", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals("-7", result.getOMI().getValue());
	}
	
}