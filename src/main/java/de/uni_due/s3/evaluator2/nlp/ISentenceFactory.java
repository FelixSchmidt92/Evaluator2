package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidContextException;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidDifficultyExeption;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;

public interface ISentenceFactory {
	
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			  String difficulty) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException;
	
	public ArrayList<String> createSentenceWithTense(List<Object> tenses) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException;
	

}
