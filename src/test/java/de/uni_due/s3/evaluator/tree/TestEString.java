package de.uni_due.s3.evaluator.tree;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestEString {

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
			{"Test1", "<OMOBJ><OMSTR>Test1</OMSTR></OMOBJ>", "SAGE Representation", "R Representation", 
				"Symja Representation", "Test1"}
		});
	}

	private EString eString;
	private String value, openMath, sage, r, symja, toString;
	
	public TestEString(String value, String openMath, String sage, String r, String symja, String toString){
		eString = new EString(value);
		this.value = value;  this.openMath = openMath;  this.sage = sage;
		this.r = r;  this.symja = symja;  this.toString = toString;
	}
	
	@Test
	public void TestGetOpenMathSyntax(){
		assertEquals(eString.getOpenMathSyntax(), openMath);
	}
	
	
	@Test
	public void TestGetSageSyntax(){
		assertEquals(eString.getSageSyntax(), sage);
	}
	
	@Test
	public void TestGetRSyntax(){
		assertEquals(eString.getRSyntax(), r);
	}
	
	@Test
	public void TestGetSymjaSyntax(){
		assertEquals(eString.getSymjaSyntax(), symja);
	}
	
	@Test
	public void TestToString(){
		assertEquals(eString.getSymjaSyntax(), toString);
	}
	
	@Test
	public void TestGetValue(){
		assertEquals(eString.getValue(), value);
	}
	
	@Test
	public void TestCountNodes(){
		assertEquals(eString.countNodes(), 1);
	}
}
