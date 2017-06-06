package de.uni_due.s3.evaluator;

import java.util.HashMap;

import de.uni_due.s3.evaluator.tree.EObject;

/**
 * 
 * @author spobel
 *
 * @param <TOMOBJ>
 */
public abstract class Evaluator<TOMOBJ> {

	private Converter<TOMOBJ> eObjectConverter;
	private HashMap<String, EObject> exerciseVariableMap;
	private HashMap<String, EObject> fillInVariableMap;

	public Evaluator(Converter<TOMOBJ> eObjectConverter) {
		this.eObjectConverter = eObjectConverter;
	}

	public void addFillInVariable(String name, TOMOBJ tomobj) {
		fillInVariableMap.put(name, eObjectConverter.convertTOMOBJ(tomobj));
	}

	public void addExerciseVariable(String name, TOMOBJ tomobj) {
		exerciseVariableMap.put(name, eObjectConverter.convertTOMOBJ(tomobj));
	}
}
