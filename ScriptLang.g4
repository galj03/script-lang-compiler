grammar ScriptLang;

options {
    language = Java;
}

// java -jar antlr-4.11.1-complete.jar ScriptLang.g4
// javac -cp .:antlr-4.11.1-complete.jar *.java
// java -cp .:antlr-4.11.1-complete.jar ScriptLangParser input.txt

//@header {
//    import ast.*;
//}

@members {
    public static void main(String[] args) throws Exception {
        ScriptLangLexer lex = new ScriptLangLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream (lex);
        ScriptLangParser parser = new ScriptLangParser(tokens);

        /*ast.Program prog = new ast.Program();
        parser.start(prog);
        prog.run();*/
    }
}

//TODO: put these together!!!
start
    //: ( (line)? COMMENT? LF )* EOF
    : (stmt)* EOF
    ;

stmt
    : 'print' '(' expr ')' SC
    | 'scan' '(' expr ')' SC
    | TYPE VARNAME SC
    | 'del' VARNAME SC
    | expr SC
    //TODO: vezerlesi szerkezetek - for, while, if, switch-case
    ;


// TODO: RTL associative!!!
expr
    : fstop=assignop (OPASSIGN nxtop=assignop)*
    ;

// TODO: RTL associative!!!
assignop
    : fstop=ternaryop ('?' ternaryop ':' ternaryop)*
    ;

ternaryop
    : fstop=eqop (OPEQ nxtop=eqop)*
    ;

eqop
    : fstop=relop (OPREL nxtop=relop)*
    ;

relop
    : fstop=addop (OPADD nxtop=addop)*
    ;

addop
    : fstop=fct (OPMUL nxtop=fct)*
    ;

fct
    : SZAM
    | VARNAME
    | 'TIME'
    | '(' expr ')'
    | 'abs' '(' expr ')'
    ;

SC       : ';' ;
LF       : '\n' ->skip ;
WS       : [ \t\r]+ ->skip ;
SZAM     : [0-9]+('.' [0-9]+)? ;
TYPE     : 'int' | 'double' ;
VARNAME  : [a-zA-Z]+ ; //if a used keyword is given, fail!!!
OPASSIGN : '=' ;
OPEQ     : '==' | '!=' ;
OPREL    : '<' | '>' ;
OPADD    : '+' | '-' ;
OPMUL    : '*' | '/' ;
COMMENT  : '#' (~[\n])* '\n' ->skip ; //TODO: test if commenting breaks the code
