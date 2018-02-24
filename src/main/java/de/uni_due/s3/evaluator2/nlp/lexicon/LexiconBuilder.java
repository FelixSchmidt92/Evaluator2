package de.uni_due.s3.evaluator2.nlp.lexicon;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LexiconBuilder {
	private ArrayList<Word> nouns = new ArrayList<Word>();
	private ArrayList<Word> pronouns = new ArrayList<Word>();
	private ArrayList<Word> determiners = new ArrayList<Word>();
	private ArrayList<Word> verbs = new ArrayList<Word>();
	private ArrayList<Word> modals = new ArrayList<Word>();
	private ArrayList<Word> adjectives = new ArrayList<Word>();
	private ArrayList<Word> adverbs = new ArrayList<Word>();
	private ArrayList<Word> prepositions = new ArrayList<Word>();
	private ArrayList<Word> conjunctions = new ArrayList<Word>();
	
	private static Lexicon defaultLexicon;
	private static String DEFAULT_LEXICON_FILE_PATH = System.getProperty("jboss.server.data.dir") + "/default-lexicon.xml";

	public void buildLexicon(String filename) throws Exception {
	

		File inputFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(inputFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("word");
		//System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			//System.out.println("\nCurrent Element :" + nNode.getNodeName());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				Word word = new Word();
				
				word.setBase(eElement.getElementsByTagName("base").item(0).getTextContent());
				word.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
				if (eElement.getElementsByTagName("id").item(0) != null) {
					word.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
					//System.out.println("id");
				}

				switch (word.getCategory()) {
				case "noun":
					if (eElement.getElementsByTagName("plural").item(0) != null) {
						String plura = (String) eElement.getElementsByTagName("plural").item(0).getTextContent();
						//System.out.println(plura);
						word.setPlural(eElement.getElementsByTagName("plural").item(0).getTextContent());
						//System.out.println("plural");
					}

					if (eElement.getElementsByTagName("nonCount").item(0) != null) {
						word.getProperties().add(Property.nonCount);
						//System.out.println("nonCount");
					}

					nouns.add(word);
					//System.out.println(word);
					break;

				case "pronoun":
					pronouns.add(word);
					//System.out.println(word);
					break;

				case "determiner":
					determiners.add(word);
					//System.out.println(word);
					break;

				case "verb":
					if (eElement.getElementsByTagName("transitive").item(0) != null) {
						word.getProperties().add(Property.transitive);
						//System.out.println("transitive");
					}

					if (eElement.getElementsByTagName("intransitive").item(0) != null) {
						word.getProperties().add(Property.intransitive);
						//System.out.println("intransitive");
					}

					if (eElement.getElementsByTagName("ditransitive").item(0) != null) {
						word.getProperties().add(Property.ditransitive);
						//System.out.println("ditransitive");
					}

					if (eElement.getElementsByTagName("present3s").item(0) != null) {
						word.setPresent3s(eElement.getElementsByTagName("present3s").item(0).getTextContent());
						//System.out.println("present3s");
					}

					if (eElement.getElementsByTagName("past").item(0) != null) {
						word.setPast(eElement.getElementsByTagName("past").item(0).getTextContent());
						//System.out.println("past");
					}

					if (eElement.getElementsByTagName("pastParticiple").item(0) != null) {
						word.setPastParticiple(
								eElement.getElementsByTagName("pastParticiple").item(0).getTextContent());
						//System.out.println("pastParticiple");
					}

					if (eElement.getElementsByTagName("presentParticiple").item(0) != null) {
						word.setPresentParticiple(
								eElement.getElementsByTagName("presentParticiple").item(0).getTextContent());
						//System.out.println("presentParticiple");
					}

					verbs.add(word);
					//System.out.println(word);
					break;

				case "adjective":
					if (eElement.getElementsByTagName("classifying").item(0) != null) {
						word.getProperties().add(Property.classifying);
						//System.out.println("classifying");
					}

					if (eElement.getElementsByTagName("predicative").item(0) != null) {
						word.getProperties().add(Property.predicative);
						//System.out.println("predicative");
					}

					if (eElement.getElementsByTagName("qualitative").item(0) != null) {
						word.getProperties().add(Property.qualitative);
						//System.out.println("qualitative");
					}

					if (eElement.getElementsByTagName("comparative").item(0) != null) {
						word.setComparative(eElement.getElementsByTagName("comparative").item(0).getTextContent());
						//System.out.println("comparative");
					}

					if (eElement.getElementsByTagName("superlative").item(0) != null) {
						word.setSuperlative(eElement.getElementsByTagName("superlative").item(0).getTextContent());
						//System.out.println("superlative");
					}

					adjectives.add(word);
					//System.out.println(word);
					break;

				case "adverb": 
					if (eElement.getElementsByTagName("intensifier").item(0) != null) {
						word.getProperties().add(Property.intensifier);
						//System.out.println("intensifier");
					}

					if (eElement.getElementsByTagName("verb_modifier").item(0) != null) {
						word.getProperties().add(Property.verb_modifier);
						//System.out.println("verb_modifier");
					}

					if (eElement.getElementsByTagName("sentence_modifier").item(0) != null) {
						word.getProperties().add(Property.sentence_modifier);
						//System.out.println("sentence_modifier");
					}

					adverbs.add(word);
					//System.out.println(word);
					break;

				case "preposition":
					prepositions.add(word);
					//System.out.println(word);
					break;

				case "modal":
					modals.add(word);
					//System.out.println(word);
					break;

				case "conjunction":
					conjunctions.add(word);
					//System.out.println(word);
					break;

				default:
					//System.out.println("unknow word");
					//System.out.println(word);
					break;
				}

			}
		}

	}
	
