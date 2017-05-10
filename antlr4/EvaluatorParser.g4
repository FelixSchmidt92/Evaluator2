parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	nestedFunction	#function
	| LeftParenthesis expression RightParenthesis #parentheses
	| operator = unaryoperator expression #unaryOperator
	| expression operator = binaryoperator expression #binaryOperator
	| value = Integer #integerValue
	| value = Float  #floatValue
	| name = ExerciseVariable #exerciseVarName
	| name = FillInVariable  #fillInVarName
    | value = String #textValue
;

unaryoperator
:
	Plus
	| Minus
	| BooleanNot
;

binaryoperator
:
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