package de.uni_due.s3.evaluator.core.function;

import org.junit.Test;


import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
public class TestPolyUtils {

	@Test
	public void testPolyGetVariables(){
		Set<String> res = new HashSet<String>();
		res.add("a");
		res.add("abc");
		res.add("b");
		
		assertEquals(res,PolyUtils.getVariables("a^2+(abc)^3+b"));
	}
}
