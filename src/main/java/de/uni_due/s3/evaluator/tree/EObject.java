package de.uni_due.s3.evaluator.tree;

/**
 * This abstract Class contains all the methods that are needed for every 
 * single Function in this Package (All Functions in 'Function-Package').
 * 
 * This Class refers itself as a Terminal, due most Classes inherit form 
 * this Class are Terminals (EBoolean, EFloat, EInteger, EString),
 * so evaluate and countNodes are already implemented
 * 
 *  TODO
 * @author dlux, frichtschied, spobel
 */
public abstract class EObject{
	
	
	/**
	 * This method returns itself as long as it is not 
	 * overwritten. EApplication overrides this method for 
	 * the correct implementation, where all arguments are 
	 * evaluated by the specific Function
	 * 
	 * @return itself if not implemented otherwise
	 */
	public EObject evaluate(){
		return this;
	}
	
	
	/**
	 * Get the OpenMath-XML-Representation from this Object.
	 * 
	 * If this Object cannot be expressed in OpenMath
	 * a 'null'-String returns.
	 * 
	 * @return an String in form of OpenMath-XML
	 */
	public String getOpenMathSyntax(){
		return "<OMOBJ>" + getPartialOpenMathXML() + "</OMOBJ>";
	}
	
	
	/**
	 * OpenMath-XML begins and ends with <OMOBJ></OMOBJ>, so 
	 * everything in between these tags returns here
	 * 
	 * @return Everything in between <OMOBJ> and </OMOBJ>
	 */
	abstract protected String getPartialOpenMathXML();
	
	
	/**
	 * Get the Number of Nodes in this EObject
	 * If it is a Terminal 1 will be returned, 
	 * otherwise the number of Nodes.
	 * 
	 * @return an Integer "1"  (if not implemented otherwise)
	 */
	public int countNodes(){
		return 1;
	}
	

	/**
	 * Returns the Sage Syntax of this EObject.
	 * 
	 * If this Object cannot be expressed in Sage
	 * a 'null'-String returns.
	 * 
	 * @return the Sage Syntax as a String
	 */
	abstract public String getSageSyntax();
	
	
	/**
	 * Returns the R Syntax of this EObject.
	 * 
	 * If this Object cannot be expressed in R
	 * a 'null'-String returns.
	 * 
	 * @return the R Syntax as a String
	 */
	abstract public String getRSyntax();
	
	
	/**
	 * Returns the Symja Syntax of this EObject.
	 * 
	 * If this Object cannot be expressed in Symja
	 * a 'null'-String returns.
	 * 
	 * @return the Symja Syntax as a String
	 */
	abstract public String getSymjaSyntax();
	

	/**
	 * Returns a String-Representation of this EObject. More
	 * specifically a simplified String-Representation what this 
	 * EObject contains.
	 * 
	 * This method should be used for Debugging.
	 * 
	 * <b>THIS METHOD SHOULD NOT BE PRESENT IN THE FINAL IMPLEMENTATION!</b>
	 * 
	 * @return A simplified String, to see what this EObject contains
	 */
	@Override 
	abstract public String toString();

	
	/**
	 * This method checks, if this object equals to another Object.
	 * 
	 * @param Object, the Object to check with
	 */
	@Override
	public boolean equals(Object obj){
		if(this == obj)
			return true;
		
		if(obj == null)
			return false;
		
		return false;
		
	}
}
