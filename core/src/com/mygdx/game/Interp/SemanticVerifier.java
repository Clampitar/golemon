
package com.mygdx.game.Interp;

import com.mygdx.game.Interp.analysis.*;
import com.mygdx.game.Interp.node.*;

public class SemanticVerifier
        extends DepthFirstAdapter {

    private Scope currentScope;

    private Type resultType;

    private Type evalType(
            Node node) {

        node.apply(this);
        return this.resultType;
    }

    @Override
    public void inAProg(
            AProg node) {

        this.currentScope = new Scope();
    }

    @Override
    public void inABlockInst(
            ABlockInst node) {

        this.currentScope = new Scope(this.currentScope);
    }

    @Override
    public void outABlockInst(
            ABlockInst node) {

        this.currentScope = this.currentScope.getParent();
    }

    @Override
    public void caseADeclInst(
            ADeclInst node) {

        Type type = evalType(node.getExp());
        this.currentScope.addDecl(node.getIdent(), type);
    }

    @Override
    public void caseAAssignInst(
            AAssignInst node) {

        Type expType = evalType(node.getExp());
        Type varType = this.currentScope.getType(node.getIdent());
        if (expType != varType) {
            throw new SemanticException(node.getAssign(),
                    "the expression can't be assigned in this variable");
        }
    }

    @Override
    public void caseAIfElseInst(
            AIfElseInst node) {

        Type type = evalType(node.getExp());

        if (type != Type.BOOL) {
            throw new SemanticException(node.getIf(),
                    "expression is not a boolean");
        }

        // scope spécifique au then
        this.currentScope = new Scope(this.currentScope);
        node.getThenPart().apply(this);
        this.currentScope = this.currentScope.getParent();

        // scope spécifique au else
        this.currentScope = new Scope(this.currentScope);
        node.getElsePart().apply(this);
        this.currentScope = this.currentScope.getParent();
    }

  /*  @Override
    public void caseAWhileInst(
            AWhileInst node) {

        Type type = evalType(node.getExp());

        if (type != Type.BOOL) {
            throw new SemanticException(node.getWhile(),
                    "expression is not a boolean");
        }

        this.currentScope = new Scope(this.currentScope);
        node.getInst().apply(this);
        this.currentScope = this.currentScope.getParent();
    }
*/
    @Override
    public void caseAPrintExpInst(
            APrintExpInst node) {

        // vérifier l'expression
        evalType(node.getExp());
    }

    @Override
    public void caseATrueTerm(
            ATrueTerm node) {

        this.resultType = Type.BOOL;
    }

    @Override
    public void caseAFalseTerm(
            AFalseTerm node) {

        this.resultType = Type.BOOL;
    }

    @Override
    public void caseANumberTerm(
            ANumberTerm node) {

        this.resultType = Type.INT;
    }

    @Override
    public void caseAStringTerm(
            AStringTerm node) {

        this.resultType = Type.STRING;
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        this.resultType = this.currentScope.getType(node.getIdent());
    }

    @Override
    public void caseAAddAdditiveExp(
            AAddAdditiveExp node) {

        Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        if (leftType == Type.STRING || rightType == Type.STRING) {
            this.resultType = Type.STRING;
            return;
        }

        if (leftType != Type.INT) {
            throw new SemanticException(node.getPlus(),
                    "left operand is not a number");
        }

        if (rightType != Type.INT) {
            throw new SemanticException(node.getPlus(),
                    "right operand is not a number");
        }

        this.resultType = Type.INT;
    }

    @Override
    public void caseASubAdditiveExp(
            ASubAdditiveExp node) {

        Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        if (leftType != Type.INT) {
            throw new SemanticException(node.getMinus(),
                    "left operand is not a number");
        }

        if (rightType != Type.INT) {
            throw new SemanticException(node.getMinus(),
                    "right operand is not a number");
        }

        this.resultType = Type.INT;
    }

    @Override
    public void caseALtExp(
            ALtExp node) {

        Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        if (leftType != Type.INT) {
            throw new SemanticException(node.getLt(),
                    "left operand is not a number");
        }

        if (rightType != Type.INT) {
            throw new SemanticException(node.getLt(),
                    "right operand is not a number");
        }

        this.resultType = Type.BOOL;
    }

    @Override
    public void caseAEqExp(
            AEqExp node) {

        Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        if (leftType != rightType) {
            throw new SemanticException(node.getEq(),
                    "operands are not of identical types");
        }

        this.resultType = Type.BOOL;
    }
}
