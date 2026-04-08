package ast;

public class NodeConstant extends NodeBase {
    private double numberValue;

    public NodeConstant(String value) {
        super(NodeType.CONSTANT);
        numberValue = Double.parseDouble(value);
    }
    
    @Override
    public String toString(){
        return String.format("Constant(%f)", numberValue);
    }

    @Override
    public Double eval(Program prog) {
        return numberValue;
    }
}
