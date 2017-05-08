parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	nestedFunction	#function
	| LeftParenthesis expression RightParenthesis #parentheses
	| unaryoperator expression #unaryOperator
	| expression binaryoperator expression #binaryOperator
	| integerNumber #integerNum
	| floatNumber  #floatNum
	| exerciseVar #exerciseVariable
	| fillInVar  #fillInVariable
	| Text #text
;

integerNumber
:
	Integer
	| (Quote Integer Quote)
;
floatNumber
:
	Float | (Quote Float Quote) 
;

exerciseVar
:
	ExerciseVariable| (Quote ExerciseVariable Quote) 
;

fillInVar
:
	FillInVariable| (Quote FillInVariable Quote)
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
    name = FunctionName FunctionOpen
	(
		arguments += expression
		(
			ArgumentSeparator arguments += expression
		)* 
	)? FunctionClose
;