package de.uni_due.s3.evaluator.function.arith1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.uni_due.s3.openmath.OMI;

public class TestPlus {

	Plus plus = new Plus();
	
	@Test
	public void testPlus(){
	List<Object> t = new ArrayList<>();
	OMI omi = new OMI();
	omi.setValue("3");
	
	OMI omi2 = new OMI();
	omi2.setValue("4");
	
	t.add(omi);
	t.add(omi2);
	
	OMI omires = new OMI();
	omires.setValue("7");
	assertEquals(((OMI)plus.execute(t)).getValue(), "7");
	}
}
