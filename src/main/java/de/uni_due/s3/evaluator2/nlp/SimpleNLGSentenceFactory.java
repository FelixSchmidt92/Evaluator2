package de.uni_due.s3.evaluator2.nlp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
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
//import simplenlg.lexicon.Lexicon;
import simplenlg.realiser.english.Realiser;

public class SimpleNLGSentenceFactory implements ISentenceFactory {

	
	@Override
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			String difficulty) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException {
		// Lexicon lexicon = Lexicon.getDefaultLexicon();
	
		
		Random rand = new Random();
		String subject="", object="", verb="";
//		System.out.println("createSentenceTransformation ... ");
		switch (context) {
			case "TESTCONTEXT":
				try {
				de.uni_due.s3.evaluator2.nlp.lexicon.Lexicon lex = LexiconBuilder.buildDefaultLexicon();

				subject = lex.getNouns().get(rand.nextInt(lex.getNouns().size())).getBase();
				Word _verb = lex.getVerbs().get(rand.nextInt(lex.getVerbs().size()));
				while(!_verb.getProperties().contains(Property.transitive)){
					_verb = lex.getVerbs().get(rand.nextInt(lex.getVerbs().size()));
				}
				verb = _verb.getBase();
				object = lex.getNouns().get(rand.nextInt(lex.getNouns().size())).getBase();	
				}
				
				catch (Exception e) {
					// TODO: handle exception
				}
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
		
		sentence.setFeature(Feature.TENSE, simplenlg.features.Tense.PRESENT);
		String source_sentence = realiser.realiseSentence(sentence);
		
//		System.out.println("createSentenceTransformation done ... ");
		
		sentence.setFeature(Feature.TENSE, simplenlg.features.Tense.PAST);
		String target_sentence = realiser.realiseSentence(sentence);
		
//		System.out.println("source: " + source_sentence + " target: " + target_sentence);

		ArrayList<String> result = new ArrayList<String>();
		result.add(source_sentence);
		result.add(target_sentence);	
		return result;
	}

}
