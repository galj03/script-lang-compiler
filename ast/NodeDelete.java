package ast;

public class NodeDelete extends NodeBase {
    private String name;
    private VariableType variableType;

    public NodeDelete(String name, VariableType variableType) {
        super(NodeType.DELETE);
        this.name = name;
        this.variableType = variableType;
    }

    public String getName() {
        return name;
    }

    public VariableType getVariableType() {
        return variableType;
    }

    @Override
    public String toString() {
        return String.format("Delete(%s, %s)", name, variableType.toString());
    }

    @Override
    public Double eval(Program prog) {
        if(!prog.hasVariable(name, variableType)){
            System.err.println(String.format("Variable %s of type %s does not exist", name, variableType.toString()));
            return Double.NaN;
        }
        
        prog.removeVariable(name, variableType);
        return 1.0;
    }
}
