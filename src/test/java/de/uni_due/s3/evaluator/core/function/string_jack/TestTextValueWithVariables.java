package de.uni_due.s3.evaluator.core.function.string_jack;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import de.uni_due.s3.evaluator.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator.core.function.Function;
import de.uni_due.s3.evaluator.core.function.TestFunctionAbstract;
import de.uni_due.s3.evaluator.exceptions.function.FunctionException;
import de.uni_due.s3.evaluator.exceptions.representation.NoRepresentationAvailableException;
import de.uni_due.s3.openmath.omutils.OMCreator;

public class TestTextValueWithVariables extends TestFunctionAbstract {

	Function func = new TextValueWithVariables();

	@Test
	public void testTextValueWithVariablesSageSyntax1() throws NoRepresentationAvailableException, FunctionException {
		ArrayList<Object> args = new ArrayList<>();

		args.add(OMCreator.createOMSTR("SomeFunc("));
		args.add(OMCreator.createOMI(55));
		args.add(OMCreator.createOMI(45));
		args.add(OMCreator.createOMSTR(", string, "));
		args.add(OMCreator.createOMF(2.2));
		args.add(OMCreator.createOMSTR(")"));

		assertEquals("SomeFunc(5545, string, 2.2)", func.getPartialSageSyntax(args));
	}

	@Test
	public void testTextValueWithVariablesSageSyntax2() throws NoRepresentationAvailableException, FunctionException {
		ArrayList<Object> args = new ArrayList<>();

		args.add(OMCreator.createOMSTR("SomeFunc("));
		ArrayList<Object> omel = new ArrayList<>();
		omel.add(OMCreator.createOMI(5));
		omel.add(OMCreator.createOMI(3));
		omel.add(OMCreator.createOMI(1));

		args.add(OMCreator.createOMA(OMSymbol.LINALG2_VECTOR, omel));
		args.add(OMCreator.createOMSTR(", someThing"));
		args.add(OMCreator.createOMSTR(")"));
		assertEquals("SomeFunc((5,3,1), someThing)", func.getPartialSageSyntax(args));
	}

	@Test
	public void testTextValueWithVariablesSageSyntax3() throws NoRepresentationAvailableException, FunctionException {
		ArrayList<Object> args = new ArrayList<>();

		args.add(OMCreator.createOMSTR("["));
		args.add(OMCreator.createOMI(55));
		args.add(OMCreator.createOMSTR(", "));
		args.add(OMCreator.createOMF(22.22));
		args.add(OMCreator.createOMSTR("]"));
		assertEquals("[55, 22.22]", func.getPartialSageSyntax(args));
	}
}
