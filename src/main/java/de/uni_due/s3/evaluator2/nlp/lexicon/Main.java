package de.uni_due.s3.evaluator2.nlp.lexicon;


public class Main {

	public static void main(String[] args) {

		String filename = "./src/main/java/de/uni_due/s3/evaluator2/nlp/lexicon/default-lexicon.xml";
		LexiconBuilder lex = new LexiconBuilder();
		try {
			lex.buildLexicon(filename);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lex.printLexicon(lex.getNouns());
//		lex.printLexicon(lex.getPronouns());
//		lex.printLexicon(lex.getAdjectives());
//		lex.printLexicon(lex.getAdverbs());
//		lex.printLexicon(lex.getPrepositions());
//		lex.printLexicon(lex.getConjunctions());
//		lex.printLexicon(lex.getVerbs());
//		lex.printLexicon(lex.getDeterminers());

	}

}
