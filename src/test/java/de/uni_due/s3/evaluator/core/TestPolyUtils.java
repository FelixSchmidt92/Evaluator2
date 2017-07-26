package de.uni_due.s3.evaluator.core;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.PolyUtils;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
public class TestPolyUtils {

	@Test
	public void testPolyGetVariables(){
		Set<String> res = new HashSet<String>();
		res.add("a");
		res.add("c");
		res.add("b");
		
		assertEquals(res,PolyUtils.getVariables("a^2+ c^3 + b"));
	}
	@Test
	public void testPolyGetVariablesWithFunctionInPoly(){
		Set<String> res = new HashSet<String>();
		res.add("a");
		res.add("c");
		
		assertEquals(res,PolyUtils.getVariables("a*sin(c)"));
	}
}