public static Lexicon buildDefaultLexicon() throws Exception {
		
	
		if(defaultLexicon!=null) {
			
			Lexicon lexiconToBuild = new Lexicon();
		
			File inputFile = new File(DEFAULT_LEXICON_FILE_PATH);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("word");
	
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
	
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					Word word = new Word();
					
					word.setBase(eElement.getElementsByTagName("base").item(0).getTextContent());
					word.setCategory(eElement.getElementsByTagName("category").item(0).getTextContent());
					if (eElement.getElementsByTagName("id").item(0) != null) {
						word.setId(eElement.getElementsByTagName("id").item(0).getTextContent());
					}
	
					switch (word.getCategory()) {
					case "noun":
						if (eElement.getElementsByTagName("plural").item(0) != null) {
							String plura = (String) eElement.getElementsByTagName("plural").item(0).getTextContent();
							word.setPlural(eElement.getElementsByTagName("plural").item(0).getTextContent());
						}
	
						if (eElement.getElementsByTagName("nonCount").item(0) != null) {
							word.getProperties().add(Property.nonCount);
						}
	
						lexiconToBuild.getNouns().add(word);
						break;
	
					case "pronoun":
						lexiconToBuild.getPronouns().add(word);
						break;
	
					case "determiner":
						lexiconToBuild.getDeterminers().add(word);
						break;
	
					case "verb":
						if (eElement.getElementsByTagName("transitive").item(0) != null) {
							word.getProperties().add(Property.transitive);
						}
	
						if (eElement.getElementsByTagName("intransitive").item(0) != null) {
							word.getProperties().add(Property.intransitive);
						}
	
						if (eElement.getElementsByTagName("ditransitive").item(0) != null) {
							word.getProperties().add(Property.ditransitive);
						}
	
						if (eElement.getElementsByTagName("present3s").item(0) != null) {
							word.setPresent3s(eElement.getElementsByTagName("present3s").item(0).getTextContent());
						}
	
						if (eElement.getElementsByTagName("past").item(0) != null) {
							word.setPast(eElement.getElementsByTagName("past").item(0).getTextContent());
						}
	
						if (eElement.getElementsByTagName("pastParticiple").item(0) != null) {
							word.setPastParticiple(
									eElement.getElementsByTagName("pastParticiple").item(0).getTextContent());
						}
	
						if (eElement.getElementsByTagName("presentParticiple").item(0) != null) {
							word.setPresentParticiple(
									eElement.getElementsByTagName("presentParticiple").item(0).getTextContent());
						}
						lexiconToBuild.getVerbs().add(word);
						break;
	
					case "adjective":
						if (eElement.getElementsByTagName("classifying").item(0) != null) {
							word.getProperties().add(Property.classifying);
						}
	
						if (eElement.getElementsByTagName("predicative").item(0) != null) {
							word.getProperties().add(Property.predicative);
						}
	
						if (eElement.getElementsByTagName("qualitative").item(0) != null) {
							word.getProperties().add(Property.qualitative);
						}
	
						if (eElement.getElementsByTagName("comparative").item(0) != null) {
							word.setComparative(eElement.getElementsByTagName("comparative").item(0).getTextContent());
						}
	
						if (eElement.getElementsByTagName("superlative").item(0) != null) {
							word.setSuperlative(eElement.getElementsByTagName("superlative").item(0).getTextContent());
						}
	
						lexiconToBuild.getAdjectives().add(word);
						break;
	
					case "adverb": 
						if (eElement.getElementsByTagName("intensifier").item(0) != null) {
							word.getProperties().add(Property.intensifier);
						}
	
						if (eElement.getElementsByTagName("verb_modifier").item(0) != null) {
							word.getProperties().add(Property.verb_modifier);
						}
	
						if (eElement.getElementsByTagName("sentence_modifier").item(0) != null) {
							word.getProperties().add(Property.sentence_modifier);
						}
	
						lexiconToBuild.getAdverbs().add(word);
						break;
	
					case "preposition":
						lexiconToBuild.getPrepositions().add(word);
						break;
	
					case "modal":
						lexiconToBuild.getModals().add(word);
						break;
	
					case "conjunction":
						lexiconToBuild.getConjunctions().add(word);
						break;
	
					default:

						break;
					}
	
				}
			}
			defaultLexicon = lexiconToBuild;
			return defaultLexicon;
		}
		
		else return defaultLexicon;

	}

	public ArrayList<Word> getNouns() {
		return nouns;
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
	
	public void printLexicon(ArrayList<Word> list){
		for(Word w : list){
			System.out.println(w);
		}
	}

}
