
package gdx.game.Scene.Semantics;

import java.util.List;
import java.util.LinkedList;

import gdx.game.Scene.analysis.*;
import gdx.game.Scene.node.*;

public class SemanticVerifierPhase2
        extends DepthFirstAdapter {

    private Scope currentScope;

    private Type resultType;
    
    private SemanticInfo semantics;
    
    private FunctionInfo currentFunctionInfo = null;

    private class FunctionVerifier{
        public LinkedList<Type> args;
        public FunctionVerifier parent;

        public FunctionVerifier(FunctionVerifier parent){
            this.parent = parent;
            args = new LinkedList<>();
        }
    }

    private FunctionVerifier currentVerifier = null;
    
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
    public void caseAFunCallInst(AFunCallInst node) {
        this.currentVerifier = new FunctionVerifier(null);
    	FunctionInfo info = this.semantics.getFunInfo(node.getIdent().getText());
    	if(info == null)
    		throw new SemanticException(node.getLPar(), "function " +node.getIdent().getText() + " is not declared");
    	visit(node.getArgs());
    	compareArgs(info, node.getRPar());
    }
    
    @Override
    public void caseAFunCallTerm(AFunCallTerm node) {
        this.currentVerifier = new FunctionVerifier(this.currentVerifier);
    	FunctionInfo info = this.semantics.getFunInfo(node.getIdent().getText());
    	if(info == null)
    		throw new SemanticException(node.getLPar(), "function " +node.getIdent().getText() + " is not declared");
    	if(info.getReturnType() == Type.VOID)
    		throw new SemanticException(node.getLPar(), "void functions cannot be used as a term");
    	visit(node.getArgs());
    	compareArgs(info, node.getRPar());
        this.currentVerifier = this.currentVerifier.parent;
    }
    
    private void compareArgs(FunctionInfo funcInfo, Token token) {
    	List<ParamInfo> info = funcInfo.getParams();
        int nimArgrs = this.currentVerifier.args.size();
    	if(info.size() != this.currentVerifier.args.size())
    		throw new SemanticException(token, "expected "+info.size()+" arguments, got "+this.currentVerifier.args.size());
    	for(int i = 0; i < info.size(); i++)
    		if(info.get(i).getType() != this.currentVerifier.args.get(i))
    			throw new SemanticException(token, "expected " +info.get(i).getType()+", got "+this.currentVerifier.args.get(i));
    }
    
    @Override
    public void caseAReturnInst(AReturnInst node) {
    	Type type = Type.VOID;
    	if(node.getExp() != null)
    		type = evalType(node.getExp());
    	if(type != currentFunctionInfo.getReturnType())
    		throw new SemanticException(node.getSc(), 
    				"cannot return "+type+"; must return "+currentFunctionInfo.getReturnType());
    }
    
    @Override
    public void caseAArg(AArg node) {
    	
    	   this.currentVerifier.args.add(evalType(node.getExp()));
    }
    
    @Override
    public void caseAWalkInst(AWalkInst node) {
        this.currentVerifier = new FunctionVerifier(null);
		visit(node.getArgs());
		if(this.currentVerifier.args.size() != 2)
			throw new SemanticException(node.getWalk(), "native method walk must have exactly 2 arguments");
		if(this.currentVerifier.args.get(0) != Type.INT || this.currentVerifier.args.get(1) != Type.INT)
				throw new SemanticException(node.getWalk(), "both arguments of native method walk must be integers");
    }
    
    @Override
    public void caseAMoveCamInst(AMoveCamInst node) {
        this.currentVerifier = new FunctionVerifier(null);
		visit(node.getArgs());
		if(this.currentVerifier.args.size() != 2)
			throw new SemanticException(node.getMoveCam(), "native method moveCam must have exactly 2 arguments");
		if(this.currentVerifier.args.get(0) != Type.INT || this.currentVerifier.args.get(1) != Type.INT)
				throw new SemanticException(node.getMoveCam(), "both arguments of native method moveCam must be integers");
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
    public void caseAFrameAdvanceInst(AFrameAdvanceInst node) {
    	if(node.getExp() != null && evalType(node.getExp()) != Type.INT)
    		throw new SemanticException(node.getFrameAdvance(), "native method frameAdvance must have a integer argument");
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
        if(node.getElsePart() != null)
        	node.getElsePart().apply(this);
        this.currentScope = this.currentScope.getParent();
    }

    @Override
    public void caseAWhileInst(
            AWhileInst node) {

    	if(node.getExp() != null) {
    		Type type = evalType(node.getExp());
        	if (type != Type.BOOL) {
            	throw new SemanticException(node.getWhile(),
                    "expression is not a boolean");
        	}
    	}
        this.currentScope = new Scope(this.currentScope);
        node.getWhileBody().apply(this);
        this.currentScope = this.currentScope.getParent();
    }
    
    @Override
    public void caseAForInst(
            AForInst node) {
        this.currentScope = new Scope(this.currentScope);
        visit(node.getDecl());
        if(node.getCond() != null) {
        	Type type = evalType(node.getCond());
        	if (type != Type.BOOL) {
        		throw new SemanticException(node.getFirstSc(),
        			"expression is not a boolean");
        	}
        }
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
    public void caseALtExp(ALtExp node) {
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
    public void caseALeExp(ALeExp node) {
        Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());
        numbersOnly(leftType, rightType, node.getLt());
        this.resultType = Type.BOOL;
    }
    
    @Override
    public void caseAGeExp(AGeExp node) {
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

        this.resultType = Type.INT;
    }
    
    @Override
    public void caseAModuloMultExp(AModuloMultExp node) {
    	Type leftType = evalType(node.getLeft());
        Type rightType = evalType(node.getRight());

        numbersOnly(leftType, rightType, node.getModulo());

        this.resultType = Type.INT;
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
    
    @Override
    public void caseAPostAddIncrement(APostAddIncrement node) {
    	Type type = this.currentScope.getType(node.getIdent());
    	numberOnly(type, node.getPlusInc());
    	this.resultType = Type.INT;
    }
    @Override
    public void caseAPostSubIncrement(APostSubIncrement node) {
    	Type type = this.currentScope.getType(node.getIdent());
    	numberOnly(type, node.getMinusInc());
    	this.resultType = Type.INT;
    }
    @Override
    public void caseAPreAddIncrement(APreAddIncrement node) {
    	Type type = this.currentScope.getType(node.getIdent());
    	numberOnly(type, node.getPlusInc());
    	this.resultType = Type.INT;
    }
    @Override
    public void caseAPreSubIncrement(APreSubIncrement node) {
    	Type type = this.currentScope.getType(node.getIdent());
    	numberOnly(type, node.getMinusInc());
    	this.resultType = Type.INT;
    }    
    
    private void numberOnly(Type type, Token token) {
    	if(type != Type.INT) {
    		throw new SemanticException(token, type+" cannot be incremented");
    	}
    }
    
    private void numbersOnly(Type leftType, Type rightType, Token token) {
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
