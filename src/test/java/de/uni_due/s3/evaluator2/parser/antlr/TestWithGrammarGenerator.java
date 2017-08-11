package de.uni_due.s3.evaluator2.parser.antlr;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.BitSet;
import java.util.Random;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.junit.Test;

import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator2.parser.antlr.EvaluatorParser;

/**
 * This Class tests the EvaluatorParser and EvaluatorLexar, if they support the generated 
 * Grammar from GrammarEvaluator
 * 
 * @author dlux
 */
public class TestWithGrammarGenerator {
	GrammarGenerator gen = new GrammarGenerator();
	
	@Test
	public void testGrammarGeneratorAtStringTerminalSpecialCases(){		
		createParser("''").expression();
		createParser("'\n'").expression();
		createParser("'\t'").expression();
		createParser("'\b'").expression();
		createParser("'\f'").expression();
		createParser("'\r'").expression();
	}
	
	@Test
	public void testGrammarGeneratorAtStringTerminalRandom(){		
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomUTF8StringValue(new Random().nextInt(50), null)).expression();
		}
	}
	
	@Test
	public void testGrammarGeneratorAtNumberTerminalSpecialCases(){
		createParser("0000000").expression();
		createParser("00000.00000").expression();
	}
	
	@Test
	public void testGrammarGeneratorAtNumberTerminalRandom(){		
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomIntegerValue(1 + new Random().nextInt(49), null)).expression();  //By 0 Empty String: ""
		}
		
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomPointNumberValue(1 + new Random().nextInt(49), 
													   1 + new Random().nextInt(49), null)).expression();  //By 0 Empty String: ""
		}
	}
	
	@Test  
	public void testGrammarGeneratorAtPositionVariableTerminalRandom(){
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomPositionVariable(1 + new Random().nextInt(49), null)).expression();  //By 0 Empty String: ""
		}
	}
	
	@Test
	public void testGrammarGeneratorAtExerciseVariableTerminalRandom(){
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomExerciseVariable(1 + new Random().nextInt(49), null)).expression();  //By 0 Empty String: ""
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtParanthesesRecursivelyWithTerminalsRandom(){ 
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomTerminalWithParantheses(new Random().nextInt(50), 50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtParantrhesesRecursivelyWithUnaryTerminalsRandom(){ 
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomUnaryTerminalWithParentheses(new Random().nextInt(50), 50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtBinaryOperationWithRandomTerminals(){ 
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomBinaryOperationWithDepthOne(50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtFunctionWithRandomTerminals(){ 
		for (int i =0; i < 1000; i++){
			createParser(gen.genRandomFunctionWithDepthOne(1 + new Random().nextInt(49), 50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtBinaryOperatorChainWithTerminalsRandom(){ 
		for (int i =0; i < 1000; i++){ 
			createParser(gen.genRandomBinaryOperatorChainWithTerminals(new Random().nextInt(50), 50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String. Only 100 Times, its Time-Comsuming
	public void testGrammarGeneratorAtBinaryOperatorChainWithFunctionsRandom(){ 
		for (int i =0; i < 100; i++){ 
			createParser(gen.genRandomBinaryOperatorChainWithFunctionArguments(new Random().nextInt(50), 50, null)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String. Only 100 Times, its Time-Comsuming
	public void testGrammarGeneratorAtFunctionInFunctionRecursivelyRandom(){ 
		for (int i =0; i < 100; i++){ 
			int t = 1 + new Random().nextInt(5);
			createParser(gen.genRandomFunctionRecursion(t, 1 + new Random().nextInt(5), t)).expression();
		}
	}
	
	@Test  //Test with FillIn, Exercise, Number and String
	public void testGrammarGeneratorAtFunctionInFunctionWithBinOppsAsTerminalRecursivelyRandom(){ 
		for (int i =0; i < 1000; i++){ 
			int t = 1 + new Random().nextInt(5);
			createParser(gen.genRandomFunctionRecursionWithBinaryTerms(t, 1 + new Random().nextInt(5), t)).expression();
		}
	}
	
	private EvaluatorParser createParser(String textArgument){
		Reader input = new StringReader(textArgument);
		CharStream cstream = null;
		try {
			cstream = CharStreams.fromReader(input);
		} catch (IOException e) {
			throw new RuntimeException("IOExceptionError at createParser", e);
		}
	    EvaluatorLexer lexer = new EvaluatorLexer(cstream);
	    lexer.removeErrorListeners();
	    lexer.addErrorListener(new TestExceptionListener());
	    CommonTokenStream tokens = new CommonTokenStream(lexer);
	    EvaluatorParser parser = new EvaluatorParser(tokens);
	    parser.setErrorHandler(new BailErrorStrategy());
		return parser;
	}
	
	
	
	/**
	 * 
	 * @author dlux
	 */
	private class TestExceptionListener implements ANTLRErrorListener{

		@Override
		public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5,
				ATNConfigSet arg6) {
		}

		@Override
		public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4,
				ATNConfigSet arg5) {
		}

		@Override
		public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {
		}

		@Override
		public void syntaxError(Recognizer<?, ?> arg0, Object arg1, int arg2, int arg3, String arg4,
				RecognitionException arg5) {
			throw new RuntimeException(arg5);
		}
	}
}
