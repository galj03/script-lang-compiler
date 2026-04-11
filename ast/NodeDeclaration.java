package ast;

public class NodeDeclaration extends NodeBase {
    private String name;
    private VariableType variableType;

    public NodeDeclaration(String name, VariableType variableType) {
        super(NodeType.DECLARATION);
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
        return String.format("Declaration(%s, %s)", name, variableType.toString());
    }

    @Override
    public Double eval(Program prog) {
        if(prog.hasVariable(name)){
            System.err.println(String.format("Variable %s already exists", name));
            return Double.NaN;
        }
        
        prog.setVariable(name, 0.0, variableType);
        return prog.getVariable(name);
    }
}
