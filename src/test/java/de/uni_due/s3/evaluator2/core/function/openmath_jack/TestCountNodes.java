package de.uni_due.s3.evaluator2.core.function.openmath_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.core.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCountNodes extends TestFunctionAbstract {

	private final Function func = new CountNodes();

	@Test
	public void testCountNodesWithOMI() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		assertEquals(OMCreator.createOMI(1), func.evaluate(omel));
	}

	@Test
	public void testCountNodesWithOMF() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(2.3));
		assertEquals(OMCreator.createOMI(1), func.evaluate(omel));
	}

	@Test
	public void testCountNodesWithOMSTR() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMSTR("String"));
		assertEquals(OMCreator.createOMI(1), func.evaluate(omel));
	}

	@Test
	public void testCountNodesIntegration1() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("countNodes(1+2)", null, null);
		OMOBJ result = OMToResultVisitor.getInstance().execute(omobj);
		assertEquals(OMCreator.createOMI(4), result.getOMI());
	}

	@Test
	public void testCountNodesIntegration2() throws OpenMathException, EvaluatorException {
		OMOBJ omobj = ExpressionParser.parse("countNodes(1+2/3)", null, null);
		OMOBJ result = OMToResultVisitor.getInstance().execute(omobj);
		assertEquals(OMCreator.createOMI(7), result.getOMI());
	}

	@Test
	public void testCountNodesWithOMS() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMS("someCD", "someName"));
		assertEquals(OMCreator.createOMI(1), func.evaluate(omel));
	}

	@Test
	public void testCountNodesWithOMV() throws OpenMathException, EvaluatorException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMV("someX"));
		assertEquals(OMCreator.createOMI(1), func.evaluate(omel));
	}

	@Test
	public void testCountNodesWithOMAWith3() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(45.1));
		omel.add(OMCreator.createOMI(45));

		OMA oma = OMCreator.createOMA(OMCreator.createOMS("someCD", "someName"), omel);

		args.add(oma);
		assertEquals(OMCreator.createOMI(4), func.evaluate(args));
	}

	@Test
	public void testCountNodesWithOMAWith4in3Recursively() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		ArrayList<Object> omel2 = new ArrayList<>();

		omel2.add(OMCreator.createOMSTR("abc123"));
		omel2.add(OMCreator.createOMV("lala"));

		OMA oma2 = OMCreator.createOMA(OMCreator.createOMS("inOMA", "inOMAName"), omel2);

		omel.add(OMCreator.createOMF(45.1));
		omel.add(OMCreator.createOMI(45));
		omel.add(oma2);

		OMA oma = OMCreator.createOMA(OMCreator.createOMS("someCD", "someName"), omel);

		args.add(oma);
		assertEquals(OMCreator.createOMI(8), func.evaluate(args));
	}

	@Test
	public void testCountNodesWithOMAWith4in4in2Recursively() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		ArrayList<Object> omel2 = new ArrayList<>();
		ArrayList<Object> omel3 = new ArrayList<>();

		omel3.add(OMCreator.createOMF(1.1));

		OMA oma3 = OMCreator.createOMA(OMCreator.createOMS("lastCD", "lastName"), omel3);

		omel2.add(OMCreator.createOMSTR("abc123"));
		omel2.add(OMCreator.createOMV("lala"));
		omel2.add(oma3);

		OMA oma2 = OMCreator.createOMA(OMCreator.createOMS("inOMA", "inOMAName"), omel2);

		omel.add(OMCreator.createOMF(45.1));
		omel.add(OMCreator.createOMI(45));
		omel.add(oma2);

		OMA oma = OMCreator.createOMA(OMCreator.createOMS("someCD", "someName"), omel);

		args.add(oma);
		assertEquals(OMCreator.createOMI(11), func.evaluate(args));
	}
}