package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;

public class SimpleNLGSentenceFactory implements ISentenceFactory {
	
	

	@Override
	public ArrayList<String> createSentenceTransformation(Context context, Tense source_tense, Tense target_tense,
			  Difficulty difficulty) {
		ArrayList<String> result = new ArrayList<String>();
		result.add("This is the source sentence");
		result.add("This is the target sentence");
		// TODO Auto-generated method stub
		return result;
	}

}
