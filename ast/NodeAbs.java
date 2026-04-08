package ast;

public class NodeAbs extends NodeBase {
    private Node arg;
    
    public NodeAbs(Node arg) {
        super(NodeType.ABS);
        this.arg = arg;
    }
    
    @Override
    public String toString(){
        return String.format("Abs(%s)", arg.toString());
    }

    @Override
    public Double eval(Program prog) {
        Double val = arg.eval(prog);
        
        return Math.abs(val);
    }
}
