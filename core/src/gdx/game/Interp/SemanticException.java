
package gdx.game.Interp;

import gdx.game.Interp.node.*;

public class SemanticException
        extends RuntimeException {

    public SemanticException(
            Token token,
            String message) {

        super("[" + token.getLine() + ":" + token.getPos() + "] " + message);
    }
}
