/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Interp.node;

import gdx.game.Interp.analysis.*;

@SuppressWarnings("nls")
public final class AFieldAssignInst extends PInst
{
    private PUnary _left_;
    private TDot _dot_;
    private TIdent _ident_;
    private TAssign _assign_;
    private PExp _exp_;
    private TSc _sc_;

    public AFieldAssignInst()
    {
        // Constructor
    }

    public AFieldAssignInst(
        @SuppressWarnings("hiding") PUnary _left_,
        @SuppressWarnings("hiding") TDot _dot_,
        @SuppressWarnings("hiding") TIdent _ident_,
        @SuppressWarnings("hiding") TAssign _assign_,
        @SuppressWarnings("hiding") PExp _exp_,
        @SuppressWarnings("hiding") TSc _sc_)
    {
        // Constructor
        setLeft(_left_);

        setDot(_dot_);

        setIdent(_ident_);

        setAssign(_assign_);

        setExp(_exp_);

        setSc(_sc_);

    }

    @Override
    public Object clone()
    {
        return new AFieldAssignInst(
            cloneNode(this._left_),
            cloneNode(this._dot_),
            cloneNode(this._ident_),
            cloneNode(this._assign_),
            cloneNode(this._exp_),
            cloneNode(this._sc_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFieldAssignInst(this);
    }

    public PUnary getLeft()
    {
        return this._left_;
    }

    public void setLeft(PUnary node)
    {
        if(this._left_ != null)
        {
            this._left_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._left_ = node;
    }

    public TDot getDot()
    {
        return this._dot_;
    }

    public void setDot(TDot node)
    {
        if(this._dot_ != null)
        {
            this._dot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._dot_ = node;
    }

    public TIdent getIdent()
    {
        return this._ident_;
    }

    public void setIdent(TIdent node)
    {
        if(this._ident_ != null)
        {
            this._ident_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._ident_ = node;
    }

    public TAssign getAssign()
    {
        return this._assign_;
    }

    public void setAssign(TAssign node)
    {
        if(this._assign_ != null)
        {
            this._assign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._assign_ = node;
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
            + toString(this._left_)
            + toString(this._dot_)
            + toString(this._ident_)
            + toString(this._assign_)
            + toString(this._exp_)
            + toString(this._sc_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._left_ == child)
        {
            this._left_ = null;
            return;
        }

        if(this._dot_ == child)
        {
            this._dot_ = null;
            return;
        }

        if(this._ident_ == child)
        {
            this._ident_ = null;
            return;
        }

        if(this._assign_ == child)
        {
            this._assign_ = null;
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
        if(this._left_ == oldChild)
        {
            setLeft((PUnary) newChild);
            return;
        }

        if(this._dot_ == oldChild)
        {
            setDot((TDot) newChild);
            return;
        }

        if(this._ident_ == oldChild)
        {
            setIdent((TIdent) newChild);
            return;
        }

        if(this._assign_ == oldChild)
        {
            setAssign((TAssign) newChild);
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
