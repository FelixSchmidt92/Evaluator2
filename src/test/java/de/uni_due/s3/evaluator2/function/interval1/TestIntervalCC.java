package de.uni_due.s3.evaluator2.function.interval1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.interval1.IntervalCC;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestIntervalCC extends TestFunctionAbstract{

	private final Function func = new IntervalCC(); 
	@Test
	public void testIntervallCC() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(3));
		OMA result = (OMA) func.evaluate(args);
		assertEquals(OMSymbol.INTERVAL1_INTERVALCC,result.getOmel().get(0));
		assertEquals(OMCreator.createOMI(1),result.getOmel().get(1));
		assertEquals(OMCreator.createOMI(3),result.getOmel().get(2));
	}
	
	@Test
	public void testIntervalCCLatexSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>(2);
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(3));
		OMOBJ omobj = OMCreator.createOMOBJ(func.evaluate(args));
		String latex = Evaluator.getLaTeX(omobj);
		assertEquals("\\\\left[1,3\\\\right]",latex);
		
	}
}
