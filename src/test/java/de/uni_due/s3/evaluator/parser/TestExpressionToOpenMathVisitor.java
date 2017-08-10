package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.GrammarGenerator;
import de.uni_due.s3.evaluator.parser.antlr.RetryRule;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMConverter;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestExpressionToOpenMathVisitor{
	
	private static HashMap<String, OMOBJ> exerciseVariableMap;
	private static HashMap<Integer, OMOBJ> fillInVariableMap;
	private static OMOBJ t1 = null;
	private static OMOBJ t2 = null;
	
	private static GrammarGenerator gen;
	
	private static ExpressionToOpenMathVisitor visitor;
	
	//Retrying here 3 Times if a Test fails, caused by an invalid UTF-8 String
	@Rule public RetryRule retry = new RetryRule(3);
	
	private ParseTree parse(String text){
		Reader input = new StringReader(text);
		CharStream cstream = null;
		try {
			cstream = CharStreams.fromReader(input);
		} catch (IOException e) {
			throw new ParserRuntimeException(text + " was not parsable");
		}
		
		EvaluatorLexer evaluatorLexer = new EvaluatorLexer(cstream);
		
		CommonTokenStream commonTokenStream = new CommonTokenStream(evaluatorLexer);
		EvaluatorParser evaluatorParser = new EvaluatorParser(commonTokenStream);
		
		return evaluatorParser.expression();
	}
	
	@BeforeClass
	public static void beforeClass(){
		gen = new GrammarGenerator();
		
		OMI omi = OMCreator.createOMI(1);
		OMSTR omstr = OMCreator.createOMSTR("Test");

		t1 = new OMOBJ();
		t2 = new OMOBJ();
		
		t1.setOMI(omi);
		t2.setOMSTR(omstr);
		
		exerciseVariableMap = new HashMap<>();
		fillInVariableMap = new HashMap<>();
		
		exerciseVariableMap.put("abc", t1);
		exerciseVariableMap.put("def", t2);
		fillInVariableMap.put(1, t1);
		fillInVariableMap.put(2, t2);
		
		visitor = new ExpressionToOpenMathVisitor(exerciseVariableMap, fillInVariableMap);
	}
	
	
	@Test
	public void testVisitIntegerValue(){
		for (int i = 0; i < 1000; i++){
			String val = gen.genRandomIntegerValue(9, null);
			int t = Integer.parseInt(val);
			
			OMI omi = (OMI) visitor.visit(parse(val));
			
			assertEquals(String.valueOf(t), omi.getValue());
		}
	}
	
	@Test
	public void testVisitFloatValue(){
		for (int i = 0; i < 10000; i++){
			double val = new Random().nextDouble();
			String value  =(new DecimalFormat("#.###############").format(val)); //Format to remove E Notation
			value = value.replaceAll(",", ".");
			
			OMF omf = (OMF) visitor.visit(parse(value));
			String result  = (new DecimalFormat("#.###############").format(omf.getDec()));  //Format to remove E Notation
			result = result.replaceAll(",", ".");
			
			assertEquals(value, result);
		}
	}
	
	@Test
	public void testVisitCircumflex(){
		OMA result = (OMA) visitor.visit(parse("2^5"));
		List<Object> plus = new ArrayList<>();
		plus.add(OMCreator.createOMI(2));
		plus.add(OMCreator.createOMI(5));
		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_POWER, plus), result);
	}
	
	@Test
	public void testVisitVariable(){
		OMA result = (OMA) visitor.visit(parse("3 + a"));
		List<Object> plus = new ArrayList<>();
		plus.add(OMCreator.createOMI(3));
		plus.add(OMCreator.createOMV("a"));
		assertEquals(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, plus), result);
	}
	
	
	@Test
	public void testVisitTextONLYValue(){
		for (int i = 0; i < 10000; i++){
			String value = gen.genRandomUTF8StringValue(50, null);
			OMSTR omstr = (OMSTR) visitor.visit(parse(value));
			
			value = value.substring(1, value.length()-1);
			assertEquals(value, omstr.getContent());
		}
	}
	
	/**
	 * @throws OpenMathException if Maps contains something wrong, which is defined here
	 */
	@Test
	public void testVisitTextWithPosAndVarValue() throws OpenMathException{
		OMSTR pre = OMCreator.createOMSTR("First ");
		OMSTR mid = OMCreator.createOMSTR(" Second ");
		OMSTR pst = OMCreator.createOMSTR(" Third");
		
		
		Object obj = visitor.visit(parse("'" + pre.getContent() + "[var=abc]" 
										+ mid.getContent() + "[pos=1]" 
										+ pst.getContent() + "'"));
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(pre);
		omel.add(OMConverter.toElement(exerciseVariableMap.get("abc")));
		omel.add(mid);
		omel.add(OMConverter.toElement(fillInVariableMap.get(1)));
		omel.add(pst);
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("stringJACK", "textValueWithVariables"), omel);
		
		assertEquals(oma, obj);
	}
	
	@Test(expected = FunctionNotImplementedRuntimeException.class)
	public void testVisitTextWithExpressionValue(){
		for (int i = 0; i < 10000; i++){
			String expression = gen.genRandomFunctionRecursionWithBinaryTerms(4, new Random().nextInt(5), 4);
			visitor.visit(parse(expression));
		}
	}
	
	
	@Test
	public void testVisitUnary(){
		for (int i = 0; i < 10000; i++){
			String[] unary = {"+","-","!"};
			String getUnary = unary[new Random().nextInt(unary.length)];
			String val = gen.genRandomTerminalWithOutEXandFILLVariable(5, null);
			Object obj = visitor.visit(parse(getUnary + val));
			
			switch(getUnary){
			case "+":
				assertTrue(!(obj instanceof OMS)); //because it should just visit the next one
				break;
			case "-":
				testUnaryMinusAndLogicNot((OMA) obj, OMCreator.createOMS("arith1", "unary_minus"));break;
			case "!":
				testUnaryMinusAndLogicNot((OMA) obj, OMCreator.createOMS("logic1", "not"));break;
			}
		}
	}
	/**@testVisitUnary*/
	private void testUnaryMinusAndLogicNot(OMA oma, OMS oms){
		assertEquals(oms.getCd(),((OMS)oma.getOmel().get(0)).getCd());
		assertEquals(oms.getName(),((OMS)oma.getOmel().get(0)).getName());
	}
	
	
	@Test
	public void testVisitBinary(){
		for (int i = 0; i < 10000; i++){
			String[] binary = {"+", "-", "*", "/", "%",
					"<", "<=", ">", ">=", 
					"=", "==", "!=", "&&", "||"};
			String getBinary = binary[new Random().nextInt(binary.length)];
			String val1 = gen.genRandomTerminalWithOutEXandFILLVariable(5, null);
			String val2 = gen.genRandomTerminalWithOutEXandFILLVariable(5, null);
			OMA oma = (OMA) visitor.visit(parse(val1 + getBinary + val2));
			
			OMS oms = null;
			switch (getBinary) {
			case "+":
				oms = OMCreator.createOMS("arith1", "plus");break;
			case "-":
				oms = OMCreator.createOMS("arith1", "minus");break;
			case "*":
				oms = OMCreator.createOMS("arith1", "times");break;
			case "/":
				oms = OMCreator.createOMS("arith1", "divide");break;
			case "%":
				oms = OMCreator.createOMS("integer1", "remainder");break;
			case "<":
				oms = OMCreator.createOMS("relation1", "lt");break;
			case "<=":
				oms = OMCreator.createOMS("relation1", "leq");break;
			case ">":
				oms = OMCreator.createOMS("relation1", "gt");break;
			case ">=":
				oms = OMCreator.createOMS("relation1", "geq");break;
			case "=":
				oms = OMCreator.createOMS("relation1", "eq");break;
			case "==":
				oms = OMCreator.createOMS("relation1", "eq");break;
			case "!=":
				oms = OMCreator.createOMS("relation1", "neq");break;
			case "&&":
				oms = OMCreator.createOMS("logic1", "and");break;
			case "||":
				oms = OMCreator.createOMS("logic1", "or");break;
			}

			assertEquals(oms.getName(),((OMS)oma.getOmel().get(0)).getName());
			assertEquals(oms.getCd(),((OMS)oma.getOmel().get(0)).getCd());
		}
	}
	
	@Test
	public void testVisitExerciseVarName(){
		OMI t1 = (OMI)visitor.visit(parse("[var=abc]"));
		OMSTR t2 = (OMSTR)visitor.visit(parse("[var=def]"));
		
		assertEquals(TestExpressionToOpenMathVisitor.t1.getOMI().getValue(), t1.getValue());
		assertEquals(TestExpressionToOpenMathVisitor.t2.getOMSTR().getContent(), t2.getContent());
	}
	
	@Test
	public void testVisitFillInVarName(){
		OMI t1 = (OMI)visitor.visit(parse("[pos=1]"));
		OMSTR t2 = (OMSTR)visitor.visit(parse("[pos=2]"));
		
		assertEquals(TestExpressionToOpenMathVisitor.t1.getOMI().getValue(), t1.getValue());
		assertEquals(TestExpressionToOpenMathVisitor.t2.getOMSTR().getContent(), t2.getContent());
	}
	
	@Test(expected=FunctionNotImplementedRuntimeException.class)
	public void testVisitNestedFunctionException(){
		for(int i = 0; i < 10000; i++){
			visitor.visit(parse(gen.genRandomFunctionRecursion(4, new Random().nextInt(5), 4)));
		}
	}
	
	@Test
	public void testVisitNestedFunction(){
		Object obj = visitor.visit(parse("plus(1, 2)"));
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("arith1", "plus"), omel);
		
		assertEquals(oma, obj);
	}
	

	@Test
	public void testVisitSet(){
		//test if set accepts many different Set Combinations
		for (int i = 0; i < 10000; i++){

			String builder = "{   ";
			for (int j = 0; j < new Random().nextInt(50); j++){
				String t = gen.genRandomTerminalWithOutEXandFILLVariable(5, null);
				builder += "   " +  t + "   ;";
			}
			builder = builder.substring(0, builder.length()-1) + "   }";
			visitor.visit(parse(builder));
		}
		
		//test one specific set
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMSTR("abc"));
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMF(1.1));
		OMA oma = OMCreator.createOMA(OMCreator.createOMS("set1", "set"), omel);
		
		Object obj = visitor.visit(parse("{'abc';1;1.1}"));
		assertEquals(oma, obj);
	}

	
	@Test
	public void visitParenthesis(){
		String term = gen.genRandomTerminalWithOutEXandFILLVariable(5, null);
		for(int j = 0; j < new Random().nextInt(10); j++){
			term = "( " + term + " )";
		}
		Object obj = visitor.visit(parse(term));
		
		if (obj instanceof OMI || obj instanceof OMF || obj instanceof OMSTR){
			
		}else{
			throw new RuntimeException("Error parsing with Parenthesis. Not Found: (OMI|OMF|OMSTR)");
		}
	}
	
}
