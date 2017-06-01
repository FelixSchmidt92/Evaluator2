package de.uni_due.s3.evaluator.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEBoolean {

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{true, "<OMOBJ><OMA><OMS cd=\"logic1\" name=\"true\"></OMA></OMOBJ>", "True", "TRUE", 
				"True", "true"}
		});
	}

	private EBoolean eBoolean;
	private String openMath, sage, r, symja, toString;
	private boolean value;
	
	public TestEBoolean(boolean value, String openMath, String sage, String r, String symja, String toString){
		eBoolean = new EBoolean(value);
		this.value = value;  this.openMath = openMath;  this.sage = sage;
		this.r = r;  this.symja = symja;  this.toString = toString;
	}
	
	@Test
	public void TestGetOpenMathSyntax(){
		assertEquals(eBoolean.getOpenMathSyntax(), openMath);
	}
	
	
	@Test
	public void TestGetSageSyntax(){
		assertEquals(eBoolean.getSageSyntax(), sage);
	}
	
	@Test
	public void TestGetRSyntax(){
		assertEquals(eBoolean.getRSyntax(), r);
	}
	
	@Test
	public void TestGetSymjaSyntax(){
		assertEquals(eBoolean.getSymjaSyntax(), symja);
	}
	
	@Test
	public void TestToString(){
		assertEquals(eBoolean.getSymjaSyntax(), toString);
	}
	
	@Test
	public void TestGetValue(){
		assertEquals(eBoolean.getValue(), value);
	}
	
	@Test
	public void TestCountNodes(){
		assertEquals(eBoolean.countNodes(), 1);
	}
}
