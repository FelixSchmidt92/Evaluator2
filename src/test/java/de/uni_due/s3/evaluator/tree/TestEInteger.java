package de.uni_due.s3.evaluator.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEInteger {

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{1, "<OMOBJ><OMi>1</OMI></OMOBJ>", "1", "1", 
				"1", "1"}
		});
	}

	private EInteger eInteger;
	private String openMath, sage, r, symja, toString;
	private int value;
	
	public TestEInteger(int value, String openMath, String sage, String r, String symja, String toString){
		eInteger = new EInteger(value);
		this.value = value;  this.openMath = openMath;  this.sage = sage;
		this.r = r;  this.symja = symja;  this.toString = toString;
	}
	
	@Test
	public void TestGetOpenMathSyntax(){
		assertEquals(eInteger.getOpenMathSyntax(), openMath);
	}
	
	
	@Test
	public void TestGetSageSyntax(){
		assertEquals(eInteger.getSageSyntax(), sage);
	}
	
	@Test
	public void TestGetRSyntax(){
		assertEquals(eInteger.getRSyntax(), r);
	}
	
	@Test
	public void TestGetSymjaSyntax(){
		assertEquals(eInteger.getSymjaSyntax(), symja);
	}
	
	@Test
	public void TestToString(){
		assertEquals(eInteger.getSymjaSyntax(), toString);
	}
	
	@Test
	public void TestGetValue(){
		assertEquals(eInteger.getValue(), value);
	}
	
	@Test
	public void TestCountNodes(){
		assertEquals(eInteger.countNodes(), 1);
	}
}
