package de.uni_due.s3.evaluator2.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;

public class ChunkerBuilder {
	
	private static ChunkerME CHUNKER_INSTANCE;
	private static String PATH_TO_CHUNKER_FILE = System.getProperty("jboss.server.data.dir") + "/chunker.bin"; //"C:/Users/Wilfried/Documents/Uni-Duisburg-Essen/Bachelor/Wintersemester2017/Projekt/jboss_mit_JACK/server/default/data/chunker.bin" ;
	
	public static ChunkerME getChunkerInstance() throws IOException{
		
		if(CHUNKER_INSTANCE==null) {	
			InputStream is;
			is = new FileInputStream(PATH_TO_CHUNKER_FILE);
			ChunkerModel chunkerModel = new ChunkerModel(is);  
			    
			CHUNKER_INSTANCE = new ChunkerME(chunkerModel);
		}
		return CHUNKER_INSTANCE;
	}

}
