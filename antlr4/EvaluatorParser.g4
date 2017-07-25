parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	nestedFunction # nestedFunctionInExpression
	| LeftParenthesis expression RightParenthesis # parenthesis
	| unaryOperatorForExpression expression # unary
	| expression binaryOperatorArithPoint expression # binaryArithPoint
	| expression binaryOperatorArithLine expression # binaryArithLine
	| expression binaryOperatorRelational expression # binaryRelational
	| expression binaryOperatorBoolean expression # binaryBoolean
	| set # setInExpression
	| value = Integer # integerValue
	| value = Float # floatValue
	| name = ExerciseVariable # exerciseVarName
	| name = FillInVariable # fillInVarName
	| value = String # textValue
;

unaryOperatorForExpression
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

set
:
	SetOpen
	(
		arguments += expression
		(
			SetArgumentSeparator arguments += expression
		)*
	)? SetClose
;

nestedFunction
:
	name = FunctionName LeftParenthesis
	(
		arguments += expression
		(
			ArgumentSeparator arguments += expression
		)*
	)? RightParenthesis
;