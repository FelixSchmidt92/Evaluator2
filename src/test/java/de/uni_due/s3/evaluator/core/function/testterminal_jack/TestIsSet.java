package de.uni_due.s3.evaluator.core.function.testterminal_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.core.function.testterminal_jack.IsSet;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIsSet extends TestFunctionAbstract {

	Function func = new IsSet();

	@Test
	public void testIsSetWithOtherTerminals() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();

		OMI omi = OMCreator.createOMI(2);
		args.add(omi);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));

		OMSTR omstr = OMCreator.createOMSTR("Not a Set");
		args.remove(0);
		args.add(omstr);
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));

		OMV omv = OMCreator.createOMV("x");
		args.remove(0);
		args.add(omv);
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));

		OMF omf = OMCreator.createOMF(1.1);
		args.remove(0);
		args.add(omf);
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
		OMS oms = OMCreator.createOMS("myCD", "myName");
		args.remove(0);
		args.add(oms);
		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));
	}

	@Test
	public void testIsSetWithFullSet() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> omel = new ArrayList<>();

		OMI omi = OMCreator.createOMI(2);
		omel.add(omi);

		ArrayList<Object> args = new ArrayList<>();

		OMA oma = OMCreator.createOMA(OMSymbol.SET1_SET, omel);
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));

	}

	@Test
	public void testIsSetWithEmptySet() throws FunctionInvalidArgumentException, CasEvaluationException,
			FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();

		OMA oma = OMCreator.createOMA(OMSymbol.SET1_SET, new ArrayList<>());
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_TRUE, func.evaluate(args));

	}

	@Test
	public void testIsSetWithOMA() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException,
			CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();

		OMA oma = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, new ArrayList<>());
		args.add(oma);

		assertEquals(OMSymbol.LOGIC1_FALSE, func.evaluate(args));

	}
}
