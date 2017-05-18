package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This Class is the abstract Class for OpenMathObjects.
 * With the Key it can return Strings, Integers, Floats, OpenMathObjects. etc..
 * 
 * 
 * 
 * @author dlux
 *
 * @param <Key>
 */
public abstract class OpenMathObject<Key>{

	protected String cd = null;
	protected String name = null;
	protected Key value;
	
	public Key getValue(){
		return value;
	}
	
	/**
	 * protected evaluation of the OpenMathObject
	 * This abstract class refers to itself as a Terminal
	 * so it returns itself
	 * 
	 * @return itself if not implemented otherwise
	 */
	protected OpenMathObject<?> evaluate(){
		return this;
	}
	
	
	/**
	 * Get the XML-Representation of this OpenmathObject
	 * 
	 * @return an String in form of XML
	 */
	public String getXML(){
		return "<OMOBJ>" + getPartialXML() + "</OMOBJ>";
	}
	
	/**
	 * Abstract function
	 * Get the partial XML-Representation of this object (like getXML excluding OMOBJ)
	 * 
	 * @return
	 */
	protected abstract String getPartialXML();
	
	
	/**
	 * Get the Number of Nodes in this OpenMathObject
	 * 
	 * @return an Integer "1"  (if not implemented otherwise)
	 */
	public int getNodesCount(){
		return 1;
	}
	
	
	/**
	 * Checks if itself is a Terminal (The abstract Class "is a Terminal")
	 * 
	 * @return true (if not implemented otherwise)
	 */
	public boolean isTerminal(){
		return true;
	}
}
