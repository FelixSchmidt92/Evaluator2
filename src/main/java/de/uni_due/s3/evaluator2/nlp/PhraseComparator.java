package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;

public interface PhraseComparator {
	
	public ArrayList<String> comparePhrase(String[] correctSentenceSubjectTokens, String[] correctSentenceSubjectPOSTags, String[] userSentenceSubjectTokens, String[] userSentenceSubjectPOSTags);

}
