package de.uni_due.s3.evaluator2.nlp.dictionary;

import java.util.ArrayList;

public class Word {
	private String base = "";
	private String category = "";
	private String plural = "";
	private String comparative = "";
	private String superlative = "";
	private String id = "";
	private String present3s = "";
	private String past = "";
	private String pastParticiple = "";
	private String presentParticiple = "";
	private ArrayList<Property> properties = new ArrayList<Property>();;

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPlural() {
		return plural;
	}

	public void setPlural(String plural) {
		this.plural = plural;
	}

	public String getComparative() {
		return comparative;
	}

	public void setComparative(String comparative) {
		this.comparative = comparative;
	}

	public String getSuperlative() {
		return superlative;
	}

	public void setSuperlative(String superlativ) {
		this.superlative = superlativ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPresent3s() {
		return present3s;
	}

	public void setPresent3s(String present3s) {
		this.present3s = present3s;
	}

	public String getPast() {
		return past;
	}

	public void setPast(String past) {
		this.past = past;
	}

	public String getPastParticiple() {
		return pastParticiple;
	}

	public void setPastParticiple(String pastParticiple) {
		this.pastParticiple = pastParticiple;
	}

	public String getPresentParticiple() {
		return presentParticiple;
	}

	public void setPresentParticiple(String presentParticiple) {
		this.presentParticiple = presentParticiple;
	}

	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

	@Override
	public String toString() {
		String word_str = "Word [base=" + base + ", category=" + category;
		if (!this.id.equals(""))
			word_str += ", id=" + this.id;
		if (!this.plural.equals(""))
			word_str += ", plural=" + this.plural;
		if (!this.comparative.equals(""))
			word_str += ", comparative=" + this.comparative;
		if (!this.superlative.equals(""))
			word_str += ", superlative=" + this.superlative;
		if (!this.present3s.equals(""))
			word_str += ", present3s=" + this.present3s;
		if (!this.past.equals(""))
			word_str += ", past=" + this.past;
		if (!this.presentParticiple.equals(""))
			word_str += ", presentParticiple=" + this.presentParticiple;
		if (!this.pastParticiple.equals(""))
			word_str += ", pastParticiple=" + this.pastParticiple;
		if (!this.getProperties().isEmpty()){
			word_str +=", Properties=" ;
			for(Property p : this.getProperties()){
				word_str +=p.toString()+", " ;
			}
			
		}

		word_str += "]";

		return word_str;
	}
}
