package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class GenerateSimpleSentence extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		System.out.println("Generiere Satz aus den folgenden Worten ...");
		for(Object arg : arguments){
			System.out.println("Word = "+getStringSyntax(arg));
		}
		
		return OMCreator.createOMSTR("Das Ergebnis");
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 4;
	}

}
