/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class TStringType extends Token
{
    public TStringType()
    {
        super.setText("string");
    }

    public TStringType(int line, int pos)
    {
        super.setText("string");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TStringType(getLine(), getPos());
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTStringType(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TStringType text.");
    }
}
