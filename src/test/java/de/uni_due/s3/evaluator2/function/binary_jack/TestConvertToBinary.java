package de.uni_due.s3.evaluator2.function.binary_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.binary_jack.ConvertToBinary;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestConvertToBinary extends TestFunctionAbstract {

	Function func = new ConvertToBinary();

	@Test
	public void TestConvertToBinaryOMI() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(3);

		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11"), res);
	}

	@Test
	public void TestConvertToBinaryNegativeOMI() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(-3);

		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111111111111111111111111101"), res);
	}

	@Test
	public void TestConvertToBinaryOMF() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(1.2);

		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111110011001100110011001100110011001100110011001100110011"), res);
	}

	@Test
	public void TestConvertToBinaryNegativeOMF() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(-1.2);

		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("1011111111110011001100110011001100110011001100110011001100110011"), res);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestConvertToBinaryOMSTR() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("String");

		args.add(omstr);
		func.evaluate(args);
	}

	@Test
	public void TestConvertToBinaryIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary(3)", null, null);
		Object result = OMToResultVisitor.getInstance().visit(omobj);
		assertEquals(OMCreator.createOMSTR("11"), result);
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void TestConvertToBinaryWithMoreArgs() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary(3, 2)", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void TestConvertToBinaryWithLesssArgs() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestConvertToBinaryOMV() throws EvaluatorException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omv = OMCreator.createOMV("StringX");

		args.add(omv);
		func.evaluate(args);
	}

}