package de.uni_due.s3.evaluator.core.function;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Contains some functions for handling polynomials
 * @author frichtscheid
 *
 */
public class PolyUtils {

	public static Set<String> getVariables(String polynomail){
		Set<String> result = new HashSet<String>();
		Pattern variable = Pattern.compile("(^[a-zA-Z])|([^a-zA-Z][a-zA-Z]([^a-zA-Z]|$))");
		Matcher m = variable.matcher(polynomail);
		while(m.find()){
			String var = m.group();
			if(var.length()==1)
				result.add(var);					//if it is just one letter
			else if(var.length()==2)
				result.add(var.substring(1, 2));	//if there are two symbols, the last one is the variable
			else
				result.add(var.substring(1,2));    //if there are three symbols, the one in the middle is the variable
		}
		return result;
		
	}
}
