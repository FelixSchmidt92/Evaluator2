package de.uni_due.s3.evaluator.parser;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.exceptions.parserruntime.ParserRuntimeException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorLexer;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.GrammarGenerator;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class TestExpressionToOpenMathVisitor{
	
	private static HashMap<String, OMOBJ> exerciseVariableMap;
	private static HashMap<Integer, OMOBJ> fillInVariableMap;
	private static OMOBJ t1 = null;
	private static OMOBJ t2 = null;
	
	private static GrammarGenerator gen;
	
	private static ExpressionToOpenMathVisitor visitor;
	
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
			String val = gen.genRandomIntegerValue(50, null);
			
			OMI omi = (OMI) visitor.visit(parse(String.valueOf(val)));
			
			assertEquals(String.valueOf(val), omi.getValue());
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
	public void testVisitTextValue(){
		for (int i = 0; i < 10000; i++){
			String value = gen.genRandomUTF8StringValue(50, null);
			OMSTR omstr = (OMSTR) visitor.visit(parse(value));
			
			value = value.substring(1, value.length()-1);
			assertEquals(value, omstr.getContent());
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

	
}
