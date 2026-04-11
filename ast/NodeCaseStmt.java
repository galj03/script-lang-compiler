package ast;

import java.util.ArrayList;

public class NodeCaseStmt extends NodeBase {
    private Node caseValue;
    private ArrayList<Node> statements;
    private boolean hasBreak;

    public NodeCaseStmt(Node caseValue, boolean hasBreak) {
        super(NodeType.CASESTMT);
        statements = new ArrayList<>();
        this.caseValue = caseValue;
        this.hasBreak = hasBreak;
    }
    
    public void addStatement(Node node){
        statements.add(node);
    }

    public boolean hasBreak() {
        return hasBreak;
    }

    public boolean matchesCaseValue(Program prog, Double switchValue) {
        Double caseVal = caseValue.eval(prog);
        return caseVal.equals(switchValue);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("CaseStmt(%s, hasBreak=%b):\n", caseValue.toString(), hasBreak));
        for (Node node : statements) {
            sb.append(node.toString());
            sb.append("\n\n");
        }

        return sb.toString();
    }

    @Override
    public Double eval(Program prog) {
        Double result = 0.0;
        for (Node stmt : statements) {
            result = stmt.eval(prog);
        }
        return result;
    }
}
