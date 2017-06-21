package de.uni_due.s3.sage;

import org.junit.Test;

import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMS;

public class TestOMOBJToSage {

	
	@Test
	public void test(){
		OMOBJToSageLexicon s = new OMOBJToSageLexicon();
		
		OMI omi = new OMI();
		
		omi.setValue("5555");
		
		
		
		System.out.println(s.convert(omi));
		
		
		OMF omf = new OMF();
		
		omf.setDec(2.132312);
		
		
		System.out.println(s.convert(omf));
		
		
		
		OMS oms = new OMS();
		
		oms.setCd("arith1");
		oms.setName("unary_plus");
		
		
		
		OMA oma = new OMA();
		
		oma.getOmel().add(oms);
		oma.getOmel().add(omf);
		
		System.out.println(s.convert(oma));
		
	}
}
