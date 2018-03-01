package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.nlp.SimpleNLGSentenceFactory;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class GenerateSimpleSentence extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		System.out.println("Generiere Satz aus den folgenden Worten ...");
		String subject = getStringSyntax(arguments.get(0));
		String verb = getStringSyntax(arguments.get(1));	
		String object = getStringSyntax(arguments.get(2));
		String tense = getStringSyntax(arguments.get(3));
		
		try {
			String generatedSentence = (new SimpleNLGSentenceFactory()).createSimpleSentenceInTense(tense, subject, object, verb );
			return OMCreator.createOMSTR( generatedSentence );

		} catch (InvalidTenseException e) {
			throw new EvaluatorException("Invalid Tense");
		}
		
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
