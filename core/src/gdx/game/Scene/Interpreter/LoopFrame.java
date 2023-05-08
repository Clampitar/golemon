package gdx.game.Scene.Interpreter;

import java.util.LinkedList;

import gdx.game.Scene.node.PAssigner;
import gdx.game.Scene.node.PExp;
import gdx.game.Scene.node.PInst;
import gdx.game.Scene.node.TIdent;

public class LoopFrame extends Frame {
	
	private PExp loopExpression;
	private LinkedList<PInst> insts;
	private PAssigner endLoopInst;

	public LoopFrame(Frame parentFrame, PExp exp) {
		this(parentFrame, exp, null);
	}
	
	public LoopFrame(Frame parentFrame, PExp exp, PAssigner endLoopInst) {
		super(parentFrame);
		this.loopExpression = exp;
		this.endLoopInst = endLoopInst;
	}
	
	@Override
	public Value getVariable(TIdent ident) {
		String varName = ident.getText();
		Value val = this.variables.get(varName);
		if(val == null) {
			val = this.parentFrame.getVariable(ident);
		}
        return val;
	}
	
	@Override
	public void iterate(LinkedList<PInst> insts) {
		super.iterate(insts);
		this.insts = insts;
	}
	
	public void reIterate() {
		iterator = insts.iterator();
	}
	
	public PExp getLoopExpression() {
		return loopExpression;
	}
	
	public PAssigner getEndLoopInst() {
		return endLoopInst;
	}

}
