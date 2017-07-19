package de.uni_due.s3.evaluator.core.function.functions.openmath_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestCountNodes {

	private final Function func = new CountNodes();

	@Test
	public void testCountNodesWithOMI() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		assertEquals(func.evaluate(omel), OMCreator.createOMI(1));
	}
	
	@Test
	public void testCountNodesWithOMF() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(2.3));
		assertEquals(func.evaluate(omel), OMCreator.createOMI(1));
	}
	
	@Test
	public void testCountNodesWithOMSTR() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMSTR("String"));
		assertEquals(func.evaluate(omel), OMCreator.createOMI(1));
	}
	
	
	@Test
	public void testCountNodesWithOMS() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMS("someCD","someName"));
		assertEquals(func.evaluate(omel), OMCreator.createOMI(1));
	}
	
	@Test
	public void testCountNodesWithOMV() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMV("someX"));
		assertEquals(func.evaluate(omel), OMCreator.createOMI(1));
	}
	
	@Test
	public void testCountNodesWithOMAWith3() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMF(45.1));
		omel.add(OMCreator.createOMI(45));
		
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("someCD", "someName"), omel);
		
		args.add(oma);
		assertEquals(func.evaluate(args), OMCreator.createOMI(4));
	}
	
	@Test
	public void testCountNodesWithOMAWith4in3Recursively() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
		assertEquals(func.evaluate(args), OMCreator.createOMI(8));
	}
	
	@Test
	public void testCountNodesWithOMAWith4in4in2Recursively() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
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
		assertEquals(func.evaluate(args), OMCreator.createOMI(11));
	}
}