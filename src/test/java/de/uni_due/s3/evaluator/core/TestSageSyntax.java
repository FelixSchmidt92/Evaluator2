package de.uni_due.s3.evaluator.core;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.core.function.SageVisitor;
import de.uni_due.s3.evaluator.exceptions.NoFunctionToCASException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestSageSyntax {

	@Test
	public void testSageSyntax() throws JAXBException{
		OMOBJ omobj = ExpressionParser.parse("plus(1, times(2, 3))", null, null);
		System.out.println(new SageVisitor().visit(omobj));
		
		
		OMOBJ omobj2 = ExpressionParser.parse("plus(divide(1.0, minus(3, 'abc')), times(1,1))", null, null);
		System.out.println(new SageVisitor().visit(omobj2));
		
		OMOBJ omobj3 = ExpressionParser.parse("set(1,2, 'abc1234', 1.3, 1.2367, '1', 'abcAgain')", null, null);
		System.out.println(new SageVisitor().visit(omobj3));
		
		
		try{
			OMOBJ omobj4 = ExpressionParser.parse("equalssemisem()", null, null);
			System.out.println(new SageVisitor().visit(omobj4));
		}catch(NoFunctionToCASException e){
			System.out.println("The Method in EqualsSemiSem is not implemtened");
		}
		
	}
}
