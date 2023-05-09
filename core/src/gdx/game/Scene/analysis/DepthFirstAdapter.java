/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Scene.analysis;

import java.util.*;
import gdx.game.Scene.node.*;

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
            List<PStructDecl> copy = new ArrayList<PStructDecl>(node.getStructDecls());
            for(PStructDecl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PFunDecl> copy = new ArrayList<PFunDecl>(node.getFunDecls());
            for(PFunDecl e : copy)
            {
                e.apply(this);
            }
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAProg(node);
    }

    public void inAStructDecl(AStructDecl node)
    {
        defaultIn(node);
    }

    public void outAStructDecl(AStructDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStructDecl(AStructDecl node)
    {
        inAStructDecl(node);
        if(node.getStruct() != null)
        {
            node.getStruct().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getFields() != null)
        {
            node.getFields().apply(this);
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAStructDecl(node);
    }

    public void inAFields(AFields node)
    {
        defaultIn(node);
    }

    public void outAFields(AFields node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFields(AFields node)
    {
        inAFields(node);
        if(node.getField() != null)
        {
            node.getField().apply(this);
        }
        {
            List<PAdditionalField> copy = new ArrayList<PAdditionalField>(node.getAdditionalFields());
            for(PAdditionalField e : copy)
            {
                e.apply(this);
            }
        }
        outAFields(node);
    }

    public void inAAdditionalField(AAdditionalField node)
    {
        defaultIn(node);
    }

    public void outAAdditionalField(AAdditionalField node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAdditionalField(AAdditionalField node)
    {
        inAAdditionalField(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getField() != null)
        {
            node.getField().apply(this);
        }
        outAAdditionalField(node);
    }

    public void inAField(AField node)
    {
        defaultIn(node);
    }

    public void outAField(AField node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAField(AField node)
    {
        inAField(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAField(node);
    }

    public void inAIntType(AIntType node)
    {
        defaultIn(node);
    }

    public void outAIntType(AIntType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntType(AIntType node)
    {
        inAIntType(node);
        if(node.getIntType() != null)
        {
            node.getIntType().apply(this);
        }
        outAIntType(node);
    }

    public void inABoolType(ABoolType node)
    {
        defaultIn(node);
    }

    public void outABoolType(ABoolType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseABoolType(ABoolType node)
    {
        inABoolType(node);
        if(node.getBoolType() != null)
        {
            node.getBoolType().apply(this);
        }
        outABoolType(node);
    }

    public void inAStringType(AStringType node)
    {
        defaultIn(node);
    }

    public void outAStringType(AStringType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStringType(AStringType node)
    {
        inAStringType(node);
        if(node.getStringType() != null)
        {
            node.getStringType().apply(this);
        }
        outAStringType(node);
    }

    public void inAVoidType(AVoidType node)
    {
        defaultIn(node);
    }

    public void outAVoidType(AVoidType node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAVoidType(AVoidType node)
    {
        inAVoidType(node);
        if(node.getVoidType() != null)
        {
            node.getVoidType().apply(this);
        }
        outAVoidType(node);
    }

    public void inAFunDecl(AFunDecl node)
    {
        defaultIn(node);
    }

    public void outAFunDecl(AFunDecl node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunDecl(AFunDecl node)
    {
        inAFunDecl(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getName() != null)
        {
            node.getName().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getParams() != null)
        {
            node.getParams().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getFunBody() != null)
        {
            node.getFunBody().apply(this);
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAFunDecl(node);
    }

    public void inAFunBody(AFunBody node)
    {
        defaultIn(node);
    }

    public void outAFunBody(AFunBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunBody(AFunBody node)
    {
        inAFunBody(node);
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAFunBody(node);
    }

    public void inAAssignerInst(AAssignerInst node)
    {
        defaultIn(node);
    }

    public void outAAssignerInst(AAssignerInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignerInst(AAssignerInst node)
    {
        inAAssignerInst(node);
        if(node.getAssigner() != null)
        {
            node.getAssigner().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAAssignerInst(node);
    }

    public void inAIfInst(AIfInst node)
    {
        defaultIn(node);
    }

    public void outAIfInst(AIfInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIfInst(AIfInst node)
    {
        inAIfInst(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getThenPart() != null)
        {
            node.getThenPart().apply(this);
        }
        if(node.getElsePart() != null)
        {
            node.getElsePart().apply(this);
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAIfInst(node);
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
        if(node.getWhileBody() != null)
        {
            node.getWhileBody().apply(this);
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAWhileInst(node);
    }

    public void inAForInst(AForInst node)
    {
        defaultIn(node);
    }

    public void outAForInst(AForInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAForInst(AForInst node)
    {
        inAForInst(node);
        if(node.getFor() != null)
        {
            node.getFor().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getDecl() != null)
        {
            node.getDecl().apply(this);
        }
        if(node.getFirstSc() != null)
        {
            node.getFirstSc().apply(this);
        }
        if(node.getCond() != null)
        {
            node.getCond().apply(this);
        }
        if(node.getSecondSc() != null)
        {
            node.getSecondSc().apply(this);
        }
        if(node.getIter() != null)
        {
            node.getIter().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getDo() != null)
        {
            node.getDo().apply(this);
        }
        if(node.getWhileBody() != null)
        {
            node.getWhileBody().apply(this);
        }
        if(node.getEnd() != null)
        {
            node.getEnd().apply(this);
        }
        outAForInst(node);
    }

    public void inAReturnInst(AReturnInst node)
    {
        defaultIn(node);
    }

    public void outAReturnInst(AReturnInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAReturnInst(AReturnInst node)
    {
        inAReturnInst(node);
        if(node.getReturn() != null)
        {
            node.getReturn().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAReturnInst(node);
    }

    public void inAFunCallInst(AFunCallInst node)
    {
        defaultIn(node);
    }

    public void outAFunCallInst(AFunCallInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunCallInst(AFunCallInst node)
    {
        inAFunCallInst(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAFunCallInst(node);
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

    public void inAFrameAdvanceInst(AFrameAdvanceInst node)
    {
        defaultIn(node);
    }

    public void outAFrameAdvanceInst(AFrameAdvanceInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFrameAdvanceInst(AFrameAdvanceInst node)
    {
        inAFrameAdvanceInst(node);
        if(node.getFrameAdvance() != null)
        {
            node.getFrameAdvance().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAFrameAdvanceInst(node);
    }

    public void inAWalkInst(AWalkInst node)
    {
        defaultIn(node);
    }

    public void outAWalkInst(AWalkInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWalkInst(AWalkInst node)
    {
        inAWalkInst(node);
        if(node.getWalk() != null)
        {
            node.getWalk().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAWalkInst(node);
    }

    public void inAMoveCamInst(AMoveCamInst node)
    {
        defaultIn(node);
    }

    public void outAMoveCamInst(AMoveCamInst node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMoveCamInst(AMoveCamInst node)
    {
        inAMoveCamInst(node);
        if(node.getMoveCam() != null)
        {
            node.getMoveCam().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        if(node.getSc() != null)
        {
            node.getSc().apply(this);
        }
        outAMoveCamInst(node);
    }

    public void inADeclAssigner(ADeclAssigner node)
    {
        defaultIn(node);
    }

    public void outADeclAssigner(ADeclAssigner node)
    {
        defaultOut(node);
    }

    @Override
    public void caseADeclAssigner(ADeclAssigner node)
    {
        inADeclAssigner(node);
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
        outADeclAssigner(node);
    }

    public void inAAssignAssigner(AAssignAssigner node)
    {
        defaultIn(node);
    }

    public void outAAssignAssigner(AAssignAssigner node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAssignAssigner(AAssignAssigner node)
    {
        inAAssignAssigner(node);
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
        outAAssignAssigner(node);
    }

    public void inAFieldAssignAssigner(AFieldAssignAssigner node)
    {
        defaultIn(node);
    }

    public void outAFieldAssignAssigner(AFieldAssignAssigner node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFieldAssignAssigner(AFieldAssignAssigner node)
    {
        inAFieldAssignAssigner(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
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
        outAFieldAssignAssigner(node);
    }

    public void inAIncrementAssigner(AIncrementAssigner node)
    {
        defaultIn(node);
    }

    public void outAIncrementAssigner(AIncrementAssigner node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIncrementAssigner(AIncrementAssigner node)
    {
        inAIncrementAssigner(node);
        if(node.getIncrement() != null)
        {
            node.getIncrement().apply(this);
        }
        outAIncrementAssigner(node);
    }

    public void inAWhileBody(AWhileBody node)
    {
        defaultIn(node);
    }

    public void outAWhileBody(AWhileBody node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAWhileBody(AWhileBody node)
    {
        inAWhileBody(node);
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAWhileBody(node);
    }

    public void inAThenPart(AThenPart node)
    {
        defaultIn(node);
    }

    public void outAThenPart(AThenPart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAThenPart(AThenPart node)
    {
        inAThenPart(node);
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAThenPart(node);
    }

    public void inAElsePart(AElsePart node)
    {
        defaultIn(node);
    }

    public void outAElsePart(AElsePart node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAElsePart(AElsePart node)
    {
        inAElsePart(node);
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        {
            List<PInst> copy = new ArrayList<PInst>(node.getInsts());
            for(PInst e : copy)
            {
                e.apply(this);
            }
        }
        outAElsePart(node);
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

    public void inAGtExp(AGtExp node)
    {
        defaultIn(node);
    }

    public void outAGtExp(AGtExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGtExp(AGtExp node)
    {
        inAGtExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getGt() != null)
        {
            node.getGt().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGtExp(node);
    }

    public void inALeExp(ALeExp node)
    {
        defaultIn(node);
    }

    public void outALeExp(ALeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseALeExp(ALeExp node)
    {
        inALeExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getLt() != null)
        {
            node.getLt().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outALeExp(node);
    }

    public void inAGeExp(AGeExp node)
    {
        defaultIn(node);
    }

    public void outAGeExp(AGeExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAGeExp(AGeExp node)
    {
        inAGeExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getGt() != null)
        {
            node.getGt().apply(this);
        }
        if(node.getAssign() != null)
        {
            node.getAssign().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAGeExp(node);
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
        if(node.getMultExp() != null)
        {
            node.getMultExp().apply(this);
        }
        outASimpleAdditiveExp(node);
    }

    public void inAMultMultExp(AMultMultExp node)
    {
        defaultIn(node);
    }

    public void outAMultMultExp(AMultMultExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAMultMultExp(AMultMultExp node)
    {
        inAMultMultExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAMultMultExp(node);
    }

    public void inAModuloMultExp(AModuloMultExp node)
    {
        defaultIn(node);
    }

    public void outAModuloMultExp(AModuloMultExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAModuloMultExp(AModuloMultExp node)
    {
        inAModuloMultExp(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getModulo() != null)
        {
            node.getModulo().apply(this);
        }
        if(node.getRight() != null)
        {
            node.getRight().apply(this);
        }
        outAModuloMultExp(node);
    }

    public void inASimpleMultExp(ASimpleMultExp node)
    {
        defaultIn(node);
    }

    public void outASimpleMultExp(ASimpleMultExp node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleMultExp(ASimpleMultExp node)
    {
        inASimpleMultExp(node);
        if(node.getUnary() != null)
        {
            node.getUnary().apply(this);
        }
        outASimpleMultExp(node);
    }

    public void inAFieldAccessUnary(AFieldAccessUnary node)
    {
        defaultIn(node);
    }

    public void outAFieldAccessUnary(AFieldAccessUnary node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFieldAccessUnary(AFieldAccessUnary node)
    {
        inAFieldAccessUnary(node);
        if(node.getLeft() != null)
        {
            node.getLeft().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAFieldAccessUnary(node);
    }

    public void inASimpleUnary(ASimpleUnary node)
    {
        defaultIn(node);
    }

    public void outASimpleUnary(ASimpleUnary node)
    {
        defaultOut(node);
    }

    @Override
    public void caseASimpleUnary(ASimpleUnary node)
    {
        inASimpleUnary(node);
        if(node.getTerm() != null)
        {
            node.getTerm().apply(this);
        }
        outASimpleUnary(node);
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

    public void inAIntegerTerm(AIntegerTerm node)
    {
        defaultIn(node);
    }

    public void outAIntegerTerm(AIntegerTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIntegerTerm(AIntegerTerm node)
    {
        inAIntegerTerm(node);
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getInteger() != null)
        {
            node.getInteger().apply(this);
        }
        outAIntegerTerm(node);
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

    public void inAParTerm(AParTerm node)
    {
        defaultIn(node);
    }

    public void outAParTerm(AParTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParTerm(AParTerm node)
    {
        inAParTerm(node);
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAParTerm(node);
    }

    public void inAFunCallTerm(AFunCallTerm node)
    {
        defaultIn(node);
    }

    public void outAFunCallTerm(AFunCallTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAFunCallTerm(AFunCallTerm node)
    {
        inAFunCallTerm(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAFunCallTerm(node);
    }

    public void inAStructTerm(AStructTerm node)
    {
        defaultIn(node);
    }

    public void outAStructTerm(AStructTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAStructTerm(AStructTerm node)
    {
        inAStructTerm(node);
        if(node.getStruct() != null)
        {
            node.getStruct().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getLPar() != null)
        {
            node.getLPar().apply(this);
        }
        if(node.getArgs() != null)
        {
            node.getArgs().apply(this);
        }
        if(node.getRPar() != null)
        {
            node.getRPar().apply(this);
        }
        outAStructTerm(node);
    }

    public void inAIncrementTerm(AIncrementTerm node)
    {
        defaultIn(node);
    }

    public void outAIncrementTerm(AIncrementTerm node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAIncrementTerm(AIncrementTerm node)
    {
        inAIncrementTerm(node);
        if(node.getIncrement() != null)
        {
            node.getIncrement().apply(this);
        }
        outAIncrementTerm(node);
    }

    public void inAPreAddIncrement(APreAddIncrement node)
    {
        defaultIn(node);
    }

    public void outAPreAddIncrement(APreAddIncrement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPreAddIncrement(APreAddIncrement node)
    {
        inAPreAddIncrement(node);
        if(node.getPlusInc() != null)
        {
            node.getPlusInc().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAPreAddIncrement(node);
    }

    public void inAPostAddIncrement(APostAddIncrement node)
    {
        defaultIn(node);
    }

    public void outAPostAddIncrement(APostAddIncrement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPostAddIncrement(APostAddIncrement node)
    {
        inAPostAddIncrement(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getPlusInc() != null)
        {
            node.getPlusInc().apply(this);
        }
        outAPostAddIncrement(node);
    }

    public void inAPreSubIncrement(APreSubIncrement node)
    {
        defaultIn(node);
    }

    public void outAPreSubIncrement(APreSubIncrement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPreSubIncrement(APreSubIncrement node)
    {
        inAPreSubIncrement(node);
        if(node.getMinusInc() != null)
        {
            node.getMinusInc().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAPreSubIncrement(node);
    }

    public void inAPostSubIncrement(APostSubIncrement node)
    {
        defaultIn(node);
    }

    public void outAPostSubIncrement(APostSubIncrement node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAPostSubIncrement(APostSubIncrement node)
    {
        inAPostSubIncrement(node);
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        if(node.getMinusInc() != null)
        {
            node.getMinusInc().apply(this);
        }
        outAPostSubIncrement(node);
    }

    public void inAParams(AParams node)
    {
        defaultIn(node);
    }

    public void outAParams(AParams node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParams(AParams node)
    {
        inAParams(node);
        if(node.getParam() != null)
        {
            node.getParam().apply(this);
        }
        {
            List<PAdditionalParam> copy = new ArrayList<PAdditionalParam>(node.getAdditionalParams());
            for(PAdditionalParam e : copy)
            {
                e.apply(this);
            }
        }
        outAParams(node);
    }

    public void inAAdditionalParam(AAdditionalParam node)
    {
        defaultIn(node);
    }

    public void outAAdditionalParam(AAdditionalParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAdditionalParam(AAdditionalParam node)
    {
        inAAdditionalParam(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getParam() != null)
        {
            node.getParam().apply(this);
        }
        outAAdditionalParam(node);
    }

    public void inAParam(AParam node)
    {
        defaultIn(node);
    }

    public void outAParam(AParam node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAParam(AParam node)
    {
        inAParam(node);
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        if(node.getIdent() != null)
        {
            node.getIdent().apply(this);
        }
        outAParam(node);
    }

    public void inAArgs(AArgs node)
    {
        defaultIn(node);
    }

    public void outAArgs(AArgs node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArgs(AArgs node)
    {
        inAArgs(node);
        if(node.getArg() != null)
        {
            node.getArg().apply(this);
        }
        {
            List<PAdditionalArg> copy = new ArrayList<PAdditionalArg>(node.getAdditionalArgs());
            for(PAdditionalArg e : copy)
            {
                e.apply(this);
            }
        }
        outAArgs(node);
    }

    public void inAAdditionalArg(AAdditionalArg node)
    {
        defaultIn(node);
    }

    public void outAAdditionalArg(AAdditionalArg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAAdditionalArg(AAdditionalArg node)
    {
        inAAdditionalArg(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getArg() != null)
        {
            node.getArg().apply(this);
        }
        outAAdditionalArg(node);
    }

    public void inAArg(AArg node)
    {
        defaultIn(node);
    }

    public void outAArg(AArg node)
    {
        defaultOut(node);
    }

    @Override
    public void caseAArg(AArg node)
    {
        inAArg(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
        outAArg(node);
    }
}