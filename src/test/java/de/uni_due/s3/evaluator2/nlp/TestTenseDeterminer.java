package de.uni_due.s3.evaluator2.nlp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestTenseDeterminer {
	
	
	@Test
	public void testDetermineTenseWithEmptyTags() {
		String[] posTags = new String[0];
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("unknown tense", actualTense);
	}
	@Test
	public void testDetermineTenseWithUnknownTense() {
		String[] posTags = {"VB", "VBN", "VBZ"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("unknown tense", actualTense);
		
	}
	@Test
	public void testDetermineTenseWithUnknownTags() {
		String[] posTags = {"VBA"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("unknown tense", actualTense);
	}
	@Test
	public void testDetermineTenseSimplePresentBase() {
		String[] posTags = {"VB"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present simple", actualTense);
	}
	@Test
	public void testDetermineTenseSimplePresentNon3rdPerson() {
		String[] posTags = {"VBP"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present simple", actualTense);
	}
	public void testDetermineTenseSimplePresent3rdPerson() {
		String[] posTags = {"VBZ"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present simple", actualTense);
	}
	@Test
	public void testDetermineTensePresentProgressive() {
		String[] posTags = {"VB", "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present progressive", actualTense);
	}
	@Test
	public void testDetermineTensePresentPerfect() {
		String[] posTags = {"VB", "VBN"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present perfect", actualTense);
	}
	@Test
	public void testDetermineTensePresentPerfectProgressive() {
		String[] posTags = {"VB", "VBN" , "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("present perfect progressive", actualTense);
	}
	
	@Test
	public void testDetermineTenseSimplePast() {
		String[] posTags = {"VBD"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("past simple", actualTense);
	}
	@Test
	public void testDetermineTensePastProgressive() {
		String[] posTags = {"VBD", "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("past progressive", actualTense);
	}
	@Test
	public void testDetermineTensePastPerfect() {
		String[] posTags = {"VBD", "VBN"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("past perfect", actualTense);
	}
	@Test
	public void testDetermineTensePastPerfectProgressive() {
		String[] posTags = {"VBD", "VBN" , "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("past perfect progressive", actualTense);
	}
	
	@Test
	public void testDetermineTenseSimpleFuture() {
		String[] posTags = {"MD", "VB"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("future simple", actualTense);
	}
	@Test
	public void testDetermineTenseFutureProgressive() {
		String[] posTags = {"MD", "VB", "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("future progressive", actualTense);
	}
	@Test
	public void testDetermineTenseFuturePerfect() {
		String[] posTags = {"MD", "VB", "VBN"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("future perfect", actualTense);
	}
	@Test
	public void testDetermineTenseFuturePerfectProgressive() {
		String[] posTags = {"MD", "VB", "VBN", "VBG"};
		String actualTense = TenseDeterminer.determineTense(posTags);
		assertEquals("future perfect progressive", actualTense);
	}

}
