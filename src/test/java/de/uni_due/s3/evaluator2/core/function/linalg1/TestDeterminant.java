package de.uni_due.s3.evaluator2.core.function.linalg1;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class TestDeterminant {

	private final Function func = new Determinant();
	
	@Test
	public void testDeterminantLatex() throws FunctionException, NoRepresentationAvailableException, EvaluatorException {
		List<Object> args = new ArrayList<Object>(1);
		args.add(OMCreator.createOMI(3));
		
		assertEquals("\\det{3}",func.getPartialLatexSyntax(args));
	}
}
