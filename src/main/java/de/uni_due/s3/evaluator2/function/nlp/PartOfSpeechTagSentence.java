package de.uni_due.s3.evaluator2.function.nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.nlp.PartOfSpeechTagger;
import de.uni_due.s3.evaluator2.nlp.dictionary.Dictionary;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class PartOfSpeechTagSentence extends Function {
	
	private static String creationErrorMessage = "Part-Of-Speech Tagger konnte nicht erstellt werden.";

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		List<Object> wordsToTag = getListSyntax(arguments.get(0));
		String[] wordsToTagArray = new String[wordsToTag.size()];
		
		for (int i = 0; i < wordsToTag.size(); i++) {
			wordsToTagArray[i] = getStringSyntax(wordsToTag.get(i));
			System.out.println("Parameter " + i + ": " +  wordsToTagArray[i]);
		}	
		try {
			
			PartOfSpeechTagger tagger = PartOfSpeechTagger.getInstance();
			String[] posTags = tagger.doPOSTagging(wordsToTagArray);
			List<Object> list = new ArrayList<>();
			
			for (String tag : posTags) {
				list.add(OMCreator.createOMSTR(tag));
			}
			return OMCreator.createOMA(OMSymbol.LIST1_LIST, list);
		
		} catch (IOException e) {
			throw new EvaluatorException(creationErrorMessage);
		}
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 1;
	}

}
