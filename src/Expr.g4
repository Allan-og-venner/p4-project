grammar Expr;
prog:	expr EOF ;
expr:	expr ('*'|'/') expr
    |	expr ('+'|'-') expr
    |	INT
    |	'(' expr ')'
    ;
expr:expr + expr;


NEWLINE : [\r\n]+ -> skip;
INT     : [0-9]+ ;
