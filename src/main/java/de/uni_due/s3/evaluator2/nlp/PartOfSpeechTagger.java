package de.uni_due.s3.evaluator2.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;



public class PartOfSpeechTagger {
	
	private static String DEFAULT_LEXICON_FILE_PATH = System.getProperty("jboss.server.data.dir") + "/en-pos-maxent.bin";


	public static void main(String[] args) {
		
		InputStream modelIn;
		try {
			modelIn = new FileInputStream(DEFAULT_LEXICON_FILE_PATH);
			POSModel model = new POSModel(modelIn);
			
			POSTaggerME tagger = new POSTaggerME(model);

			String sent[] = new String[]{"The", "man", "runs", "home"};		  
				
			String tags[] = tagger.tag(sent);
			
			for (String string : tags) {
				System.out.println(string);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	


     }

	
	
	
	}

