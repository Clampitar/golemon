/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import java.util.*;
import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class AProg extends PProg
{
    private final LinkedList<PStructDecl> _structDecls_ = new LinkedList<PStructDecl>();
    private final LinkedList<PFunDecl> _funDecls_ = new LinkedList<PFunDecl>();
    private final LinkedList<PInst> _insts_ = new LinkedList<PInst>();

    public AProg()
    {
        // Constructor
    }

    public AProg(
        @SuppressWarnings("hiding") List<?> _structDecls_,
        @SuppressWarnings("hiding") List<?> _funDecls_,
        @SuppressWarnings("hiding") List<?> _insts_)
    {
        // Constructor
        setStructDecls(_structDecls_);

        setFunDecls(_funDecls_);

        setInsts(_insts_);

    }

    @Override
    public Object clone()
    {
        return new AProg(
            cloneList(this._structDecls_),
            cloneList(this._funDecls_),
            cloneList(this._insts_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAProg(this);
    }

    public LinkedList<PStructDecl> getStructDecls()
    {
        return this._structDecls_;
    }

    public void setStructDecls(List<?> list)
    {
        for(PStructDecl e : this._structDecls_)
        {
            e.parent(null);
        }
        this._structDecls_.clear();

        for(Object obj_e : list)
        {
            PStructDecl e = (PStructDecl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._structDecls_.add(e);
        }
    }

    public LinkedList<PFunDecl> getFunDecls()
    {
        return this._funDecls_;
    }

    public void setFunDecls(List<?> list)
    {
        for(PFunDecl e : this._funDecls_)
        {
            e.parent(null);
        }
        this._funDecls_.clear();

        for(Object obj_e : list)
        {
            PFunDecl e = (PFunDecl) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._funDecls_.add(e);
        }
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
            + toString(this._structDecls_)
            + toString(this._funDecls_)
            + toString(this._insts_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._structDecls_.remove(child))
        {
            return;
        }

        if(this._funDecls_.remove(child))
        {
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
        for(ListIterator<PStructDecl> i = this._structDecls_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PStructDecl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
        }

        for(ListIterator<PFunDecl> i = this._funDecls_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PFunDecl) newChild);
                    newChild.parent(this);
                    oldChild.parent(null);
                    return;
                }

                i.remove();
                oldChild.parent(null);
                return;
            }
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