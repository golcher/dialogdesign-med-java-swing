package com.golcher.tidsintervall.komponenter.data;

public class TidsintervallWrapper
{
    private final TidsintervallNyckel _nyckel; // Immutable key att använda i HashMap
    private TidsintervallVo _varde;
    private boolean _arVald;

    public TidsintervallWrapper(TidsintervallVo vo)
    {
        _nyckel = new TidsintervallNyckel(vo);
        _varde = vo;
        _arVald = false;
    }

    public void sattStatus(boolean arVald)
    {
        _arVald = arVald;
    }

    public TidsintervallNyckel nyckel()
    {
        return _nyckel;
    }

    public TidsintervallVo varde()
    {
        return _varde;
    }

    public boolean arVald()
    {
        return _arVald;
    }

    @Override
    public String toString()
    {
        return _varde.toString();
    }

    public void sattVarde(TidsintervallVo vo)
    {
        _varde = vo;
    }
}
