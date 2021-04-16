/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Interp.node;

import java.util.*;
import gdx.game.Interp.analysis.*;

@SuppressWarnings("nls")
public final class AElsePart extends PElsePart
{
    private TElse _else_;
    private final LinkedList<PInst> _insts_ = new LinkedList<PInst>();

    public AElsePart()
    {
        // Constructor
    }

    public AElsePart(
        @SuppressWarnings("hiding") TElse _else_,
        @SuppressWarnings("hiding") List<?> _insts_)
    {
        // Constructor
        setElse(_else_);

        setInsts(_insts_);

    }

    @Override
    public Object clone()
    {
        return new AElsePart(
            cloneNode(this._else_),
            cloneList(this._insts_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAElsePart(this);
    }

    public TElse getElse()
    {
        return this._else_;
    }

    public void setElse(TElse node)
    {
        if(this._else_ != null)
        {
            this._else_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._else_ = node;
    }

    public LinkedList<PInst> getInsts()
    {
        return this._insts_;
    }

    public void setInsts(List<?> list)
    {
        for(PInst e : this._insts_)
        {
            e.parent(null);
        }
        this._insts_.clear();

        for(Object obj_e : list)
        {
            PInst e = (PInst) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._insts_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._else_)
            + toString(this._insts_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._else_ == child)
        {
            this._else_ = null;
            return;
        }

        if(this._insts_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._else_ == oldChild)
        {
            setElse((TElse) newChild);
            return;
        }

        for(ListIterator<PInst> i = this._insts_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PInst) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        throw new RuntimeException("Not a child.");
    }
}