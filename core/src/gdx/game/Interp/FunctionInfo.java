package gdx.game.Interp;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


import gdx.game.Interp.node.*;

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

    public void checkArgTypes(
            List<Type> argTypes,
            Token location) {

        // it�rateur pour les arguments
        Iterator<Type> argTypesIterator = argTypes.iterator();
        // boucle sur les paramètres
        for (ParamInfo param : this.paramList) {

            if (argTypesIterator.hasNext()) {
                Type argType = argTypesIterator.next();
                if (argType != param.getType()) {
                    throw new SemanticException(location,
                            "invalid argument for the the "
                                    + param.getName().getText() + " parameter");
                }
            }
            else {
                throw new SemanticException(location,
                        "missing argument for the " + param.getName().getText()
                                + " parameter");
            }
        }

        // argument de trop?
        if (argTypesIterator.hasNext()) {
            throw new SemanticException(location, "too many arguments");
        }
    }

    public void assignArgs(
            List<Value> args,
            Frame frame,
            Token location) {

        // itérateur pour les arguments
        Iterator<Value> argsIterator = args.iterator();
        // boucle sur les paramètres
        for (ParamInfo param : this.paramList) {

            if (argsIterator.hasNext()) {
                Value arg = argsIterator.next();
                frame.putVariable(param.getName(), arg, frame);
            }
            else {
                throw new InterpreterException(location, frame,
                        "missing value for the " + param.getName().getText()
                                + " parameter");
            }
        }

        // argument de trop?
        if (argsIterator.hasNext()) {
            throw new InterpreterException(location, frame,
                    "too many arguments");
        }
    }

}
