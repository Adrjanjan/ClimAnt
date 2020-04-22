grammar ClimAnt ;

@header {
package pl.edu.agh.climant;
}

compilationUnit : classDeclaration EOF ;

classDeclaration : accessModifier CLASS NL* identifier LEFT_BRACE classBody RIGHT_BRACE ;

accessModifier
    : PRIVATE
//    | PROTECTED # TODO jak robimy dziedziczenie
    | PUBLIC ;

classBody : field* constructor? method* ;

field : accessModifier SIMPLE_IDENTIFIER;

constructor : CONSTRUCTOR  methodParameters methodBody;

method : accessModifier FUNCTION identifier methodParameters methodBody;

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
    :  BOOL ('[' ']')*
    | STRING ('[' ']')*
    | CHAR ('[' ']')*
    | INT ('[' ']')*
    | REAL ('[' ']')*
    | VOID
    ;
classType : identifier ('[' ']')* ;

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

booleanExpression :
    | expression cmp=GT expression
    | expression cmp=LT expression
    | expression cmp=EQUAL expression
    | expression cmp=NOTEQUAL expression
    | expression cmp=GE expression
    | expression cmp=LE expression
    | expression cmp=AND expression
    | expression cmp=OR expression
    | expression cmp=XOR expression
    | cmp=NOT expression
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
//    | printStatement
    | forStatement
    | returnStatement
    | ifStatement
    | expression
    ;

ifStatement :  IF LEFT_PARENTHESIS booleanExpression RIGHT_PARENTHESIS trueStatement=statement (ELSE falseStatement=statement)?;

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
NL : '\n' | '\r' | [\r\n] ;

//constants
fragment BlockComment : '/*' .*? ('*/' | EOF) ;
fragment LineComment : '//' ~ [\r\n]* ;
fragment BoolLiterals : 'true' | 'false' ;
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
   | '0' .. '9'
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

// Bool Literals

BoolLiteral : BoolLiterals;

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
identifier : Identifier | 'to' | 'module' | 'open' | 'with' | 'provides' | 'uses' | 'opens' | 'requires' | 'exports';

Identifier : Letter LetterOrDigit*  ;

fragment
Letter : [a-zA-Z_] ;

fragment
LetterOrDigit : [a-zA-Z0-9_] ;

// Whitespace and comments
WS : [ \t\r\n\u000C]+ -> skip ;

COMMENT : '/*' .*? '*/' -> channel(HIDDEN) ;

LINE_COMMENT : '//' ~[\r\n]* -> channel(HIDDEN) ;