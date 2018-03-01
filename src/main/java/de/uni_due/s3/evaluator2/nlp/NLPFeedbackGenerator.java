package de.uni_due.s3.evaluator2.nlp;

import java.io.IOException;

public class NLPFeedbackGenerator {
	
	public String generateFeedback(String correctSentence, String inputSentence) {
		
		try {
			PartOfSpeechTagger posTagger = PartOfSpeechTagger.getInstance();
			
			String[] correctSentenceTags = posTagger.doPOSTagging(correctSentence);
			String[] userSentenceTags = posTagger.doPOSTagging(correctSentence);
			
			// TODO vergleiche Tags und erzeuge Feedback ...
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		return null;
		
	}

}
