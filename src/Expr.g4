grammar Expr;

/* Program rules*/
prog: block EOF
    | EOF ;
block: statement
    | statement block;

/* Statement rules*/
statement:  decl
    | expr SEMICOLON
    | assign SEMICOLON
    | command SEMICOLON
    | control;
decl: defin SEMICOLON
    | fdecl
    | cdecl;
defin: modifier type IDENTIFIER EQ expr
    | modifier type IDENTIFIER L_BRACKET NUMERAL R_BRACKET EQ expr
    | modifier type IDENTIFIER
    | modifier type IDENTIFIER EQ KEY_NEW expr;
assign: value EQ expr;

/* Function rules*/
fdecl: modifier KEY_FUNC IDENTIFIER L_PAREN fparams? R_PAREN KEY_RETURNTYPE_ARROW type L_CBRACKET block R_CBRACKET
    | modifier KEY_ACTION IDENTIFIER L_PAREN fparams? R_PAREN KEY_RETURNTYPE_ARROW type COLON STRING L_CBRACKET block R_CBRACKET;
fparams: fparam COMMA fparams
    | fparam;
fparam: type IDENTIFIER;
cdecl: KEY_CLASS IDENTIFIER L_CBRACKET block R_CBRACKET
    | KEY_CLASS IDENTIFIER KEY_EXTENDS IDENTIFIER L_CBRACKET block R_CBRACKET;
call: IDENTIFIER L_PAREN exprs R_PAREN
    | IDENTIFIER L_PAREN R_PAREN;

/*  Control command rules */
command: KEY_RETURN expr
    | KEY_BREAK
    | KEY_CONTINUE;
control: loop
    | ifthen;
loop: KEY_LOOP KEY_WHILE L_PAREN expr R_PAREN L_CBRACKET block R_CBRACKET
    | KEY_LOOP KEY_FOR IDENTIFIER KEY_IN expr L_CBRACKET block R_CBRACKET;
ifthen: KEY_IF L_PAREN expr R_PAREN L_CBRACKET block R_CBRACKET;

/* Expression rules */
//Logical expressions
expr:  expr AND relation
    | expr OR relation
    | relation;
//Relational expressions
relation: relation LT arith
    | relation LTEQ arith
    | relation EQEQ arith
    | relation GTEQ arith
    | relation GT arith
    | relation NOTEQ arith
    | arith;

/* Arimatic rules */
arith: arith PLUS term
    | arith MINUS term
    | term;
term:   term MULT factor
    | term DIV factor
    | term MOD factor
    | factor;
factor: L_PAREN expr R_PAREN
    | value
    | NOT factor
    | MINUS factor
    | PLUS factor;

/* Value rules */
value: NUMERAL
    | FLOAT
    | IDENTIFIER
    | call
    | array
    | STRING
    | CHAR
    | classAccess;
accessibleObject: IDENTIFIER
    | call
    | arrayAccess;
exprs: expr COMMA exprs
    | expr;

/* Type rules*/
type: TYPE_INT
    | TYPE_FLOAT
    | TYPE_CHAR
    | TYPE_STRING
    | TYPE_VOID
    | IDENTIFIER;
modifier : KEY_STATIC | ;

/* Array rules*/
array: L_CBRACKET R_CBRACKET | L_CBRACKET exprs R_CBRACKET;
arrayAccess: IDENTIFIER L_BRACKET expr R_BRACKET;

/* Class rules */
classAccess: accessibleObject( PERIOD accessibleValue)*;
accessibleValue: call
    | IDENTIFIER;


/* Operators */
EQ: '=';
GT: '>';
LT: '<';
NOT: '!';
EQEQ: '==';
LTEQ: '<=';
GTEQ: '>=';
NOTEQ: '!=';
AND: '&&';
OR: '||';
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
MOD: '%';


/* Separaters */
L_PAREN: '(';
R_PAREN: ')';
L_CBRACKET: '{';
R_CBRACKET: '}';
L_BRACKET: '[';
R_BRACKET: ']';
SEMICOLON: ';';
COMMA: ',';
COLON: ':';
PERIOD: '.';

/* Keywords */
KEY_NEW: 'new';
KEY_CLASS : 'class';
KEY_FUNC : 'func';
KEY_EXTENDS : 'extends';
KEY_BREAK : 'break';
KEY_RETURN : 'return';
KEY_CONTINUE: 'continue';
KEY_IN: 'in';
KEY_FOR: 'for';
KEY_WHILE: 'while';
KEY_IF: 'if';
KEY_LOOP: 'loop';
KEY_STATIC : 'static';
KEY_RETURNTYPE_ARROW : '->';
KEY_NULL: 'null';
KEY_ACTION: 'action';

/* Types */
TYPE_INT : 'int';
TYPE_FLOAT : 'float';
TYPE_CHAR : 'char';
TYPE_STRING : 'string';
TYPE_VOID : 'void';
CHAR: '\''.?'\'';
STRING: '"'.*?'"';
NUMERAL     : [0-9]+ ;
FLOAT : ((NUMERAL?)'.'([0-9]*))
    |(NUMERAL'.'([0-9]*?));
IDENTIFIER : [a-zA-Z][a-zA-Z0-9_-]*;


/* Extra */
BLOCK_COMMENT: '/*' .*? '*/' -> channel(HIDDEN); // Hides comments from compiler
WS: [ \t]+ -> skip ; // toss out whitespace
NEWLINE: [\r\n]+ -> skip;// toss out newlines

