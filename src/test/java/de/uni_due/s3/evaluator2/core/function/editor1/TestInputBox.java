package de.uni_due.s3.evaluator2.core.function.editor1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestInputBox {

	Function func = new InputBox();
	
	@Test(expected = FunctionException.class)
	public void testInputBoxExecute() throws EvaluatorException, OpenMathException {
		func.evaluate(new ArrayList<>());
	}
	
	@Test
	public void testInputBoxToString() throws EvaluatorException, OpenMathException {
		String actual = func.getPartialStringSyntax(new ArrayList<>());
		assertEquals("", actual);
	}
	
	@Test
	public void testInputBoxToLatex() throws EvaluatorException, OpenMathException {
		String actual = func.getPartialLatexSyntax(new ArrayList<>());
		assertEquals("", actual);
	}
	
	
	@Test(expected = FunctionException.class)
	public void testInputBoxIntegrationWithOMOBJ() throws EvaluatorException, OpenMathException {
		OMOBJ actual = new OMOBJ();
		actual.setOMA(OMCreator.createOMA(OMSymbol.EDITOR1_INPUT_BOX, new ArrayList<>()));
		Evaluator.evaluate(actual);
	}
	
	@Test(expected = FunctionNotImplementedException.class)
	public void testInputBoxIntegrationWithExpression() throws EvaluatorException, OpenMathException {
		Evaluator.evaluate("inputBox()", null, null);
	}
}
