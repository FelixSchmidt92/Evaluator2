package de.uni_due.s3.evaluator2.core.visitor;

import java.util.List;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.dictionaries.OMSymbol;
import de.uni_due.s3.evaluator2.core.function.Function;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;
import de.uni_due.s3.openmath.jaxb.OMV;

public class OMToPaletteVisitor {

	private static final String inputPlaceholder = "x";
	
	
	public static Object visit(Object omElement) throws FunctionNotImplementedException {
		switch (omElement.getClass().getSimpleName()) {
		case "OMV":
			return visit((OMV) omElement);
		case "OMA":
			return visit((OMA) omElement);
		default:
			return omElement;
		}
	}
	private static Object visit(OMA oma) throws FunctionNotImplementedException {
		List<Object> argsArguments = oma.getOmel();
		OMS argsOms = (OMS) argsArguments.remove(0);
		Function func = null;
		try { // A Runtime-Exception can be thrown, by using getFunction
		func = OMSFunctionDictionary.getInstance().getFunction(argsOms);
		}catch (FunctionNotImplementedRuntimeException er){
			throw new FunctionNotImplementedException(er.getMessage());
		}
		return func.generatePalette(argsArguments);
	}
	
	private static Object visit(OMV omv) {
		if(omv.getName().equals(inputPlaceholder)) {
			return OMSymbol.EDITOR1_INPUT_BOX;
		}else {
			return omv;
		}
	}
	
	
}
