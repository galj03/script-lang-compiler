package ast;

public class NodeAssignment extends NodeBase {
    private String name;
    private Node arg;

    public NodeAssignment(String name, Node arg) {
        super(NodeType.ASSIGNMENT);
        this.name = name;
        this.arg = arg;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Assignment(%s, %s)", name, arg.toString());
    }

    @Override
    public Double eval(Program prog) {
        if(!prog.hasVariable(name)){
            System.err.println(String.format("<NodeAssignment> Variable %s does not exist", name));
            return Double.NaN;
        }

        Double value = arg.eval(prog);
        prog.setVariable(name, value);
        return value;
    }
}
