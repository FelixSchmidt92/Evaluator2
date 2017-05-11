package de.uni_due.s3.evaluator.parser.antlr;

import java.nio.charset.Charset;
import java.util.Random;

/**
 * This Class can generate various Strings, that the Parser should accept.
 * 
 * It can generate simple FillInVariables or PointNumbers up to Functions in Functions (recursively)
 * This Class uses an Enum Terminal for an 'standardized interface' to select randomly from Terminals.
 * 
 * @author dlux
 */
public class GrammarGenerator {
	private String alphabetString = "abcdeghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	private String numbersString = "0123456789"; 
	private String umlautsString = "äöüÄÖÜ"; 
	
	private char[] alphabet = alphabetString.toCharArray(); // [a-zA-Z]
	private char[] numbers = numbersString.toCharArray(); // [0-9]
//	private char[] umlauts = umlautsString.toCharArray(); // [äöüÄÖÜ]
	
	private char[] alphabetNumbers = (alphabetString + numbersString).toCharArray(); // [a-zA-Z0-9]
	private char[] alphabetNumbersUmlauts = (alphabetString + numbersString + umlautsString).toCharArray(); // [a-zA-Z0-9_äöüÄÖÜ]
	private char[] alphabetUmlauts = (alphabetString + umlautsString).toCharArray(); // [a-zA-Z_äöüÄÖÜ]
	
	private String[] unaryOperators = {"+", "-", "!", ""};
	private String[] binaryOperators = {"+", "-", "*", "/", "%",
										"<", "<=", ">", ">=", 
										"=", "==", "!=", "&&", "||"};
	

	/**
	 * Ends Recursion for genRandomFunctionRecursionWithBinaryTerms
	 * 
	 * @param length +1 current length of Arguments 
	 * @param depth the current depth of function recursion
	 * @param initLength +1 the Argument length 
	 * @return a String with function in functions in it
	 */
	private String genArgsRecursionBinary(int length, int depth, int initLength){
		if(length == 0){
			return genRandomFunctionRecursionWithBinaryTerms(initLength, depth - 1, initLength);
		}else{
			return genRandomFunctionRecursionWithBinaryTerms(initLength, depth - 1, initLength) + ", " + genArgsRecursionBinary(length -1, depth, initLength);
		}
	}
	
	/**
	 * Generates a Recursive Function in Function String like 'ab(ab((1/2), (2+8)), cd((-2 + 1), (3 + 2)))'<p>
	 * FunctionName length and VariableName length is set to 3 
	 * 
	 * @param length +1 the amount of arguments in Function (has to be same as initLength)
	 * @param depth the depth of Functions in Function 
	 * @param initLength +1 the initial length of Function (same as length)
	 * @return a String that has Function in Functions and Terminals as Binary Operations
	 */
	public String genRandomFunctionRecursionWithBinaryTerms(int length, int depth, int initLength){
		if (depth == 0){
			return "(" + genRandomBinaryOperationWithDepthOne(3, null) + ")";
		}else{
			return genRandomFunctionName(3, null) + "(" + genArgsRecursionBinary(length, depth, initLength) + ")";
		}
	}
	
	/**
	 * Ends Recursion for genRandomFunctionRecursion
	 * 
	 * @param length +1 current length of Arguments
	 * @param depth the current depth of function recursion
	 * @param initLength +1 the Argument length
	 * @return a String with function in functions in it
	 */
	private String genArgsRecursion(int length, int depth, int initLength){
		if(length == 0){
			return genRandomFunctionRecursion(initLength, depth - 1, initLength);
		}else{
			return genRandomFunctionRecursion(initLength, depth - 1, initLength) + ", " + genArgsRecursion(length -1, depth, initLength);
		}
	}
	
