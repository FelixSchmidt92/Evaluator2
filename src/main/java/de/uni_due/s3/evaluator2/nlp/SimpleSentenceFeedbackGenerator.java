package de.uni_due.s3.evaluator2.nlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.util.Span;

public class SimpleSentenceFeedbackGenerator {
	
	private static String SUBJECT_MISSING_MESSAGE = "The subject is missing in the input sentence.";
	private static String VERB_MISSING_MESSAGE = "The verb is missing in the input sentence.";
	private static String OBJECT_MISSING_MESSAGE = "The object is missing in the input sentence.";
	
	private static String DETERMINER_MISSING_MESSAGE = "";
	
	public static String generateFeedbackForSimpleSentence(String[] correctSentenceTokens, String[] correctSentencePOSTags, String[] userSentenceTokens, String[] userSentencePOSTags) throws IOException {
		
		try {
		// auslagern?
		ChunkerME chunker = ChunkerBuilder.getChunkerInstance();
		
		VerbPhraseComparator verbPhraseComparator = new VerbPhraseComparator();
		NounPhraseComparator subjectPhraseComparator = new NounPhraseComparator(NounPhraseComparator.TYPE_SUBJECT);
		NounPhraseComparator objectPhraseComparator = new NounPhraseComparator(NounPhraseComparator.TYPE_OBJECT);
		////
		String message = new String();
		ArrayList<String> messageList = new ArrayList<>();
		Span[] correctSentenceSpans  = chunker.chunkAsSpans(correctSentenceTokens, correctSentencePOSTags); 
	    Span[] userSentenceSpans  = chunker.chunkAsSpans(userSentenceTokens, userSentencePOSTags); 
	
	    
	    ArrayList<Span> correctSpanList = new ArrayList<Span>( Arrays.asList(correctSentenceSpans) );
	    ArrayList<Span> userSpanList = new ArrayList<Span>( Arrays.asList(userSentenceSpans) );
	   
	    Span correctSubjectSpan = extractFirstIfType(correctSpanList, "NP");
	    Span correctVerbSpan = extractFirstIfType(correctSpanList, "VP");
	    Span correctObjectSpan = extractFirstIfType(correctSpanList, "NP");
	    
	    Span userSubjectSpan = extractFirstIfType(userSpanList, "NP");
	    Span userVerbSpan = extractFirstIfType(userSpanList, "VP");
	    Span userObjectSpan = extractFirstIfType(userSpanList, "NP");
	    
	    if(userSubjectSpan==null) {
	    	messageList.add(SUBJECT_MISSING_MESSAGE);
	    }
	    else {
	    	if(correctSubjectSpan!=null) {
	    		String[] correctSubjectTokens = Arrays.copyOfRange(correctSentenceTokens, correctSubjectSpan.getStart(), correctSubjectSpan.getEnd());
	 		    String[] correctSubjectPOSTags = Arrays.copyOfRange(correctSentencePOSTags, correctSubjectSpan.getStart(), correctSubjectSpan.getEnd());
	 		    
	 		    String[] userSubjectTokens = Arrays.copyOfRange(userSentenceTokens, userSubjectSpan.getStart(), userSubjectSpan.getEnd());
	 		    String[] userSubjectPOSTags = Arrays.copyOfRange(userSentencePOSTags, userSubjectSpan.getStart(), userSubjectSpan.getEnd());
	 		    
	 		    messageList.addAll(subjectPhraseComparator.comparePhrase(correctSubjectTokens, correctSubjectPOSTags, userSubjectTokens, userSubjectPOSTags));
	    	}    	
	    }
	    if(userVerbSpan==null) {
	    	messageList.add(VERB_MISSING_MESSAGE);
	    }
	    else {
	    	if(correctVerbSpan!=null) {
	    		String[] correctVerbTokens = Arrays.copyOfRange(correctSentenceTokens, correctVerbSpan.getStart(), correctVerbSpan.getEnd());
	 		    String[] correctVerbPOSTags = Arrays.copyOfRange(correctSentencePOSTags, correctVerbSpan.getStart(), correctVerbSpan.getEnd());
	 		    
	 		    String[] userVerbTokens = Arrays.copyOfRange(userSentenceTokens, userVerbSpan.getStart(), userVerbSpan.getEnd());
	 		    String[] userVerbPOSTags = Arrays.copyOfRange(userSentencePOSTags, userVerbSpan.getStart(), userVerbSpan.getEnd());
	 		    
	 		    messageList.addAll(verbPhraseComparator.comparePhrase(correctVerbTokens, correctVerbPOSTags, userVerbTokens, userVerbPOSTags));	    	}
	    }
	    if(userObjectSpan==null) {
	    	messageList.add(OBJECT_MISSING_MESSAGE);	
	    }
	    else {
	    	if(correctObjectSpan!=null) {
	    		String[] correctObjectTokens = Arrays.copyOfRange(correctSentenceTokens, correctObjectSpan.getStart(), correctObjectSpan.getEnd());
	 		    String[] correctObjectPOSTags = Arrays.copyOfRange(correctSentencePOSTags, correctObjectSpan.getStart(), correctObjectSpan.getEnd());
	 		    
	 		    String[] userObjectTokens = Arrays.copyOfRange(userSentenceTokens, userObjectSpan.getStart(), userObjectSpan.getEnd());
	 		    String[] userObjectPOSTags = Arrays.copyOfRange(userSentencePOSTags, userObjectSpan.getStart(), userObjectSpan.getEnd());
	 		    
	 		    messageList.addAll(objectPhraseComparator.comparePhrase(correctObjectTokens, correctObjectPOSTags, userObjectTokens, userObjectPOSTags));
	    	}	
	    }
		
	    for (String currentMessage : messageList) {
			message = message + " " + currentMessage;
		}
	    System.out.println(message);
		
	    return message;
	    
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
		
	private static Span extractFirstIfType(ArrayList<Span> spans, String type) {
		
		if( ( !spans.isEmpty() ) && spans.get(0).getType().equals(type)) {
	    	Span span = spans.get(0);
	    	spans.remove(0);
	    	return span;
	    }
		return null;
	}
	
	
	public static void main (String[] args){
		
		ArrayList<String> result = new ArrayList<>();
		
		String sentence1 = "The animals had been eating apples.";
		String sentence2 = "animal was eating apple.";

		System.out.println(sentence1);
		System.out.println(sentence2);
		try {
				
		   InputStream modelIn;
		   String pathToFile = "C:/Users/Wilfried/Documents/Uni-Duisburg-Essen/Bachelor/Wintersemester2017/Projekt/jboss_mit_JACK/server/default/data/en-pos-maxent.bin";
		   modelIn = new FileInputStream(pathToFile);
		   POSModel model = new POSModel(modelIn);
		   
		   POSTaggerME tagger = new POSTaggerME(model);	
		   SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;  
		   String[] tokensS1 = tokenizer.tokenize(sentence1);
		   String[] tokensS2 = tokenizer.tokenize(sentence2);
		   
		   String[] posS1 = tagger.tag(tokensS1);
		   String[] posS2 = tagger.tag(tokensS2);
		   
//		   for (int i = 0; i < posS1.length; i++) {
//			System.out.println(posS1[i]);
//		}
//		   for (int i = 0; i < posS2.length; i++) {
//				System.out.println(posS2[i]);
//			}
					
				
//		// Get the main Phrases (Subject, Verb, Object)  
//	  
//			InputStream is = new 
//		    FileInputStream("C:/Users/Schwein/Desktop/chunker.bin"); 
//		    ChunkerModel chunkerModel = new ChunkerModel(is);  
//		    
//		    //Instantiate the ChunkerME class 
//		    ChunkerME chunkerME = new ChunkerME(chunkerModel);
//		     
//		    //Generating the chunks 
//		    Span[] spans  = chunkerME.chunkAsSpans(tokensS1, posS1); 
//		    Span[] spans2  = chunkerME.chunkAsSpans(tokensS2, posS2); 
//
//		    for (int i = 0; i < spans.length; i++) {
//				System.out.println(spans[i]);
//			}
//		    System.out.println("----------");
//		    for (int i = 0; i < spans2.length; i++) {
//						System.out.println(spans2[i]);
//					}
//		    ArrayList<Span> spanList = new ArrayList<Span>( Arrays.asList(spans) );
//		    ArrayList<Span> spanList2 = new ArrayList<Span>( Arrays.asList(spans2) );
//		    
//		    // Satz besteht immer aus Subjekt, Verb, Objekt (in der Reihenfolge)
//		    // 
//		    
//		    Span subjectSpan = extractFirstIfType(spanList, "NP");
//		    Span verbSpan = extractFirstIfType(spanList, "VP");
//		    Span objectSpan = extractFirstIfType(spanList, "NP");
//		    
//		    Span subjectSpan2 = extractFirstIfType(spanList2, "NP");
//		    Span verbSpan2 = extractFirstIfType(spanList2, "VP");
//		    Span objectSpan2 = extractFirstIfType(spanList2, "NP");
//
//
//		    if(subjectSpan2==null) {
//		    	result.add(SUBJECT_MISSING_MESSAGE);
//		    }
//		    else {
//		    	
//		    }
//		    if(verbSpan2==null) {
//		    	result.add(VERB_MISSING_MESSAGE);
//		    }
//		    else {
//		    	
//		    }
//		    if(objectSpan2==null) {
//		    	result.add(OBJECT_MISSING_MESSAGE);		    
//		    }
//		    else {
//		    	
//		    }
//		    
//		    String[] correctVerbTokens = Arrays.copyOfRange(tokensS1, verbSpan.getStart(), verbSpan.getEnd());
//		    String[] correctVerbPOSTags = Arrays.copyOfRange(posS1, verbSpan.getStart(), verbSpan.getEnd());
//		    
//		    String[] userVerbTokens = Arrays.copyOfRange(tokensS2, verbSpan2.getStart(), verbSpan2.getEnd());
//		    String[] userVerbPOSTags = Arrays.copyOfRange(posS2, verbSpan2.getStart(), verbSpan2.getEnd());
//		    
//		    VerbPhraseComparator verbPhraseComparator = new VerbPhraseComparator();
//		    result.addAll(verbPhraseComparator.comparePhrase(correctVerbTokens, correctVerbPOSTags, userVerbTokens, userVerbPOSTags));
//		    
//		    for(String s : result) {
//		    	System.out.println(s);
//		    }
		    
		    System.out.print(generateFeedbackForSimpleSentence(tokensS1, posS1, tokensS2, posS2));
		         						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
