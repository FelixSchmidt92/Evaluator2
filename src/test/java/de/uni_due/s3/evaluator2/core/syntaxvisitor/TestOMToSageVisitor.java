package de.uni_due.s3.evaluator2.core.syntaxvisitor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMF;
import de.uni_due.s3.openmath.jaxb.OMI;
import de.uni_due.s3.openmath.jaxb.OMOBJ;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMSTR;
import de.uni_due.s3.openmath.jaxb.OMV;
import de.uni_due.s3.openmath.omutils.OMCreator;

@RunWith(Parameterized.class)
public class TestOMToSageVisitor {

	private OMOBJ omobj;
	private OMA oma;
	private OMI omi;
	private OMF omf;
	private OMV omv;
	private OMS oms;
	private OMSTR omstr;

	private static OMToSageVisitor visitor = new OMToSageVisitor();

	static Object[][] parameters = { { "12", "2.12", "x", "String", OMSymbol.LOGIC1_TRUE, "True" }, // [0]
			{ "42", "1.234", "y", "Another", OMSymbol.LOGIC1_FALSE, "False" },
			{ "12345", "0.123", "z", "This", OMSymbol.NUMS1_PI, "pi" },
			{ "0", "0.0012", "abc", "Test", OMSymbol.NUMS1_E, "e" },
			{ "-1", "12345.1", "def", "", OMSymbol.NUMS1_NAN, "NaN" },
			{ "9", "1.1", "ghi", "a", OMSymbol.NUMS1_I, "I" }, // [5]
			{ "100", "5.5", "n", "äöü", OMSymbol.NUMS1_INFINITY, "Infinity" },

	};

	private String omiString, omfString, omvString, omstrString, omsTerminalString;
	private OMS omsTerminal;

	@Parameterized.Parameters
	public static Collection<Object[]> test() {
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		for (Object[] a : parameters) {
			list.add(a);
		}
		return list;
	}

	public TestOMToSageVisitor(String omiString, String omfString, String omvString, String omstrString,
			OMS omsTerminal, String omsTerminalString) {
		this.omiString = omiString;
		this.omfString = omfString;
		this.omvString = omvString;
		this.omstrString = omstrString;
		this.omsTerminal = omsTerminal;
		this.omsTerminalString = omsTerminalString;
	}

	@Before
	public void init() {
		ArrayList<Object> omel = new ArrayList<>();
		oms = OMSymbol.ARITH1_PLUS;
		omi = OMCreator.createOMI(Integer.parseInt(omiString));
		omf = OMCreator.createOMF(Double.parseDouble(omfString));
		omel.add(omi);
		omel.add(omf);

		omv = OMCreator.createOMV(omvString);
		omstr = OMCreator.createOMSTR(omstrString);

		oma = OMCreator.createOMA(oms, omel);

		omobj = new OMOBJ();
		omobj.setOMA(oma);
	}

	@Test
	public void testVisitTerminals() throws NoRepresentationAvailableException {
		assertEquals(omiString, visitor.visit(omi));
		assertEquals(omfString, visitor.visit(omf));
		assertEquals(omvString, visitor.visit(omv));
		assertEquals("'" + omstrString + "'", visitor.visit(omstr));
		assertEquals(omsTerminalString, visitor.visit(omsTerminal));
	}

	/* The Class Plus has to be implemented in Functions */
	@Test
	public void testVisitApplication() throws EvaluatorException {
		assertEquals("(" + omiString + " + " + omfString + ")", visitor.visit(oma));
		assertEquals("(" + omiString + " + " + omfString + ")", visitor.visit(omobj));
	}
}
