package de.uni_due.s3.evaluator2.core.dictionaries;

import java.util.HashMap;
import java.util.Map;

import de.uni_due.s3.openmath.jaxb.OMS;

public class OMSPriority {
	private static final Map<OMS,Priority> priorities;
	
	static {
		priorities = new HashMap<OMS,Priority>();
		priorities.put(OMSymbol.ARITH1_UNARY_MINUS, Priority.PRIO1);
		priorities.put(OMSymbol.LOGIC1_NOT, Priority.PRIO1);
		
		priorities.put(OMSymbol.ARITH1_POWER, Priority.PRIO2);
		priorities.put(OMSymbol.ARITH1_DIVIDE, Priority.PRIO2);
		priorities.put(OMSymbol.ARITH1_TIMES, Priority.PRIO2);
		priorities.put(OMSymbol.ARITHJACK_IEEEREMAINDER, Priority.PRIO2);
		priorities.put(OMSymbol.INTEGER1_REMAINDER, Priority.PRIO2);
		
		priorities.put(OMSymbol.ARITH1_PLUS, Priority.PRIO3);
		priorities.put(OMSymbol.ARITH1_MINUS, Priority.PRIO3);
		
		priorities.put(OMSymbol.RELATION1_LT, Priority.PRIO5);
		priorities.put(OMSymbol.RELATION1_LEQ, Priority.PRIO5);
		priorities.put(OMSymbol.RELATION1_GT, Priority.PRIO5);
		priorities.put(OMSymbol.RELATION1_GEQ, Priority.PRIO5);
		
		priorities.put(OMSymbol.RELATION1_EQ, Priority.PRIO6);
		priorities.put(OMSymbol.RELATION1_NEQ, Priority.PRIO6);
		
		priorities.put(OMSymbol.LOGIC1_AND, Priority.PRIO10);
		priorities.put(OMSymbol.LOGIC1_OR, Priority.PRIO11);
		priorities.put(OMSymbol.LOGICJACK_IFTHENELSE, Priority.PRIO12);		
		
	}
	
	public static Priority getPriority(OMS oms) {
		if(priorities.containsKey(oms)) {
			return priorities.get(oms);
		}else {
			return Priority.LOWEST;
		}
	}
	
	/**
	 * This declares the priority for operations based on the table in 
	 * <a href="www.javaschubla.de/2007/javaerst0190.html" >java-Operations </a>
	 * 
	 * @author frichtscheid
	 *
	 */
	public enum Priority {
		/**
		 * for ++, --, sign +, sign -, ~, ! and power(^)
		 */
		PRIO1,
		
		/**
		 * for *, /, %
		 */
		PRIO2,
		
		/**
		 * for +, -, string-concatenation
		 */
		PRIO3,
		
		/**
		 * for <<,>>, >>>
		 */
		PRIO4,
		
		/**
		 * for <,<=,>,>=
		 */
		PRIO5,
		
		/**
		 * for ==, !=
		 */
		PRIO6,
		
		/**
		 * for &
		 */
		PRIO7,
		
		/**
		 * for ^ exlcusive or
		 */
		PRIO8,
		
		/**
		 * for |
		 */
		PRIO9,
		
		/**
		 * for &&
		 */
		PRIO10,
		
		/**
		 * for ||
		 */
		PRIO11,
		
		/**
		 * for ?: (ifthenelse)
		 */
		PRIO12,
		
		/**
		 * for =, *=, /=, %=, +=, -=, <<=, >>=, >>>=, &=, ^=, |=
		 */
		PRIO13,
		
		/**
		 * for anything else
		 */
		LOWEST,
	}

	
}
