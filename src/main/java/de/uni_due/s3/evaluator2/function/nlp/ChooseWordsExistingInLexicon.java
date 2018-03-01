package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class ChooseWordsExistingInLexicon extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
		List<Object> wordsToCheck = getListSyntax(arguments.get(1));
		String wordCategory = getStringSyntax(arguments.get(0));
		
		System.out.println("Checke Wörter der Kategorie ..." + wordCategory);
		for(Object arg : wordsToCheck){
			System.out.println("Word = "+getStringSyntax(arg));
		}
		
		List<Object> list = new ArrayList<>();

		Object source_sentence = OMCreator.createOMSTR("Wort");
		Object target_sentence = OMCreator.createOMSTR("Abc");
		list.add(source_sentence);
		list.add(target_sentence);
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, list);
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