	/**
	 * Generates a Recursive Function in Function String like 'ab(ab(1, 2), cd(2, 3))'<p>
	 * FunctionName length and VariableName length is set to 3 
	 * 
	 * @param length +1 the amount of arguments in Function (has to be same as initLength)
	 * @param depth the depth of Functions in Function
	 * @param initLength +1 the initial length of Function (same as length)
	 * @return a String that has Function in Functions
	 */
	public String genRandomFunctionRecursion(int length, int depth, int initLength){
		if (depth == 0){
			return genRandomUnaryTerminal(3, null);
		}else{
			return genRandomFunctionName(3, null) + "(" + genArgsRecursion(length, depth, initLength) + ")";
		}
	}
	
	
	/**
	 * Generates a binaryOperation-Chain like term1 BINOPP1 term2 BINOPP2 term3 ....
	 * 
	 * @param builder null
	 * @param chainLength The length (count of binary operators) in this chain
	 * @param length The length of the Terminals and count of the Function Arguments(not randomly 0)
	 * @return returns a binaryChain as String
	 */
	public String genRandomBinaryOperatorChainWithFunctionArguments(int chainLength, int length, String builder){
		if (builder == null){
			return genRandomBinaryOperatorChainWithFunctionArguments(chainLength, length, "");
		}else{
			if (chainLength == 0){
				return builder + genRandomFunctionWithDepthOne(length, length, null);
			}else{
				return genRandomFunctionWithDepthOne(length, length, null) + " " 
					   + binaryOperators[new Random().nextInt(binaryOperators.length)] + " "
					   + genRandomBinaryOperatorChainWithFunctionArguments(chainLength -1, length, "");
			}
		}	
	}
	
	
	/**
	 * Generates a binaryOperation-Chain like term1 BINOPP1 term2 BINOPP2 term3 ....
	 * 
	 * @param builder null
	 * @param chainLength The length (count of binary operators) in this chain
	 * @param length The length of the Terminals (not randomly 0)
	 * @return returns a binaryChain as String
	 */
	public String genRandomBinaryOperatorChainWithTerminals(int chainLength, int length, String builder){
		if (builder == null){
			return genRandomBinaryOperatorChainWithTerminals(chainLength, length, "");
		}else{
			if (chainLength == 0){
				return builder + genRandomUnaryTerminal(length, null);
			}else{
				return genRandomUnaryTerminal(length, null) + " " 
					   + binaryOperators[new Random().nextInt(binaryOperators.length)] + " "
					   + genRandomBinaryOperatorChainWithTerminals(chainLength -1, length, null);
			}
		}	
	}
	

