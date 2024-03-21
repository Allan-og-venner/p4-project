grammar Expr;

prog:   block EOF ;
block: statement
    | statement block;
statement:  decl
    | expr SEMICOLON
    | assign SEMICOLON
    | command SEMICOLON
    | control;
decl:   defin SEMICOLON
    | fdecl
    | cdecl;


defin:  modifier type IDENTIFIER EQ expr
    | modifier type IDENTIFIER L_BRACKET NUMERAL R_BRACKET EQ expr
    | modifier type IDENTIFIER;
assign: IDENTIFIER EQ expr;

/* Function rules*/
fdecl: modifier KEY_FUNC IDENTIFIER L_PAREN fparam R_PAREN KEY_RETURNTYPE_ARROW type L_CBRACKET block R_CBRACKET
    | modifier KEY_FUNC IDENTIFIER L_PAREN R_PAREN KEY_RETURNTYPE_ARROW type L_CBRACKET block R_CBRACKET;
fparam: type IDENTIFIER
    | type IDENTIFIER COMMA fparam;
aparam: expr
    | expr COMMA aparam;
cdecl: KEY_CLASS IDENTIFIER L_CBRACKET block R_CBRACKET
    | KEY_CLASS IDENTIFIER KEY_EXTENDS IDENTIFIER L_CBRACKET block '}';
call: IDENTIFIER'('aparam')'
    | IDENTIFIER'('')';

/*  Control command rules */
command: KEY_RETURN expr
    | KEY_BREAK
    | KEY_CONTINUE;
control: loop
    | ifthen;
loop: KEY_LOOP KEY_WHILE '('expr')' '{'block'}'
    | KEY_LOOP KEY_FOR IDENTIFIER KEY_IN IDENTIFIER '{'block'}';
ifthen: KEY_IF '('expr')' '{'block'}';

/* Expression rules */
expr: logic
    | relation
    | arith
    | value;
logic:  relation '&&' logic
    | relation '||' logic
    | relation; //Logical expressions
relation: arith '<' relation
    | arith '<=' relation
    | arith '==' relation
    | arith '>=' relation
    | arith '>' relation
    | arith '!=' relation
    | arith; //Relational expressions

/* Arimatic rules */
arith:  term '+' arith
    | term '-' arith
    | term;
term:   factor '*' term
    | factor '/' term
    | factor '%' term
    | factor;
factor: '('expr')'
    | value
    | '!'factor;

/* Value rules */
value:  NUMERAL
    | FLOAT
    | IDENTIFIER
    | call
    | array
    | string
    | char
    | classAccess;
acessibleValue: IDENTIFIER
    | call
    | arrayAccess;
values: value',' values
    | value;

/* Type rules*/
type:   TYPE_INT
    | TYPE_FLOAT
    | TYPE_CHAR
    | TYPE_STRING
    | TYPE_VOID
    | IDENTIFIER;
modifier : KEY_STATIC | ;

/* String rules */
char:   '\''CHAR'\'';
string: '"'STRING'"' | '"''"';

/* Array rules*/
array:  '{''}' | '{'values'}';
arrayAccess: IDENTIFIER'['expr']';

/* Class rules */
classAccess: acessibleValue('.'call |'.'IDENTIFIER)+;


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
PERIODE: '.';

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

/* Types */
TYPE_INT : 'int';
TYPE_FLOAT : 'float';
TYPE_CHAR : 'char';
TYPE_STRING : 'string';
TYPE_VOID : 'void';
CHAR: '\''.'\'';
STRING: '"'.*?'"';
NUMERAL     :  [+-]?[0-9]+ ;
FLOAT : ((NUMERAL?)'.'([0-9]*))
        |(NUMERAL'.'([0-9]*?));
IDENTIFIER : [a-zA-Z][a-zA-Z0-9_-]*;


/* Extra */
BLOCK_COMMENT: '/' .? '/' -> channel(HIDDEN); // Hides comments from compiler
WS: [ \t]+ -> skip ; // toss out whitespace
NEWLINE: [\r\n]+ -> skip;// toss out newlines

