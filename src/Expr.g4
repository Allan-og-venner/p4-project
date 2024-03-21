grammar Expr;
prog:   block EOF ;
block: statement | statement block;
statement:  decl | expr ';' | assign';' | command';' | control;
decl:   defin';' | fdecl | cdecl;


defin:  modifier type IDENTIFIER '=' expr
       | modifier type IDENTIFIER'['NUMERAL']' '=' expr
       | modifier type IDENTIFIER;
assign: IDENTIFIER '=' expr;


fdecl: modifier KEY_FUNC IDENTIFIER'('fparam')' '{'block'}' | modifier KEY_FUNC IDENTIFIER'('')' '{'block'}';
fparam: type IDENTIFIER | type IDENTIFIER',' fparam;
aparam: IDENTIFIER | IDENTIFIER',' aparam;

cdecl: KEY_CLASS IDENTIFIER '{'block'}' | KEY_CLASS IDENTIFIER KEY_EXTENDS IDENTIFIER '{'block'}';

call: IDENTIFIER'('aparam')' | IDENTIFIER'('')';

command: KEY_RETURN expr | KEY_BREAK | KEY_CONTINUE;
control: loop | ifthen;
loop:   KEY_LOOP KEY_WHILE '('expr')' '{'block'}'
        | KEY_LOOP KEY_FOR IDENTIFIER KEY_IN IDENTIFIER;
ifthen: KEY_IF '('expr')' '{'block'}';

expr:   logic | relation | arith | value;
logic:  relation '&&' logic | relation '||' logic | relation;
relation: arith '<' relation
        | arith '<=' relation
        | arith '==' relation
        | arith '>=' relation
        | arith '>' relation
        | arith '!=' relation
        | arith;
arith:  term '+' arith | term '-' arith | term;
term:   factor '*' term | factor '/' term | factor '%' term | factor;
factor: '('expr')' | value | '!'factor;

value:  NUMERAL | FLOAT | IDENTIFIER | call | array | string | char | fieldaccess;
array:  '{''}' | '{'values'}';
values: value',' values | value;
type:   TYPE_INT | TYPE_FLOAT | TYPE_CHAR | TYPE_STRING | IDENTIFIER;
modifier : MOD_STATIC | ;
char:   '\''CHAR'\'';
string: '"'STRING'"' | '"''"';
fieldaccess: IDENTIFIER'.'IDENTIFIER | IDENTIFIER'.'fieldaccess;

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

MOD_STATIC : 'static';

TYPE_INT : 'int';
TYPE_FLOAT : 'float';
TYPE_CHAR : 'char';
TYPE_STRING : 'string';

CHAR: '\''.'\'';
STRING: '"'CHAR+'"';

BLOCK_COMMENT
    : '/' .? '/' -> channel(HIDDEN);

WS : [ \t]+ -> skip ; // toss out whitespace
NEWLINE : [\r\n]+ -> skip;
NUMERAL     :  [+-]?[0-9]+ ;
FLOAT : ((NUMERAL?)'.'([0-9]*))
        |(NUMERAL'.'([0-9]*?));
IDENTIFIER : [a-zA-Z][a-zA-Z0-9_-]*;