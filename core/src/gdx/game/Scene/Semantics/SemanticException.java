
package gdx.game.Scene.Semantics;

import gdx.game.Scene.node.*;

public class SemanticException
        extends RuntimeException {

    public SemanticException(
            Token token,
            String message) {

        super("[" + token.getLine() + ":" + token.getPos() + "] " + message);
    }
}
