lexer grammar EvaluatorLexer;

LeftParenthesis: '(';
RightParenthesis: ')';
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
ExerciseVariable: '[var=' [a-zA-Z_������] [a-zA-Z0-9_������]* ']';
FillInVariable: '[pos=' [0-9]+ ']';
StandardVariable: '#{' [a-zA-Z] [a-zA-Z0-9]* '}';
FunctionName: [a-zA-z] [a-zA-Z0-9]* ;
FunctionOpen: '(';
FunctionClose: ')';
ArgumentSeparator: ',';
Quote: '\'';
Integer: [0-9]+;
Float: [0-9]+( '.' [0-9]+);
Text: '\'' .*? '\'';
WS: [ \r\t\n] -> skip;









