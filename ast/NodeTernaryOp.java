package ast;

public class NodeTernaryOp extends NodeBase {
    private Node condition;
    private Node trueExpr;
    private Node falseExpr;

    public NodeTernaryOp(Node condition, Node trueExpr, Node falseExpr) {
        super(NodeType.TERNARY_OP);
        this.condition = condition;
        this.trueExpr = trueExpr;
        this.falseExpr = falseExpr;
    }

    @Override
    public String toString() {
        return String.format("TernaryOp(%s, %s, %s)", condition.toString(), trueExpr.toString(), falseExpr.toString());
    }

    @Override
    public Double eval(Program prog) {
        if (condition.eval(prog) != 0.0) {
            return trueExpr.eval(prog);
        } else {
            return falseExpr.eval(prog);
        }
    }
}