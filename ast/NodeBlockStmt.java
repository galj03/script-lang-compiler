package ast;

import java.util.ArrayList;

public class NodeBlockStmt extends NodeBase {
    private ArrayList<Node> statements;

    public NodeBlockStmt() {
        super(NodeType.BLOCKSTMT);
        statements = new ArrayList<>();
    }
    
    public void addStatement(Node node){
        statements.add(node);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("BlockStmt:\n");
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
