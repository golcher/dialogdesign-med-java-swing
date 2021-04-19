package com.golcher.tidsintervall.komponenter.data;

public class TidsintervallNyckel
{
    private final String _nyckel; // Immutable key (id + kalla) att använda i HashMap

    public TidsintervallNyckel(TidsintervallVo vo)
    {
        _nyckel = vo.getId() + "-" + vo.getKalla().toString();
    }

    @Override
    public String toString()
    {
        return _nyckel;
    }

}
