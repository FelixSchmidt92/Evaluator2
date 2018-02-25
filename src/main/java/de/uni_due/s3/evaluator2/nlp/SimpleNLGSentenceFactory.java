package de.uni_due.s3.evaluator2.nlp;

import java.util.ArrayList;
import java.util.Random;

import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidContextException;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidDifficultyExeption;
import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
import de.uni_due.s3.evaluator2.nlp.lexicon.LexiconBuilder;
import de.uni_due.s3.evaluator2.nlp.lexicon.Property;
import de.uni_due.s3.evaluator2.nlp.lexicon.Word;
import simplenlg.features.*;
import simplenlg.framework.DocumentElement;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.NPPhraseSpec;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.phrasespec.VPPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class SimpleNLGSentenceFactory implements ISentenceFactory {

	@Override
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			String difficulty) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException {
		// Lexicon lexicon = Lexicon.getDefaultLexicon();
		
		Random rand = new Random();
		String subject="", object="", verb="";
		System.out.println("createSentenceTransformation ... ");
		switch (context) {
			case "TESTCONTEXT":
				//String filename = "./src/main/java/de/uni_due/s3/evaluator2/nlp/lexicon/default-lexicon.xml";
				String filename = "C:/Users/Wilfried/Documents/Uni-Duisburg-Essen/Bachelor/Wintersemester2017/Projekt/Jack2-Development-37ecbcb79389ba2e8ba6486e8d4d76f2f3b2b5c4/de.uni_due.s3.jack2.server.core/resources/default-lexicon.xml";
				LexiconBuilder lex = new LexiconBuilder();
				try {
					lex.buildLexicon(filename);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				subject = lex.getNouns().get(rand.nextInt(lex.getNouns().size())).getBase();
				Word _verb = lex.getVerbs().get(rand.nextInt(lex.getVerbs().size()));
				while(!_verb.getProperties().contains(Property.transitive)){
					_verb = lex.getVerbs().get(rand.nextInt(lex.getVerbs().size()));
				}
				verb = _verb.getBase();
				object = lex.getNouns().get(rand.nextInt(lex.getNouns().size())).getBase();
				break;
			default:
				break;
		}
		 
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		
		
		SPhraseSpec sentence  = nlgFactory.createClause();
		sentence.setSubject(subject);
		sentence.setVerb(verb);
		sentence.setObject(object);
		
		//sentence.setFeature(Feature.TENSE, Tense.PRESENT);
		String source_sentence = realiser.realiseSentence(sentence);
		
		System.out.println("createSentenceTransformation done ... ");
		
		//String target_sentence = 

		ArrayList<String> result = new ArrayList<String>();
		result.add(source_sentence);
		//result.add(subject+" "+ verb+" "+ object);
		result.add("This is the target sentence");
		return result;
	}

}
