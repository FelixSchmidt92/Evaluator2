package de.uni_due.s3.evaluator.core.syntaxvisitor;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMATP;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMR;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;
import de.uni_due.s3.openmath.omutils.OpenMathException;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.syntaxvisitor.OMToCasVisitor;
import de.uni_due.s3.evaluator.exceptions.cas.CasException;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;

public class TestOMToCasVisitor {

	private class ImplVisitor extends OMToCasVisitor {

		@Override
		protected String visit(OMF omf) {
			// TODO Auto-generated method stub
			return "omf";
		}

		@Override
		protected String visit(OMI omi) {
			// TODO Auto-generated method stub
			return "omi";
		}

		@Override
		protected String visit(OMS oms) {
			// TODO Auto-generated method stub
			return "oms";
		}

		@Override
		protected String visit(OMSTR omstr) {
			// TODO Auto-generated method stub
			return "omstr";
		}

		@Override
		protected String visit(OMV omv) {
			// TODO Auto-generated method stub
			return "omv";
		}

		@Override
		protected String getCASRepresentationForFunction(Function function, List<Object> omel) {
			// TODO Auto-generated method stub
			return "function";
		}

	}

	private ImplVisitor vis;

	@Before
	public void init() {
		vis = new ImplVisitor();
	}

	@Test
	public void testVisitObject()
			throws NoRepresentationAvailableException, OpenMathException, CasException, FunctionException {
		OMI omi = OMCreator.createOMI(1);
		OMF omf = OMCreator.createOMF(1.0);
		OMV omv = OMCreator.createOMV("test");
		OMSTR omstr = OMCreator.createOMSTR("str");
		OMS oms = OMCreator.createOMS("arith1", "plus"); // OMSEvaluatorSyntaxDictionary.getInstance().getOMS("plus");
		List<Object> args = new ArrayList<Object>();
		args.add(omi);
		args.add(omi);
		OMA oma = OMCreator.createOMA(oms, args);
		OMOBJ omobj = OMCreator.createOMOBJ(omi);

		Assert.assertEquals("omi", vis.visit(omobj));
		Assert.assertEquals("omi", vis.visit(omi));
		Assert.assertEquals("oms", vis.visit(oms));
		Assert.assertEquals("omf", vis.visit(omf));
		Assert.assertEquals("omv", vis.visit(omv));
		Assert.assertEquals("omstr", vis.visit(omstr));
		Assert.assertEquals("function", vis.visit(oma));
	}

	@Test
	public void testVisitOMOBJ()
			throws NoRepresentationAvailableException, OpenMathException, CasException, FunctionException {
		OMI omi = OMCreator.createOMI(1);
		OMF omf = OMCreator.createOMF(1.0);
		OMV omv = OMCreator.createOMV("test");
		OMSTR omstr = OMCreator.createOMSTR("str");
		OMS oms = OMCreator.createOMS("arith1", "plus"); // OMSEvaluatorSyntaxDictionary.getInstance().getOMS("plus");
		List<Object> args = new ArrayList<Object>();
		args.add(omi);
		args.add(omi);
		OMA oma = OMCreator.createOMA(oms, args);
		OMOBJ omobj = OMCreator.createOMOBJ(omi);

		Assert.assertEquals("omi", vis.visit(omobj));
		Assert.assertEquals("omi", vis.visit(OMCreator.createOMOBJ(omi)));
		Assert.assertEquals("omf", vis.visit(OMCreator.createOMOBJ(omf)));
		Assert.assertEquals("oms", vis.visit(OMCreator.createOMOBJ(oms)));
		Assert.assertEquals("omv", vis.visit(OMCreator.createOMOBJ(omv)));
		Assert.assertEquals("omstr", vis.visit(OMCreator.createOMOBJ(omstr)));
		Assert.assertEquals("function", vis.visit(oma));
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testVisitObjectWithWrongObject()
			throws NoRepresentationAvailableException, CasException, FunctionException {
		vis.visit(new Integer(10));
		vis.visit(new OMATP());
	}

	@Test(expected = NoRepresentationAvailableException.class)
	public void testVisitOMOBJWithWrongChild()
			throws NoRepresentationAvailableException, CasException, FunctionException {
		OMR omr = new OMR();
		OMOBJ omobj = new OMOBJ();
		omobj.setOMR(omr);
		vis.visit(omobj);
		vis.visit(new OMOBJ());
	}
}
