package de.uni_due.s3.evaluator2.integration.random_jack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashMap;

import org.junit.Test;

import de.uni_due.s3.evaluator2.Evaluator;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator2.integration.TestIntegration;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandom extends TestIntegration {

	@Test
	public void testRandom() throws EvaluatorException, OpenMathException {
		
		for(int i=0;i<10000;i++){
			assertTrue(1 >  Evaluator.getNumberResult("random()", exerciseVariableMap, fillInVariableMap));
			assertTrue(0 <= Evaluator.getNumberResult("random()", exerciseVariableMap, fillInVariableMap));
		}
	}
	
	@Test
	public void testRandomStatistically() throws EvaluatorException, OpenMathException {
		HashMap<Double, Integer> statistics = new HashMap<>();  //<'Random Number', 'Occurrences'>
		for (int i = 0; i < 10000; i++){
			double random = Evaluator.getNumberResult("random()", exerciseVariableMap, fillInVariableMap);
			if (statistics.containsKey(random)){
				statistics.put(random, statistics.get(random) +1);
			}else{
				statistics.put(random, 1);
			}
		}
	
		int max = 0;
		for (int value : statistics.values()){
			if(value > max){ max = value; }
		}
		
		assertTrue(2 >= max);  							//max 2 same random numbers out of 10 000
		assertTrue(9990 < statistics.values().size()); //max 9 random numbers overlapping out of 10 000 
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomWithOneArgument() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("random(4)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomWithTwoArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("random(4, 5)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
	
	@Test(expected=FunctionInvalidNumberOfArgumentsException.class)
	public void testRandomWithThreeArguments() throws EvaluatorException, OpenMathException {
		Evaluator.getNumberResult("random(2, 4, 0.1)", exerciseVariableMap, fillInVariableMap);
		fail();
	}
}
