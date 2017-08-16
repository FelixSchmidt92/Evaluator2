package de.uni_due.s3.evaluator2.core.function;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSPriority;

public abstract class BinaryFunction extends Function{
	
	public final OMSPriority.Priority priority;

	public BinaryFunction(OMSPriority.Priority priority) {
		this.priority = priority;
	}

}
