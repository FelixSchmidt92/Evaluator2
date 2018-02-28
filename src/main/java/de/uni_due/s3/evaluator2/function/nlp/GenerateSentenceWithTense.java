package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

public class GenerateSentenceWithTense extends Function{

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
				
		List<Object> tenses = getListSyntax(arguments.get(0));
		
		for(Object tense : tenses){
			System.out.println("Tense = "+getStringSyntax(tense));
		}
		
		Random rand = new Random();
		String rand_tense = getStringSyntax(tenses.get(rand.nextInt(tenses.size())));
		String context = "TESTCONTEXT";
						
		ArrayList<String> sentences = new ArrayList<String>();
		/*sentences.add("sentence");
		sentences.add("correct tense");*/
		
		try {
			sentences = (new SimpleNLGSentenceFactory()).createSentenceWithTense(rand_tense, context);		
		} catch (InvalidContextException | InvalidDifficultyExeption | InvalidTenseException e) {
			throw new FunctionInvalidArgumentException(this, "");
		};
		
		List<Object> list = new ArrayList<>();

		Object sentence = OMCreator.createOMSTR(sentences.get(0));
		Object correct_tense = OMCreator.createOMSTR(sentences.get(1));
		list.add(sentence);
		list.add(correct_tense);
		
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
		return 4;
	}

}
