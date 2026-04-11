package ast;

public class NodeDelete extends NodeBase {
    private String name;

    public NodeDelete(String name) {
        super(NodeType.DELETE);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Delete(%s)", name);
    }

    @Override
    public Double eval(Program prog) {
        if(!prog.hasVariable(name)){
            System.err.println(String.format("<NodeDelete> Variable %s does not exist", name));
            return Double.NaN;
        }
        
        prog.removeVariable(name);
        return 1.0;
    }
}