	/**
	 * Generates a Function with depth=1 <p>
	 * Example: ranFuncName(term1, term2, ...)
	 * 
	 * @param null
	 * @param maxArgs Count of Arguments in this Function + 1
	 * @param length The RANDOM length of the Terminals and the length of the Function(but not randomly 0)
	 * @return returns a String which represents a Function
	 */
	public String genRandomFunctionWithDepthOne(int maxArgs, int length, String builder){
		return genRandomFunctionName(length, builder) + "(" + genFunctionTerminals(maxArgs, length)+ ")"; 
	}
	
	
	/**
	 * Generates a random Binary Operation like '+[var=a] / -44.123'
	 * 
	 * @param builder null
	 * @param length the RANDOM length of the Terminals (but not randomly 0)
	 * @return returns a String, which represents a Binary Operation
	 */
	public String genRandomBinaryOperationWithDepthOne(int length, String builder){
		return genRandomUnaryTerminal(length, null) + " "
			   + binaryOperators[new Random().nextInt(binaryOperators.length)] + " "
			   + genRandomUnaryTerminal(length, null);
	}
	
	
	/**
	 * Same as genRandomTerminalWithParentheses, expect an unaryTerminal
	 * 
	 * @param builder null
	 * @param depth The depth (count of Parentheses on one side) of Parentheses
	 * @param length The RANDOM length of a random Terminal (but never randomly 0)
	 * @return returns a String with Parentheses and a unary Terminal
	 */
	public String genRandomUnaryTerminalWithParentheses(int depth, int length, String builder){
		return genRandomTerminalWithParantheses(depth, length, genRandomUnaryTerminal(length, null));
	}
	
	
	/**
	 * Generates a String with Paratheses in Parantheses. In these Parentheses is a Random Terminal<p>
	 * Example: genRandomTerminalWithParentheses(5, 3, null) returns ((((('RandomTerminal')))))
	 * 
	 * @param builder null
	 * @param depth The depth (count of Parentheses on one side) of Parentheses
	 * @param length The RANDOM length of a random Terminal (but never randomly 0)
	 * @return returns a String with Parentheses and a Terminal
	 */
	public String genRandomTerminalWithParantheses(int depth, int length, String builder){
		if (builder == null){
			return genRandomTerminalWithParantheses(depth, length, genRandomTerminal(length, null));
		}else{
			if (depth == 0){
				return builder;
			}else{
				return genRandomTerminalWithParantheses(depth -1, length, "(" + builder + ")");
			}
		}
	}
	
	
	/**
	 * Generates a Random Terminal with an Unary operator
	 * 
	 * @param builder null (or prefix of Terminal)
	 * @param maxLength The Max length of this Terminal (Length is between 1 to length)
	 * @return returns a String with a random Terminal with a unary Operator
	 */
	public String genRandomUnaryTerminal(int maxLength, String builder){
		if(builder == null){
			return genRandomUnaryTerminal(maxLength, "");
		}
			return builder += unaryOperators[new Random().nextInt(unaryOperators.length)] 
						   + Terminal.values()[new Random().nextInt(Terminal.values().length)].make(maxLength);
	}
	
	
	/**
	 * Generates a Random Terminal
	 * 
	 * @param builder null (or prefix of Terminal)
	 * @param maxLength The Max length of this Terminal (Length is between 1 to length)
	 * @return returns a String with a random Terminal 
	 */
	public String genRandomTerminal(int maxLength, String builder){
		if(builder == null){
			return genRandomTerminal(maxLength, "");
		}
			return builder += Terminal.values()[new Random().nextInt(Terminal.values().length)].make(maxLength);
	}
	
	
	/**
	 * Generates [a-zA-Z]+ [a-zA-Z0-9]*   where length represents how often the expression should be repeated
	 * Calling: genRandomFunctionName(length, null)
	 * 
	 * @param builder null
	 * @param length the length of the expression
	 * @return return an String
	 * 						@Note  This Function does not return parentheses 
	 * 							   after the Function Name
	 */
	public String genRandomFunctionName(int length, String builder){
		if (length == 0){
			return "";
		}else{
			return genRandomCharSequenceValue(length -1 , Character.toString((alphabet)[new Random().nextInt(alphabet.length)]), alphabetNumbers, "");
		}
	}
	
	
	/**
	 * Generates a String with all possible UTF-8 Characters. It generates ' .* '  excluding '
	 * 
	 * @Call genRandomUTF8StringValue(length, null)
	 * 
	 * @param builder null
	 * @param length The length of the expression
	 * @return returns a String, which contains length times UTF-8 Characters between ' 
	 * 						@Note  Some Characters are commands, which are not
	 * 							   displayed or even delete other Characters, but the
	 * 							   String still contains length times UTF-8-Characters.
	 * 							   By displaying, these Characters are interpreted
	 */
	public String genRandomUTF8StringValue(int length, String builder){
		if (builder == null){
			return genRandomUTF8StringValue(length, "");
		}else{
			if (builder.contains("'")){
				builder = builder.replaceAll("'", ""); // remove ', because its not allowed
				return genRandomUTF8StringValue(length + 1, builder);
			}else if (length == 0){
				return "'" + builder + "'";
			}else{
				return genRandomUTF8StringValue(length -1, builder + new String(UTF8Gen(), Charset.forName("UTF-8")));
			}
		}
	}

	
	/**
	 * Generates '[pos=' [0-9]+ ']'   where length represents how often [0-9]+ should be repeated
	 * 
	 * @Call genRandomPositionVariable(length, null)
	 * 
	 * @param builder null
	 * @param length the length of the expression (0 is one-Times)
	 * @return returns an String of a FillInVariable
	 */
	public String genRandomPositionVariable(int length, String builder){
		if (builder == null){
			return genRandomPositionVariable(length, "[pos=");
		}else{
			if (length == 0){
				return (builder + "]"); 
			}
			else return genRandomPositionVariable(length - 1, (builder += numbers[new Random().nextInt(numbers.length)]) );
		}
	}
	
