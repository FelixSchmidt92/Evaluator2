package de.uni_due.s3.evaluator.functions.evaluator;

import java.util.List;

import de.uni_due.s3.evaluator.functions.IFunction;
import de.uni_due.s3.evaluator.openmath.OpenMathObject;
import de.uni_due.s3.evaluator.openmath.OpenMathObjectCreator;

public class Abs implements IFunction{

	@Override
	public OpenMathObject execute(List<OpenMathObject> omo) {
		// TODO Auto-generated method stub
		return OpenMathObjectCreator.createOpenMathInteger(1);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "abs";
	}

}
