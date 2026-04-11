package ast;

public class NodeUnaryOp extends NodeBase {
    private Node arg;
    private String opType;
        
    public NodeUnaryOp(String opType, Node arg) {
        super(NodeType.UNARY_OP);
        this.opType = opType;
        this.arg = arg;
    }
    
    @Override
    public String toString(){
        return String.format("Unary(%s, %s)", opType, arg.toString());
    }

    @Override
    public Double eval(Program prog) {
        if(!"+".equals(opType) && !"-".equals(opType)){
            System.err.println(String.format("Unsupported unary operator: %s", opType));
            return Double.NaN;
        }

        Double argVal = arg.eval(prog);
        return "+".equals(opType) ? argVal : -argVal;
    }
}
