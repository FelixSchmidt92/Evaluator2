package de.uni_due.s3.evaluator2.nlp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.nlp.GenerateSimpleSentence;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestGenerateSimpleSentence extends TestFunctionAbstract {

	private static Function func = new GenerateSimpleSentence();
	
	@Test
	public void testGenerateSimpleSentencePresent() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("woman"));
		args.add(OMCreator.createOMSTR("eat"));
		args.add(OMCreator.createOMSTR("orange"));
		args.add(OMCreator.createOMSTR("present simple"));

		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMSTR("The woman eats oranges."), result);
	}
	
	@Test(expected = EvaluatorException.class)
	public void testGenerateUnknownTense() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMSTR("woman"));
		args.add(OMCreator.createOMSTR("eat"));
		args.add(OMCreator.createOMSTR("orange"));
		args.add(OMCreator.createOMSTR("present abc"));

		Object result = func.evaluate(args);
		fail();
	}
	
	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testGenerateWithTooManyParams() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("genSimpleSentence('Test','Test','Test','Test','Test')", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}


}
