package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.nlp.PartOfSpeechTagger;
import de.uni_due.s3.evaluator2.nlp.dictionary.DictionaryBuilder;
import de.uni_due.s3.evaluator2.nlp.dictionary.Dictionary;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class ChooseWordsExistingInLexicon extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		List<Object> wordsToCheck = getListSyntax(arguments.get(1));
		String wordCategory = getStringSyntax(arguments.get(0));
		List<Object> wordsInLexicon = new ArrayList<>();
		Dictionary lexicon;
		
		try {
			lexicon = DictionaryBuilder.buildDefaultLexicon();
			
			for(Object word : wordsToCheck){
				if(lexicon.wordExistsForCategory(getStringSyntax(word), wordCategory)){
					wordsInLexicon.add(OMCreator.createOMSTR(getStringSyntax(word)));
				}
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return OMCreator.createOMA(OMSymbol.LIST1_LIST, wordsInLexicon);
	}

	@Override
	protected int minArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	protected int maxArgs() {
		// TODO Auto-generated method stub
		return 2;
	}

}
