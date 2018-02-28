package de.uni_due.s3.evaluator2.nlp;

public class TenseCombination {
	
	public simplenlg.features.Tense getTense() {
		return tense;
	}

	public String getForm() {
		return form;
	}

	private simplenlg.features.Tense tense;
	private String form;
	
	public TenseCombination(simplenlg.features.Tense tense, String form) {
		
		this.tense = tense;
		this.form = form;
	}

}
