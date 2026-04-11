package ast;

public class NodeIdentifier extends NodeBase {
    private String name;

    public NodeIdentifier(String name) {
        super(NodeType.IDENTIFIER);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Identifier(%s)", name);
    }

    @Override
    public Double eval(Program prog) {
        if(!prog.hasVariable(name)){
            System.err.println(String.format("<NodeIdentifier> Variable %s does not exist", name));
            return Double.NaN;
        }

        return prog.getVariable(name);
    }
}
