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
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("For(cond:%s):\n", condition.toString()));
        if(initialization != null){
            sb.append(String.format("Initialization: %s\n", initialization.toString()));
        }
        if(increment != null){
            sb.append(String.format("Increment: %s\n", increment.toString()));
        }
        sb.append(String.format("Body:\n%s\n", body.toString()));
        return sb.toString();
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
