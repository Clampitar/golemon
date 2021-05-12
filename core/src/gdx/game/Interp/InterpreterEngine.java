
package gdx.game.Interp;

import java.util.*;

import com.badlogic.gdx.graphics.OrthographicCamera;

import gdx.game.MyGdxGame;
import gdx.game.Player;
import gdx.game.Interp.analysis.*;
import gdx.game.Interp.node.*;

public class InterpreterEngine
        extends DepthFirstAdapter {

    private Value result;
    private List<Value> currentArgs;
    private Frame currentFrame;
    private SemanticInfo semantics;
    private PInst currentInst;
    
    public int frameDelay = 0;
    
    Player player;
    OrthographicCamera cam;
    MyGdxGame game;

    public InterpreterEngine(Player player,MyGdxGame game, OrthographicCamera cam,  SemanticInfo semantics) {
		this.player = player;
		this.game = game;
		this.cam = cam;
		this.semantics = semantics;
		currentFrame = null;
    }
    
    private void visit(Node node) {
        if (node != null) {
            node.apply(this);
        }
    }
    
    @Override
    public void caseAProg(AProg node) {
    	if(this.currentFrame == null)
    		this.currentFrame = new Frame(node.getInsts());
    	frameDelay = 0;
    	//Si la lecture est intérompue l'itérateur est conservé 
    	//et le programme peut continuer à la même ligne si celui-si est ré-appelé
    	try {
    		while (hasNext()) {
    			try {
    				currentInst = (PInst) currentFrame.next();
    				visit(currentInst);
    			} catch(ReturnException e) {exitFunction();}
    			
			}
    	} catch(frameAdvanceException e) {}
    }
    
    public boolean hasNext() {
        if(this.currentFrame.hasNext()) return true;
    	if(this.currentFrame instanceof LoopFrame && !currentFrame.hasNext()) {
    		PAssigner assigner = ((LoopFrame) this.currentFrame).getEndLoopInst();
    		visit(assigner);
    		PExp exp = ((LoopFrame) this.currentFrame).getLoopExpression();
    		BoolValue val = (BoolValue) eval(exp);
    		
    		if(val.getValue()) {
    			((LoopFrame) this.currentFrame).reIterate();
    			return true;
    		} else {
    			this.currentFrame = this.currentFrame.getParentFrame();
    		}
    	} else {
    		this.currentFrame = this.currentFrame.getParentFrame();
    		if(this.currentFrame == null) return false;
    		return this.currentFrame.hasNext();
    	}
    	return hasNext();
    }

    public void caseAIfInst(
            AIfInst node) {

        Value value = eval(node.getExp());

        if (((BoolValue) value).getValue()) {
            // executer le then
            node.getThenPart().apply(this);
        }
        else {
            // executer le else
        	if(node.getElsePart() != null)
        		node.getElsePart().apply(this);
        }
    }
    
    @Override
    public void caseAWhileInst(AWhileInst node) {
    	this.currentFrame = new LoopFrame(this.currentFrame, node.getExp());
    	while((node.getExp() == null) || ((BoolValue)eval(node.getExp())).getValue())
    		visit(node.getWhileBody());
    	this.currentFrame = this.currentFrame.getParentFrame();
    }
    
    @Override
    public void caseAWhileBody(AWhileBody node) {
    	this.currentFrame.iterate(node.getInsts());
    	super.caseAWhileBody(node);
    }
    
    public void caseAForInst(AForInst node) {
    	this.currentFrame = new LoopFrame(this.currentFrame, node.getCond(), node.getIter());
    	visit(node.getDecl());
    	while((node.getCond() == null) || ((BoolValue)eval(node.getCond())).getValue()) {
    		visit(node.getWhileBody());
    		visit(node.getIter());
    	}
		this.currentFrame = this.currentFrame.getParentFrame();
    };

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
    
/*    
    @Override
    public void caseASayInst(ASayInst node) {
    	Value value = eval(node.getExp());
        game.say(value.toString());
    }
*/ //Avorté par manque de temps
    
    @Override
    public void caseAPostAddIncrement(APostAddIncrement node) {
    	Value value = this.currentFrame.getVariable(node.getIdent());
    	this.result = value;
    	int val = ((IntValue) value).getValue();
    	val++;
    	this.currentFrame.putVariable(node.getIdent(), new IntValue(val));
    	
    }
    
    @Override
    public void caseAPostSubIncrement(APostSubIncrement node) {
    	Value value = this.currentFrame.getVariable(node.getIdent());
    	int val = ((IntValue) value).getValue();
    	this.result = value;
    	val--;
    	this.currentFrame.putVariable(node.getIdent(), new IntValue(val));
    }
    
    @Override
    public void caseAPreAddIncrement(APreAddIncrement node) {
    	Value value = this.currentFrame.getVariable(node.getIdent());
    	int val = ((IntValue) value).getValue();
    	++val;
    	value = new IntValue(val);
    	this.currentFrame.putVariable(node.getIdent(), value);
    	this.result = value;
    }
    
    @Override
    public void caseAPreSubIncrement(APreSubIncrement node) {
    	Value value = this.currentFrame.getVariable(node.getIdent());
    	int val = ((IntValue) value).getValue();
    	--val;
    	value = new IntValue(val);
    	this.currentFrame.putVariable(node.getIdent(), value);
    	this.result = value;
    }
    
    @Override
    public void caseADeclAssigner(ADeclAssigner node) {
    	Value value = eval(node.getExp());
        this.currentFrame.putVariable(node.getIdent(), value);
    }
    
    @Override
    public void caseAAssignAssigner(AAssignAssigner node) {
    	Value value = eval(node.getExp());
        this.currentFrame.putVariable(node.getIdent(), value);
    }
    
    @Override
    	public void caseAReturnInst(AReturnInst node) {
    		if(node.getExp() != null) {
    			Value value = eval(node.getExp());
    			this.currentFrame.setReturnValue(value);
    		}
    		throw new ReturnException();
    	}

    @Override
    public void caseAEqExp(
            AEqExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        if (leftValue instanceof StringValue) {

            this.result = new BoolValue(((StringValue) leftValue).getValue()
                    .equals(((StringValue) rightValue).getValue()));
        }
        else if (leftValue instanceof IntValue) {

            this.result = new BoolValue(((IntValue) leftValue)
                    .getValue() == ((IntValue) rightValue).getValue());
        }
        else {
        	//booléen

            this.result = new BoolValue(((BoolValue) leftValue)
                    .getValue() == ((BoolValue) rightValue).getValue());
        }
    }

    @Override
    public void caseALtExp(
            ALtExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        this.result = new BoolValue(((IntValue) leftValue)
                .getValue() < ((IntValue) rightValue).getValue());
    }
    
    @Override
    public void caseAGtExp(
            AGtExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        this.result = new BoolValue(((IntValue) leftValue)
                .getValue() > ((IntValue) rightValue).getValue());
    }
    
    @Override
    public void caseALeExp(
            ALeExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        this.result = new BoolValue(((IntValue) leftValue)
                .getValue() <= ((IntValue) rightValue).getValue());
    }
    
    @Override
    public void caseAGeExp(
            AGeExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

        this.result = new BoolValue(((IntValue) leftValue)
                .getValue() >= ((IntValue) rightValue).getValue());
    }

    @Override
    public void caseASubAdditiveExp(
            ASubAdditiveExp node) {

        Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());

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


        this.result = new IntValue(((IntValue) leftValue).getValue()
                + ((IntValue) rightValue).getValue());
    }
    
    @Override
    public void caseAMultMultExp(AMultMultExp node) {
    	 Value leftValue = eval(node.getLeft());
         Value rightValue = eval(node.getRight());
         
         this.result = new IntValue(((IntValue) leftValue).getValue()
                 * ((IntValue) rightValue).getValue());
    }
    
    @Override
    public void caseAModuloMultExp(AModuloMultExp node) {
    	Value leftValue = eval(node.getLeft());
        Value rightValue = eval(node.getRight());
        
        this.result = new IntValue(((IntValue) leftValue).getValue()
                % ((IntValue) rightValue).getValue());
    }

    private Value eval(
            Node node) {

        node.apply(this);
        return this.result;
    }

    @Override
    public void caseAIntegerTerm(
            AIntegerTerm node) {
        Boolean minus = (node.getMinus() != null);
    	try {
            int number = Integer.parseInt(node.getInteger().getText());
            this.result = new IntValue(minus ? -number : number);
        }
        catch (NumberFormatException e) {
            throw new InterpreterException(node.getInteger(),
                    this.currentFrame,"Number is invalid");
        }
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        this.result = this.currentFrame.getVariable(node.getIdent());
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
    
    @Override
    public void caseAFrameAdvanceInst(AFrameAdvanceInst node) {
    	this.frameDelay = 1;
    	if(node.getExp() != null) {
    		Value value = eval(node.getExp());
    		this.frameDelay = ((IntValue) value).getValue();
    	}
    	if(frameDelay > 0)
    		throw new frameAdvanceException();
    }
    
    @Override
    public void caseAWalkInst(AWalkInst node) {
    	List<Value> previousArgs = this.currentArgs;
        this.currentArgs = new LinkedList<>();
        visit(node.getArgs());
    	IntValue x = (IntValue) this.currentArgs.get(0);
    	IntValue y = (IntValue) this.currentArgs.get(1);
    	player.walk(x.getValue(), y.getValue());
    	this.currentArgs = previousArgs;
    }
    
    @Override
    public void caseAMoveCamInst(AMoveCamInst node) {
    	List<Value> previousArgs = this.currentArgs;
        this.currentArgs = new LinkedList<>();
        visit(node.getArgs());
    	IntValue xVal = (IntValue) this.currentArgs.get(0);
    	IntValue yVal = (IntValue) this.currentArgs.get(1);
    	int x = xVal.getValue();
    	int y = yVal.getValue();
    	if(x != 0 && y != 0 ) {
    		x *= Math.sin(Math.PI / 4);
            y *= Math.sin(Math.PI / 4);
    	}
    	cam.translate(x, y);
    	this.currentArgs = previousArgs;
    }
    
    @Override
    public void caseAArg(AArg node) {
    	this.currentArgs.add(eval(node.getExp()));
    }
    
    @Override
    public void caseAFunCallInst(AFunCallInst node) {
    	List<Value> previousArgs = this.currentArgs;
        this.currentArgs = new LinkedList<>();
        visit(node.getArgs());
        List<Value> args = this.currentArgs;
        this.currentArgs = previousArgs;
        
        FunctionInfo info = this.semantics.getFunInfo(node.getIdent().getText());
        Frame frame = new Frame(currentFrame, info, node.getIdent());
    	
        info.assignArgs(args, frame, node.getLPar());
        
     // noter la localisation courante
        this.currentFrame.setLocation(node.getLPar());

        // Exécuter le corps de la fonction
        this.currentFrame = frame;
    	
    	try {
    		visit(info.getFunBody());
    	} catch (ReturnException e) {}
    	

        this.currentFrame = frame.getParentFrame();
    	
    	this.currentFrame.setLocation(null);
    	
    }
    
    @Override
    	public void caseAFunCallTerm(AFunCallTerm node) {
    	List<Value> previousArgs = this.currentArgs;
        this.currentArgs = new LinkedList<>();
        visit(node.getArgs());
        List<Value> args = this.currentArgs;
        this.currentArgs = previousArgs;
        
        FunctionInfo info = this.semantics.getFunInfo(node.getIdent().getText());
        Frame frame = new Frame(currentFrame, info, node.getIdent());
    	
        info.assignArgs(args, frame, node.getLPar());
        
     // noter la localisation courante
        this.currentFrame.setLocation(node.getLPar());

        // Exécuter le corps de la fonction
        this.currentFrame = frame;
    	
    	try {
    		visit(info.getFunBody());
    	} catch (ReturnException e) {}
    	this.result = this.currentFrame.getReturnValue();

        this.currentFrame = frame.getParentFrame();
    	
    	this.currentFrame.setLocation(null);
    	}
    
    @Override
    public void caseAFunBody(AFunBody node) {
    	this.currentFrame.iterate(node.getInsts());
		while (currentFrame.hasNext()) {
			visit((PInst) currentFrame.next());
		}
    }
    
    private void exitFunction() {
    	// is only called in void functions, so this is irrelevent
    	while(this.currentFrame instanceof LoopFrame) // exit loops
    		this.currentFrame = this.currentFrame.getParentFrame();
    	this.currentFrame = this.currentFrame.getParentFrame();
    	this.currentFrame.setLocation(null);
    }
    
    private class ReturnException
    extends RuntimeException {
		private static final long serialVersionUID = -6195413933405715175L;
    }
    private class frameAdvanceException
    extends RuntimeException {
		private static final long serialVersionUID = -3997841407698069347L;
    }

 
}