	/**
	 * Generates '[var=' [a-zA-Z_öäüÖÄÜ]+ [a-zA-Z0-9_öäüÖÄÜ]* ']'   where length represents how often the expression should be repeated
	 * 
	 * @Call genRandomExerciseVariable(length, null)
	 * 
	 * @param builder null
	 * @param length the length of the expression
	 * @return returns an String of a ExerciseVariable
	 */
	public String genRandomExerciseVariable(int length, String builder){
		if (length == 0){
			return "[var=]";
		}else{
			return genRandomCharSequenceValue(length -1 , "[var=" + (alphabetUmlauts)[new Random().nextInt(alphabetUmlauts.length)], alphabetNumbersUmlauts, "]");
		}
	}
	
	
	/**
	 * Generates '#{' [a-zA-Z]+ [a-zA-Z0-9]* '}'  where length represents how often the expression should be repeated
	 * 
	 * @Call genRandomStandardVariable(length, null)
	 * 
	 * @param builder null
	 * @param length the length of the expression
	 * @return an String with #{'GeneratedText'}
	 */
	@Deprecated
	public String genRandomStandardVariable(int length, String builder){
		if (length == 0){
			return "#{}";
		}else{
			return genRandomCharSequenceValue(length -1 , "#{" + (alphabet)[new Random().nextInt(alphabet.length)], alphabetNumbers, "}");
		}
	}
	
	
	/**
	 * Generates  [0-9]*  where length represents how often the expression should be repeated
	 * 
	 * @Call genRandomIntegerValue(length, null)
	 * 
	 * @param length the length of the expression
	 * @param builder null
	 * @return returns an String
	 */
	public String genRandomIntegerValue(int length, String builder){
		if (length == 0){
			return "";
		}else{
			return genRandomCharSequenceValue(length , "",  numbers, "");
		}
	}

	
	/**
	 * Generates  [1-9] [0-9]* . [0-9]+ where length represents how often the expression should be repeated<p>
	 * 
	 * @Call genRandomPointNumberValue(length1, length2, null)
	 * 
	 * @param builder null
	 * @param length1 the length of the expression  before '.'
	 * @param length2 the length of the expression  after  '.'
	 * @param returns an String
	 */
	public String genRandomPointNumberValue(int length1, int length2, String builder){
		return genRandomIntegerValue(length1, null) + "." + genRandomCharSequenceValue(length2, "", numbers, "");
	}
	

	/**
	 * Generates the FunctionTerminals in form of "term1, term2, term3, ..."
	 * 
	 * @param maxArgs Count of Terminals as Arguments + 1
	 * @param length the RANDOM length of Terminals (but not randomly 0)
	 * @return returns a String that matches function-Arguments
	 */
	private String genFunctionTerminals(int maxArgs, int length){
		if (maxArgs == 0){
			return genRandomUnaryTerminal(length, null);
		}else{
			return genRandomUnaryTerminal(length, null) + ", " + genFunctionTerminals(maxArgs - 1, length);
		}
	}

	
	/**
	 * Generates a Random String Sequence by the Given chars and length
	 * 
	 * @param length: the length  of the Expression
	 * @param builder: prefix of String(not null)
	 * @param chars: Char-Array, which character should be used
	 * @param endString: postfix of String(not null)
	 * @return returns an String in form builder + chars + endString where chars is length+1 often
	 */
	private String genRandomCharSequenceValue(int length, String builder, char[] chars, String endString){
		if (length == 0){
			return (builder + endString); 
		}
		else return genRandomCharSequenceValue(length - 1, (builder += chars[new Random().nextInt(chars.length)]), chars, endString);
	}
	
	
	/**
	 * Random Byte Generator for UTF-8.
	 * See more at UTF-8-Standard
	 * 
	 * @return a byte (or more) in UTF-8-Standard
	 */
	private byte[] UTF8Gen(){
		byte[] b1 = new byte[1];
		byte[] bn = new byte[3];
		
		new Random().nextBytes(b1);
		new Random().nextBytes(bn);
		for (byte bs : bn){
			bs = (byte) (bs & 0xBF); // set to x0xx xxxx
			bs = (byte) (bs | 0x80); // set to 1xxx xxxx
		}
		
		byte z = (byte) (b1[0] & 0xF8); // extract xxxx x000
		switch (z){
		case (byte) 0xC0:  // is it 1100 0000?		
			return new byte[] {b1[0], bn[0]};
		
		case (byte) 0xE0:  // is it 1110 0000?
			return new byte[] {b1[0], bn[0], bn[1]};
			
		case (byte) 0xF0:  // is it 1111 0000?
			return new byte[] {b1[0], bn[0], bn[1], bn[2]};
		
		default:		   // is it xxxx x000?
			b1[0] = (byte) (b1[0] & 0x7F); // set to 0xxx xxxx
			return b1;
		}
	}
}
