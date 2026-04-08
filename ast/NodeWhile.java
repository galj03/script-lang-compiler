package ast;

public class NodeWhile extends NodeBase {
    private Node condition;
    private NodeBlockStmt body;

    public NodeWhile(Node condition, NodeBlockStmt body) {
        super(NodeType.WHILE);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format("While(%s, %s)", condition.toString(), body.toString());
    }

    @Override
    public Double eval(Program prog) {
        Double result = 1.0;
        while(condition.eval(prog) != 0.0){
            result = body.eval(prog);
        }

        return result;
    }
}
