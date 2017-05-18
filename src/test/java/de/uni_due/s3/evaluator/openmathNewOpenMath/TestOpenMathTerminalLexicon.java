package de.uni_due.s3.evaluator.openmathNewOpenMath;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator.openmath.NewOpenMath.OpenMathTerminalLexicon;

@RunWith(Parameterized.class)
public class TestOpenMathTerminalLexicon {
	
	private String cd, name;
	private boolean expected;

	public TestOpenMathTerminalLexicon(String cd, String name, boolean expected) {
		this.cd = cd;
		this.name = name;
		this.expected = expected;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> test(){
		return Arrays.asList(new Object[][]{
			{"set1", "set", true},
			{"linalg2", "vector", true},
			{"linalg2", "matrixrow", true},
			{"linalg2", "matrix", true},
			
			{"linalg1", "abs", false},
			{"linalg1", "sqrt", false},
		});
	}
	
	@Test
	public void testTerminalCheck(){
		assertTrue(OpenMathTerminalLexicon.TerminalCheck(cd, name) == expected);
	}
	
}
