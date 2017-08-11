package de.uni_due.s3.evaluator2.core;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * Contains some functions for handling polynomials
 * 
 * @author frichtscheid
 *
 */
public class PolyUtils {

	private final static Pattern variable = Pattern.compile("(^[a-zA-Z])|([^a-zA-Z][a-zA-Z]([^a-zA-Z]|$))");

	public static String getSageSyntaxVariableRepresentation(String term) {
		StringBuilder result = new StringBuilder();
		Set<String> vars = new HashSet<>();
		Matcher m = variable.matcher(term);
		while (m.find()) {
			String var = m.group();
			if (var.length() == 1)
				// if it is just one letter
				vars.add(var);
			else if (var.length() == 2)
				// if there are two symbols, the last one is the variable
				vars.add(var.substring(1, 2));
			else
				// if there are three symbols, the one in the middle is the
				// variable
				vars.add(var.substring(1, 2));
		}
		String prefix = "";
		for (String var : vars) {
			result.append(prefix);
			result.append(var);
			prefix = " ";
		}

		if (vars.size() != 0) {
			result.insert(0, "var('");

			result.append("');");
		}
		return result.toString();
	}

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

	public static Set<String> getVariables(OMA oma) {
		Set<String> result = new LinkedHashSet<String>();
		for (Object om : oma.getOmel()) {
			if (om instanceof OMV) {
				result.add(((OMV) om).getName());
			} else if (om instanceof OMA) {
				result.addAll(getVariables((OMA) om));
			}
		}

		return result;
	}

}
