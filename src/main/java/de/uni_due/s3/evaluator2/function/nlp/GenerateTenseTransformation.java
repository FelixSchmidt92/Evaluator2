package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.nlp.Context;
import de.uni_due.s3.evaluator2.nlp.Difficulty;
import de.uni_due.s3.evaluator2.nlp.SimpleNLGSentenceFactory;
import de.uni_due.s3.evaluator2.nlp.Tense;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidContextException;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidDifficultyExeption;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class GenerateTenseTransformation extends Function{

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
				
		
		String source_tense = getStringSyntax(arguments.get(0));  
		String target_tense = getStringSyntax(arguments.get(1)); 
		String subject = getStringSyntax(arguments.get(2)); 
		String verb = getStringSyntax(arguments.get(3));
		String object = getStringSyntax(arguments.get(4));
		
		ArrayList<String> sentences = new ArrayList<String>();
		try {
			sentences = (new SimpleNLGSentenceFactory()).createSentencesForTenseTransformation(source_tense, target_tense, subject, object, verb);		
		} catch (InvalidContextException | InvalidDifficultyExeption | InvalidTenseException e) {
			throw new FunctionInvalidArgumentException(this, "");
		};
		List<Object> list = new ArrayList<>();

		Object source_sentence = OMCreator.createOMSTR(sentences.get(0));
		Object target_sentence = OMCreator.createOMSTR(sentences.get(1));
		list.add(source_sentence);
		list.add(target_sentence);
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, list);
	
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 5;
	}

}
