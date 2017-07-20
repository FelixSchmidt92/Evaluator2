package de.uni_due.s3.evaluator.core.function.functions.linalg_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.OMExecutor;
import de.uni_due.s3.evaluator.core.function.functions.TestFunction;
import de.uni_due.s3.evaluator.core.functionData.OMSymbol;
import de.uni_due.s3.evaluator.exceptions.cas.CasEvaluationException;
import de.uni_due.s3.evaluator.exceptions.cas.CasNotAvailableException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionInvalidArgumentException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator.exceptions.parser.ParserException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.parser.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.evaluator.parser.ExpressionParser;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestRandomMatrixEigenvalue extends TestFunction{
	
	Function func = new RandomMatrixEigenvalue();
	
	static OMOBJ omobj = null;
	
	@BeforeClass
	public static void init() throws FunctionNotImplementedException, UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException {
		omobj = ExpressionParser.parse("matrix(matrixrow(1,0,0), matrixrow(0,1,0), matrixrow(0,0,1))", null, null);
	}
	
	@Test
	public void testRandomMatrixEigenValueWithMatrixRow() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("QQ"));
		args.add(OMCreator.createOMI(3));
		
		ArrayList<Object> row = new ArrayList<>();
		row.add(OMCreator.createOMI(1));
		row.add(OMCreator.createOMI(1));
		row.add(OMCreator.createOMI(1));
		
		OMA mrow = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, row);
		OMA mrow2 = OMCreator.createOMA(OMSymbol.LINALG2_MATRIXROW, row);
		args.add(mrow);
		args.add(mrow2);
		
		assertEquals(omobj.getOMA(), func.evaluate(args));
	}
	
	
	@Test
	public void testRandomMatrixEigenValueWithVector() throws FunctionInvalidArgumentException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		ArrayList<Object> args = new ArrayList<>();
		args.add(OMCreator.createOMSTR("QQ"));
		args.add(OMCreator.createOMI(3));
		
		ArrayList<Object> row = new ArrayList<>();
		row.add(OMCreator.createOMI(1));
		row.add(OMCreator.createOMI(1));
		row.add(OMCreator.createOMI(1));
		
		OMA mrow = OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, row);
		OMA mrow2 = OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, row);
		args.add(mrow);
		args.add(mrow2);

		assertEquals(omobj.getOMA(), func.evaluate(args));
	}
	
	@Test
	public void testRandomMatrixEigenValueCaseIntegration() throws UndefinedFillInVariableException, UndefinedExerciseVariableException, ParserException, CasEvaluationException, FunctionException, CasNotAvailableException, NoRepresentationAvailableException, OpenMathException {
		//vector written as [a,b,...,n] are not allowed!!
		OMOBJ obj = ExpressionParser.parse("randomMatrixEigenvalue('QQ', '3', vector(1,1,1), vector(1,1,1))", null, null);
		obj = OMExecutor.execute(obj);
		
		assertEquals(omobj, obj);
	}

}
