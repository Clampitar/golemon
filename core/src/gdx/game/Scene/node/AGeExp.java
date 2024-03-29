/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class AGeExp extends PExp
{
    private PExp _left_;
    private TGt _gt_;
    private TAssign _assign_;
    private PAdditiveExp _right_;

    public AGeExp()
    {
        // Constructor
    }

    public AGeExp(
        @SuppressWarnings("hiding") PExp _left_,
        @SuppressWarnings("hiding") TGt _gt_,
        @SuppressWarnings("hiding") TAssign _assign_,
        @SuppressWarnings("hiding") PAdditiveExp _right_)
    {
        // Constructor
        setLeft(_left_);

        setGt(_gt_);

        setAssign(_assign_);

        setRight(_right_);

    }

    @Override
    public Object clone()
    {
        return new AGeExp(
            cloneNode(this._left_),
            cloneNode(this._gt_),
            cloneNode(this._assign_),
            cloneNode(this._right_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAGeExp(this);
    }

    public PExp getLeft()
    {
        return this._left_;
    }

    public void setLeft(PExp node)
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

    public TGt getGt()
    {
        return this._gt_;
    }

    public void setGt(TGt node)
    {
        if(this._gt_ != null)
        {
            this._gt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._gt_ = node;
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

    public PAdditiveExp getRight()
    {
        return this._right_;
    }

    public void setRight(PAdditiveExp node)
    {
        if(this._right_ != null)
        {
            this._right_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._right_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._left_)
            + toString(this._gt_)
            + toString(this._assign_)
            + toString(this._right_);
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

        if(this._gt_ == child)
        {
            this._gt_ = null;
            return;
        }

        if(this._assign_ == child)
        {
            this._assign_ = null;
            return;
        }

        if(this._right_ == child)
        {
            this._right_ = null;
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
            setLeft((PExp) newChild);
            return;
        }

        if(this._gt_ == oldChild)
        {
            setGt((TGt) newChild);
            return;
        }

        if(this._assign_ == oldChild)
        {
            setAssign((TAssign) newChild);
            return;
        }

        if(this._right_ == oldChild)
        {
            setRight((PAdditiveExp) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
