package de.uni_due.s3.evaluator2.nlp.lexicon;

import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class Main {

	public static void main(String[] args) {
		
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		
		String subject = "Move";
		String verb = "help";
		String object = "passenger";
		
		SPhraseSpec sentence  = nlgFactory.createClause();
		sentence.setSubject(subject);
		sentence.setVerb(verb);
		sentence.setObject(object);
		
		sentence.setTense(Tense.PRESENT);
		sentence.setFeature(Feature.PROGRESSIVE, true);
//		sentence.setFeature(Feature.PERFECT, true);
		sentence.setFeature(Feature.PASSIVE, true);
		
		
		System.out.println(realiser.realiseSentence(sentence));
		
//		sentence.clearAllFeatures();
		
		sentence.setTense(Tense.PRESENT);
		System.out.println(realiser.realiseSentence(sentence));

		
		

//		String filename = "./src/main/java/de/uni_due/s3/evaluator2/nlp/lexicon/default-lexicon.xml";
//		LexiconBuilder lex = new LexiconBuilder();
//		try {
//			lex.buildLexicon(filename);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		lex.printLexicon(lex.getNouns());
////		lex.printLexicon(lex.getPronouns());
////		lex.printLexicon(lex.getAdjectives());
////		lex.printLexicon(lex.getAdverbs());
////		lex.printLexicon(lex.getPrepositions());
////		lex.printLexicon(lex.getConjunctions());
////		lex.printLexicon(lex.getVerbs());
////		lex.printLexicon(lex.getDeterminers());

	}

}
