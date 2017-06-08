package de.uni_due.s3.evaluator;


public interface Converter<TOMOBJ> {
	
	public Object convertTOMOBJ(TOMOBJ tomobj);
}
