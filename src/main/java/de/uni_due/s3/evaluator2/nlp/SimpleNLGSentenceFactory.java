package de.uni_due.s3.evaluator2.nlp;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

	/**
	 * method to map the XML Tense Parameter to the corresponding SimpleNLG Enum
	 * 
	 * @param xmlTense
	 *            the xmlTense Parameter to be mapped
	 * @return the Combination of Form and Tense in SimpleNLG Format
	 */
	private static TenseCombination mapTense(String xmlTense) throws InvalidTenseException {

		TenseCombination tenseCombination;

		switch (xmlTense) {

		case "past simple":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PAST, "simple");
			break;
		case "present simple":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PRESENT, "simple");
			break;
		case "future simple":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.FUTURE, "simple");
			break;
		case "present progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PRESENT, Feature.PROGRESSIVE);
			break;
		case "past progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PAST, Feature.PROGRESSIVE);
			break;
		case "future progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.FUTURE, Feature.PROGRESSIVE);
			break;
		case "present perfect":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PRESENT, Feature.PERFECT);
			break;
		case "past perfect":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PAST, Feature.PERFECT);
			break;
		case "future perfect":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.FUTURE, Feature.PERFECT);
			break;
		case "present perfect progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PRESENT,
					Feature.PERFECT + Feature.PROGRESSIVE);
			break;
		case "past perfect progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.PAST,
					Feature.PERFECT + Feature.PROGRESSIVE);
			break;
		case "future perfect progressive":
			tenseCombination = new TenseCombination(simplenlg.features.Tense.FUTURE,
					Feature.PERFECT + Feature.PROGRESSIVE);
			break;
		default:
			throw new InvalidTenseException();
		}

		return tenseCombination;

	}

	/**
	 * method to set the complete tense for a sentence (consisting of tense
	 * (e.g. past, perfect, ... ) and form (simple, perfect, ... ) ).
	 * 
	 * @param sentence
	 *            the tense of this sentence will be adjusted.
	 * @param sentenceTense
	 *            the target tense for the sentence to be adjusted.
	 */
	private void setCompleteTenseForSentence(SPhraseSpec sentence, TenseCombination sentenceTense) {
		sentence.setFeature(Feature.TENSE, sentenceTense.getTense());
		sentence.setFeature(Feature.PROGRESSIVE, sentenceTense.isProgressive());
		sentence.setFeature(Feature.PERFECT, sentenceTense.isPerfect());
	}

	/**
	 * method to generate a sentence in a given tense.
	 * 
	 * @param tense
	 *            of the sentence to generate.
	 * @param context
	 *            of the sentence to generate.
	 */
	private SPhraseSpec generateRandomSentence(String context, Lexicon lexicon, NLGFactory nlgFactory) {
		// TODO write a method to fetch the words of the sentence according to
		// the context/difficulty
		Random rand = new Random();
		String subject = "", object = "", verb = "";
		switch (context) {
		case "TESTCONTEXT":
			try {
				de.uni_due.s3.evaluator2.nlp.lexicon.LexiconJack lex = LexiconBuilder.buildDefaultLexicon();

				subject = lex.getNouns().get(rand.nextInt(lex.getNouns().size())).getBase();
				Word _verb = lex.getVerbs().get(rand.nextInt(lex.getVerbs().size()));
				while (!_verb.getProperties().contains(Property.transitive)) {
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

		SPhraseSpec sentence = nlgFactory.createClause();
		sentence.setSubject(subject);
		sentence.setVerb(verb);
		sentence.setObject(object);

		return sentence;
	}

	@Override
	public ArrayList<String> createSentenceTransformation(String context, String source_tense, String target_tense,
			String difficulty) throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException {

		TenseCombination sourceTense = mapTense(source_tense);
		TenseCombination targetTense = mapTense(target_tense);

		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec sentence = generateRandomSentence(context, lexicon, nlgFactory);

		setCompleteTenseForSentence(sentence, sourceTense);
		String source_sentence = realiser.realiseSentence(sentence);

		setCompleteTenseForSentence(sentence, targetTense);
		String target_sentence = realiser.realiseSentence(sentence);
		System.out.println(target_sentence);

		ArrayList<String> result = new ArrayList<String>();
		result.add(source_sentence);
		result.add(target_sentence);
		return result;
	}

	@Override
	public ArrayList<String> createSentenceWithTense(String tense, String context)
			throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException {
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);

		SPhraseSpec sentence = generateRandomSentence(context, lexicon, nlgFactory);
		setCompleteTenseForSentence(sentence, mapTense(tense));
		String rand_sentence = realiser.realiseSentence(sentence);

		System.out.println(rand_sentence);
		System.out.println(tense);
		ArrayList<String> result = new ArrayList<String>();
		result.add(rand_sentence);
		result.add(tense);

		return result;
	}

	@Override
	public String createSimpleSentenceInTense(String tense, String subject, String object, String verb)
			throws InvalidTenseException {
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		
		SPhraseSpec sentence = nlgFactory.createClause();
		sentence.setSubject(subject);
		sentence.setVerb(verb);
		sentence.setObject(object);
		
		setCompleteTenseForSentence(sentence, mapTense(tense));
		String generatedSentence = realiser.realiseSentence(sentence);
		
		return generatedSentence;
		
	}

	@Override
	public ArrayList<String> createSentencesForTenseTransformation(String source_tense, String target_tense,
			String subject, String object, String verb)
			throws InvalidContextException, InvalidDifficultyExeption, InvalidTenseException {
		
		TenseCombination sourceTense = mapTense(source_tense);
		TenseCombination targetTense = mapTense(target_tense);
		
		Lexicon lexicon = Lexicon.getDefaultLexicon();
		NLGFactory nlgFactory = new NLGFactory(lexicon);
		Realiser realiser = new Realiser(lexicon);
		
		SPhraseSpec sentence = nlgFactory.createClause();
		sentence.setSubject(subject);
		sentence.setVerb(verb);
		sentence.setObject(object);

		setCompleteTenseForSentence(sentence, sourceTense);
		String source_sentence = realiser.realiseSentence(sentence);

		setCompleteTenseForSentence(sentence, targetTense);
		String target_sentence = realiser.realiseSentence(sentence);
		System.out.println(target_sentence);

		ArrayList<String> result = new ArrayList<String>();
		result.add(source_sentence);
		result.add(target_sentence);
		return result;
	}

	

}
