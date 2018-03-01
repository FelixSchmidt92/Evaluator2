package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;
import java.util.List;

import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidContextException;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidDifficultyExeption;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;

public interface ISentenceFactory {
	
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			  String difficulty) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException;
	
	public ArrayList<String> createSentenceWithTense(String tense, String context) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException;
	
	public String createSimpleSentenceInTense(String tense, String subject, String object, String verb) throws InvalidTenseException;

	public ArrayList<String> createSentencesForTenseTransformation( String source_tense, String target_tense, String subject, String object, String verb) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException;
	
}
