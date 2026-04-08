package ast;

public interface Node {
    public NodeType getType();
    public String toString();

    public Double eval(Program prog);
}
