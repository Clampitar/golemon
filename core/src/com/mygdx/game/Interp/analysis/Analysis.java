/* This file was generated by SableCC (http://www.sablecc.org/). */

package com.mygdx.game.Interp.analysis;

import com.mygdx.game.Interp.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProg(AProg node);
    void caseAFunDecl(AFunDecl node);
    void caseAFunBody(AFunBody node);
    void caseADeclInst(ADeclInst node);
    void caseAAssignInst(AAssignInst node);
    void caseAIfElseInst(AIfElseInst node);
    void caseAReturnInst(AReturnInst node);
    void caseAPrintExpInst(APrintExpInst node);
    void caseAPrintlnInst(APrintlnInst node);
    void caseABlockInst(ABlockInst node);
    void caseAThenPart(AThenPart node);
    void caseAElsePart(AElsePart node);
    void caseAEqExp(AEqExp node);
    void caseALtExp(ALtExp node);
    void caseASimpleExp(ASimpleExp node);
    void caseAAddAdditiveExp(AAddAdditiveExp node);
    void caseASubAdditiveExp(ASubAdditiveExp node);
    void caseASimpleAdditiveExp(ASimpleAdditiveExp node);
    void caseATrueTerm(ATrueTerm node);
    void caseAFalseTerm(AFalseTerm node);
    void caseANumberTerm(ANumberTerm node);
    void caseAStringTerm(AStringTerm node);
    void caseAVarTerm(AVarTerm node);
    void caseAParTerm(AParTerm node);
    void caseAFunCallTerm(AFunCallTerm node);

    void caseTPrint(TPrint node);
    void caseTPrintln(TPrintln node);
    void caseTTrue(TTrue node);
    void caseTFalse(TFalse node);
    void caseTIf(TIf node);
    void caseTThen(TThen node);
    void caseTElse(TElse node);
    void caseTEnd(TEnd node);
    void caseTFun(TFun node);
    void caseTReturn(TReturn node);
    void caseTDecl(TDecl node);
    void caseTSc(TSc node);
    void caseTAssign(TAssign node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTLt(TLt node);
    void caseTEq(TEq node);
    void caseTLPar(TLPar node);
    void caseTRPar(TRPar node);
    void caseTLBr(TLBr node);
    void caseTRBr(TRBr node);
    void caseTIdent(TIdent node);
    void caseTString(TString node);
    void caseTNumber(TNumber node);
    void caseTBlank(TBlank node);
    void caseTComment(TComment node);
    void caseEOF(EOF node);
    void caseInvalidToken(InvalidToken node);
}
