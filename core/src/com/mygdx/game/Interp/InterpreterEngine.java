
package com.mygdx.game.Interp;

import java.util.*;

import com.mygdx.game.Interp.analysis.*;
import com.mygdx.game.Interp.node.*;

public class InterpreterEngine
        extends DepthFirstAdapter {

    private Map<String, Value> variables = new HashMap<>();

    private Value result;

    @Override
    public void caseAWhileInst(
            AWhileInst node) {

        while (true) {

            Value value = eval(node.getExp());

            if (!(value instanceof BoolValue)) {
                throw new InterpreterException(node.getWhile(),
                        "expression is not a boolean");
            }

            if (!((BoolValue) value).getValue()) {
                // sortir de la boucle
                break;
            }

            // executer l'instruction
            node.getInst().apply(this);
        }

    }

    @Override
    public void caseAIfElseInst(
            AIfElseInst node) {

        Value value = eval(node.getExp());

        if (!(value instanceof BoolValue)) {
            throw new InterpreterException(node.getIf(),
                    "expression is not a boolean");
        }

        if (((BoolValue) value).getValue()) {
            // executer le then
            node.getThenInst().apply(this);
        }
        else {
            // executer le else
            node.getElseInst().apply(this);
        }
    }

    @Override
    public void caseAPrintlnInst(
            APrintlnInst node) {

        System.out.println();
    }

    @Override
    public void caseAPrintExpInst(
            APrintExpInst node) {

        Value value = eval(node.getExp());
        System.out.print(value);
    }

    @Override
    public void caseADeclInst(
            ADeclInst node) {

        Value value = eval(node.getExp());
        String varName = node.getIdent().getText();
        this.variables.put(varName, value);
    }

    @Override
    public void caseAAssignInst(
            AAssignInst node) {

        Value value = eval(node.getExp());
        String varName = node.getIdent().getText();
        this.variables.put(varName, value);
    }

    @Override
    public void caseAEqExp(
            AEqExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        if (leftValue instanceof StringValue) {

            if (!(rightValue instanceof StringValue)) {
                throw new InterpreterException(node.getEq(),
                        "right value is not a string");
            }

            this.result = new BoolValue(((StringValue) leftValue).getValue()
                    .equals(((StringValue) rightValue).getValue()));
        }
        else if (leftValue instanceof IntValue) {

            if (!(rightValue instanceof IntValue)) {
                throw new InterpreterException(node.getEq(),
                        "right value is not a number");
            }

            this.result = new BoolValue(((IntValue) leftValue)
                    .getValue() == ((IntValue) rightValue).getValue());
        }
        else {
            // booléens
            if (!(rightValue instanceof BoolValue)) {
                throw new InterpreterException(node.getEq(),
                        "right value is not a boolean");
            }

            this.result = new BoolValue(((BoolValue) leftValue)
                    .getValue() == ((BoolValue) rightValue).getValue());
        }
    }

    @Override
    public void caseALtExp(
            ALtExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        if (!(leftValue instanceof IntValue)) {
            throw new InterpreterException(node.getLt(),
                    "left value is not a number");
        }

        if (!(rightValue instanceof IntValue)) {
            throw new InterpreterException(node.getLt(),
                    "right value is not a number");
        }

        this.result = new BoolValue(((IntValue) leftValue)
                .getValue() < ((IntValue) rightValue).getValue());
    }

    @Override
    public void caseASubAdditiveExp(
            ASubAdditiveExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        if (!(leftValue instanceof IntValue)) {
            throw new InterpreterException(node.getMinus(),
                    "left value is not a number");
        }

        if (!(rightValue instanceof IntValue)) {
            throw new InterpreterException(node.getMinus(),
                    "right value is not a number");
        }

        this.result = new IntValue(((IntValue) leftValue).getValue()
                - ((IntValue) rightValue).getValue());
    }

    @Override
    public void caseAAddAdditiveExp(
            AAddAdditiveExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        if (leftValue instanceof StringValue
                || rightValue instanceof StringValue) {

            this.result = new StringValue(
                    leftValue.toString() + rightValue.toString());
            return;
        }

        if (!(leftValue instanceof IntValue)) {
            throw new InterpreterException(node.getPlus(),
                    "left value is not a number");
        }

        if (!(rightValue instanceof IntValue)) {
            throw new InterpreterException(node.getPlus(),
                    "right value is not a number");
        }

        this.result = new IntValue(((IntValue) leftValue).getValue()
                + ((IntValue) rightValue).getValue());
    }

    private Value eval(
            Node node) {

        node.apply(this);
        return this.result;
    }

    @Override
    public void caseANumberTerm(
            ANumberTerm node) {

        try {
            int number = Integer.parseInt(node.getNumber().getText());
            this.result = new IntValue(number);
        }
        catch (NumberFormatException e) {
            throw new InterpreterException(node.getNumber(),
                    "Number is invalid");
        }
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        String varName = node.getIdent().getText();

        if (this.variables.containsKey(varName)) {
            this.result = this.variables.get(varName);
        }
        else {
            throw new InterpreterException(node.getIdent(),
                    "Variable " + varName + " is not defined");
        }
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.result = new BoolValue(true);
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.result = new BoolValue(false);
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        String string = node.getString().getText();
        // enlever les double guillemets
        string = string.substring(1, string.length() - 1);

        this.result = new StringValue(string);
    }
}
