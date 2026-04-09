package ast;

public class NodeFor extends NodeBase {
    private Node condition;
    private NodeBlockStmt body;
    private Node initialization;
    private Node increment;

    public NodeFor(Node condition, NodeBlockStmt body) {
        super(NodeType.FOR);
        this.condition = condition;
        this.body = body;
    }

    public void setInitialization(Node initialization) {
        this.initialization = initialization;
    }

    public void setIncrement(Node increment) {
        this.increment = increment;
    }

    @Override
    public String toString() {
        return String.format("For(%s, %s)", condition.toString(), body.toString());
    }

    @Override
    public Double eval(Program prog) {
        Double result = 1.0;
        if (initialization != null) {
            result = initialization.eval(prog);
        }

        while (condition.eval(prog) != 0.0) {
            result = body.eval(prog);
            
            if (increment != null) {
                increment.eval(prog);
            }
        }

        return result;
    }
}
