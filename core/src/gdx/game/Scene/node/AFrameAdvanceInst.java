/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class AFrameAdvanceInst extends PInst
{
    private TFrameAdvance _frameAdvance_;
    private PExp _exp_;
    private TSc _sc_;

    public AFrameAdvanceInst()
    {
        // Constructor
    }

    public AFrameAdvanceInst(
        @SuppressWarnings("hiding") TFrameAdvance _frameAdvance_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TSc _sc_)
    {
        // Constructor
        setFrameAdvance(_frameAdvance_);

        setExp(_exp_);

        setSc(_sc_);

    }

    @Override
    public Object clone()
    {
        return new AFrameAdvanceInst(
            cloneNode(this._frameAdvance_),
            cloneNode(this._exp_),
            cloneNode(this._sc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFrameAdvanceInst(this);
    }

    public TFrameAdvance getFrameAdvance()
    {
        return this._frameAdvance_;
    }

    public void setFrameAdvance(TFrameAdvance node)
    {
        if(this._frameAdvance_ != null)
        {
            this._frameAdvance_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._frameAdvance_ = node;
    }

    public PExp getExp()
    {
        return this._exp_;
    }

    public void setExp(PExp node)
    {
        if(this._exp_ != null)
        {
            this._exp_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._exp_ = node;
    }

    public TSc getSc()
    {
        return this._sc_;
    }

    public void setSc(TSc node)
    {
        if(this._sc_ != null)
        {
            this._sc_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sc_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._frameAdvance_)
            + toString(this._exp_)
            + toString(this._sc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._frameAdvance_ == child)
        {
            this._frameAdvance_ = null;
            return;
        }

        if(this._exp_ == child)
        {
            this._exp_ = null;
            return;
        }

        if(this._sc_ == child)
        {
            this._sc_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._frameAdvance_ == oldChild)
        {
            setFrameAdvance((TFrameAdvance) newChild);
            return;
        }

        if(this._exp_ == oldChild)
        {
            setExp((PExp) newChild);
            return;
        }

        if(this._sc_ == oldChild)
        {
            setSc((TSc) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
