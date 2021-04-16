
package gdx.game.Interp;

import java.util.*;

import gdx.game.MyGdxGame;
import gdx.game.Player;
import gdx.game.Interp.analysis.*;
import gdx.game.Interp.node.*;

public class InterpreterEngine
        extends DepthFirstAdapter {

    private Map<String, Value> variables = new HashMap<>();

    private Value result;
    private Value[] results;
    private Frame currentFrame;
    private SemanticInfo semantics;
    private Iterator<PInst> iterator;
    public int frameDelay = 0;
    
    Player player;
    MyGdxGame game;

    public InterpreterEngine(Player player, MyGdxGame game, SemanticInfo semantics) {
		this.player = player;
		this.game = game;
		this.semantics = semantics;
		iterator = null;
    }
    
    private void visit(Node node) {
        if (node != null) {
            node.apply(this);
        }
    }
    
    @Override
    public void caseAProg(AProg node) {
    	this.currentFrame = new Frame();
    	frameDelay = 0;
    	if(iterator == null) {
    		iterator = node.getInsts().iterator();
    	}
    	//Si la lecture est intérompue l'itérateur est conservé 
    	//et le programme peut continuer à la même ligne si celui-si est ré-appelé
    	while (iterator.hasNext()) {
			visit((PInst) iterator.next());
			if(frameDelay > 0)
				return;
		}
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
            node.getElsePart().apply(this);
        }
    }
    
    @Override
    public void caseAWhileInst(AWhileInst node) {
    	while(true) {
    		BoolValue value = (BoolValue) eval(node.getExp());
    		if(!value.getValue())
    			break;
    		visit(node.getWhileBody());
    	}
    }
    
    public void caseAForInst(AForInst node) {
    	//Value
    	while(true) {
    		BoolValue condition = (BoolValue) eval(node.getCond());
    		if(!condition.getValue())
    			break;
    		visit(node.getWhileBody());
    	}
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
        player.walk(5, 10);
    }

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

    private Value eval(
            Node node) {

        node.apply(this);
        return this.result;
    }
    
    private Value[] evalList(
    		Node node) {
    	node.apply(this);
    	return this.results;
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
                    this.currentFrame,"Number is invalid");
        }
    }

    @Override
    public void caseAVarTerm(
            AVarTerm node) {

        String varName = node.getIdent().getText();

        this.result = this.variables.get(varName);
        
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
    	frameDelay = 1;
    	Value value = eval(node.getExp());
    	if(value instanceof IntValue) {
    		frameDelay = ((IntValue) value).getValue();
    	}
    }
    
    @Override
    public void caseAWalkInst(AWalkInst node) {
    	IntValue x = (IntValue) evalList(node.getArgs())[0];
    	IntValue y = (IntValue) evalList(node.getArgs())[1];
    	player.walk(x.getValue(), y.getValue());
    }
    
    @Override
    public void caseAArgs(AArgs node) {
    	// TODO Auto-generated method stub
    	super.caseAArgs(node);
    }

 
}
