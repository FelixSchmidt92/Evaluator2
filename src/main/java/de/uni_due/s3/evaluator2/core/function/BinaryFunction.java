package de.uni_due.s3.evaluator2.core.function;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;

/**
 * This class is a special kind of Function. Each BinaryFunction represents a
 * binary operator like +, -, && and has a priority. In general the priority of
 * an operator is expressed through the openmath-tree which will be created
 * after parsing an expression. But if you want to compare the priority of
 * operators (like in OMLatexVisitor) they have to be specified. Each function
 * that extends this class has to specify its priority.
 * 
 * @author frichtscheid
 *
 */
public abstract class BinaryFunction extends Function {

	public final OMSPriority.Priority priority;

	public BinaryFunction(OMSPriority.Priority priority) {
		this.priority = priority;
	}

}
