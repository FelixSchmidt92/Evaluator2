parser grammar SageSyntaxParser;

options {
	tokenVocab = SageSyntaxLexer;
}

set
:

	LEFTPARENTHESIS
	((matrix
		| vector
		| simple_stmt
		)
		
	(ARGSEPERATOR
		(matrix
		| vector
		| simple_stmt
		)
		
	)* )?
	RIGHTPARENTHESIS
;

matrix
:
	matrixrow
	(
		NEWLINE matrixrow
	)* NEWLINE ?
;

matrixrow
:
	LEFTBRACKET SPACE* simple_stmt
	(
		SPACE+ simple_stmt
	)* SPACE* RIGHTBRACKET
;

vector
:
	RIGHTPARENTHESIS SPACE* simple_stmt
	(
		SPACE* ARGSEPERATOR SPACE* simple_stmt
	)* SPACE* LEFTPARENTHESIS
;


simple_stmt
:
	term
	|FLOAT
	|INT
	|TRUE
	|FALSE
;

term
:
	(FLOAT | INT)
	(
			SPACE ADD SPACE
			| SPACE SUB SPACE
			| MUL
			| DIV
	) (term | FLOAT | INT)
;