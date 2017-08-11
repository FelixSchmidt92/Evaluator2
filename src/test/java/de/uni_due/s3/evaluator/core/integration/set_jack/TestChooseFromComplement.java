package de.uni_due.s3.evaluator.core.integration.set_jack;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.Evaluator;
import de.uni_due.s3.evaluator.core.integration.TestIntegration;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentTypeException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;


public class TestChooseFromComplement extends TestIntegration {
	
	private static HashMap<Integer, OMOBJ> fillIn = new HashMap<>();
	private static HashMap<String, OMOBJ> exerVar = new HashMap<>();
	private static ArrayList<OMOBJ> results = new ArrayList<OMOBJ>();
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
		
		
		OMOBJ a = new OMOBJ();
		OMOBJ b = new OMOBJ();
		a.setOMV(OMCreator.createOMV("a"));
		b.setOMV(OMCreator.createOMV("b"));
		
		results.add(a);
		results.add(b);
		results.add(c);
	}
	
	@Test
	public void testChooseFromComplement1() throws OpenMathException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("h"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{h;10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement3() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("a"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a}','{b;c}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement4() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement5() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplement6() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		assertTrue(results.contains(Evaluator.evaluate("chooseFromComplement('{a;b;c}','{d;e;f}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithInput1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {		
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;[pos=1]}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithInput2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("hallo"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{[pos=2];10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithVariables1() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		OMOBJ expected = new OMOBJ();
		expected.setOMV(OMCreator.createOMV("c"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{a;b;[var=a]}','{a;b}')", exerVar, fillIn)));
	}
	
	@Test
	public void testChooseFromComplementWithVariables2() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR("hallo"));
		assertTrue(expected.equals(Evaluator.evaluate("chooseFromComplement('{[var=b];10;m}','{m;10}')", exerVar, fillIn)));
	}
	
	@Test (expected=FunctionInvalidArgumentException.class) 
	public void testChooseFromComplementWithSameElements() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;1.5}','{a;b;1.5}')", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidArgumentTypeException.class)
	public void testChooseFromComplementWithWrongInputCharacter() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;c;d}', a)", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithOneArgument() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException{
		Evaluator.evaluate("chooseFromComplement('{a;b;c;d}')", exerVar, fillIn);
	}
	
	@Test (expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testChooseFromComplementWithThreeArguments() throws CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, OpenMathException {
		Evaluator.evaluate("chooseFromComplement('{a;b;c;d}', '{a;b}', '{c}')", exerVar, fillIn);
	}

	@Test (expected=UndefinedExerciseVariableException.class)
	public void testChooseFromComplementWithMissingExcerciseVariable() throws OpenMathException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		Evaluator.evaluate("chooseFromComplement('[var=j]', '{a}')", exerVar, fillIn);
	}
	
	@Test (expected=UndefinedFillInVariableException.class)
	public void testChooseFromComplementWithMissingInput() throws OpenMathException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		Evaluator.evaluate("chooseFromComplement('[pos=42]', '{a}')", exerVar, fillIn);
	}

}
