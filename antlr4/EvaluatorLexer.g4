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
ExerciseVariable: '[var=' [a-zA-Z_öÖäÄüÜ] [a-zA-Z0-9_öÖäÄüÜ]* ']';
FillInVariable: '[pos=' [0-9]+ ']';
StandardVariable: '#{' [a-zA-Z] [a-zA-Z0-9]* '}';
FunctionName: [a-zA-Z] [a-zA-Z0-9]*;
Quote: '\'';
ArgumentSeparator: ',';
Integer: [0-9]+ | '\'' [0-9]+ '\'';
Float: [0-9]+( '.' [0-9]+) | ( '\'' [0-9]+( '.' [0-9]+) '\'' );
String: '\'' .*? '\'' ;
WS: [ \r\t\n] -> skip;