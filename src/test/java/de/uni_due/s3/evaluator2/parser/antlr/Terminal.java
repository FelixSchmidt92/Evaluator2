package de.uni_due.s3.evaluator2.parser.antlr;

import java.util.Random;

/**
 * ENUM-Interface for all random TerminalGenerators
 * 
 * This interface can be used to select a Random Terminal with a specific length
 * with the help of make which returns a 'specific' String
 * 
 * @author dlux
 */
public enum Terminal{
	 EXERCISE_VARIABLE{
		public String make(int length){
			return new GrammarGenerator().genRandomExerciseVariable(random(length), null);
		}
	 },
	 POSITION_VARIABLE{
		public String make(int length){
			return new GrammarGenerator().genRandomPositionVariable(random(length), null);
		}
	 },
	 INTEGER_VALUE{
		public String make(int length){
			return new GrammarGenerator().genRandomIntegerValue(random(length), null);
		}
	 },
	 POINTNUMBER_VALUE{
		public String make(int length){
			return new GrammarGenerator().genRandomPointNumberValue(random(length), random(length),  null);
		}
	 },
	 STRING_UTF8_VALUE{
		public String make(int length){
			return new GrammarGenerator().genRandomUTF8StringValue(random(length), null);
		}
	 };
		
	public abstract String make(int length);
	
	private static Integer random(int length){
		if(length == 0){
			return 0;
		}
		return  1 + new Random().nextInt(length-1);
	}
}

