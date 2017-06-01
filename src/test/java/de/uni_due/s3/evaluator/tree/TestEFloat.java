package de.uni_due.s3.evaluator.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEFloat {

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{1.3, "<OMOBJ><OMF dec=\"1.3\"/></OMOBJ>", "1.3", "1.3", 
				"1.3", "1.3"}
		});
	}

	private EFloat eFloat;
	private String openMath, sage, r, symja, toString;
	private double value;
	
	public TestEFloat(double value, String openMath, String sage, String r, String symja, String toString){
		eFloat = new EFloat(value);
		this.value = value;  this.openMath = openMath;  this.sage = sage;
		this.r = r;  this.symja = symja;  this.toString = toString;
	}
	
	@Test
	public void TestGetOpenMathSyntax(){
		assertEquals(eFloat.getOpenMathSyntax(), openMath);
	}
	
	
	@Test
	public void TestGetSageSyntax(){
		assertEquals(eFloat.getSageSyntax(), sage);
	}
	
	@Test
	public void TestGetRSyntax(){
		assertEquals(eFloat.getRSyntax(), r);
	}
	
	@Test
	public void TestGetSymjaSyntax(){
		assertEquals(eFloat.getSymjaSyntax(), symja);
	}
	
	@Test
	public void TestToString(){
		assertEquals(eFloat.getSymjaSyntax(), toString);
	}
	
	@Test
	public void TestGetValue(){
		assertEquals(eFloat.getValue(), value, 0);
	}
	
	@Test
	public void TestCountNodes(){
		assertEquals(eFloat.countNodes(), 1);
	}
}
