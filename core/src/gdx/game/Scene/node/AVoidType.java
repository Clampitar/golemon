/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class AVoidType extends PType
{
    private TVoidType _voidType_;

    public AVoidType()
    {
        // Constructor
    }

    public AVoidType(
        @SuppressWarnings("hiding") TVoidType _voidType_)
    {
        // Constructor
        setVoidType(_voidType_);

    }

    @Override
    public Object clone()
    {
        return new AVoidType(
            cloneNode(this._voidType_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAVoidType(this);
    }

    public TVoidType getVoidType()
    {
        return this._voidType_;
    }

    public void setVoidType(TVoidType node)
    {
        if(this._voidType_ != null)
        {
            this._voidType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._voidType_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._voidType_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._voidType_ == child)
        {
            this._voidType_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._voidType_ == oldChild)
        {
            setVoidType((TVoidType) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}