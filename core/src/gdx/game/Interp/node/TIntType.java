/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Interp.node;

import gdx.game.Interp.analysis.*;

@SuppressWarnings("nls")
public final class TIntType extends Token
{
    public TIntType()
    {
        super.setText("int");
    }

    public TIntType(int line, int pos)
    {
        super.setText("int");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TIntType(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTIntType(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TIntType text.");
    }
}