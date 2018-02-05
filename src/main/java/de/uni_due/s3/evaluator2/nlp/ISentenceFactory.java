package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;

public interface ISentenceFactory {
	
	public ArrayList<String> createSentenceTransformation(Context context, Tense source_tense, Tense target_tense,
														  Difficulty difficulty);
	

}
