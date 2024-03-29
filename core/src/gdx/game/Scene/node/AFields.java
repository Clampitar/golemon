/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.node;

import java.util.*;
import gdx.game.Scene.analysis.*;

@SuppressWarnings("nls")
public final class AFields extends PFields
{
    private PField _field_;
    private final LinkedList<PAdditionalField> _additionalFields_ = new LinkedList<PAdditionalField>();

    public AFields()
    {
        // Constructor
    }

    public AFields(
        @SuppressWarnings("hiding") PField _field_,
        @SuppressWarnings("hiding") List<?> _additionalFields_)
    {
        // Constructor
        setField(_field_);

        setAdditionalFields(_additionalFields_);

    }

    @Override
    public Object clone()
    {
        return new AFields(
            cloneNode(this._field_),
            cloneList(this._additionalFields_));
    }

    @Override
    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFields(this);
    }

    public PField getField()
    {
        return this._field_;
    }

    public void setField(PField node)
    {
        if(this._field_ != null)
        {
            this._field_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._field_ = node;
    }

    public LinkedList<PAdditionalField> getAdditionalFields()
    {
        return this._additionalFields_;
    }

    public void setAdditionalFields(List<?> list)
    {
        for(PAdditionalField e : this._additionalFields_)
        {
            e.parent(null);
        }
        this._additionalFields_.clear();

        for(Object obj_e : list)
        {
            PAdditionalField e = (PAdditionalField) obj_e;
            if(e.parent() != null)
            {
                e.parent().removeChild(e);
            }

            e.parent(this);
            this._additionalFields_.add(e);
        }
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._field_)
            + toString(this._additionalFields_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._field_ == child)
        {
            this._field_ = null;
            return;
        }

        if(this._additionalFields_.remove(child))
        {
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._field_ == oldChild)
        {
            setField((PField) newChild);
            return;
        }

        for(ListIterator<PAdditionalField> i = this._additionalFields_.listIterator(); i.hasNext();)
        {
            if(i.next() == oldChild)
            {
                if(newChild != null)
                {
                    i.set((PAdditionalField) newChild);
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
