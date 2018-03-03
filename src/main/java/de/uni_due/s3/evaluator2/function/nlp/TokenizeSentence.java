package de.uni_due.s3.evaluator2.function.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import opennlp.tools.tokenize.SimpleTokenizer;

public class TokenizeSentence extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
	      
		String sentenceToTokenize = getStringSyntax(arguments.get(0));

		SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;  
		String[] tokens = simpleTokenizer.tokenize(sentenceToTokenize);
	
		List<Object> list = new ArrayList<>();
		
		for (String token : tokens) {
			list.add(OMCreator.createOMSTR(token));
		}
		
		return OMCreator.createOMA(OMSymbol.LIST1_LIST, list);
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
