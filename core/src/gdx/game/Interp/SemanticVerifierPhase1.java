package gdx.game.Interp;

import java.util.LinkedList;
import java.util.List;

import gdx.game.Interp.analysis.DepthFirstAdapter;
import gdx.game.Interp.node.*;

public class SemanticVerifierPhase1 extends DepthFirstAdapter {
	
	private SemanticInfo semantics;
	
	private Type currentType;
	
	private LinkedList<ParamInfo> currentParams;
	
	public SemanticVerifierPhase1(SemanticInfo semantics) {
		this.semantics = semantics;
	}
	
	private void visit(Node node) {
		if(node != null) {
			node.apply(this);
		}
	}
	
	@Override
	public void caseAFunDecl(AFunDecl node) {
		this.currentParams = new LinkedList<>();
		visit(node.getParams());
		List<ParamInfo> params = this.currentParams;
		this.currentParams = null;
		
		this.currentType = null;
		visit(node.getType());
		Type returnType = this.currentType;
		this.currentType = null;
		
		this.semantics.addFunDecl(node, params, returnType);
	}
	
	@Override
	public void caseAParam(AParam node) {
		this.currentType = null;
		visit(node.getType());
		Type type = this.currentType;
		this.currentType = null;
		
		currentParams.add(new ParamInfo(node, type));
	}
	
	@Override
    public void caseAIntType(
            AIntType node) {

        this.currentType = Type.INT;
    }

    @Override
    public void caseABoolType(
            ABoolType node) {

        this.currentType = Type.BOOL;
    }

    @Override
    public void caseAStringType(
            AStringType node) {

        this.currentType = Type.STRING;
    }

}
