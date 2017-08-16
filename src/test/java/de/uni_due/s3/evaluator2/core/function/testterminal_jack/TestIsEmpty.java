package de.uni_due.s3.evaluator2.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsEmpty extends TestFunctionAbstract {

	Function func = new IsEmpty();

	@Test
	public void testIsEmptyOMI() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMI omi = OMCreator.createOMI(0);
		args.add(omi);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMF() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMF omf = OMCreator.createOMF(0.0);
		args.add(omf);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMSTR() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMSTR omstr = OMCreator.createOMSTR("");
		args.add(omstr);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, obj);

		args = new ArrayList<>();
		omstr = OMCreator.createOMSTR("NotEmpty");
		args.add(omstr);

		obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMS() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMS oms = OMSymbol.SET1_EMPTYSET;
		args.add(oms);

		Object evaluate = func.evaluate(args);
		Object obj = evaluate;
		assertEquals(OMSymbol.LOGIC1_TRUE, obj);

		args = new ArrayList<>();
		oms = OMSymbol.NUMS1_I;
		args.add(oms);

		obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMV() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();
		OMV omv = OMCreator.createOMV("");
		args.add(omv);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, obj);

		args = new ArrayList<>();
		omv = OMCreator.createOMV("x");
		args.add(omv);

		obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMANotEmptyMatrix() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();

		ArrayList<Object> rowele = new ArrayList<>();
		rowele.add(OMCreator.createOMI(1));
		rowele.add(OMCreator.createOMI(2));
		rowele.add(OMCreator.createOMI(3));
		rowele.add(OMCreator.createOMI(0));

		OMA row = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, rowele);

		ArrayList<Object> matrixele = new ArrayList<>();

		matrixele.add(row);

		OMA matrix = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrixele);

		args.add(matrix);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}

	@Test
	public void testIsEmptyOMAEmptyMatrix() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();

		ArrayList<Object> rowele = new ArrayList<>();
		rowele.add(OMCreator.createOMI(1));
		rowele.add(OMCreator.createOMI(2));
		rowele.add(OMCreator.createOMI(3));
		rowele.add(OMSymbol.EDITOR1_INPUT_BOX);

		OMA row = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, rowele);

		ArrayList<Object> matrixele = new ArrayList<>();

		matrixele.add(row);

		OMA matrix = OMCreator.createOMA(OMSymbol.LINALG2_MATRIX, matrixele);

		args.add(matrix);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, obj);
	}

	@Test
	public void testIsEmptyOMAEmptySet() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();

		ArrayList<Object> setele = new ArrayList<>();

		OMA set = OMCreator.createOMA(OMSymbol.SET1_SET, setele);

		args.add(set);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_TRUE, obj);
	}

	@Test
	public void testIsEmptyOMANotEmptySet() throws OpenMathException, EvaluatorException {
		ArrayList<Object> args = new ArrayList<>();

		ArrayList<Object> setele = new ArrayList<>();

		OMA set = OMCreator.createOMA(OMSymbol.SET1_SET, setele);

		ArrayList<Object> setele2 = new ArrayList<>();
		setele2.add(set);

		OMA set2 = OMCreator.createOMA(OMSymbol.SET1_SET, setele2);

		args.add(set2);

		Object obj = func.evaluate(args);
		assertEquals(OMSymbol.LOGIC1_FALSE, obj);
	}
}