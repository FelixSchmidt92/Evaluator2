parser grammar EvaluatorParser;

options {
	tokenVocab = EvaluatorLexer;
}

expression
:
	name = nestedFunction	#function
	| LeftParenthesis expression RightParenthesis #parentheses
	| unaryoperator expression #unaryOperator
	| expression binaryoperator expression #muldiv
	| variable #variables
	| terminal #terminale
;

terminal
:
   integerNumber
   | floatNumber
   | String
;

integerNumber
:
	Integer | (Quote Integer Quote)
;
floatNumber
:
	Float | (Quote Float Quote)
;

variable
:
	exerciseVar | fillInVar
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
	Plus			#unaryPlus
	| Minus 		#unaryMinus
	| BooleanNot 	#unaryNot
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
	FunctionName FunctionOpen
	(
		arguments += expression
		(
			ArgumentSeparator arguments += expression
		)* 
	)?
	FunctionClose
;
