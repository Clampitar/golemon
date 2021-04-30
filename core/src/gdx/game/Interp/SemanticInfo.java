package gdx.game.Interp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdx.game.Interp.node.*;

public class SemanticInfo {
    public void addScope(Node node, Scope scope){
    	this.scopes.put(node, scope);
    }

    public Scope getScope(){
        return new Scope();//TODO implement
    }
    
    private FunctionTable functionTable = new FunctionTable();

    private Map<Node, Scope> scopes = new HashMap<>();

    private Map<Node, Integer> numbers = new HashMap<>();

    private Map<Node, Type> types = new HashMap<>();

    public void addFunDecl(
            AFunDecl declaration,
            List<ParamInfo> paramList,
            Type returnType) {

        this.functionTable.addFunDecl(declaration, paramList, returnType);
    }

    public FunctionInfo getFunInfo(
            String funName) {

        return this.functionTable.getFunInfo(funName);
    }

    public void addInteger(
            AIntegerTerm node,
            int number) {

        this.numbers.put(node, number);
    }

    public int getInteger(
            AIntegerTerm node) {

        return this.numbers.get(node).intValue();
    }

    public void addType(
            Node node,
            Type type) {

        this.types.put(node, type);
    }

    public Type getType(
            Node node) {

        return this.types.get(node);
    }
}
