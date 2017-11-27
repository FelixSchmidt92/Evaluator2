package de.uni_due.s3.evaluator2.core.function.string_jack;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OMTypeChecker;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Function is not registered in OMSEvaluatorSyntaxDictionary.
 * 
 * This is a Terminal like Vector or Matrix, but not callable by the Evaluator.
 * The ParserTree creates a custom JackString in OMA.
 * 
 * This class translates a String with variables in it (which are NOT an
 * Expression) into a concatening String with the specific Syntax.
 * 
 * 
 * 
 * @author dlux
 *
 */
public class TextWithVariables extends Function {


	@Override
	protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
		return OMCreator.createOMA(OMSymbol.STRINGJACK_TEXTWITHVARIABLES, arguments);
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return -1;
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		StringBuilder sb = new StringBuilder();
		for (Object arg : arguments) {
			sb.append(getStringSyntax(arg));
		}
		return sb.toString();
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String temp = "";

		for (Object obj : arguments) {
			temp += getLatexSyntax(obj);
		}
		return temp;
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException {
		List<Object> convertedOmel = new ArrayList<>();
		//Generate a List of already substututed Vars
		
		for (Object om : omel) {
			if(OMTypeChecker.isOMSTR(om)) {
				OMSTR str = (OMSTR) om;
				
				if (str.getContent().contains(";")) {
				//Maybe in a String can be a ';' like in "[var=a];[var=b];0" the ";0"
					String[] strArray = str.getContent().split(";");
					
					for(String string : strArray) {
						//Ignore ZeroLength-Strings as Elements
						if(string.length() != 0) {
							convertedOmel.add(OMCreator.createOMSTR(string));
						}
					}
				}else {
				//No ';' in OMSTR just add it here
					convertedOmel.add(om);
				}
				
			}else {
			//add all other Elements which are not OMSTR
				convertedOmel.add(om);
			}
		}
		return convertedOmel;
	}

	/**
	 * Here we want a Concatination of Strings and OMA'S in Sage Syntax
	 * 
	 * e.g 'var(x) + [pos=1].det()'
	 * 
	 * if pos=1 is a Matrix: [[1,2],[3,4]] should return: "var(x) +
	 * [[1,2],[3,4]].det()"
	 * 
	 */
	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String temp = "";

		for (Object obj : arguments) {
			temp += getSageSyntax(obj);
		}
		return temp;
	}
	
	
	
	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		String temp = "";

		for (Object obj : arguments) {
			temp += getRSyntax(obj);
		}
		return temp;
	}
}
