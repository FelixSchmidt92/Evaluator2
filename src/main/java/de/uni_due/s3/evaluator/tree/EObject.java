package de.uni_due.s3.evaluator.tree;

/**
 * This Class is the abstract Class for OpenMathObjects.
 * With the Key it can return Strings, Integers, Floats, OpenMathObjects. etc..
 * 
 * 
 *  TODO
 * @author dlux
 *
 * @param <Key>
 */
public abstract class EObject{
	
	
	/**
	 * protected evaluation of the OpenMathObject
	 * This abstract class refers to itself as a Terminal
	 * so it returns itself
	 * 
	 * @return itself if not implemented otherwise
	 */
	public EObject evaluate(){
		return this;
	}
	
	
	/**
	 * Get the XML-Representation of this OpenmathObject
	 * 
	 * @return an String in form of XML
	 */
	public String getOpenMathSyntax(){
		return "<OMOBJ>" + getPartialXML() + "</OMOBJ>";
	}
	
	/**
	 * Get the Number of Nodes in this OpenMathObject
	 * 
	 * @return an Integer "1"  (if not implemented otherwise)
	 */
	public int getNodesCount(){
		return 1;
	}
	
	/**
	 * TODO 
	 * @return
	 */
	abstract protected String getPartialXML();
	
	/**
	 * TODO
	 * @return
	 */
	abstract protected String getSageSyntax();
	
	/**
	 * 
	 * @return
	 */
	abstract protected String getRSyntax();
	
	/**
	 * 
	 * @return
	 */
	abstract protected String getSymjaSyntax();
	

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
