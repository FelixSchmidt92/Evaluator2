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

public class GenerateSentenceTransformation extends Function{

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		
/*		String string1 = getStringSyntax(arguments.get(0));

		System.out.println(string1);*/
		
		String context = getStringSyntax(arguments.get(0)).toUpperCase(); 
		String source_tense = getStringSyntax(arguments.get(1)).toUpperCase();  
		String target_tense = getStringSyntax(arguments.get(2)).toUpperCase(); 
		String difficulty = getStringSyntax(arguments.get(3)).toUpperCase();
		
		System.out.println("context = "+context);
		System.out.println("source_tense = "+source_tense);
		System.out.println("target_tense = "+target_tense);
		System.out.println("difficulty = "+difficulty);
		
/*
		String context = "TESTCONTEXT";
		String source_tense = "";  
		String target_tense = ""; 
		String difficulty = "";
*/	
		
		ArrayList<String> sentences = new ArrayList<String>();
		/*sentences.add("this is a test1");
		sentences.add("this is a test2");*/
		try {
			sentences = (new SimpleNLGSentenceFactory()).createSentenceTransformation(context, source_tense, target_tense, difficulty);		
		} catch (InvalidContextException | InvalidDifficultyExeption | InvalidTenseException e) {
			throw new FunctionInvalidArgumentException(this, "");
		};
		List<Object> list = new ArrayList<>();

		Object a = OMCreator.createOMSTR(sentences.get(0));
		Object b = OMCreator.createOMSTR(sentences.get(1));
		list.add(a);
		list.add(b);
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
