/* This file was generated by SableCC (http://www.sablecc.org/). */

package gdx.game.Interp.parser;

import gdx.game.Interp.node.*;
import gdx.game.Interp.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTPrint(@SuppressWarnings("unused") TPrint node)
    {
        this.index = 0;
    }

    @Override
    public void caseTPrintln(@SuppressWarnings("unused") TPrintln node)
    {
        this.index = 1;
    }

    @Override
    public void caseTTrue(@SuppressWarnings("unused") TTrue node)
    {
        this.index = 2;
    }

    @Override
    public void caseTFalse(@SuppressWarnings("unused") TFalse node)
    {
        this.index = 3;
    }

    @Override
    public void caseTIf(@SuppressWarnings("unused") TIf node)
    {
        this.index = 4;
    }

    @Override
    public void caseTThen(@SuppressWarnings("unused") TThen node)
    {
        this.index = 5;
    }

    @Override
    public void caseTElse(@SuppressWarnings("unused") TElse node)
    {
        this.index = 6;
    }

    @Override
    public void caseTEnd(@SuppressWarnings("unused") TEnd node)
    {
        this.index = 7;
    }

    @Override
    public void caseTReturn(@SuppressWarnings("unused") TReturn node)
    {
        this.index = 8;
    }

    @Override
    public void caseTStruct(@SuppressWarnings("unused") TStruct node)
    {
        this.index = 9;
    }

    @Override
    public void caseTDo(@SuppressWarnings("unused") TDo node)
    {
        this.index = 10;
    }

    @Override
    public void caseTVar(@SuppressWarnings("unused") TVar node)
    {
        this.index = 11;
    }

    @Override
    public void caseTWhile(@SuppressWarnings("unused") TWhile node)
    {
        this.index = 12;
    }

    @Override
    public void caseTFor(@SuppressWarnings("unused") TFor node)
    {
        this.index = 13;
    }

    @Override
    public void caseTIntType(@SuppressWarnings("unused") TIntType node)
    {
        this.index = 14;
    }

    @Override
    public void caseTBoolType(@SuppressWarnings("unused") TBoolType node)
    {
        this.index = 15;
    }

    @Override
    public void caseTStringType(@SuppressWarnings("unused") TStringType node)
    {
        this.index = 16;
    }

    @Override
    public void caseTVoidType(@SuppressWarnings("unused") TVoidType node)
    {
        this.index = 17;
    }

    @Override
    public void caseTSay(@SuppressWarnings("unused") TSay node)
    {
        this.index = 18;
    }

    @Override
    public void caseTFrameAdvance(@SuppressWarnings("unused") TFrameAdvance node)
    {
        this.index = 19;
    }

    @Override
    public void caseTWalk(@SuppressWarnings("unused") TWalk node)
    {
        this.index = 20;
    }

    @Override
    public void caseTMoveCam(@SuppressWarnings("unused") TMoveCam node)
    {
        this.index = 21;
    }

    @Override
    public void caseTSc(@SuppressWarnings("unused") TSc node)
    {
        this.index = 22;
    }

    @Override
    public void caseTAssign(@SuppressWarnings("unused") TAssign node)
    {
        this.index = 23;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 24;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 25;
    }

    @Override
    public void caseTMult(@SuppressWarnings("unused") TMult node)
    {
        this.index = 26;
    }

    @Override
    public void caseTPlusInc(@SuppressWarnings("unused") TPlusInc node)
    {
        this.index = 27;
    }

    @Override
    public void caseTMinusInc(@SuppressWarnings("unused") TMinusInc node)
    {
        this.index = 28;
    }

    @Override
    public void caseTModulo(@SuppressWarnings("unused") TModulo node)
    {
        this.index = 29;
    }

    @Override
    public void caseTLt(@SuppressWarnings("unused") TLt node)
    {
        this.index = 30;
    }

    @Override
    public void caseTGt(@SuppressWarnings("unused") TGt node)
    {
        this.index = 31;
    }

    @Override
    public void caseTEq(@SuppressWarnings("unused") TEq node)
    {
        this.index = 32;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 33;
    }

    @Override
    public void caseTDot(@SuppressWarnings("unused") TDot node)
    {
        this.index = 34;
    }

    @Override
    public void caseTLPar(@SuppressWarnings("unused") TLPar node)
    {
        this.index = 35;
    }

    @Override
    public void caseTRPar(@SuppressWarnings("unused") TRPar node)
    {
        this.index = 36;
    }

    @Override
    public void caseTIdent(@SuppressWarnings("unused") TIdent node)
    {
        this.index = 37;
    }

    @Override
    public void caseTString(@SuppressWarnings("unused") TString node)
    {
        this.index = 38;
    }

    @Override
    public void caseTInteger(@SuppressWarnings("unused") TInteger node)
    {
        this.index = 39;
    }

    @Override
    public void caseTFloat(@SuppressWarnings("unused") TFloat node)
    {
        this.index = 40;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 41;
    }
}
