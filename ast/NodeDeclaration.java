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
        if(prog.hasVariable(name, variableType)){
            System.err.println(String.format("Variable %s of type %s already exists", name, variableType.toString()));
            return Double.NaN;
        }
        
        prog.setVariable(name, variableType, 0.0);
        return prog.getVariable(name, variableType);
    }
}
