package ast;

public abstract class NodeBase implements Node {
    private NodeType type;

    public NodeBase(NodeType type){
        this.type = type;
    }

    @Override
    public NodeType getType() {
        return type;
    }

    @Override
    public String toString(){
        return "$<NODE TOSTRING INVALID>";
    }

    @Override
    public abstract Double eval(Program prog);
}
