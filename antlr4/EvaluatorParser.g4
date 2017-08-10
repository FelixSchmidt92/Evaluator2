parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	name = Variable # variable
	|
	(
		name = FunctionName LeftParenthesis
		(
			arguments += expression
			(
				ArgumentSeparator arguments += expression
			)*
		)? RightParenthesis
	) # nestedFunctionInExpression
	| LeftParenthesis expression RightParenthesis # parenthesis
	| operator = unaryOperatorForExpression expression # unary
	| expression Circumflex expression # binaryCircumflex
	| expression operator = binaryOperatorArithPoint expression #
	binaryArithPoint
	| expression operator = binaryOperatorArithLine expression # binaryArithLine
	| expression operator = binaryOperatorRelational expression #
	binaryRelational
	| expression operator = binaryOperatorBoolean expression # binaryBoolean
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