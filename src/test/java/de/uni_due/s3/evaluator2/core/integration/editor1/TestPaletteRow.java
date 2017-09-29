package de.uni_due.s3.evaluator2.core.integration.editor1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestPaletteRow {

	@Test
	public void testPaletteRow() throws EvaluatorException, OpenMathException {
		OMOBJ result = Evaluator.evaluate("paletterow(plus())", null, null);
		List<Object>args = new ArrayList<Object>();
		args.add(OMSymbol.ARITH1_PLUS);
		assertEquals(OMCreator.createOMA(OMSymbol.EDITOR1_PALETTE_ROW, args),result.getOMA());
	}
}
