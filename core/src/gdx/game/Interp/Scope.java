
package gdx.game.Interp;

import java.util.*;

import gdx.game.Interp.node.*;

public class Scope {

    private Scope parent;

    private Map<String, Type> declaredVariables = new HashMap<>();

    public Scope() {

    }

    public Scope(
            Scope parentScope) {

        this.parent = parentScope;
    }

    public void addDecl(
            TIdent ident,
            Type type) {

        String name = ident.getText();
        if (alreadyDeclared(name)) {
            throw new SemanticException(ident,
                    "variable " + name + " is already declared");
        }
        this.declaredVariables.put(name, type);
    }

    private boolean alreadyDeclared(
            String name) {

        if (this.declaredVariables.containsKey(name)) {
            return true;
        }
        if (this.parent != null) {
            return this.parent.alreadyDeclared(name);
        }
        return false;
    }

    public Type getType(
            TIdent ident) {

        String name = ident.getText();
        if (this.declaredVariables.containsKey(name)) {
            return this.declaredVariables.get(name);
        }
        if (this.parent != null) {
            return this.parent.getType(ident);
        }
        throw new SemanticException(ident,
                "variable " + name + " is not declared");
    }

    public Scope getParent() {

        return this.parent;
    }
}
