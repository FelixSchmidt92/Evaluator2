parser grammar EvaluatorParser;

@header {package de.uni_due.s3.evaluator2.parser.antlr;
}

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	name = Variable # variableInExpression
	| constant # constantInExpression
	| function # functionInExpression
	| LeftParenthesis expression RightParenthesis # parenthesisInExpression
	| unaryOperator expression # unaryOperatorInExpression
	| expression Circumflex expression # binaryOperatorCircumflexInExpression
	| expression binaryOperatorArithPoint expression #
	binaryOperatorArithPointInExpression
	| expression binaryOperatorArithLine expression #
	binaryOperatorArithLineInExpression
	| expression binaryOperatorRelational expression #
	binaryOperatorRelationalInExpression
	| expression binaryOperatorBoolean expression #
	binaryOperatorBooleanInExpression
	| list # listInExpression
	| value = Integer # integerValueInExpression
	| value = Float # floatValueInExpression
	| name = ExerciseVariable # exerciseVarNameInExpression
	| name = FillInVariable # fillInVarNameInExpression
	| value = String # textValueInExpression
;

unaryOperator
:
	operator =
	(
		Plus
		| Minus
		| BooleanNot
	)
;

binaryOperatorBoolean
:
	operator =
	(
		BooleanAnd
		| BooleanOr
	)
;

binaryOperatorRelational
:
	operator =
	(
		Equal
		| NotEqual
		| LessThan
		| LessThanOrEqual
		| GreaterThan
		| GreaterThanOrEqual
	)
;

binaryOperatorArithLine
:
	operator =
	(
		Plus
		| Minus
	)
;

binaryOperatorArithPoint
:
	operator =
	(
		Multiplication
		| Division
		| Modulus
	)
;

constant
:
	name =
	(
		True
		| False
		| E
		| I
		| Infinity
		| Pi
	)
;

function
:
	(
		name = FunctionName LeftParenthesis
		(
			arguments += expression
			(
				ArgumentSeparator arguments += expression
			)*
		)? RightParenthesis
	)
;

list
:
	ListOpen
	(
		arguments += expression
		(
			ListArgumentSeparator arguments += expression
		)*
	)? ListClose
;