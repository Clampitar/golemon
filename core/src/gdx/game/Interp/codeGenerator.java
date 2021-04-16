package gdx.game.Interp;

import gdx.game.Interp.analysis.DepthFirstAdapter;
import gdx.game.Interp.node.AProg;

public class codeGenerator extends DepthFirstAdapter {

    @Override
    public void inAProg(AProg node) {
        System.out.println("public class main");
    }
}
