package com.mygdx.game.Interp;

import com.mygdx.game.Interp.analysis.DepthFirstAdapter;
import com.mygdx.game.Interp.node.AProg;

public class codeGenerator extends DepthFirstAdapter {

    @Override
    public void inAProg(AProg node) {
        System.out.println("public class main");
    }
}
