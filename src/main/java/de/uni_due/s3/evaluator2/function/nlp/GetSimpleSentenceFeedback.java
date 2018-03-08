package de.uni_due.s3.evaluator2.function.nlp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.nlp.SimpleNLGSentenceFactory;
import de.uni_due.s3.evaluator2.nlp.SimpleSentenceFeedbackGenerator;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class GetSimpleSentenceFeedback extends Function {

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {

		List<Object> correctSentenceTokens_1 = getListSyntax(arguments.get(0));
		List<Object> correctSentencePOSTags_1 = getListSyntax(arguments.get(1));
		List<Object> userSentenceTokens_1 = getListSyntax(arguments.get(2));
		List<Object> userSentencePOSTags_1 = getListSyntax(arguments.get(3));

		String[] correctSentenceTokens = new String[correctSentenceTokens_1.size()];
		String[] correctSentencePOSTags = new String[correctSentencePOSTags_1.size()];
		String[] userSentenceTokens = new String[userSentenceTokens_1.size()];
		String[] userSentencePOSTags = new String[userSentencePOSTags_1.size()];

		for (int i = 0; i < correctSentenceTokens.length; i++) {
			correctSentenceTokens[i] = getStringSyntax(correctSentenceTokens_1.get(i));
		}

		for (int i = 0; i < correctSentencePOSTags.length; i++) {
			correctSentencePOSTags[i] = getStringSyntax(correctSentencePOSTags_1.get(i));
		}

		for (int i = 0; i < userSentenceTokens.length; i++) {
			userSentenceTokens[i] = getStringSyntax(userSentenceTokens_1.get(i));
		}

		for (int i = 0; i < userSentencePOSTags.length; i++) {
			userSentencePOSTags[i] = getStringSyntax(userSentencePOSTags_1.get(i));
		}

		String generatedSentence;
		try {
			generatedSentence = SimpleSentenceFeedbackGenerator.generateFeedbackForSimpleSentence(correctSentenceTokens, correctSentencePOSTags, userSentenceTokens, userSentencePOSTags);
			return OMCreator.createOMSTR(generatedSentence);

		} catch (IOException e) {
			throw new EvaluatorException("The Chunker file couldn't be loaded");
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
