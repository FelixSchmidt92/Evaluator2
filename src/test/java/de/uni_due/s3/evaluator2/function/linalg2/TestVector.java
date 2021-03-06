package de.uni_due.s3.evaluator2.function.linalg2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator2.function.linalg2.Vector;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestVector extends TestFunctionAbstract {
	private static ConstructorFunction func = new Vector();

	@Test
	public void testVectorInteger() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(4));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result);
	}

	@Test
	public void testVectorFloat() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMF(0.0));
		Object result = func.evaluate(args);
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result);
	}

	@Test
	public void testVectorIntegration() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("vector(1,2,3)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), result.getOMA());
	}

	@Test
	public void testVectorEvaluation() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = Evaluator.evaluate("vector(1,2,3)", null, null);
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(1));
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		assertEquals(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, args), omobj.getOMA());
	}

	@Test
	public void testVectorSageSyntax() throws EvaluatorException, OpenMathException {
		List<Object> args = new ArrayList<Object>();
		args.add(OMCreator.createOMI(2));
		args.add(OMCreator.createOMI(3));
		args.add(OMCreator.createOMI(1));
		assertEquals("vector([2,3,1])", func.getPartialSageSyntax(args));
	}

	@Test(expected = FunctionInvalidNumberOfArgumentsException.class)
	public void testVectorWithLessThanMinParam() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("vector()", null, null);
		OMToResultVisitor.getInstance().visit(omobj);
		fail();
	}
	
	@Test
	public void testOMToLatexVisitorWithVector() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("vector(1,2,3,4)", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\left(\\begin{array}{r}" + 
				"1\\\\" + 
				"2\\\\" + 
				"3\\\\" + 
				"4\\\\" +
				
				
				"\\end{array}\\right)", latex);
	}
	
	@Test
	public void testOMToLatexVisitorWithVector2() throws EvaluatorException, OpenMathException {
		OMOBJ obj = Evaluator.evaluate("vector(123,2.023,[var=PI])", new HashMap<>(), new HashMap<>());
		String latex = OMToLatexVisitor.getInstance().visit(obj);
		assertEquals("\\left(\\begin{array}{r}" + 
				"123\\\\" + 
				"2.023\\\\" + 
				"\\pi\\\\" + 
				"\\end{array}\\right)", latex);
	}
}
