package de.uni_due.s3.evaluator.core;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.OMToSageVisitor;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidNumberOfArgumentsException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMOBJ;

public class TestSageSyntax {

	@Test
	public void testSageSyntax() throws FunctionInvalidNumberOfArgumentsException, NoRepresentationAvailableException, CasException{
		OMOBJ omobj = ExpressionParser.parse("plus(1, times(2, 3))", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj));
		
		
		OMOBJ omobj2 = ExpressionParser.parse("plus(divide(1.0, minus(3, 'abc')), times(1,1))", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj2));
		
		OMOBJ omobj3 = ExpressionParser.parse("set(1,2, 'abc1234', 1.3, 1.2367, '1', 'abcAgain')", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj3));
		
		
		try{
			OMOBJ omobj4 = ExpressionParser.parse("equalssemisem()", null, null);
			System.out.println(new OMToSageVisitor().visit(omobj4));
		}catch(NoRepresentationAvailableException e){
			System.out.println("The Method for the Representation of Sage in EqualsSemiSem is not implemtened");
		}	
	}
}
