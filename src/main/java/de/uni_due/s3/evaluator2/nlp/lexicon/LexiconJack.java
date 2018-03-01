package de.uni_due.s3.evaluator2.nlp.lexicon;

import java.util.ArrayList;

public class LexiconJack {

	private ArrayList<Word> nouns = new ArrayList<Word>();
	private ArrayList<Word> pronouns = new ArrayList<Word>();
	private ArrayList<Word> determiners = new ArrayList<Word>();
	private ArrayList<Word> verbs = new ArrayList<Word>();
	private ArrayList<Word> modals = new ArrayList<Word>();
	private ArrayList<Word> adjectives = new ArrayList<Word>();
	private ArrayList<Word> adverbs = new ArrayList<Word>();
	private ArrayList<Word> prepositions = new ArrayList<Word>();
	private ArrayList<Word> conjunctions = new ArrayList<Word>();

	public ArrayList<Word> getNouns() {
		return nouns;
	}
	
	private boolean isWordInList(String word, ArrayList<Word> wordsList){
		for(Word word_element: wordsList){
			if(word_element.getBase().equals(word)){
				return true;
			}
		}
		return false;
	}

	public boolean wordExistsForCategory(String word, String category) {
		boolean result=false;
		switch (category) {
			case "noun":
				result=isWordInList(word, this.getNouns());
				break;
			case "pronoun":
				result=isWordInList(word, this.getPronouns());
				break;
			case "determiner":
				result=isWordInList(word, this.getDeterminers());
				break;
			case "verb":
				result=isWordInList(word, this.getVerbs());
				break;
			case "modal":
				result=isWordInList(word, this.getModals());
				break;
			case "adjective":
				result=isWordInList(word, this.getAdjectives());
				break;
			case "adverb":
				result=isWordInList(word, this.getAdverbs());
				break;
			case "preposition":
				result=isWordInList(word, this.getPrepositions());
				break;
			case "conjuction":
				result=isWordInList(word, this.getConjunctions());
				break;
			default:
				break;

		}
		return result;
	}

	public void setNouns(ArrayList<Word> nouns) {
		this.nouns = nouns;
	}

	public ArrayList<Word> getPronouns() {
		return pronouns;
	}

	public void setPronouns(ArrayList<Word> pronouns) {
		this.pronouns = pronouns;
	}

	public ArrayList<Word> getDeterminers() {
		return determiners;
	}

	public void setDeterminers(ArrayList<Word> determiners) {
		this.determiners = determiners;
	}

	public ArrayList<Word> getVerbs() {
		return verbs;
	}

	public void setVerbs(ArrayList<Word> verbs) {
		this.verbs = verbs;
	}

	public ArrayList<Word> getModals() {
		return modals;
	}

	public void setModals(ArrayList<Word> modals) {
		this.modals = modals;
	}

	public ArrayList<Word> getAdjectives() {
		return adjectives;
	}

	public void setAdjectives(ArrayList<Word> adjectives) {
		this.adjectives = adjectives;
	}

	public ArrayList<Word> getAdverbs() {
		return adverbs;
	}

	public void setAdverbs(ArrayList<Word> adverbs) {
		this.adverbs = adverbs;
	}

	public ArrayList<Word> getPrepositions() {
		return prepositions;
	}

	public void setPrepositions(ArrayList<Word> prepositions) {
		this.prepositions = prepositions;
	}

	public ArrayList<Word> getConjunctions() {
		return conjunctions;
	}

	public void setConjunctions(ArrayList<Word> conjunctions) {
		this.conjunctions = conjunctions;
	}

	public void printLexicon(ArrayList<Word> list) {
		for (Word w : list) {
			System.out.println(w);
		}
	}

}
