grammar ScriptLang;

options {
    language = Java;
}

// works on Windows - for linux, try: .:antlr-4.11.1-complete.jar (without parentheses)
// java -jar antlr-4.11.1-complete.jar ScriptLang.g4
// javac -cp ".;antlr-4.11.1-complete.jar" *.java
// java -cp ".;antlr-4.11.1-complete.jar" ScriptLangParser input.txt

@header {
    import ast.*;
    import java.util.Stack;
    import java.util.LinkedList;
}

@members {
    public static void main(String[] args) throws Exception {
        ScriptLangLexer lex = new ScriptLangLexer(new ANTLRFileStream(args[0]));
        CommonTokenStream tokens = new CommonTokenStream (lex);
        ScriptLangParser parser = new ScriptLangParser(tokens);

        ast.Program prog = new ast.Program();
        parser.start(prog);
        prog.run();
        //System.out.println(prog.toString());
    }
}

start [ast.Program prog]
    : (stmt { $prog.addStatement($stmt.result); }
        )* EOF
    ;

stmt returns [ast.Node result]
    : 'print' '(' expr ')' SC   { $result = new ast.NodePrint($expr.result); }
    | 'scan' '(' expr ')' SC    { $result = new ast.NodeScan($expr.result); }
    | TYPE vn=VARNAME SC    {
                                ast.VariableType type = "int".equals($TYPE.text) ? ast.VariableType.INT : ast.VariableType.DOUBLE; 
                                $result = new ast.NodeDeclaration($vn.text, type); 
                            }
    | 'del' vn=VARNAME SC  { $result = new ast.NodeDelete($vn.text); }
    | expr SC   { $result = $expr.result; }
    | 'if' '(' cond=expr ')' then=blockstmt   { $result = new ast.NodeIf($cond.result, ((NodeBlockStmt)$then.result)); }
      ('elseif' '(' elifcnd=expr ')' elifbl=blockstmt  { ((NodeIf)$result).addElseIf($elifcnd.result, ((NodeBlockStmt)$elifbl.result)); }
      )*
      ('else' elsebl=blockstmt { ((NodeIf)$result).setElseBranch(((NodeBlockStmt)$elsebl.result)); }
      )?
    | 'while' '(' cond=expr ')' body=blockstmt    { $result = new ast.NodeWhile($cond.result, ((NodeBlockStmt)$body.result)); }
    |   { ast.Node initNode = null; ast.Node incNode = null; }
        'for' '(' (init=expr { initNode = $init.result; }
        )?
        ';' cnd=expr ';'
        (inc=expr   { incNode = $inc.result; }
        )?
        ')' body=blockstmt  {
                                $result = new ast.NodeFor($cnd.result, ((NodeBlockStmt)$body.result));
                                if(initNode != null) ((NodeFor)$result).setInitialization(initNode);
                                if(incNode != null) ((NodeFor)$result).setIncrement(incNode);
                            }
    | 'switch' '(' swval=expr ')' '{'   { $result = new ast.NodeSwitch($swval.result); }
      (cs=casestmt { ((NodeSwitch)$result).addCaseStatement(((NodeCaseStmt)$cs.result)); }
      )+
      ('default' ':' (stmt  { ((NodeSwitch)$result).setDefaultStatement($stmt.result); }
        )*)?
      '}'
    ;

blockstmt returns [ast.Node result]
    : '{'   { $result = new ast.NodeBlockStmt(); }
    (stmt   { ((NodeBlockStmt)$result).addStatement($stmt.result); }
    )* '}'
    ;

casestmt returns [ast.Node result]
    :   { LinkedList<Node> nodes = new LinkedList<>(); boolean hasBreak = false; }
    'case' cond=expr ':'
    (stmt   { nodes.add($stmt.result); }
    )*
    ('break' SC { hasBreak = true; }
    )?      {
                $result = new ast.NodeCaseStmt($cond.result, hasBreak);
                for (Node n : nodes) {
                    ((NodeCaseStmt)$result).addStatement(n);
                }
            }
    ;

expr returns [ast.Node result]
    :   { Stack<String> vars = new Stack<>(); }
        (v=VARNAME OPASSIGN { vars.push($v.text); }
        )+ val=assignop     {
                                $result = $val.result;
                                while(!vars.isEmpty()){
                                    String variable = vars.pop();
                                    $result = new ast.NodeAssignment(variable, $result);
                                }
                            }
    | fstop=assignop    { $result = $fstop.result; }
    ;

// TODO: RTL associative!!!
//TODO: allow nested ternary operators
assignop returns [ast.Node result]
    : cond=ternaryop    { $result = $cond.result; }
    ('?' optrue=ternaryop ':' opfalse=ternaryop { $result = new ast.NodeTernaryOp($cond.result, $optrue.result, $opfalse.result); }
    )?
    ;

ternaryop returns [ast.Node result]
    : fstop=eqop    { $result = $fstop.result; } 
    (OPEQ nxtop=eqop    { $result = new ast.NodeBinaryOp($OPEQ.text, $result, $nxtop.result); }
    )*
    ;

eqop returns [ast.Node result]
    : fstop=relop   { $result = $fstop.result; } 
    (OPREL nxtop=relop  { $result = new ast.NodeBinaryOp($OPREL.text, $result, $nxtop.result); }
    )*
    ;

relop returns [ast.Node result]
    : fstop=addop   { $result = $fstop.result; } 
    (OPADD nxtop=addop  { $result = new ast.NodeBinaryOp($OPADD.text, $result, $nxtop.result); }
    )*
    ;

addop returns [ast.Node result]
    : fstop=fct     { $result = $fstop.result; } 
    (OPMUL nxtop=fct    { $result = new ast.NodeBinaryOp($OPMUL.text, $result, $nxtop.result); }
    )*
    ;

fct returns [ast.Node result]
    : SZAM  { $result = new ast.NodeConstant($SZAM.text); }
    | v=VARNAME   { $result = new ast.NodeIdentifier($v.text); }
    | 'TIME'    { 
                    //Long unixTime = new Long(System.currentTimeMillis() / 1000L);
                    long unixTime = System.currentTimeMillis() / 1000L;
                    $result = new ast.NodeConstant(String.valueOf(unixTime));
                }
    | '(' expr ')'  { $result = $expr.result; }
    | 'abs' '(' expr ')'    { $result = new ast.NodeAbs($expr.result); }
    | OPADD fct { $result = new ast.NodeUnaryOp($OPADD.text, $fct.result); }
    ;

SC       : ';' ;
LF       : '\n' ->skip ;
WS       : [ \t\r]+ ->skip ;
SZAM     : [0-9]+('.' [0-9]+)? ;
TYPE     : 'int' | 'double' ;
VARNAME  : [a-zA-Z]+ ; //if a used keyword is given, fail!!!
OPASSIGN : '=' ;
OPEQ     : '==' | '!=' ;
OPREL    : '<' | '>' | '<=' | '>=' ;
OPADD    : '+' | '-' ;
OPMUL    : '*' | '/' ;
COMMENT  : '#' (~[\n])* '\n' ->skip ; //TODO: test if commenting breaks the code
