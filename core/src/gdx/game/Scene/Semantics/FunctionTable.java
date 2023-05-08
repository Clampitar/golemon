package gdx.game.Scene.Semantics;

import java.util.*;

import gdx.game.Scene.node.*;

public class FunctionTable {

    private Map<String, FunctionInfo> functions = new HashMap<>();

    public void addFunDecl(
            AFunDecl declaration,
            List<ParamInfo> paramList,
            Type returnType) {

        String name = declaration.getName().getText();

        // Est-ce que la fonction est déjà déclarée
        if (this.functions.get(name) != null) {
            throw new SemanticException(declaration.getName(),
                    "Function " + name + " has already been declared");
        }

        FunctionInfo functionInfo
                = new FunctionInfo(declaration, paramList, returnType);
        this.functions.put(name, functionInfo);
    }

    public FunctionInfo getFunInfo(
            String funName) {

        return this.functions.get(funName);
    }


}
