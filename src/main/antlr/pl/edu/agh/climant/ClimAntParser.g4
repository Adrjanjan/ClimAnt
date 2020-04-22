parser grammar ClimAntParser;

//TODO packages/imports

classDeclaration
    : modifierList CLASS NL*
    (NL* ) ;

modifierList
    : PRIVATE
    | PROTECTED
    | PUBLIC ;

//literal
//    : IntegerLiteral
//    | FloatingPointLiteral
//    | StringLiteral
//    | BoolLiteral
//    ;
///////////////
//moduleName
//	:	identifier
//	|	moduleName '.' identifier
//	;
//
//packageName
//	:	identifier
//	|	packageName '.' identifier
//	;
//
//typeName
//	:	identifier
//	|	packageOrTypeName '.' identifier
//	;
//
//packageOrTypeName
//	:	identifier
//	|	packageOrTypeName '.' identifier
//	;
//
//expressionName
//	:	identifier
//	|	ambiguousName '.' identifier
//	;
//
//methodName
//	:	identifier
//	;
//
//ambiguousName
//	:	identifier
//	|	ambiguousName '.' identifier
//	;
//
////TODO parser