/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class TVar extends Token
{
    public TVar()
    {
        super.setText("var");
    }

    public TVar(int line, int pos)
    {
        super.setText("var");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TVar(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTVar(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TVar text.");
    }
}
