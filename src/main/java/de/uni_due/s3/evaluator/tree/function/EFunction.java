package de.uni_due.s3.evaluator.tree.function;

import java.util.List;

import de.uni_due.s3.evaluator.tree.EObject;

/**
 * TODO DOCU
 * @author dlux
 *
 */
public abstract class EFunction {

	
	abstract public EObject execute(List<EObject> childs);
	
	
	public EObject evaluate(EObject e){
		return null;
	}
	
	
	
	abstract protected int minArgs();
	
	
	
	abstract protected int maxArgs();	
}
