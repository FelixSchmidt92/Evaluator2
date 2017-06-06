package de.uni_due.s3.evaluator.tree;


import java.util.LinkedList;
import java.util.List;

import de.uni_due.s3.evaluator.tree.function.EFunction;

/**
 * EApplication is a Non-Terminal. It implements the evaluate-method
 * and does the most logic in this Tree.
 * 
 * This Object also contains the Function which can be applied to this Object, 
 * with evaluate this Function will be executed
 * 
 * @UnderConstruction TODO implement abstract methods from EObject
 * @author dlux, frichtscheid, spobel
 */
public class EApplication extends EObject{

	private List<EObject> arguments;
	private EFunction function;

	
	/**
	 * Constructor to make a new EApplication. 
	 * 
	 * @param function The EFunction, which can be applied to the Arguments
	 * @param arguments A List of EObject-Arguments
	 */
	public EApplication(EFunction function, List<EObject> arguments) {
		this.arguments = arguments;
		this.function = function;
	}
	
	
	/**
	 * First: Check if arguments should be evaluated 
	 * 		  (some methods, don't want to get evaluated 
	 * 		   arguments)
	 * Then:  Evaluate the arguments with the current Function
	 *  
	 * 
	 * @return an new EObject containing the result after 
	 * 		   executing the specific function in EApplication
	 */
	@Override
	public EObject evaluate(){
		List<EObject> evaluatedArguments = new LinkedList<EObject>();
		if (function.argumentsShouldBeEvaluated()) {
		//Iterate over every argument and evaluate
			for(EObject eObj : arguments){ //FIXME spobel sollten wir arguments (private) hier nutzen ? wäre für die zukunft besser mit dem getter zu arbeiten ?
				evaluatedArguments.add(eObj.evaluate()); 
			}
		} else {
		//do not evaluate just pass through
			evaluatedArguments = arguments;
		}
		//FIXME spobel ??? sollte an dieser Stelle nicht die argument Liste aus diesem Object überschrieben werden ?
		// würde man jetzt auf die arguments zugreifen wären sie wieder die alten ? 
		return function.evaluate(evaluatedArguments);
	}


	@Override
	public String getSageSyntax() { //FIXME spobel sollte das auch an die Function delegiert werden ? sollte ja iwie eine für eine Matrix darstellung geben ... 
		return null;
	}


	@Override
	public String getRSyntax() {
		return null;
	}


	@Override
	public String getSymjaSyntax() {
		return null;
	}


	@Override
	public String toString() {
		return null;
	}


	@Override
	protected String getPartialOpenMathXML() {
		return null;
	}
	
	
	@Override
	public int countNodes(){
		int count = 1; // 1 --> Count yourself!
		//FIXME spobel muss die efunction auch gezählt werden ?
		for (EObject eObj: arguments){
			count += eObj.countNodes();
		}
		return count;
	}
	
	
	/**
	 * Returns the list of Arguments which were 
	 * passed by constructing this EApplication.
	 * 
	 * @return a argument-list of EObjects
	 */
	public List<EObject> getArguments(){
		return arguments;
	}
}
