package de.uni_due.s3.evaluator2.core.function.binary_jack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.OMExecutor;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.function.binary_jack.ConvertToBinary;
import de.uni_due.s3.evaluator2.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator2.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
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
	public void TestConvertToBinaryOMI() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(3);

		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11"), res);
	}

	@Test
	public void TestConvertToBinaryNegativeOMI() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(-3);

		args.add(omi);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111111111111111111111111101"), res);
	}

	@Test
	public void TestConvertToBinaryOMF() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(1.2);

		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("11111111110011001100110011001100110011001100110011001100110011"), res);
	}

	@Test
	public void TestConvertToBinaryNegativeOMF() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(-1.2);

		args.add(omf);
		Object res = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("1011111111110011001100110011001100110011001100110011001100110011"), res);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestConvertToBinaryOMSTR() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("String");

		args.add(omstr);
		func.evaluate(args);
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestConvertToBinaryOMA() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("", ""), new ArrayList<>());

		args.add(oma);
		func.evaluate(args);
	}

	@Test
	public void TestConvertToBinaryIntegration() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary(3)", null, null);
		OMOBJ result = OMExecutor.execute(omobj);
		assertEquals(OMCreator.createOMSTR("11"), result.getOMSTR());
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void TestConvertToBinaryWithMoreArgs() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary(3, 2)", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void TestConvertToBinaryWithLesssArgs() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException,
			UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ omobj = ExpressionParser.parse("convertToBinary()", null, null);
		OMExecutor.execute(omobj);
		fail();
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void TestConvertToBinaryOMV() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omv = OMCreator.createOMV("StringX");

		args.add(omv);
		func.evaluate(args);
	}

}