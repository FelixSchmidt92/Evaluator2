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

public class NLPFeedbackGenerator {
	
	private static String SUBJECT_MISSING_MESSAGE = "In dem Satz scheint das Subjekt zu fehlen.";
	private static String VERB_MISSING_MESSAGE = "In dem Satz scheint das Verb zu fehlen.";
	private static String OBJECT_MISSING_MESSAGE = "In dem Satz scheint das Objekt zu fehlen";
	
	private static String DETERMINER_MISSING_MESSAGE = "";
	
	
	public static String generateFeedback(String[] correctSentenceTokens, String[] correctSentencePOSTags, String[] userSentenceTokens, String[] userSentencePOSTags) {
	
		for (int i = 0; i < correctSentencePOSTags.length; i++) {
			
		}
		
		return null;
		
	}
	
	private static String compareSubjects(String[] correctSentenceSubjectTokens, String[] correctSentenceSubjectPOSTags, String[] userSentenceSubjectTokens, String[] userSentenceSubjectPOSTags){
		
		return null;
	}
	
	private static String compareVerbs(String[] correctSentenceSubjectTokens, String[] correctSentenceSubjectPOSTags, String[] userSentenceSubjectTokens, String[] userSentenceSubjectPOSTags) {
		return null;
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
		
		String sentence1 = "The animals will have eaten.";
		String sentence2 = "The animals eat fish";
		
		try {
				
		   InputStream modelIn;
		   String pathToFile = "C:/Users/Schwein/Downloads/en-pos-maxent.bin";
		   modelIn = new FileInputStream(pathToFile);
		   POSModel model = new POSModel(modelIn);
		   
		   POSTaggerME tagger = new POSTaggerME(model);	
		   SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;  
		   String[] tokensS1 = tokenizer.tokenize(sentence1);
		   String[] tokensS2 = tokenizer.tokenize(sentence2);
		   
		   String[] posS1 = tagger.tag(tokensS1);
		   String[] posS2 = tagger.tag(tokensS2);
		   
		   for (int i = 0; i < posS1.length; i++) {
			System.out.println(posS1[i]);
		}
				
		// Get the main Phrases (Subject, Verb, Object)  
	  
			InputStream is = new 
		    FileInputStream("C:/Users/Schwein/Desktop/chunker.bin"); 
		    ChunkerModel chunkerModel = new ChunkerModel(is);  
		    
		    //Instantiate the ChunkerME class 
		    ChunkerME chunkerME = new ChunkerME(chunkerModel);
		     
		    //Generating the chunks 
		    Span[] spans  = chunkerME.chunkAsSpans(tokensS1, posS1); 
		    
		    for (int i = 0; i < spans.length; i++) {
				System.out.println(spans[i]);
			}
		    ArrayList<Span> spanList = new ArrayList<Span>( Arrays.asList(spans) );
		    
		    // Satz besteht immer aus Subjekt, Verb, Objekt (in der Reihenfolge)
		    // 
		    
		    Span subjectSpan = extractFirstIfType(spanList, "NP");
		    Span verbSpan = extractFirstIfType(spanList, "VP");
		    Span objectSpan = extractFirstIfType(spanList, "NP");

		    if(subjectSpan==null) {
		    	result.add(SUBJECT_MISSING_MESSAGE);
		    }
		    else {
		    	
		    }
		    if(verbSpan==null) {
		    	result.add(VERB_MISSING_MESSAGE);
		    }
		    else {
		    	
		    }
		    if(objectSpan==null) {
		    	result.add(OBJECT_MISSING_MESSAGE);		    
		    }
		    else {
		    	
		    }
		    for(String s : result) {
		    	System.out.println(s);
		    }
		         
	        generateFeedback(tokensS1, posS1, tokensS2, posS2);
						
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
