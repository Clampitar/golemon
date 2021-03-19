/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.mygdx.game.Interp.node;

import com.mygdx.game.Interp.analysis.*;

@SuppressWarnings("nls")
public final class ASimpleExp extends PExp
{
    private PAdditiveExp _additiveExp_;

    public ASimpleExp()
    {
        // Constructor
    }

    public ASimpleExp(
        @SuppressWarnings("hiding") PAdditiveExp _additiveExp_)
    {
        // Constructor
        setAdditiveExp(_additiveExp_);

    }

    @Override
    public Object clone()
    {
        return new ASimpleExp(
            cloneNode(this._additiveExp_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASimpleExp(this);
    }

    public PAdditiveExp getAdditiveExp()
    {
        return this._additiveExp_;
    }

    public void setAdditiveExp(PAdditiveExp node)
    {
        if(this._additiveExp_ != null)
        {
            this._additiveExp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._additiveExp_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._additiveExp_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._additiveExp_ == child)
        {
            this._additiveExp_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._additiveExp_ == oldChild)
        {
            setAdditiveExp((PAdditiveExp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
