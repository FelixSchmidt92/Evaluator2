package de.uni_due.s3.sage;

/**
 * A Wrapper-class to have Two keys in HashMaps.
 * 
 * It is used by OMOBJToSageStringConverter
 * 
 * @author dlux
 *
 */
public class KeyPair {

	public final String first, second;
	
	/**
	 * Constructor
	 * 
	 * @param first first Parameter
	 * @param second second Parameter
	 */
	public KeyPair(String first, String second){
		this.first = first;
		this.second = second;
	}
	
	
	/**
	 * Check if two Object of KeyPair are equal.
	 * 
	 * @return true if equal, false otherwise
	 */
	@Override
	public boolean equals (final Object obj){
		if(!(obj instanceof KeyPair)) return false;
		
		if (!((KeyPair)obj).first.equals(first)) return false;
		if (!((KeyPair)obj).second.equals(second)) return false;
		
		return true;
	}
	
	
	/**
	 * Generate a HashCode for this Object. Using 659 as a Prime.
	 * 
	 * @return an HashCode hopefully unique for this Object
	 */
	@Override
	public int hashCode(){
		return (659 + first.hashCode()) * (659 + second.hashCode());
	}
	
}
