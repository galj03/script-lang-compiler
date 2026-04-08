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
        //lehetne switch case is
        if(!"+".equals(opType) && !"-".equals(opType))return Double.NaN;

        Double argVal = arg.eval(prog);
        return "+".equals(opType) ? argVal : -argVal;
    }
}
