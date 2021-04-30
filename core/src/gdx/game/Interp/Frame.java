package gdx.game.Interp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import gdx.game.Interp.node.*;


public class Frame {

    protected Frame parentFrame;
    protected Map<String, Value> variables = new HashMap<>();
    private Value returnValue;
    private FunctionInfo functionInfo;    
    protected Iterator<PInst> iterator;

    /* Localisation de l'appel d'une fonction */
    private Token location;

    /* Constructeur pour le programme principal */
    public Frame(LinkedList<PInst> insts) {
    	this.iterator = insts.iterator();
    }
    
    public Frame(Frame parentFrame) {
    	this.parentFrame = parentFrame;
    }
    
    /* Construction pour les appels de fonction */
    public Frame(
            Frame parentFrame,
            FunctionInfo functionInfo,
            Token location) {

        this.parentFrame = parentFrame;
        this.functionInfo = functionInfo;
        this.location = location;
    }

    /* Construction pour les appels de fonction */
    public Frame(
            Frame parentFrame,
            FunctionInfo functionInfo,
            Token location,
            LinkedList<PInst> insts) {

        this.parentFrame = parentFrame;
        this.functionInfo = functionInfo;
        this.location = location;
        this.iterator = insts.iterator();
    }

    public void putVariable(
            TIdent ident,
            Value value) {

        String varName = ident.getText();
        this.variables.put(varName, value);
    }

    public Value getVariable(
            TIdent ident) {

        String varName = ident.getText();
        return this.variables.get(varName);
    }

    public Value getReturnValue() {

        return this.returnValue;
    }

    public Frame getParentFrame() {

        return this.parentFrame;
    }

    public Object getFunctionInfo() {

        return this.functionInfo;
    }

    public void setReturnValue(
            Value value) {

        this.returnValue = value;
    }

    public void setLocation(
            Token location) {

        this.location = location;
    }

    public String getName() {

        if (this.functionInfo == null) {
            return "programme principal";
        }

        return this.functionInfo.getName();
    }

    public int getLine() {

        return this.location.getLine();
    }

    public int getPos() {

        return this.location.getPos();
    }
    
    public Boolean hasNext() {
    	return iterator.hasNext();
    }
    
    public PInst next() {
    	return iterator.next();
    }
    
    public void iterate(LinkedList<PInst> insts) {
    	this.iterator = insts.iterator();
    }


}
