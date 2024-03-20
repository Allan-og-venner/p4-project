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
arith:  term '+' arith | term '-' arith;
term:   ;
factor:   ;
value:  ;

IDENTIFIER : [a-zA-Z][a-zA-Z0-9_-]*;
NEWLINE : [\r\n]+ -> skip;
NUMERAL     :  [+-]?[0-9]+ ;
FLOAT : (NUMERAL)?.[0-9]*|(NUMERAL.([0-9]*)?);
