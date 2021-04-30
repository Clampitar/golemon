package gdx.game.Interp;

import java.util.LinkedList;

import gdx.game.Interp.node.PAssigner;
import gdx.game.Interp.node.PExp;
import gdx.game.Interp.node.PInst;
import gdx.game.Interp.node.TIdent;

public class BlockFrame extends Frame {
	
	private PExp loopExpression;
	private LinkedList<PInst> insts;
	private PAssigner endLoopInst;

	public BlockFrame(Frame parentFrame, PExp exp) {
		this(parentFrame, exp, null);
	}
	
	public BlockFrame(Frame parentFrame, PExp exp, PAssigner endLoopInst) {
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
