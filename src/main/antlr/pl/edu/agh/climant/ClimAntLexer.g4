lexer grammar ClimAnt ;


//Lexer rules
//Keywords
BOOL : 'bool' ;
BREAK : 'break' ;
CASE : 'case' ;
CHAR : 'char' ;
CLASS : 'class' ;
CONST : 'const' ;
CONTINUE : 'continue' ;
DO : 'do' ;
REAL : 'real' ;
ELSE : 'else' ;
FOR : 'for' ;
IF : 'if' ;
GOTO : 'goto' ;
INT : 'int' ;
LONG : 'long' ;
NEW : 'new' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
PUBLIC : 'public' ;
RETURN : 'return' ;
STATIC : 'static' ;
SWITCH : 'switch' ;
THIS : 'this' ;
VOID : 'void' ;
WHILE : 'while' ;

//constants
fragment BlockComment : '/*' .*? ('*/' | EOF) ;
fragment LineComment : '//' ~ [\r\n]* ;
fragment BoolLiteral : 'true' | 'false' ;
fragment Int : 'int' ;
fragment Esc : '\\' ;
fragment Colon : ':' ;
fragment SQuote : '\'' ;
fragment DQuote : '"' ;
fragment LParen : '(' ;
fragment RParen : ')' ;
fragment LBrace : '{' ;
fragment RBrace : '}' ;
fragment LBrack : '[' ;
fragment RBrack : ']' ;
fragment Lt : '<' ;
fragment Gt : '>' ;
fragment Equal : '=' ;
fragment Question : '?' ;
fragment Star : '*' ;
fragment Plus : '+' ;
fragment Minus : '-' ;
fragment PlusAssign : '+=' ;
fragment Underscore : '_' ;
fragment Comma : ',' ;
fragment Semi : ' ;' ;
fragment Dot : '.' ;
fragment Range : '..' ;
fragment Pound : '#' ;
fragment Tilde : '~' ;
fragment At : '@' ;
fragment NameStartChar : [A-Za-z] ;
fragment NameChar
   : NameStartChar
   | '0' .. '9'         //TODO tu się może wypierdolić xD
   | Underscore ;
fragment NameChars : NameChar* ;


// Integer literals

IntegerLiteral : Minus? DecimalNumeral ;

fragment DecDigit : [0-9] ;
fragment DecimalNumeral
   : '0'
   | [1-9] DecDigit* ;

// Float literals

FloatingPointLiteral : Minus? DecimalNumeral Dot DecimalNumeral? ;

// String Literals

StringLiteral : DQuote NameChars? DQuote ;


// Operators

ASSIGN : '=' ;
GT : '>' ;
LT : '<' ;
BANG : '!' ;
TILDE : '~' ;
QUESTION : '?' ;
COLON : ':' ;
ARROW : '->' ;
EQUAL : '==' ;
LE : '<=' ;
GE : '>=' ;
NOTEQUAL : '!=' ;
AND : '&&' ;
OR : '||' ;
INC : '++' ;
DEC : '--' ;
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
BITAND : '&' ;
BITOR : '|' ;
CARET : '^' ;
MOD : '%' ;

ADD_ASSIGN : '+=' ;
SUB_ASSIGN : '-=' ;
MUL_ASSIGN : '*=' ;
DIV_ASSIGN : '/=' ;

LSHIFT_ASSIGN : '<<=' ;
RSHIFT_ASSIGN : '>>=' ;

// Identifiers
Identifier : Letter LetterOrDigit*  ;

fragment
Letter : [a-zA-Z_] ;

fragment
LetterOrDigit : [a-zA-Z0-9_] ;

// Whitespace and comments
WS : [ \t\r\n\u000C]+ -> skip ;

COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN) ;