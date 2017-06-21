lexer grammar SageSyntaxLexer;

LEFTBRACKET		: '[';
RIGHTBRACKET	: ']';
LEFTPARENTHESIS	: '(';
RIGHTPARENTHESIS	: ')';
ARGSEPERATOR	: ',';
ADD                : '+';
DIV                : '/';
MUL                : '*';
SUB                : '-';
SPACE			   : ' ';
NEWLINE			   : '\n';

// Actual lexer rules


FLOAT              : DIGIT+ '.' DIGIT*
                   | '.' DIGIT+
                   ;

INT                : DIGIT+;

TRUE	: 'True';
FALSE	: 'False';

                   
fragment DIGIT     : '0'..'9'
                   ;