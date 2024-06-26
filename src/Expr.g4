grammar Expr;

/* Program rules*/
prog: block EOF
    | EOF ;
block: statement
    | statement block
    | KEY_SETUP COLON block;

/* Statement rules*/
statement: decl
    | call SEMICOLON
    | method SEMICOLON
    | assign SEMICOLON
    | command SEMICOLON
    | control;
decl: defin SEMICOLON
    | fdecl
    | cdecl
    | cardType;
defin: modifier type IDENTIFIER (L_BRACKET R_BRACKET)? (EQ expr)?;
assign: value EQ expr;

cardType : KEY_CARDTYPE L_PAREN (IDENTIFIER EQ STRING)? (COLON (cardMethod| cardField))* R_PAREN;
cardMethod : IDENTIFIER L_PAREN fparams? R_PAREN KEY_RETURNTYPE_ARROW type L_CBRACKET block R_CBRACKET;
cardField : type IDENTIFIER;

/* Function rules*/
fdecl: modifier KEY_FUNC IDENTIFIER L_PAREN fparams? R_PAREN KEY_RETURNTYPE_ARROW type L_CBRACKET block R_CBRACKET
    |  KEY_ACTION IDENTIFIER L_PAREN fparams? R_PAREN KEY_RETURNTYPE_ARROW type COLON expr L_CBRACKET block R_CBRACKET;
fparams: fparam COMMA fparams
    | fparam;
fparam: type IDENTIFIER;
cdecl: KEY_CLASS IDENTIFIER L_CBRACKET block R_CBRACKET
    | KEY_CLASS IDENTIFIER KEY_EXTENDS IDENTIFIER L_CBRACKET block R_CBRACKET;
call: KEY_NEW? IDENTIFIER L_PAREN exprs? R_PAREN;

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
term: term MULT factor
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
    | access;
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
modifier : KEY_STATIC?;

access: accessibleObject accessing+;
accessing: (L_BRACKET expr R_BRACKET | PERIOD accessibleValue);

/* Array rules*/
array: L_CBRACKET R_CBRACKET | L_CBRACKET exprs R_CBRACKET;
arrayAccess: IDENTIFIER L_BRACKET expr R_BRACKET;

method: accessibleObject(PERIOD accessibleValue)* PERIOD call;
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
KEY_CARDTYPE: 'cardType';
KEY_SETUP: 'setup';

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

