package de.uni_due.s3.evaluator2.core.visitor;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.visitor.OMToPaletteVisitor;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class TestOMToPaletteVisitor {
	
	@Test
	public void testOMToPaletteVisitorWithOMA() throws FunctionNotImplementedException {
		Object result = OMToPaletteVisitor.visit(OMCreator.createOMA(OMSymbol.ARITH1_PLUS, new ArrayList<Object>()));
		assertEquals("<OMS name=\"plus\" cd=\"arith1\"/>",result.toString());
	}
	
	@Test
	public void testOMToPaletteVisitorWithOMV() throws FunctionNotImplementedException {
		Object omv = OMToPaletteVisitor.visit(OMCreator.createOMV("y"));
		assertEquals(omv,OMCreator.createOMV("y"));
	}
	
	@Test
	public void testOMToPaletteVisitorWithPlaceholder() throws FunctionNotImplementedException {
		Object omv = OMToPaletteVisitor.visit(OMCreator.createOMV("x"));
		assertEquals(omv,OMSymbol.EDITOR1_INPUT_BOX);
	}
}
