
package com.mygdx.game.Interp;

import com.mygdx.game.Interp.node.*;

public class InterpreterException
        extends RuntimeException {

    public InterpreterException(
            Token token,
            String message) {

        super("[" + token.getLine() + ":" + token.getPos() + "] " + message);
    }

}
