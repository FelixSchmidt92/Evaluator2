package de.uni_due.s3.evaluator2.parser;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator2.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ErroneousFillInVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator2.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

@RunWith(Parameterized.class)
public class TestExpressionParserParameterized {

	private static HashMap<String, OMOBJ> exerciseVariableMap;
	private static HashMap<Integer, OMOBJ> fillInVariableMap;

	private String omsStr, omi, omstr, omf, oma, pos, var, treeSring;
	private OMS oms;
	private static Object[][] input = {
			{ "[var=E]", OMSymbol.NUMS1_E, "1", "'String'", "1.0", "plus(1,2)", "[pos=1]", "[var=a]",
					"'First' + 'Second'" },
			{ "[var=PI]", OMSymbol.NUMS1_PI, "15", "'Another'", "1.0", "1+2", "[pos=2]", "[var=b]",
					"'plus(4,3,1,23,1.1)'" },
			{ "[var=PI]", OMSymbol.NUMS1_PI, "22", "'abc123'", "2.222", "1+2", "[pos=3]", "[var=c]",
					"plus(plus(plus(plus(plus(1, 'test')))))" }, };

	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] in : input) {
			list.add(in);
		}
		return list;
	}

	public TestExpressionParserParameterized(String omsStr, OMS oms, String omi, String omstr, String omf, String oma, String pos,
			String var, String treeString) {
		this.omsStr = omsStr;
		this.oms = oms;
		this.omi = omi;
		this.omstr = omstr;
		this.omf = omf;
		this.oma = oma;
		this.pos = pos;
		this.var = var;
		this.treeSring = treeString;
	}

	@BeforeClass
	public static void beforeClass() {
		exerciseVariableMap = new HashMap<>();
		fillInVariableMap = new HashMap<>();

		OMOBJ t1 = new OMOBJ();
		OMOBJ t2 = new OMOBJ();
		OMOBJ t3 = new OMOBJ();

		t1.setOMSTR(OMCreator.createOMSTR("Test"));
		t2.setOMI(OMCreator.createOMI(99));
		t3.setOMF(OMCreator.createOMF(99.99));

		exerciseVariableMap.put("a", t1);
		exerciseVariableMap.put("b", t2);
		exerciseVariableMap.put("c", t3);

		fillInVariableMap.put(1, t1);
		fillInVariableMap.put(2, t2);
		fillInVariableMap.put(3, t3);
	}

	@Test
	public void testParseOMS() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(omsStr, exerciseVariableMap, fillInVariableMap);
		OMOBJ expected = new OMOBJ();
		expected.setOMS(oms);
		assertEquals(expected.getOMS(), omobj.getOMS());
	}

	@Test
	public void testParseOMI() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(omi, null, null);
		OMOBJ expected = new OMOBJ();
		expected.setOMI(OMCreator.createOMI(Integer.parseInt(omi)));
		assertEquals(expected, omobj);
	}

	@Test
	public void testParseOMSTR() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(omstr, null, null);
		OMOBJ expected = new OMOBJ();
		expected.setOMSTR(OMCreator.createOMSTR(omstr.substring(1, omstr.length() - 1)));
		assertEquals(expected, omobj);
	}

	@Test
	public void testParseOMF() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(omf, null, null);
		OMOBJ expected = new OMOBJ();
		expected.setOMF(OMCreator.createOMF(Double.parseDouble(omf)));
		assertEquals(expected, omobj);
	}

	@Test
	public void testParseOMA() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ omobj = ExpressionParser.parse(oma, null, null);
		OMOBJ expected = new OMOBJ();
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(1));
		omel.add(OMCreator.createOMI(2));
		expected.setOMA(OMCreator.createOMA(OMCreator.createOMS("arith1", "plus"), omel));
		assertEquals(expected, omobj);
	}

	@Test
	public void testParserExerciseVariable() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ expected = exerciseVariableMap.get(var.substring(5, var.length() - 1));
		OMOBJ actual = ExpressionParser.parse(var, exerciseVariableMap, fillInVariableMap);
		assertEquals(expected, actual);
	}

	@Test
	public void testParserFillInVariable() throws FunctionNotImplementedException, UndefinedFillInVariableException,
			UndefinedExerciseVariableException, ParserException, ErroneousFillInVariableException,
			ErroneousExerciseVariableException, OpenMathException {
		OMOBJ expected = fillInVariableMap.get(Integer.parseInt(pos.substring(5, pos.length() - 1)));
		OMOBJ actual = ExpressionParser.parse(pos, exerciseVariableMap, fillInVariableMap);
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateParseTree() {
		ExpressionParser.createParseTree(treeSring);
	}
}
