package de.uni_due.s3.evaluator.core;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import de.uni_due.s3.JAXBOpenMath.openmath.OMOBJ;
import de.uni_due.s3.evaluator.core.function.OMToSageVisitor;
import de.uni_due.s3.evaluator.exceptions.function.FunctionArgumentNumberException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMOBJChildNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.openmath.OMObjectNotSupportedException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;

public class TestSageSyntax {

	@Test
	public void testSageSyntax() throws JAXBException, OMObjectNotSupportedException, OMOBJChildNotSupportedException, FunctionArgumentNumberException, NoRepresentationAvailableException{
		OMOBJ omobj = ExpressionParser.parse("plus(1, times(2, 3))", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj));
		
		
		OMOBJ omobj2 = ExpressionParser.parse("plus(divide(1.0, minus(3, 'abc')), times(1,1))", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj2));
		
		OMOBJ omobj3 = ExpressionParser.parse("set(1,2, 'abc1234', 1.3, 1.2367, '1', 'abcAgain')", null, null);
		System.out.println(new OMToSageVisitor().visit(omobj3));
		
		
		try{
			OMOBJ omobj4 = ExpressionParser.parse("equalssemisem()", null, null);
			System.out.println(new OMToSageVisitor().visit(omobj4));
		}catch(FunctionNotImplementedException e){
			System.out.println("The Method in EqualsSemiSem is not implemtened");
		}	
	}
}
