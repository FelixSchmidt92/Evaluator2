package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;
import java.util.Arrays;

public class VerbPhraseComparator implements PhraseComparator {

	
	
	@Override
	public ArrayList<String> comparePhrase(String[] correctSentenceTokens,
			String[] correctSentencePOSTags, String[] userSentenceTokens,
			String[] userSentencePOSTags) {
		ArrayList<String> messages = new ArrayList<>();
		
		String correctSentenceTense = TenseDeterminer.determineTense(correctSentencePOSTags);
		String userSentenceTense = TenseDeterminer.determineTense(userSentencePOSTags);
		
		if(!correctSentenceTense.equals(userSentenceTense)){
			messages.add("There seems to be a problem with the tense of your sentence.");
			
			if(!userSentenceTense.equals("unknown tense")) {
				
				messages.add(correctSentenceTense + " was requested." + " Tense of input sentence is "+ userSentenceTense + ".");
			}
			
			
				ArrayList<String> correctSentenceTags = new ArrayList<>(Arrays.asList(correctSentencePOSTags));
				ArrayList<String> userSentenceTags = new ArrayList<>(Arrays.asList(userSentencePOSTags));
				
				if(correctSentenceTags.containsAll(userSentenceTags) ) {
					if( correctSentenceTags.size()==userSentenceTags.size()) {
						messages.add("The order of the verbs within the sentence seems to be wrong.");
					}
					else {
						//get verbs and their POS-Tag, that are missing in the user sentence
						for (int i = 0; i < correctSentenceTags.size(); i++) {
							String tag = correctSentenceTags.get(i);
							if(!userSentenceTags.contains(tag)) {
								String word = correctSentenceTokens[i];
								messages.add("The " + tag + " " + word + " is missing in your sentence.");							
							}
						}
					}
				}
				
				else if(userSentenceTags.containsAll(correctSentenceTags)) {
					messages.add("There are too many verbs in your sentence.");
					//get verbs and their POS-Tag, that aren't in the correct sentence but in the user sentence 
					for (int i = 0; i < userSentenceTags.size(); i++) {
						String tag = userSentenceTags.get(i);
						if(!correctSentenceTags.contains(tag)) {
							String word = userSentenceTokens[i];
							messages.add("The " + tag + " " + word + " is not expected in the sentence.");							
						}
						
					}
				}
		}
			
		
		
		return messages;
	}
	
	
	
	
	

}
