package de.uni_due.s3.evaluator2.nlp;

public class TenseDeterminer {
	
	private static String UNKNOWN_TENSE = "unknown tense";
	
	private static  String VERB_BASE_FORM = "VB";
	private static String VERB_PAST_TENSE = "VBD";
	private static String VERB_PRESENT_PARTICIPLE = "VBG";
	private static String VERB_PAST_PARTICIPLE = "VBN";
	private static String VERB_NON_THIRD_PERSON_SINGULAR = "VBP";
	private static String VERB_THIRD_PERSON_SINGULAR = "VBZ";
	private static String MODAL_VERB = "MD";	
	
	private static String PRESENT_SIMPLE = "present simple";
	private static String PRESENT_PERFECT = "present perfect";
	private static String PRESENT_PROGRESSIVE = "present progressive";
	private static String PRESENT_PERFECT_PROGRESSIVE = "present perfect progressive";
	
	private static String FUTURE_SIMPLE = "future simple";
	private static String FUTURE_PERFECT = "future perfect";
	private static String FUTURE_PROGRESSIVE = "future progressive";
	private static String FUTURE_PERFECT_PROGRESSIVE = "future perfect progressive";
	
	private static String PAST_SIMPLE = "past simple";
	private static String PAST_PERFECT = "past perfect";
	private static String PAST_PROGRESSIVE = "past progressive";
	private static String PAST_PERFECT_PROGRESSIVE = "past perfect progressive";
	
	public static String determineTense(String[] posTags) {
		
		if(posTags.length==0) {
			return UNKNOWN_TENSE;
		}
		
		if(posTags[0].equals(VERB_THIRD_PERSON_SINGULAR)  || posTags[0].equals(VERB_BASE_FORM) || posTags[0].equals(VERB_NON_THIRD_PERSON_SINGULAR) ) {
			// Present tenses ...		
			if(posTags.length==1) 
				return PRESENT_SIMPLE;
			else {
				if(posTags[1].equals(VERB_PAST_PARTICIPLE)) {
					if(posTags.length==2) {
						return PRESENT_PERFECT;
					}
					else {
						if(posTags[2].equals(VERB_PRESENT_PARTICIPLE)) {
							if(posTags.length==3) {
								return PRESENT_PERFECT_PROGRESSIVE;
							}
							else {
								return UNKNOWN_TENSE;
							}
						}
						else 
							return UNKNOWN_TENSE;				
					}
				}
				else if(posTags[1].equals(VERB_PRESENT_PARTICIPLE)) {
					if(posTags.length==2) {
						return PRESENT_PROGRESSIVE;
					}
					else{
						return UNKNOWN_TENSE;
					}
				}
				else 
					return UNKNOWN_TENSE;
								
			}
		}
		
		else if(posTags[0].equals(VERB_PAST_TENSE)) {
			// Past tenses...
			if(posTags.length==1) 
				return PAST_SIMPLE;
			else {
				if(posTags[1].equals(VERB_PRESENT_PARTICIPLE)) {
					if(posTags.length==2) 
						return PAST_PROGRESSIVE;
					else {
						return UNKNOWN_TENSE;
					}
				}
				else if(posTags[1].equals(VERB_PAST_PARTICIPLE)) {
					if(posTags.length==2) 
						return PAST_PERFECT;
					else if(posTags[2].equals(VERB_PRESENT_PARTICIPLE)){
						if(posTags.length==3) 
							return PAST_PERFECT_PROGRESSIVE;
						else {
							return UNKNOWN_TENSE;
						}
					}
				}
				else {
					return UNKNOWN_TENSE;
				}
				
			}
			
		}
		else if(posTags[0].equals(MODAL_VERB)) {
			if(posTags.length==1)
				return UNKNOWN_TENSE;
			else if(posTags[1].equals(VERB_BASE_FORM) ) {
				if(posTags.length==2) 
					return FUTURE_SIMPLE;
				else if (posTags[2].equals(VERB_PRESENT_PARTICIPLE)) {
					if(posTags.length==3) {
						return FUTURE_PROGRESSIVE;	
					}
					else {
						return UNKNOWN_TENSE;
					}				
				}
				else if(posTags[2].equals(VERB_PAST_PARTICIPLE)) {
					if(posTags.length==3) {
						return FUTURE_PERFECT;
					}
					else if(posTags[3].equals(VERB_PRESENT_PARTICIPLE)) {
						if(posTags.length==4) {
						return FUTURE_PERFECT_PROGRESSIVE;
						}
						else {
							return UNKNOWN_TENSE;
						}
					}
					else {
						return UNKNOWN_TENSE;
					}
				}
				else {
					return UNKNOWN_TENSE;
				}		
			}
		}		
		return UNKNOWN_TENSE;
	}

}
