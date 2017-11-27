package de.uni_due.s3.evaluator2.core.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.openmath.omutils.OpenMathException;

/**
 * This Test is here to check if this abstract class has at least these Methods
 * which has to be overwritten
 * 
 * @author dlux
 *
 */
public class TestConstructorFunction {

	private ConstructorFunction cfunc = new ConstructorFunction() {

		@Override
		protected int minArgs() {
			return 12;
		}

		@Override
		protected int maxArgs() {
			return -1;
		}

		@Override
		protected Object execute(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return new Double(1.3);
		}

		@Override
		public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return "sage";
		}

		@Override
		public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return "r";
		}

		@Override
		public List<Object> getPartialListSyntax(List<Object> omel) throws EvaluatorException, OpenMathException {
			return new ArrayList<>();
		}

		@Override
		public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
			return "latex";
		}
	};

	@Test
	public void testMinArgs() {
		assertEquals(cfunc.minArgs(), 12);
	}
	
	@Test
	public void testMaxArgs() {
		assertEquals(cfunc.maxArgs(),-1);
	}
	
	@Test
	public void testExecute() throws EvaluatorException, OpenMathException {
		assertEquals(cfunc.execute(null), new Double(1.3));
	}
	
	@Test
	public void testGetPartialSageSyntax() throws EvaluatorException, OpenMathException {
		assertEquals(cfunc.getPartialSageSyntax(null), "sage");
	}
	
	@Test
	public void testGetPartialRSyntax() throws EvaluatorException, OpenMathException {
		assertEquals(cfunc.getPartialRSyntax(null), "r");
	}
	
	@Test
	public void testGetPartialListSyntax() throws EvaluatorException, OpenMathException {
		assertEquals(cfunc.getPartialListSyntax(null), new ArrayList<>());
	}
	
	@Test
	public void testGetPartialLatexSyntax() throws EvaluatorException, OpenMathException {
		assertEquals(cfunc.getPartialLatexSyntax(null), "latex");
	}

}
