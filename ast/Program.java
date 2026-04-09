package ast;

import java.util.ArrayList;

public class Program {
    private ArrayList<Node> statements;

    //TODO (enhancement): generalize this to a single map with a wrapper class for the value and type
    private Map<VariableType, Map<String, Double>> variables;
    //private Map<String, Double> doubleValues;
    //private Map<String, Integer> intValues;

    public Double getVariable(String name, VariableType type) {
        // if (VariableType.DOUBLE == type) {
        //     return doubleValues.get(name);
        // } else if (VariableType.INT == type) {
        //     return intValues.get(name);
        // }
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            return typeVariables.get(name);
        }
        return null;
    }

    //TODO: test for integers
    public void setVariable(String name, VariableType type, Double value) {
        // if (VariableType.DOUBLE == type) {
        //     this.doubleValues.put(name, value);
        // } else if (VariableType.INT == type) {
        //     this.intValues.put(name, value);
        // }
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            typeVariables.put(name, value);
        }
    }

    public boolean hasVariable(String name, VariableType type) {
        // if (VariableType.DOUBLE == type) {
        //     return this.doubleValues.containsKey(name);
        // } else if (VariableType.INT == type) {
        //     return this.intValues.containsKey(name);
        // }
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            return typeVariables.containsKey(name);
        }
        return false;
    }

    public void removeVariable(String name, VariableType type) {
        // if (VariableType.DOUBLE == type) {
        //     this.doubleValues.remove(name);
        // } else if (VariableType.INT == type) {
        //     this.intValues.remove(name);
        // }
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            typeVariables.remove(name);
        }
    }

    public Program(){
        this.statements = new ArrayList<>();
        this.variables = new HashMap<>();
    }

    public void addStatement(Node node){
        statements.add(node);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Node node : statements) {
            sb.append(node.toString());
            sb.append("\n\n");
        }

        return sb.toString();
    }

    public void run(){
        for (Node n : statements) {
            System.out.printf("Value: %f\n", n.eval(this));
        }
    }
}
