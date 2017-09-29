package de.uni_due.s3.evaluator2.core;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains some functions for handling polynomials
 * 
 * @author frichtscheid
 *
 */
public class PolyUtils {

	private final static Pattern variable = Pattern.compile("(^[a-zA-Z])|([^a-zA-Z][a-zA-Z]([^a-zA-Z]|$))");

	public static Set<String> getVariables(String polynomial) {
		Set<String> result = new LinkedHashSet<String>();
		Matcher m = variable.matcher(polynomial);
		while (m.find()) {
			String var = m.group();
			if (var.length() == 1)
				// if it is just one letter
				result.add(var);
			else if (var.length() == 2)
				// if there are two symbols, the last one is the variable
				result.add(var.substring(1, 2));
			else
				// if there are three symbols, the one in the middle is the
				// variable
				result.add(var.substring(1, 2));
		}
		return result;

	}

}
