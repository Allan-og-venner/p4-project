grammar Kirsten;
prog:   expr EOF ;
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
term:   factor '*' term | factor '/' term | factor '%' term;
factor: '('expr')' | value | '!'factor;
value:  NUMERAL | FLOAT | IDENTIFIER;

IDENTIFIER : [a-zA-Z][a-zA-Z0-9_-]*;
NEWLINE : [\r\n]+ -> skip;
NUMERAL     :  [+-]?[0-9]+ ;
FLOAT : ((NUMERAL?)'.'([0-9]*))
        |(NUMERAL'.'([0-9]*?));
