package de.uni_due.s3.evaluator.core.function.binary_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.binary_jack.EqualsBinary;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

//FIXME hier fehlt ein Integrationstest !
@RunWith(Parameterized.class)
public class TestEqualsBinary extends TestFunctionAbstract {

	Function func = new EqualsBinary();
	String left, right;
	OMS expected;
	Object invalidType;

	public static Object[][] arguments = { 
			{ "1010", "1010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMF(1.1) }, // [0]
			{ "1010", "001010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMA(OMSymbol.SET1_SET, new ArrayList<>()) },
			{ "001010", "1010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMS("myCD", "myName") },
			{ "1010", "101000", OMSymbol.LOGIC1_FALSE, OMCreator.createOMI(1) },
			{ "-1010", "-0001010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMI(1) },};

	
	public TestEqualsBinary(String left, String right, OMS expected, Object invalidType) {
		this.left = left;
		this.right = right;
		this.expected = expected;
		this.invalidType = invalidType;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] in : arguments) {
			list.add(in);
		}
		return list;
	}

	@Test
	public void testEqualsBinary() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR(left));
		args.add(OMCreator.createOMSTR(right));

		assertEquals(expected, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsBinaryWithInvalidType() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(invalidType);
		args.add(invalidType);

		func.evaluate(args);
	}
}
