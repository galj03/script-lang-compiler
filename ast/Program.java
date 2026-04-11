package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Program {
    private ArrayList<Node> statements;

    private Map<VariableType, Map<String, Double>> variables;
    private Map<String, VariableType> variableTypes;

    public Double getVariable(String name) {
        VariableType type = variableTypes.get(name);
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            return typeVariables.get(name);
        }
        return null;
    }

    //TODO: test for integers
    //only for existing variables, otherwise it will throw an error
    public void setVariable(String name, Double value) {
        VariableType type = variableTypes.get(name);
        //System.out.println(String.format("<Program> Setting variable %s of type %s to value %f", name, type, value));
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            typeVariables.put(name, value);
        }
    }

    //for new variables
    public void setVariable(String name, Double value, VariableType type) {
        //System.out.println(String.format("<Program> Setting variable %s of type %s to value %f", name, type, value));
        variableTypes.put(name, type);
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            typeVariables.put(name, value);
        }
    }

    public boolean hasVariable(String name) {
        VariableType type = variableTypes.get(name);
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            //System.out.println(String.format("<Program> Checking if variable %s of type %s exists: %b", name, type, typeVariables.containsKey(name)));
            return typeVariables.containsKey(name);
        }
        return false;
    }

    public boolean hasVariable(String name, VariableType type) {
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            //System.out.println(String.format("<Program> Checking if variable %s of type %s exists: %b", name, type, typeVariables.containsKey(name)));
            return typeVariables.containsKey(name);
        }
        return false;
    }

    public void removeVariable(String name) {
        VariableType type = variableTypes.get(name);
        Map<String, Double> typeVariables = variables.get(type);
        if (typeVariables != null) {
            typeVariables.remove(name);
            variableTypes.remove(name);
        }
    }

    public Program(){
        this.statements = new ArrayList<>();
        this.variables = new HashMap<>();
        variables.put(VariableType.INT, new HashMap<>());
        variables.put(VariableType.DOUBLE, new HashMap<>());
        this.variableTypes = new HashMap<>();
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
            //System.out.printf("Value: %f\n", n.eval(this));
            n.eval(this);
        }
    }
}
