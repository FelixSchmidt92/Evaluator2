package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;

import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidContextException;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidDifficultyExeption;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
//import simplenlg.lexicon.Lexicon;



public class SimpleNLGSentenceFactory implements ISentenceFactory {
	
	@Override
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			  String difficulty)  throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException{
//		Lexicon lexicon = Lexicon.getDefaultLexicon();
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("This is the source sentence");
		result.add("This is the target sentence");		
		return result;
	}

}
