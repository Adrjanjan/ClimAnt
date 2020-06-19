grammar ClimAnt ;

@header {
package pl.edu.agh.climant;
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// PARSER RULES //////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

compilationUnit : classDeclaration EOF ;

classDeclaration : accessModifier CLASS identifier LEFT_BRACE NL* classBody NL* RIGHT_BRACE NL* ;

accessModifier
    : PRIVATE
    | PUBLIC
    ;

classBody : field* constructor? method* ;

field : accessModifier type SIMPLE_IDENTIFIER;

constructor : accessModifier CONSTRUCTOR methodParameters methodBody;

methodSignature: accessModifier type identifier methodParameters;

method : methodSignature methodBody;

methodBody : block;

methodParameters : LEFT_PARENTHESIS parameter (',' parameter)* RIGHT_PARENTHESIS
          |  LEFT_PARENTHESIS parameter (',' parameter)* (',' parameterWithDefaultValue)* RIGHT_PARENTHESIS
          |  LEFT_PARENTHESIS parameterWithDefaultValue (',' parameterWithDefaultValue)* RIGHT_PARENTHESIS
          |  LEFT_PARENTHESIS RIGHT_PARENTHESIS
          ;

parameter: type identifier;
parameterWithDefaultValue: type identifier ASSIGN expression;

type : primitiveType
     | classType ;

primitiveType
    : BOOL (LEFT_BRACKET RIGHT_BRACKET)*
    | STRING (LEFT_BRACKET RIGHT_BRACKET)*
    | CHAR (LEFT_BRACKET RIGHT_BRACKET)*
    | INT (LEFT_BRACKET RIGHT_BRACKET)*
    | REAL (LEFT_BRACKET RIGHT_BRACKET)*
    | VOID
    ;

classType : identifier (LEFT_BRACKET RIGHT_BRACKET)* ;

expression
    : identifier #VarReference
    | owner=expression DOT identifier LEFT_PARENTHESIS argumentList RIGHT_PARENTHESIS #FunctionCall
    | identifier LEFT_PARENTHESIS argumentList RIGHT_PARENTHESIS #FunctionCall
    | newCall= identifier LEFT_PARENTHESIS argumentList RIGHT_PARENTHESIS #ConstructorCall
    | value #ValueExpr
    | LEFT_PARENTHESIS expression MUL expression RIGHT_PARENTHESIS #Multiply
    | expression MUL expression  #Multiply
    | LEFT_PARENTHESIS expression DIV expression RIGHT_PARENTHESIS #Divide
    | expression DIV expression #Divide
    | LEFT_PARENTHESIS expression ADD expression RIGHT_PARENTHESIS #Add
    | expression ADD expression #Add
    | LEFT_PARENTHESIS expression SUB expression RIGHT_PARENTHESIS #Substract
    | expression SUB expression #Substract
    | LEFT_PARENTHESIS expression MOD expression RIGHT_PARENTHESIS #Modulo
    | expression MOD expression #Modulo
    | LEFT_PARENTHESIS expression POWER expression RIGHT_PARENTHESIS #Power
    | expression POWER expression #Power
    | expression cmp=GT expression #ConditionalExpression
    | expression cmp=LT expression #ConditionalExpression
    | expression cmp=EQUAL expression #ConditionalExpression
    | expression cmp=NOTEQUAL expression #ConditionalExpression
    | expression cmp=GE expression #ConditionalExpression
    | expression cmp=LE expression #ConditionalExpression
    | expression cmp=AND expression #ConditionalExpression
    | expression cmp=OR expression #ConditionalExpression
    | expression cmp=XOR expression #ConditionalExpression
    | cmp=NOT expression #ConditionalExpression
    ;

value
    : NumberLiteral
    | BoolLiteral
    | StringLiteral;

argumentList
    : expression (COMMA expression)*
    | expression?
    ;

block : LEFT_BRACE statement* RIGHT_BRACE;

statement
    : block
    | assignment
    | variableDeclaration
    | printStatement
    | forStatement
    | returnStatement
    | ifStatement
    | expression
    ;

printStatement : 'print' expression;

ifStatement :  IF LEFT_PARENTHESIS expression RIGHT_PARENTHESIS trueStatement=statement (ELSE falseStatement=statement)?;

returnStatement: RETURN expression;

forStatement : FOR LEFT_PARENTHESIS forConditions RIGHT_PARENTHESIS statement ;

forConditions : iterator=identifier IN startExpr=expression range=RANGE endExpr=expression ;

assignment: identifier ASSIGN expression ; // do isteniejącej zmiennej

variableDeclaration // deklaracja
    : type identifier ASSIGN expression
    | type identifier
    ;

identifier : SIMPLE_IDENTIFIER (NL* DOT SIMPLE_IDENTIFIER)* ;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////// LEXER RULES //////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

//Keywords - some are reserved for later usage
BOOL : 'bool' ;
BREAK : 'break' ;
CASE : 'case' ;
CHAR : 'char' ;
CLASS : 'class' ;
CONST : 'const' ;
CONSTRUCTOR : 'constructor' ;
CONTINUE : 'continue' ;
DO : 'do' ;
REAL : 'real' ;
ELSE : 'else' ;
FOR : 'for' ;
FUNCTION : 'fun' ;
IF : 'if' ;
IMPORT : 'import' ;
IN : 'in' ;
INT : 'int' ;
LONG : 'long' ;
NEW : 'new' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
PUBLIC : 'public' ;
RETURN : 'return' ;
STATIC : 'static' ;
STRING : 'string';
SWITCH : 'switch' ;
THIS : 'this' ;
VOID : 'void' ;
WHILE : 'while' ;
NL : '\n' | '\r' | '\r\n' ;
LEFT_PARENTHESIS : '(' ;
RIGHT_PARENTHESIS : ')' ;
LEFT_BRACE : '{' ;
RIGHT_BRACE : '}' ;
LEFT_BRACKET : '[' ;
RIGHT_BRACKET : ']' ;
COMMA : ',' ;
DOT : '.' ;
RANGE : '..' ;

//constants
fragment DQuote : '"' ;
fragment Minus : '-' ;
fragment FloatingPointSeparator : '.';


// Integer literals
NumberLiteral :IntegerLiteral | FloatingPointLiteral;

IntegerLiteral : Minus? DecimalNumeral ;

fragment DecDigit : [0-9] ;
fragment DecimalNumeral
   : '0'
   | [1-9] DecDigit* ;

// Float literals

FloatingPointLiteral : Minus? DecimalNumeral FloatingPointSeparator DecimalNumeral? ;

// String Literals

StringLiteral : DQuote ~('\n' | '\r' | '"')* DQuote ;

// Bool Literals

BoolLiteral :  'true' | 'false';

// Operators
ASSIGN : '=' ;
INC : '++' ;
DEC : '--' ;

// Boolean
GT : '>' ;
LT : '<' ;
NOT : '!' | 'not' ;
EQUAL : '==' ;
LE : '<=' ;
GE : '>=' ;
NOTEQUAL : '!=' ;
AND : '&&' | 'and' ;
OR : '||' | 'or' ;
XOR : '^' | 'xor' ;

// Math
ADD : '+' ;
SUB : '-' ;
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;

// Just In Case
POWER : '**' ;
QUESTION : '?' ;
COLON : ':' ;

BITAND : '&' ;
BITOR : '|' ;

ADD_ASSIGN : '+=' ;
SUB_ASSIGN : '-=' ;
MUL_ASSIGN : '*=' ;
DIV_ASSIGN : '/=' ;

LSHIFT_ASSIGN : '<<=' ;
RSHIFT_ASSIGN : '>>=' ;

// Identifiers
SIMPLE_IDENTIFIER : LETTER LETTER_OR_DIGIT*  ;
fragment LETTER : [a-zA-Z_] ;
fragment LETTER_OR_DIGIT : [a-zA-Z0-9_] ;

// Whitespace and comments
WS : [ \t\r\n\u000C]+ -> skip ;
COMMENT : '/*' .*? ('*/')  -> channel(HIDDEN) ;
LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN) ;