package ast;

public class NodeAssignment extends NodeBase {
    private String name;
    private VariableType variableType;
    private Node arg;

    public NodeAssignment(String name, VariableType variableType, Node arg) {
        super(NodeType.ASSIGNMENT);
        this.name = name;
        this.variableType = variableType;
        this.arg = arg;
    }

    public String getName() {
        return name;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    @Override
    public String toString() {
        return String.format("Assignment(%s, %s, %s)", name, variableType.toString(), arg.toString());
    }

    @Override
    public Double eval(Program prog) {
        if(!prog.hasVariable(name, variableType)){
            System.err.println(String.format("Variable %s of type %s does not exist", name, variableType.toString()));
            return Double.NaN;
        }

        Double value = arg.eval(prog);
        prog.setVariable(name, variableType, value);
        return value;
    }
}
