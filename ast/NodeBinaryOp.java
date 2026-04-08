package ast;

public class NodeBinaryOp extends NodeBase {
    private String opType;
    private Node lhs;
    private Node rhs;
    
    public NodeBinaryOp(String opType, Node lhs, Node rhs) {
        super(NodeType.BINARY_OP);
        this.opType = opType;
        this.lhs = lhs;
        this.rhs = rhs;
    }
    
    @Override
    public String toString(){
        return String.format("BinaryOp(%s, %s, %s)", opType, lhs.toString(), rhs.toString());
    }

    @Override
    public Double eval(Program prog) {
        Double lhsval = lhs.eval(prog);
        Double rhsval = rhs.eval(prog);

        switch (opType) {
            case "+":
                return lhsval + rhsval;
            case "-":
                return lhsval - rhsval;
            case "*":
                return lhsval * rhsval;
            case "/":
                    if(rhsval == 0){
                        System.err.println("Warning: division by zero");
                        return Double.NaN;
                    }
                    return lhsval / rhsval;
            case "==":
                return lhsval.equals(rhsval) ? 1.0 : 0.0;
            case "!=":
                return !lhsval.equals(rhsval) ? 1.0 : 0.0;
            case "<":
                return lhsval < rhsval ? 1.0 : 0.0;
            case "<=":
                return lhsval <= rhsval ? 1.0 : 0.0;
            case ">":
                return lhsval > rhsval ? 1.0 : 0.0;
            case ">=":
                return lhsval >= rhsval ? 1.0 : 0.0;
            default:
                return Double.NaN;
        }
    }
}
