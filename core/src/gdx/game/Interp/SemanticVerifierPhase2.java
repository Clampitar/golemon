
package gdx.game.Interp;

import gdx.game.Interp.analysis.*;
import gdx.game.Interp.node.*;

public class SemanticVerifierPhase2
        extends DepthFirstAdapter {

    private Scope currentScope;

    private Type resultType;
    
    private SemanticInfo semantics;
    
    private FunctionInfo currentFunctionInfo;
    
    public SemanticVerifierPhase2(SemanticInfo semantics) {
		this.semantics = semantics;
	}
    
    private void visit(
            Node node) {

        if (node != null) {
            node.apply(this);
        }
    }

    private Type evalType(
            Node node) {

        node.apply(this);
        return this.resultType;
    }

    @Override
    public void caseAProg(
            AProg node) {

        this.currentScope = new Scope();
        
        for (PFunDecl pFunDecl : node.getFunDecls()) {
        	visit(pFunDecl);
        }
        
        this.currentScope = new Scope(this.currentScope);
        this.semantics.addScope(node, this.currentScope);
        
        for(PInst inst : node.getInsts()) {
        	visit(inst);
        }
        
        this.currentScope = this.currentScope.getParent();
    }
    
    @Override
    public void caseAFunDecl(AFunDecl node) {
    	this.currentScope = new Scope(this.currentScope);
    	semantics.addScope(node, currentScope);
    	
    	this.currentFunctionInfo = this.semantics.getFunInfo(node.getName().getText());
    	this.currentFunctionInfo.addParamsToScope(this.currentScope);
    	visit(node.getFunBody());
    	
    	this.currentFunctionInfo = null;
    	this.currentScope = this.currentScope.getParent();
    }

    @Override
    public void inABlockInst(
            ABlockInst node) {

        this.currentScope = new Scope(this.currentScope);
        this.semantics.addScope(node, currentScope);
    }

    @Override
    public void outABlockInst(
            ABlockInst node) {

        this.currentScope = this.currentScope.getParent();
    }

    @Override
    public void caseADeclAssigner(
            ADeclAssigner node) {

        Type type = evalType(node.getExp());
        this.currentScope.addDecl(node.getIdent(), type);
    }

    @Override
    public void caseAAssignAssigner(
            AAssignAssigner node) {

        Type expType = evalType(node.getExp());
        Type varType = this.currentScope.getType(node.getIdent());
        if (expType != varType) {
            throw new SemanticException(node.getAssign(),
                    "the expression can't be assigned in this variable");
        }
    }

    @Override
    public void caseAIfInst(
            AIfInst node) {

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

    @Override
    public void caseAWhileInst(
            AWhileInst node) {

        Type type = evalType(node.getExp());

        if (type != Type.BOOL) {
            throw new SemanticException(node.getWhile(),
                    "expression is not a boolean");
        }

        this.currentScope = new Scope(this.currentScope);
        node.getWhileBody().apply(this);
        this.currentScope = this.currentScope.getParent();
    }
    
    @Override
    public void caseAForInst(
            AForInst node) {

        Type type = evalType(node.getCond());
        
        if (type != Type.BOOL) {
            throw new SemanticException(node.getFirstSc(),
                    "expression is not a boolean");
        }
        
        node.getDecl();

        this.currentScope = new Scope(this.currentScope);
        node.getWhileBody().apply(this);
        this.currentScope = this.currentScope.getParent();
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
    public void caseAIntegerTerm(AIntegerTerm node) {
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

        numbersOnly(leftType, rightType, node.getLt());

        this.resultType = Type.BOOL;
    }
    
    @Override
    public void caseAGtExp(AGtExp node) {
    	Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        numbersOnly(leftType, rightType, node.getGt());

        this.resultType = Type.BOOL;
    }
    
    @Override
    public void caseAMultMultExp(AMultMultExp node) {
    	Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        numbersOnly(leftType, rightType, node.getMult());

        this.resultType = Type.BOOL;
    }
    
    @Override
    public void caseAModuloMultExp(AModuloMultExp node) {
    	Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        numbersOnly(leftType, rightType, node.getModulo());

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
    
    public void numbersOnly(Type leftType, Type rightType, Token token) {
    	if (leftType != Type.INT) {
            throw new SemanticException(token,
                    "left operand is not a number");
        }

        if (rightType != Type.INT) {
            throw new SemanticException(token,
                    "right operand is not a number");
        }
    }
}
