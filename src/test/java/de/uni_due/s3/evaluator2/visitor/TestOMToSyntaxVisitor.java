package de.uni_due.s3.evaluator2.visitor;

import java.util.List;

import org.junit.Test;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.Function;
import de.uni_due.s3.evaluator2.visitor.OMToSyntaxVisitor;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class TestOMToSyntaxVisitor {

	private class ImplVisitor extends OMToSyntaxVisitor<String> {
		
		public ImplVisitor() throws EvaluatorException, OpenMathException {
			super();
		}
		

		@Override
		protected String visit(OMF omf) {
			return "omf";
		}

		@Override
		protected String visit(OMI omi) {
			return "omi";
		}

		@Override
		protected String visit(OMSTR omstr) {
			return "omstr";
		}

		@Override
		protected String visit(OMV omv) {
			return "omv";
		}

		@Override
		protected String getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel) {
			return "function";
		}

	}
	
	@Test(expected = EvaluatorException.class)
	public void testVisitWrongImplementation() throws EvaluatorException, OpenMathException {
		new ImplVisitor();
	}
	
	private class ImplVisitor2 extends OMToSyntaxVisitor<String> {
		
		public ImplVisitor2() throws EvaluatorException, OpenMathException {
			super();
		}
		
		/*only for Test in JUnit, it is unused*/
		@SuppressWarnings("unused")
		public ImplVisitor2 getInstance() throws EvaluatorException, OpenMathException {
			return new ImplVisitor2();
		}

		@Override
		protected String visit(OMF omf) {
			return "omf";
		}

		@Override
		protected String visit(OMI omi) {
			return "omi";
		}

		@Override
		protected String visit(OMSTR omstr) {
			return "omstr";
		}

		@Override
		protected String visit(OMV omv) {
			return "omv";
		}

		@Override
		protected String getSyntaxRepresentationForFunction(Function function, OMS oms, List<Object> omel) {
			return "function";
		}

	}
	
	@Test(expected = EvaluatorException.class)
	public void testVisitWrongImplementation2() throws EvaluatorException, OpenMathException {
		new ImplVisitor2();
	}
}
