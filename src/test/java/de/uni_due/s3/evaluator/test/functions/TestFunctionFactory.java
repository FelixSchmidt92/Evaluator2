package de.uni_due.s3.evaluator.test.functions;

import static org.junit.Assert.*;
import org.junit.Test;

import de.uni_due.s3.evaluator.functions.FunctionFactory;
import de.uni_due.s3.evaluator.functions.IFunction;

import de.uni_due.s3.evaluator.functions.evaluator.*;

public class TestFunctionFactory {

	@Test
	public void testInstantiation(){
		FunctionFactory factory = FunctionFactory.getInstance();
		assertTrue(factory == FunctionFactory.getInstance() );
	}
	
	
	@Test
	public void testCreateFunction() throws Exception{
		Abs abs = new Abs();
		//System.out.println(abs.getClass().getName());
		IFunction function = FunctionFactory.getInstance().createFunction("abs");
		System.out.println(function);
		function.execute(null);
		assertFalse(function == null);
		assertTrue(function.getName().equals("abs"));
		
	}
	
	@Test(expected=Exception.class)
	public void testCreateFunctionWithANonExistingOne() throws Exception{
		FunctionFactory.getInstance().createFunction("123");
		FunctionFactory.getInstance().createFunction("dasd213");
	}
}
