package de.uni_due.s3.evaluator.core.integration.set_jack;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;


public class TestChooseFromComplement extends TestIntegration {
	
	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	private static ArrayList<String> results = new ArrayList<String>();
	@BeforeClass
	public static void beforeTest() {
		OMOBJ hallo = new OMOBJ();
		OMOBJ c = new OMOBJ();
		c.setOMV(OMCreator.createOMV("c"));
		hallo.setOMSTR(OMCreator.createOMSTR("hallo"));
		
		fillIn.put(1, c);
		fillIn.put(2, hallo);
		
		exerVar.put("a", c);
		exerVar.put("b", hallo);
		
		results.add("'a'");
		results.add("'b'");
		results.add("'c'");
	}
	
	@Test
	public void testChooseFromComplement1() throws EvaluatorException {
		assertTrue("'c'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement2() throws EvaluatorException {
		assertTrue("'h'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{h;10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement3() throws EvaluatorException {
		assertTrue("'a'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a}','{b;c}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement4() throws EvaluatorException {
		assertTrue(results.contains(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement5() throws EvaluatorException {
		assertTrue(results.contains(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement6() throws EvaluatorException {
		assertTrue(results.contains(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithInput1() throws EvaluatorException {		
		assertTrue("'c'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;[pos=1]}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithInput2() throws EvaluatorException {
		assertTrue("'hallo'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{[pos=2];10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithVariables1() throws EvaluatorException {
		assertTrue("'c'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;[var=a]}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithVariables2() throws EvaluatorException {
		assertTrue("'hallo'".equals(Evaluator.getResultAsEvaluatorString("chooseFromComplement('{[var=b];10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test (expected=IllegalArgumentException.class) 
	public void testChooseFromComplementWithSameElements() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;1.5}','{a;b;1.5}')", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidArgumentException.class)
	public void testChooseFromComplementWithWrongInputCharacter() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c;d}', a)", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithOneArgument() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c;d}')", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithThreeArguments() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('{a;b;c;d}', '{a;b}', '{c}')", exerVar, fillIn);
	}

	@Test (expected=UndefinedExerciseVariableException.class)
	public void testChooseFromComplementWithMissingExcerciseVariable() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('[var=j]', '{a}')", exerVar, fillIn);
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testChooseFromComplementWithMissingInput() throws EvaluatorException {
		Evaluator.getResultAsEvaluatorString("chooseFromComplement('[pos=42]', '{a}')", exerVar, fillIn);
	}

}
