package de.uni_due.s3.evaluator.core;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMV;

/**
 * Contains some functions for handling polynomials
 * @author frichtscheid
 *
 */
public class PolyUtils {

	public static Set<String> getVariables(String polynomail){
		Set<String> result = new LinkedHashSet<String>();
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
	
	public static Set<String> getVariables(OMA oma){
		Set<String> result = new LinkedHashSet<String>();
		for(Object om:oma.getOmel()){
			if(om instanceof OMV){
				result.add(((OMV)om).getName());
			}else if(om instanceof OMA){
				result.addAll(getVariables((OMA)om));
			}
		}
		
		return result;
	}

}
