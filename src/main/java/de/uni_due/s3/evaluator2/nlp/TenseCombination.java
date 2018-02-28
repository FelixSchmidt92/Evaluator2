package de.uni_due.s3.evaluator2.nlp;

import de.uni_due.s3.evaluator2.nlp.exceptions.InvalidTenseException;
import simplenlg.features.Feature;

public class TenseCombination {
	
	private simplenlg.features.Tense tense;

	private boolean isProgressive = false;
	private boolean isSimple = false;
	private boolean isPerfect = false;
	
	public simplenlg.features.Tense getTense() {
		return tense;
	}


	public boolean isProgressive() {
		return isProgressive;
	}

	public boolean isSimple() {
		return isSimple;
	}

	public boolean isPerfect() {
		return isPerfect;
	}

	
	public TenseCombination(simplenlg.features.Tense tense, String form) throws InvalidTenseException {
		
		switch(form) {
		
		case Feature.PERFECT + Feature.PROGRESSIVE:
			isPerfect = true;
			isProgressive = true;
			break;
		case Feature.PROGRESSIVE:
			isProgressive = true;
			break;	
		case Feature.PERFECT:
			isPerfect = true;
			break;		
		case "simple":
			isSimple=true;
			break;	
		default:
			throw new InvalidTenseException();	
		}
		this.tense = tense;
	}

}
