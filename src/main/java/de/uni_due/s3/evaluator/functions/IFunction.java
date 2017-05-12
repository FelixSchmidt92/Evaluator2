package de.uni_due.s3.evaluator.functions;

import java.util.List;

import de.uni_due.s3.evaluator.openmath.OpenMathObject;

public interface IFunction {
	public OpenMathObject execute(List<OpenMathObject> omo);
	public String getName();
}

