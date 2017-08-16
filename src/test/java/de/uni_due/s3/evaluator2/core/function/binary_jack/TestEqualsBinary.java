package de.uni_due.s3.evaluator2.core.function.binary_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentTypeException;
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

	public static Object[][] arguments = { { "10", "1010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMF(1.1) }, // [0]
			{ "10", "001010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMA(OMSymbol.SET1_SET, new ArrayList<>()) },
			{ "11", "1011", OMSymbol.LOGIC1_TRUE, OMCreator.createOMS("myCD", "myName") },
			{ "10", "101000", OMSymbol.LOGIC1_FALSE, OMCreator.createOMI(2) },
			{ "-10", "-0001010", OMSymbol.LOGIC1_TRUE, OMCreator.createOMI(50) },
			{ "-7", "-000111", OMSymbol.LOGIC1_TRUE, OMCreator.createOMF(0.1) },// [5]
	};

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
	public void testEqualsBinary() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR(left));
		args.add(OMCreator.createOMSTR(right));

		assertEquals(expected, func.evaluate(args));
	}

	@Test(expected = FunctionInvalidArgumentTypeException.class)
	public void testEqualsBinaryWithInvalidType() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(invalidType);
		args.add(invalidType);

		func.evaluate(args);
	}
}
