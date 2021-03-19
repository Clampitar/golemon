/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.mygdx.game.Interp.node;

import java.util.*;
import com.mygdx.game.Interp.analysis.*;

@SuppressWarnings("nls")
public final class AProg extends PProg
{
    private final LinkedList<PInst> _insts_ = new LinkedList<PInst>();

    public AProg()
    {
        // Constructor
    }

    public AProg(
        @SuppressWarnings("hiding") List<?> _insts_)
    {
        // Constructor
        setInsts(_insts_);

    }

    @Override
    public Object clone()
    {
        return new AProg(
            cloneList(this._insts_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProg(this);
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
            + toString(this._insts_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
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