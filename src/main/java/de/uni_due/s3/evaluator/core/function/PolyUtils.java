package de.uni_due.s3.evaluator.core.function;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains some functions for handleing polynomials
 * @author frichtscheid
 *
 */
public class PolyUtils {

	public static Set<String> getVariables(String polynomail){
		Set<String> result = new HashSet<String>();
		Pattern variable = Pattern.compile("[a-zA-Z]+");
		Matcher m = variable.matcher(polynomail);
		while(m.find()){
			result.add(m.group());
		}
		return result;
		
	}
}
