package ast;

public class NodePrint extends NodeBase {
    private Node arg;
    
    public NodePrint(Node arg) {
        super(NodeType.PRINT);
        this.arg = arg;
    }
    
    @Override
    public String toString(){
        return String.format("Print(%s)", arg.toString());
    }

    @Override
    public Double eval(Program prog) {
        Double val = arg.eval(prog);
        System.out.println(val); //TODO: test
        return val;
    }
}
