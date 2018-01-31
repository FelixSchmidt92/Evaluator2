package de.uni_due.s3.evaluator2.function.mc_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.mc_jack.MCIndex;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestMCIndex extends TestFunctionAbstract{

	Function func = new MCIndex();
	
	@Test
	public void testMCIndexExecute() throws EvaluatorException, OpenMathException {
		List<Object> elements = new ArrayList<>();
		
		elements.add(OMCreator.createOMI(1));
		elements.add(OMCreator.createOMI(2));
		elements.add(OMCreator.createOMI(3));
		elements.add(OMCreator.createOMI(4));
		
		OMA expected = OMCreator.createOMA(OMSymbol.MCJACK_MCINDEX, elements);
		
		Object actual = func.evaluate(elements);
		
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testMCIndexInteger() throws EvaluatorException, OpenMathException {
		List<Object> elements = new ArrayList<>();
		
		elements.add(OMCreator.createOMI(1));
		elements.add(OMCreator.createOMI(2));
		elements.add(OMCreator.createOMI(3));
		elements.add(OMCreator.createOMI(4));
		
		Object actual = func.getPartialIntegerSyntax(elements);
		
		assertEquals(1, actual);
	}
}
