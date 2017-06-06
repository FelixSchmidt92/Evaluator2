package de.uni_due.s3.evaluator;

import de.uni_due.s3.evaluator.tree.EObject;

public interface Converter<TOMOBJ> {
	
	public EObject convertTOMOBJ(TOMOBJ tomobj);
}
