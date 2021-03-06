lexer grammar EvaluatorLexer;

@lexer::header {package de.uni_due.s3.evaluator2.parser.antlr;
}

LeftParenthesis: '(';
RightParenthesis: ')';
ListOpen: '{';
ListClose: '}';
Circumflex: '^';
Plus: '+';
Minus: '-';
BooleanNot:	'!';
Multiplication:	'*';
Division: '/';
Modulus: '%';
LessThan: '<';
LessThanOrEqual: '<=';
GreaterThan: '>';
GreaterThanOrEqual: '>=';
Equal: '==' | '=';
NotEqual: '!=';
BooleanAnd:	'&&';
BooleanOr: '||';
ExerciseVariable: '[var=' [a-zA-Z_öÖäÄüÜ] [a-zA-Z0-9_öÖäÄüÜ]* ']';
FillInVariable: '[pos=' [0-9]+ ']';
Variable: ('a'..'z');
True: 'True' | 'TRUE';
False: 'False' | 'FALSE';
FunctionName: [a-zA-Z] [a-zA-Z0-9]*;
ArgumentSeparator: ',';
ListArgumentSeparator: ';';
Integer: [0-9]+;
Float: [0-9]+( '.' [0-9]+);
fragment EscApos: '\\' '\'';
String: '\'' (EscApos | .)*? '\'';
WS: [ \r\t\n] -> skip;
