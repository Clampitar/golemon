/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class TPlusInc extends Token
{
    public TPlusInc()
    {
        super.setText("++");
    }

    public TPlusInc(int line, int pos)
    {
        super.setText("++");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TPlusInc(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTPlusInc(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TPlusInc text.");
    }
}
