package de.uni_due.s3.evaluator.core.function.functions.binary_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestConvertToBinary extends TestFunction{
	
	Function func = new ConvertToBinary();
	
	@Test
	public void TestCounvertToBinaryOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(3);
		
		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11"), res);
	}
	
	@Test
	public void TestCounvertToBinaryNegativeOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(-3);
		
		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111111111111111111111111101"), res);
	}
	
	@Test
	public void TestCounvertToBinaryOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(1.2);
		
		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111110011001100110011001100110011001100110011001100110011"), res);
	}
	
	@Test
	public void TestCounvertToBinaryNegativeOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(-1.2);
		
		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("1011111111110011001100110011001100110011001100110011001100110011"), res);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestCounvertToBinaryOMSTR() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("String");
		
		args.add(omstr);
		func.evaluate(args);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestCounvertToBinaryOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("", ""), new ArrayList<>());
		
		args.add(oma);
		func.evaluate(args);
	}
	
	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestCounvertToBinaryOMV() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException{
		ArrayList<Object> args = new ArrayList<>();
		OMV omv = OMCreator.createOMV("StringX");
		
		args.add(omv);
		func.evaluate(args);
	}
	
}