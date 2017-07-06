parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	nestedFunction # nestedFunctionInExpression
	| LeftParenthesis expression RightParenthesis # parenthesis
	| unaryOperatorForExpression expression # unary
	| expression binaryOperatorForExpression expression # binary
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

binaryOperatorForExpression
:
	operator =
	(
		Multiplication
		| Division
		| Modulus
		| Plus
		| Minus
		| LessThan
		| LessThanOrEqual
		| GreaterThan
		| GreaterThanOrEqual
		| Equal
		| NotEqual
		| BooleanAnd
		| BooleanOr
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