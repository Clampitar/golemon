
package gdx.game.Scene.Interpreter;

import gdx.game.Scene.node.*;

public class InterpreterException
        extends RuntimeException {
	
	private Frame frame;

    public InterpreterException(
            Token token,
            Frame frame,
            String message) {

        super("[" + token.getLine() + ":" + token.getPos() + "] " + message);
        this.frame = frame;
    }
    
    public void printCallContext() {

        // Frame courant
        Frame currentFrame = this.frame;
        System.err.println("  Dans " + this.frame.getName());

        // Frames ancêtres
        Frame ancestorFrame = currentFrame.getParentFrame();
        while (ancestorFrame != null) {
            System.err.println("  Appelé par " + "[" + ancestorFrame.getLine()
                    + ":" + ancestorFrame.getPos() + "] "
                    + ancestorFrame.getName());
            ancestorFrame = ancestorFrame.getParentFrame();
        }

    }

}
