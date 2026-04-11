package ast;

import java.util.Scanner;

public class NodeScan extends NodeBase {
    private Node arg;
    
    public NodeScan(Node arg) {
        super(NodeType.SCAN);
        this.arg = arg;
    }
    
    @Override
    public String toString(){
        return String.format("Scan(%s)", arg.toString());
    }

    //TODO: test
    @Override
    public Double eval(Program prog) {
        if(!(arg instanceof NodeIdentifier)) return Double.NaN; //TODO: test

        Scanner scanner = new Scanner(System.in);
        String valStr = scanner.nextLine();

        Double val = Double.parseDouble(valStr);
        prog.setVariable(((NodeIdentifier)arg).getName(), val);
        return val;
    }
}
