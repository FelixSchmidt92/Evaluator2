package de.uni_due.s3.evaluator2.function.list2;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.function.list2.Append;
import de.uni_due.s3.evaluator2.parser.ExpressionParser;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestAppend {

	private Function func = new Append();
	private List<Object> args;
	private Object result;

	@Test
	public void testAppend1() throws EvaluatorException, OpenMathException {
		List<Object> l1 = new ArrayList<>();
		l1.add(OMCreator.createOMI(10));
		l1.add(OMCreator.createOMI(20));
		l1.add(OMCreator.createOMI(30));
		
		List<Object> l2 = new ArrayList<>();
		l1.add(OMCreator.createOMI(40));
		
		args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, l1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, l2));
		result = func.evaluate(args);
		
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(10));
		l3.add(OMCreator.createOMI(20));
		l3.add(OMCreator.createOMI(30));
		l3.add(OMCreator.createOMI(40));
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3), result);
	}

	@Test
	public void testAppend2() throws EvaluatorException, OpenMathException {
		List<Object> l1 = new ArrayList<>();
		l1.add(OMCreator.createOMI(10));
		l1.add(OMCreator.createOMI(20));
		l1.add(OMCreator.createOMI(30));
		
		List<Object> l2 = new ArrayList<>();
		l1.add(OMCreator.createOMI(30));
		
		args = new ArrayList<>();
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, l1));
		args.add(OMCreator.createOMA(OMSymbol.LIST1_LIST, l2));
		result = func.evaluate(args);
		
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(10));
		l3.add(OMCreator.createOMI(20));
		l3.add(OMCreator.createOMI(30));
		l3.add(OMCreator.createOMI(30));
		assertEquals(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3), result);
	}
	
	@Test
	public void testListIntegration1() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("appendToList(list(1,2,3),2)", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(1));
		l3.add(OMCreator.createOMI(2));
		l3.add(OMCreator.createOMI(3));
		l3.add(OMCreator.createOMI(2));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3)), result);
	}
	
	@Test
	public void testListIntegration2() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("appendToList(list(1,2,3),{2})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(1));
		l3.add(OMCreator.createOMI(2));
		l3.add(OMCreator.createOMI(3));
		l3.add(OMCreator.createOMI(2));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3)), result);
	}
	
	@Test
	public void testListIntegration3() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("appendToList(list(1,2,3),{vector(1)})", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(1));
		l3.add(OMCreator.createOMI(2));
		l3.add(OMCreator.createOMI(3));
		List<Object> arg1 = new ArrayList<>();
		arg1.add(OMCreator.createOMI(1));
		
		l3.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, arg1));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3)), result);
	}
	
	@Test
	public void testListIntegration4() throws EvaluatorException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse("appendToList(2,vector(1))", null, null);
		OMOBJ result = OMCreator.createOMOBJ(OMToResultVisitor.getInstance().visit(omobj));
		List<Object> l3 = new ArrayList<>();
		l3.add(OMCreator.createOMI(2));
		List<Object> arg1 = new ArrayList<>();
		arg1.add(OMCreator.createOMI(1));
		l3.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, arg1));
		assertEquals(OMCreator.createOMOBJ(OMCreator.createOMA(OMSymbol.LIST1_LIST, l3)), result);
	}
}
