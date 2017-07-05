parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	nestedFunction
	| LeftParenthesis expression RightParenthesis
	| unaryoperator expression
	| expression binaryoperator expression
	| set
	| terminal
;

terminal
:
	value = Integer # integerValue
	| value = Float # floatValue
	| name = ExerciseVariable # exerciseVarName
	| name = FillInVariable # fillInVarName
	| value = String # textValue
;

unaryoperator
:
	operator =
	(
		Plus
		| Minus
		| BooleanNot
	)
;

binaryoperator
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