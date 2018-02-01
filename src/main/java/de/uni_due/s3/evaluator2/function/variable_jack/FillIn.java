package de.uni_due.s3.evaluator2.function.variable_jack;

import java.util.List;

import de.uni_due.s3.evaluator2.exceptions.EvaluatorException;
import de.uni_due.s3.evaluator2.function.ConstructorFunction;
import de.uni_due.s3.evaluator2.visitor.operation.OMToResultVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToBooleanVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToDoubleVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToIntegerVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToListVisitor;
import de.uni_due.s3.evaluator2.visitor.primitve.OMToStringVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToLatexVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToRVisitor;
import de.uni_due.s3.evaluator2.visitor.syntax.OMToSageVisitor;
import de.uni_due.s3.openmath.omutils.OpenMathException;

public class FillIn extends ConstructorFunction{

	@Override
	public Object getPartialSymbolicSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToResultVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public Boolean getPartialBooleanSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToBooleanVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public Double getPartialDoubleSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToDoubleVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public Integer getPartialIntegerSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToIntegerVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public List<Object> getPartialListSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return OMToListVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public String getPartialStringSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToStringVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public String getPartialLatexSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToLatexVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public String getPartialRSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Auto-generated method stub
		return OMToRVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	public String getPartialSageSyntax(List<Object> arguments) throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToSageVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	protected Object getPartialOpenMathElementResult(List<Object> arguments)
			throws EvaluatorException, OpenMathException {
		// TODO Catch Exception and Handling
		return OMToResultVisitor.getInstance().visit(arguments.get(0));
	}

	@Override
	protected int minArgs() {
		return 1;
	}

	@Override
	protected int maxArgs() {
		return 1;
	}

}
