package de.uni_due.s3.evaluator2.core.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.uni_due.s3.evaluator2.core.dictionaries.OMSFunctionDictionary;
import de.uni_due.s3.evaluator2.core.function.arith1.Divide;
import de.uni_due.s3.evaluator2.core.function.arith1.Minus;
import de.uni_due.s3.evaluator2.core.function.arith1.Plus;
import de.uni_due.s3.evaluator2.core.function.arith1.Power;
import de.uni_due.s3.evaluator2.core.function.arith1.Times;
import de.uni_due.s3.evaluator2.core.function.arith1.UnaryMinus;
import de.uni_due.s3.evaluator2.core.function.arith_jack.IEEERemainder;
import de.uni_due.s3.evaluator2.core.function.integer1.Remainder;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanAnd;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanNot;
import de.uni_due.s3.evaluator2.core.function.logic1.BooleanOr;
import de.uni_due.s3.evaluator2.core.function.logic_jack.IfThenElse;
import de.uni_due.s3.evaluator2.core.function.relation1.Equal;
import de.uni_due.s3.evaluator2.core.function.relation1.GreaterThan;
import de.uni_due.s3.evaluator2.core.function.relation1.GreaterThanOrEqual;
import de.uni_due.s3.evaluator2.core.function.relation1.LessThan;
import de.uni_due.s3.evaluator2.core.function.relation1.LessThanOrEqual;
import de.uni_due.s3.evaluator2.core.function.relation1.NotEqual;
import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedException;
import de.uni_due.s3.evaluator2.exceptions.function.FunctionNotImplementedRuntimeException;
import de.uni_due.s3.openmath.jaxb.OMA;
import de.uni_due.s3.openmath.jaxb.OMS;

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

	public String getBinaryLatex(Object omElement) throws EvaluatorException {
		if (omElement instanceof OMA) {
			OMA child = (OMA) omElement;
			List<Object> childOmel = new ArrayList<Object>(child.getOmel().size() - 1);
			OMS childOMS = (OMS) child.getOmel().get(0);
			
			Function childFunc = null;
			try {
			childFunc = OMSFunctionDictionary.getInstance().getFunction(childOMS);
			}catch (FunctionNotImplementedRuntimeException er){
				throw new FunctionNotImplementedException(er.getMessage());
			}

			for (int i = 1; i < child.getOmel().size(); i++) {
				childOmel.add(child.getOmel().get(i));
			}

			if (childFunc instanceof BinaryFunction) {
				if(priorities.get(childFunc.getClass()).compareTo(priorities.get(this.getClass())) > 0) {
					return "\\left(" + childFunc.getPartialLatexSyntax(childOmel) + "\\right)";
				} else {
					return childFunc.getPartialLatexSyntax(childOmel);
				}
			} else {
				return childFunc.getPartialLatexSyntax(childOmel);
			}
		} else {
			return getLatexSyntax(omElement);
		}
	}
	
	
	private static final Map<Class<?>,Integer> priorities;
		
	static {
			priorities = new HashMap<Class<?>,Integer>();
			priorities.put(UnaryMinus.class, 1);
			priorities.put(BooleanNot.class, 1);
			
			priorities.put(Power.class, 2);
			priorities.put(Divide.class, 2);
			priorities.put(Times.class, 2);
			priorities.put(IEEERemainder.class, 2);
			priorities.put(Remainder.class, 2);
			
			priorities.put(Plus.class, 3);
			priorities.put(Minus.class, 3);
			
			priorities.put(LessThan.class, 5);
			priorities.put(LessThanOrEqual.class, 5);
			priorities.put(GreaterThan.class, 5);
			priorities.put(GreaterThanOrEqual.class, 5);
			
			priorities.put(Equal.class, 6);
			priorities.put(NotEqual.class, 6);
			
			priorities.put(BooleanAnd.class, 10);
			priorities.put(BooleanOr.class, 11);
			priorities.put(IfThenElse.class, 12);		
			
		}
}
