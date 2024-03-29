/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class APrintExpInst extends PInst
{
    private TPrint _print_;
    private PExp _exp_;
    private TSc _sc_;

    public APrintExpInst()
    {
        // Constructor
    }

    public APrintExpInst(
        @SuppressWarnings("hiding") TPrint _print_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TSc _sc_)
    {
        // Constructor
        setPrint(_print_);

        setExp(_exp_);

        setSc(_sc_);

    }

    @Override
    public Object clone()
    {
        return new APrintExpInst(
            cloneNode(this._print_),
            cloneNode(this._exp_),
            cloneNode(this._sc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPrintExpInst(this);
    }

    public TPrint getPrint()
    {
        return this._print_;
    }

    public void setPrint(TPrint node)
    {
        if(this._print_ != null)
        {
            this._print_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._print_ = node;
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
            + toString(this._print_)
            + toString(this._exp_)
            + toString(this._sc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._print_ == child)
        {
            this._print_ = null;
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
        if(this._print_ == oldChild)
        {
            setPrint((TPrint) newChild);
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
