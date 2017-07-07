package de.uni_due.s3.evaluator.core.function.functions.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestUnaryMinus {

	private UnaryMinus func = new UnaryMinus();
	@Test
	public void testUnaryMinusInteger() throws FunctionException, CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(3));
		OMI result = (OMI) func.evaluate(args);
		assertEquals("-3", result.getValue());
	}
	
	@Test
	public void testUnaryMinusFloat() throws FunctionException, CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{

		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMF(2.5));
		OMF result = (OMF) func.evaluate(args);
		assertEquals(new Double(-2.5), result.getDec());
	}
	

	
	@Test
	public void testUnaryMinusIntegration() throws FunctionException, OpenMathException, CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException{
		OMOBJ omobj = ExpressionParser.parse("-1075", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals("-1075", result.getOMI().getValue());
		
		omobj = ExpressionParser.parse("-2075.32", null, null);
		result = OMExecutor.execute(omobj);
		assertEquals(new Double(-2075.32), result.getOMF().getDec());
	}
	
	@Test
	public void testUnaryMinusSageSyntax() throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException{
		OMF omf = OMCreator.createOMF(1.0);

		List<Object>args = new ArrayList<Object>(1);
		args.add(omf);

		assertEquals("-1.0",func.getPartialSageSyntax(args));
	}
	
	@Test(expected=FunctionInvalidArgumentTypeException.class)
	public void testUnaryMinusWithWrongArguments() throws FunctionException, OpenMathException, CasEvaluationException, CasNotAvailableException, NoRepresentationAvailableException{
		OMOBJ omobj = ExpressionParser.parse("-'test'", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
	}
	
}
