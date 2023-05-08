package gdx.game.Scene.Semantics;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import gdx.game.Scene.Interpreter.Frame;
import gdx.game.Scene.Interpreter.Value;
import gdx.game.Scene.node.*;

public class FunctionInfo {

    private TIdent name;

    private PFunBody funBody;

    private TEnd end;

    private List<ParamInfo> paramList = new LinkedList<>();

    private Type returnType;

    public FunctionInfo(
            AFunDecl declaration,
            List<ParamInfo> paramList,
            Type returnType) {

        this.name = declaration.getName();
        this.funBody = declaration.getFunBody();
        this.end = declaration.getEnd();
        this.paramList = paramList;
        this.returnType = returnType;
    }

    public PFunBody getFunBody() {

        return this.funBody;
    }

    public String getName() {

        return this.name.getText();
    }

    public Token getEnd() {

        return this.end;
    }

    public List<ParamInfo> getParams() {

        return this.paramList;
    }

    public Type getReturnType() {

        return this.returnType;
    }

    public void addParamsToScope(
            Scope scope) {

        for (ParamInfo param : this.paramList) {

            scope.addDecl(param.getName(), param.getType());
        }
    }

    public void assignArgs(
            List<Value> args,
            Frame frame,
            Token location) {

        // iterateur pour les arguments
        Iterator<Value> argsIterator = args.iterator();
        // boucle sur les parametres
        for (ParamInfo param : this.paramList) {

            // La verification est faite dans semantingVerifier()
            Value arg = argsIterator.next();
            frame.putVariable(param.getName(), arg);

        }
    }

}
