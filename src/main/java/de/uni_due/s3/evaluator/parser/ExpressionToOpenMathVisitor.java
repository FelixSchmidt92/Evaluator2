package de.uni_due.s3.evaluator.parser;

import java.util.Map;

import de.uni_due.s3.evaluator.core.OMConverter;
import de.uni_due.s3.evaluator.core.function.FunctionFactory;
import de.uni_due.s3.evaluator.exceptions.UndefinedExerciseVariableException;
import de.uni_due.s3.evaluator.exceptions.UndefinedFillInVariableException;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParser.ExpressionContext;
import de.uni_due.s3.evaluator.parser.antlr.EvaluatorParserBaseVisitor;
import de.uni_due.s3.openmath.OMA;
import de.uni_due.s3.openmath.OMF;
import de.uni_due.s3.openmath.OMI;
import de.uni_due.s3.openmath.OMOBJ;
import de.uni_due.s3.openmath.OMS;
import de.uni_due.s3.openmath.OMSTR;

	
public class ExpressionToOpenMathVisitor extends EvaluatorParserBaseVisitor<Object> {
	
	/**
	 * These variables are defined by the evaluator-class and are used to integrate the variables into the OM-tree
	 */
	private Map<String,OMOBJ> exerciseVariableMap;
	private Map<Integer,OMOBJ> fillInVariableMap;
	
	public ExpressionToOpenMathVisitor(Map<String,OMOBJ> exerciseVariableMap, Map<Integer,OMOBJ> fillInVariableMap) {
		this.exerciseVariableMap = exerciseVariableMap;
		this.fillInVariableMap = fillInVariableMap;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Object visitParentheses(EvaluatorParser.ParenthesesContext ctx) {
		return visit(ctx.getChild(1)); 	//visit second child only (there are only Arguments 
										//in between the parentheses)
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMA visitUnaryOperator(EvaluatorParser.UnaryOperatorContext ctx) {
		OMS oms = new OMS();

		switch (ctx.operator.getText()){
		case "+":
			oms.setCd("arith1");
			oms.setName("unary_plus");
			break;

		case "-":
			oms.setCd("arith1");
			oms.setName("unary_minus");
			break;

		case "!":
			oms.setCd("logic1");
			oms.setName("not");
			break;
		}
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		oma.getOmel().add(visitChildren(ctx));
		return oma;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMSTR visitTextValue(EvaluatorParser.TextValueContext ctx) {
		OMSTR omstr = new OMSTR();
		omstr.setContent(ctx.getText().substring(1, ctx.getText().length()-1)); //delete ' at beginning and end
		return omstr;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMA visitBinaryOperator(EvaluatorParser.BinaryOperatorContext ctx) {
		OMS oms = new OMS();
		
		switch (ctx.operator.getText()){
		case "+":
			oms.setCd("arith1");
			oms.setName("plus");
			break;

		case "-":
			oms.setCd("arith1");
			oms.setName("minus");
			break;

		case "*":
			oms.setCd("arith1");
			oms.setName("times");
			break;

		case "/":
			oms.setCd("arith1");
			oms.setName("divide");
			break;

		case "%":   //defining here an own cd and name to have this as an binary operator
			oms.setCd("jackbinary1");
			oms.setName("modulus");
			break;

		case "<":
			oms.setCd("relation1");
			oms.setName("lt");
			break;

		case "<=":
			oms.setCd("relation1");
			oms.setName("leq");
			break;

		case ">":
			oms.setCd("relation1");
			oms.setName("gt");
			break;

		case ">=":
			oms.setCd("relation1");
			oms.setName("geq");
			break;

		case "=":
			oms.setCd("relation1");
			oms.setName("eq");
			break;

		case "==":
			oms.setCd("logic1"); // equivalent "==" as "≡"
			oms.setName("equivalent");
			break;

		case "!=":
			oms.setCd("relation1");
			oms.setName("neq");
			break;

		case "&&":
			oms.setCd("logic1");
			oms.setName("and");
			break;

		case "||":
			oms.setCd("logic1");
			oms.setName("or");
			break;
		}
		
		OMA oma = new OMA();
		oma.getOmel().add(oms);		//add OMS and children
		oma.getOmel().add(visit(ctx.getChild(0))); //left side
		oma.getOmel().add(visit(ctx.getChild(2))); // right side
		
		return oma;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Object visitFunction(EvaluatorParser.FunctionContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMF visitFloatValue(EvaluatorParser.FloatValueContext ctx) {
		OMF omf = new OMF();
		if (ctx.getText().startsWith("'")){  //delete ' at beginning and end if exists
			omf.setDec(Double.parseDouble(ctx.getText().substring(1, ctx.getText().length()-1))); 
		}else{
			omf.setDec(Double.parseDouble(ctx.getText()));
		}
		return omf;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Get the content of a ExerciseVariable(eg. [var=a]), which is wrapped in a OMOBJ-Object and returns the unwrapped content
	 * </p>
	 */
	@Override
	public Object visitExerciseVarName(EvaluatorParser.ExerciseVarNameContext ctx) throws UndefinedExerciseVariableException {
		String var = ctx.getText();	//eg. [var=a]
		String varName = var.substring(var.indexOf('=')+1,var.indexOf(']'));	//eg. a
		if(exerciseVariableMap.containsKey(varName)){
			OMOBJ varOmobj = exerciseVariableMap.get(varName);
			return OMConverter.toElement(varOmobj);	//removes the OMOBJ-tags and returns the child of the OMOBJ-Object
		}else{
			throw new UndefinedExerciseVariableException(varName);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMI visitIntegerValue(EvaluatorParser.IntegerValueContext ctx) {
		OMI omi = new OMI();
		if (ctx.getText().startsWith("'")){  //delete ' at beginning and end if exists
			omi.setValue(ctx.getText().substring(1, ctx.getText().length()-1)); 
		}else{
			omi.setValue(ctx.getText());
		}
		return omi;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * Gets the content of a FillInVariable(eg. [pos=1]), which is wrapped in an OMOBJ-Object and returns the unwrapped content.
	 * 
	 * </p>
	 */
	@Override
	public Object visitFillInVarName(EvaluatorParser.FillInVarNameContext ctx) {
		String var = ctx.getText();	//eg. [pos=1]
		int varNumber = Integer.parseInt(var.substring(var.indexOf('=')+1,var.indexOf(']')));	//eg. 1
		if(fillInVariableMap.containsKey(varNumber)){
			OMOBJ varOmobj = fillInVariableMap.get(varNumber);
			return OMConverter.toElement(varOmobj);	//removes the OMOBJ-tags and returns the child of the OMOBJ-Object
		}else{
			throw new UndefinedFillInVariableException(varNumber);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Object visitUnaryoperator(EvaluatorParser.UnaryoperatorContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public Object visitBinaryoperator(EvaluatorParser.BinaryoperatorContext ctx) {
		return visitChildren(ctx);
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>
	 * The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.
	 * </p>
	 */
	@Override
	public OMA visitNestedFunction(EvaluatorParser.NestedFunctionContext ctx) {
		OMS oms = new OMS();
		oms.setCd(FunctionFactory.getInstance().getFunctionContentDictionary(ctx.name.getText()));
		oms.setName(FunctionFactory.getInstance().getFunctionName(ctx.name.getText()));
		
		OMA oma = new OMA();
		oma.getOmel().add(oms);
		
		for (ExpressionContext childctx : ctx.arguments){
			oma.getOmel().add(visit(childctx));
		}
		return oma;
	}
}