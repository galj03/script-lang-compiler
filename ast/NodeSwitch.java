package ast;

import java.util.ArrayList;

public class NodeSwitch extends NodeBase {
    private Node switchValue;
    private ArrayList<NodeCaseStmt> caseStatements;
    private ArrayList<Node> defaultStatements; //optional

    public NodeSwitch(Node switchValue) {
        super(NodeType.SWITCH);
        this.switchValue = switchValue;
    }

    public void addCaseStatement(NodeCaseStmt caseStmt) {
        if (caseStatements == null) {
            caseStatements = new ArrayList<>();
        }
        caseStatements.add(caseStmt);
    }

    public void setDefaultStatement(Node defaultCase) {
        if(defaultStatements == null) {
            defaultStatements = new ArrayList<>();
        }
        this.defaultStatements.add(defaultCase);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Switch(%s):\n", switchValue.toString()));
        for (NodeCaseStmt caseStmt : caseStatements) {
            sb.append(caseStmt.toString());
        }
        if (defaultStatements != null) {
            sb.append("Default:\n");
            for (Node stmt : defaultStatements) {
                sb.append(stmt.toString());
                sb.append("\n\n");
            }
        }

        return sb.toString();
    }

    @Override
    public Double eval(Program prog) {
        Double switchVal = switchValue.eval(prog);
        
        for(NodeCaseStmt caseStmt : caseStatements) {
            if (caseStmt.matchesCaseValue(prog, switchVal)) {
                return caseStmt.eval(prog);
            }
        }

        if (defaultStatements != null) {
            Double result = 0.0;
            for (Node stmt : defaultStatements) {
                result = stmt.eval(prog);
            }
            return result;
        }

        return 1.0;
    }
}
