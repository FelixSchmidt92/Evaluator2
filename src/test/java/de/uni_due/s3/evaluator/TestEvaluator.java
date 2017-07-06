package de.uni_due.s3.evaluator;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmath.omutils.OMConverter;
import org.openmath.omutils.OpenMathException;
import org.openmath.openmath.OMOBJ;

import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;


public class TestEvaluator {

	private Evaluator eval;
	
	@Before
	public void init(){
		this.eval = new Evaluator();
	}
	
	@Test
	public void testEvaluateString() throws FunctionException, JAXBException, OpenMathException{
		Assert.assertEquals("<OMOBJ><OMI>4</OMI></OMOBJ>", OMConverter.toString(eval.evaluate("1+3")));
		Assert.assertEquals("<OMOBJ><OMI>4</OMI></OMOBJ>", OMConverter.toString(eval.evaluate("plus(1,3)")));
	}
	
	@Test(expected=FunctionNotImplementedException.class)
	public void testEvaluateWithException() throws FunctionException, OpenMathException, JAXBException{
		eval.evaluate("abcdefghxjc()");
		eval.evaluate("123cdefghxjc()");
		eval.evaluate("abcdefghxjc");	//should this throw a another exception? (dlux: this throws a ParserException from ParserErrorStrategy)
		
		eval.evaluate((OMOBJ)OMConverter.toObject("<OMOBJ><OMA>"
				+ "<OMS name=\"abcdefggg\" cd=\"arith1\"/>"
				+ "<OMI>2</OMI>"
				+ "<OMI>3</OMI>"																			
			+ "</OMA></OMOBJ>"));
	}
	
	@Test
	public void testEvaluateOMOBJ() throws FunctionException, JAXBException, OpenMathException{
		Assert.assertEquals("<OMOBJ><OMI>15</OMI></OMOBJ>", OMConverter.toString(eval.evaluate((OMOBJ)OMConverter.toObject("<OMOBJ><OMA>"
																			+ "<OMS name=\"plus\" cd=\"arith1\"/>"
																			+ "<OMI>2</OMI>"
																			+ "<OMI>13</OMI>"																			
																		+ "</OMA></OMOBJ>"))));
	}
	
	

	
	
}
