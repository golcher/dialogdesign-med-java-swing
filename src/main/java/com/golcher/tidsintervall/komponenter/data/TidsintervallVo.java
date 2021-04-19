package com.golcher.tidsintervall.komponenter.data;

public class TidsintervallVo
{
    private final String _id;
    private final TidsintervallKalla _kalla;
    private final String _start;
    private final String _slut;
    private final String _namn;

    public TidsintervallVo(
            String _id,
            TidsintervallKalla _kalla,
            String _start,
            String _slut,
            String _namn
    )
    {
        this._id = _id;
        this._kalla = _kalla;
        this._start = _start;
        this._slut = _slut;
        this._namn = _namn;
    }

    public String getId()
    {
        return _id;
    }

    public TidsintervallKalla getKalla()
    {
        return _kalla;
    }

    public String getStart()
    {
        return _start;
    }

    public String getSlut()
    {
        return _slut;
    }

    public String getNamn()
    {
        return _namn;
    }

    @Override
    public String toString()
    {
        return _start + " - " + _slut + ((_namn.length() > 0) ? "(" + _namn + ")" : "");
    }
}
