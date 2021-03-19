/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.mygdx.game.Interp.analysis;

import java.util.*;
import com.mygdx.game.Interp.node.*;

public class DepthFirstAdapter extends AnalysisAdapter
{
    public void inStart(Start node)
    {
        defaultIn(node);
    }

    public void outStart(Start node)
    {
        defaultOut(node);
    }

    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
        // Do nothing
    }

    @Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPProg().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }

    public void inAProg(AProg node)
    {
        defaultIn(node);
    }

    public void outAProg(AProg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAProg(AProg node)
    {
        inAProg(node);
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAProg(node);
    }

    public void inADeclInst(ADeclInst node)
    {
        defaultIn(node);
    }

    public void outADeclInst(ADeclInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclInst(ADeclInst node)
    {
        inADeclInst(node);
        if(node.getVar() != null)
        {
            node.getVar().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outADeclInst(node);
    }

    public void inAAssignInst(AAssignInst node)
    {
        defaultIn(node);
    }

    public void outAAssignInst(AAssignInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignInst(AAssignInst node)
    {
        inAAssignInst(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAAssignInst(node);
    }

    public void inAIfElseInst(AIfElseInst node)
    {
        defaultIn(node);
    }

    public void outAIfElseInst(AIfElseInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfElseInst(AIfElseInst node)
    {
        inAIfElseInst(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getThenInst() != null)
        {
            node.getThenInst().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getElseInst() != null)
        {
            node.getElseInst().apply(this);
        }
        outAIfElseInst(node);
    }

    public void inABlockInst(ABlockInst node)
    {
        defaultIn(node);
    }

    public void outABlockInst(ABlockInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABlockInst(ABlockInst node)
    {
        inABlockInst(node);
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outABlockInst(node);
    }

    public void inAWhileInst(AWhileInst node)
    {
        defaultIn(node);
    }

    public void outAWhileInst(AWhileInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileInst(AWhileInst node)
    {
        inAWhileInst(node);
        if(node.getWhile() != null)
        {
            node.getWhile().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getInst() != null)
        {
            node.getInst().apply(this);
        }
        outAWhileInst(node);
    }

    public void inAPrintExpInst(APrintExpInst node)
    {
        defaultIn(node);
    }

    public void outAPrintExpInst(APrintExpInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrintExpInst(APrintExpInst node)
    {
        inAPrintExpInst(node);
        if(node.getPrint() != null)
        {
            node.getPrint().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAPrintExpInst(node);
    }

    public void inAPrintlnInst(APrintlnInst node)
    {
        defaultIn(node);
    }

    public void outAPrintlnInst(APrintlnInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPrintlnInst(APrintlnInst node)
    {
        inAPrintlnInst(node);
        if(node.getPrintln() != null)
        {
            node.getPrintln().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAPrintlnInst(node);
    }

    public void inAEqExp(AEqExp node)
    {
        defaultIn(node);
    }

    public void outAEqExp(AEqExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAEqExp(AEqExp node)
    {
        inAEqExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAEqExp(node);
    }

    public void inALtExp(ALtExp node)
    {
        defaultIn(node);
    }

    public void outALtExp(ALtExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALtExp(ALtExp node)
    {
        inALtExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getLt() != null)
        {
            node.getLt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALtExp(node);
    }

    public void inASimpleExp(ASimpleExp node)
    {
        defaultIn(node);
    }

    public void outASimpleExp(ASimpleExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleExp(ASimpleExp node)
    {
        inASimpleExp(node);
        if(node.getAdditiveExp() != null)
        {
            node.getAdditiveExp().apply(this);
        }
        outASimpleExp(node);
    }

    public void inAAddAdditiveExp(AAddAdditiveExp node)
    {
        defaultIn(node);
    }

    public void outAAddAdditiveExp(AAddAdditiveExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAddAdditiveExp(AAddAdditiveExp node)
    {
        inAAddAdditiveExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAAddAdditiveExp(node);
    }

    public void inASubAdditiveExp(ASubAdditiveExp node)
    {
        defaultIn(node);
    }

    public void outASubAdditiveExp(ASubAdditiveExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASubAdditiveExp(ASubAdditiveExp node)
    {
        inASubAdditiveExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outASubAdditiveExp(node);
    }

    public void inASimpleAdditiveExp(ASimpleAdditiveExp node)
    {
        defaultIn(node);
    }

    public void outASimpleAdditiveExp(ASimpleAdditiveExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleAdditiveExp(ASimpleAdditiveExp node)
    {
        inASimpleAdditiveExp(node);
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        outASimpleAdditiveExp(node);
    }

    public void inATrueTerm(ATrueTerm node)
    {
        defaultIn(node);
    }

    public void outATrueTerm(ATrueTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseATrueTerm(ATrueTerm node)
    {
        inATrueTerm(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATrueTerm(node);
    }

    public void inAFalseTerm(AFalseTerm node)
    {
        defaultIn(node);
    }

    public void outAFalseTerm(AFalseTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFalseTerm(AFalseTerm node)
    {
        inAFalseTerm(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalseTerm(node);
    }

    public void inANumberTerm(ANumberTerm node)
    {
        defaultIn(node);
    }

    public void outANumberTerm(ANumberTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseANumberTerm(ANumberTerm node)
    {
        inANumberTerm(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumberTerm(node);
    }

    public void inAStringTerm(AStringTerm node)
    {
        defaultIn(node);
    }

    public void outAStringTerm(AStringTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringTerm(AStringTerm node)
    {
        inAStringTerm(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringTerm(node);
    }

    public void inAVarTerm(AVarTerm node)
    {
        defaultIn(node);
    }

    public void outAVarTerm(AVarTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVarTerm(AVarTerm node)
    {
        inAVarTerm(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAVarTerm(node);
    }
}