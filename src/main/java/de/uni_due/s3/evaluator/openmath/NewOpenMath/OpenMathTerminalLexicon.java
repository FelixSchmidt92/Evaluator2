package de.uni_due.s3.evaluator.openmath.NewOpenMath;

/**
 * This is a Lexicon for all Terminals, that can be set.
 * 
 * This is used by the OpenMathApplication to check if itself is a terminal.
 * 
 * By allowing certain cd's and name's as a Terminal, the OMA will return 
 * itself instead of an evaluation of its objects.
 * 
 * @author dlux
 */
public class OpenMathTerminalLexicon {
	
	/**
	 * This static method checks if the OMA is a Terminal
	 * 
	 * @param cd the Content Dictionary
	 * @param name the Name in the Content Dictionary
	 * @return true if (specified) Terminal, false otherwise
	 */
	public static boolean TerminalCheck(String cd, String name){
		
		switch (cd){
		case "set1":
			switch(name){
			case "set":
				return true;
			default:
				return false;
			}
			
		case "linalg2":
			switch(name){
			case "vector":
			case "matrixrow":
			case "matrix":
				return true;
			default:
				return false;
			}
			
		default:
			return false;
		}
	}
}
