package ast;

public class NodeIf extends NodeBase {
    private Node condition;
    private NodeBlockStmt thenBranch;
    private Map<Node, NodeBlockStmt> elseIfBranches;
    private NodeBlockStmt elseBranch;

    public NodeIf(Node condition, NodeBlockStmt thenBranch) {
        super(NodeType.IF);
        this.condition = condition;
        this.thenBranch = thenBranch;
    }

    public void addElseIf(Node condition, NodeBlockStmt branch) {
        if (elseIfBranches == null) {
            elseIfBranches = new HashMap<>();
        }
        elseIfBranches.put(condition, branch);
    }

    public void setElseBranch(NodeBlockStmt elseBranch) {
        this.elseBranch = elseBranch;
    }

    @Override
    public String toString() {
        return String.format("If(%s, %s)", condition.toString(), thenBranch.toString());
        //TODO: add else if and else to string
    }

    @Override
    public Double eval(Program prog) {
        //if
        if(condition.eval(prog) != 0.0){
            return thenBranch.eval(prog);
        }
        
        //elseif
        for(Map.Entry<Node, NodeBlockStmt> elseIf : elseIfBranches.entrySet()){
            if(elseIf.getKey().eval(prog) != 0.0){
                return elseIf.getValue().eval(prog);
            }
        }

        //else
        if (elseBranch != null) {
            return elseBranch.eval(prog);
        }

        //ha nincs else
        return 1.0;
    }
}
