package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;
import java.util.Arrays;

public class NounPhraseComparator implements PhraseComparator {

	public static String TYPE_OBJECT = "object";
	public static String TYPE_SUBJECT = "subject";
	
	private String DETERMINER_TAG ="DT";
	
	private String PROPER_NOUN_TAG ="NNP";
	private String PROPER_NOUN_PLURAL_TAG ="NNPS";
	
	private String NOUN_TAG ="NN";
	private String NOUN_PLURAL_TAG ="NNS";

	private String type;
	public NounPhraseComparator(String type) {
		this.type = type;
	}
	@Override
	public ArrayList<String> comparePhrase(String[] correctSentenceTokens, String[] correctSentencePOSTags,
			String[] userSentenceTokens, String[] userSentencePOSTags) {
		
		ArrayList<String> messages = new ArrayList<>();
		
		if( !( Arrays.equals(correctSentenceTokens, userSentenceTokens) && (Arrays.equals(correctSentencePOSTags, userSentencePOSTags)))) {
			
			if(correctSentencePOSTags[0].equals(DETERMINER_TAG) ) {
				if(!userSentencePOSTags[0].equals(DETERMINER_TAG)) {
					messages.add("The determiner of the " + type +" is missing in the input sentence.");
					if(correctSentencePOSTags.length>1) {
						messages.add(compareNouns(1, correctSentencePOSTags, 0, userSentencePOSTags));
					}
				}
				
				else {
					if(( correctSentencePOSTags.length>1) && (userSentencePOSTags.length>1)) {
						messages.add(compareNouns(1, correctSentencePOSTags, 1, userSentencePOSTags));
					}
				}
				
			}
			else if(userSentencePOSTags[0].equals(DETERMINER_TAG)) {
				messages.add("The " + type +" should not have a determiner"); 
				if(userSentencePOSTags.length>1) {
					messages.add(compareNouns(0, correctSentencePOSTags, 1, userSentencePOSTags));
				}
			}
			
			else {
				messages.add(compareNouns(0, correctSentencePOSTags, 0, userSentencePOSTags));
			}
			 
		}

		return messages;
	}
	
	private boolean isProperNoun(String tagToCheck) {
		return tagToCheck.equals(PROPER_NOUN_TAG) || tagToCheck.equals(PROPER_NOUN_PLURAL_TAG) ;
	}
	
	private boolean isNoun(String tagToCheck) {
		return tagToCheck.equals(NOUN_TAG) || tagToCheck.equals(NOUN_PLURAL_TAG) ;
	}
	
	private boolean isPlural(String tagToCheck) {
		return tagToCheck.equals(PROPER_NOUN_PLURAL_TAG) || tagToCheck.equals(NOUN_PLURAL_TAG);
	}
	
	private String compareNouns(int correctSentenceIndex, String[] correctSentencePOSTags, int userSentenceIndex, String[] userSentencePOSTags) {
	
		
		if(isProperNoun(correctSentencePOSTags[correctSentenceIndex])) {
			if(isProperNoun(userSentencePOSTags[userSentenceIndex]) && (!userSentencePOSTags[userSentenceIndex].equals(correctSentencePOSTags[correctSentenceIndex]))){
				if(isPlural(correctSentencePOSTags[correctSentenceIndex])) {
					return "The " + type + " should be in plural but is in singular.";
				}
				else {
					return "The " + type + " should be in singular but is in plural.";
				}
			}	
		}
		else if(isNoun(correctSentencePOSTags[correctSentenceIndex])) {
			if(isNoun(userSentencePOSTags[userSentenceIndex]) && (!userSentencePOSTags[userSentenceIndex].equals(correctSentencePOSTags[correctSentenceIndex]))){
				if(isPlural(correctSentencePOSTags[correctSentenceIndex])) {
					return "The " + type + " should be in plural but is in singular.";
				}
				else {
					return "The " + type + " should be in singular but is in plural.";
				}
			}
		}
		
		return null;
	}
	

}
