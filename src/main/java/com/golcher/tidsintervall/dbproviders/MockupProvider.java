package com.golcher.tidsintervall.dbproviders;

import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallKalla;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;

import java.util.ArrayList;

public class MockupProvider implements ITidsIntervallDbProvider
{
    private final ArrayList<TidsintervallVo> _fejkadData;

    public MockupProvider(KomponentFilter fejkaDatakallaFor)
    {
        _fejkadData = new ArrayList<>();
        switch (fejkaDatakallaFor)
        {
            case Standard: fejkaStandard(); break;
            case Egen: fejkaEgen(); break;
            case Valda: fejkaValda(); break;
        }
    }

    private void fejkaValda()
    {
        _fejkadData.add(new TidsintervallVo("1", TidsintervallKalla.Standard, "08:00", "12:00", "Förmiddag"));
        _fejkadData.add(new TidsintervallVo("3", TidsintervallKalla.Egen, "13:00", "15:00", ""));
    }

    private void fejkaEgen()
    {
        _fejkadData.add(new TidsintervallVo("1", TidsintervallKalla.Egen, "08:00", "10:00", ""));
        _fejkadData.add(new TidsintervallVo("2", TidsintervallKalla.Egen, "10:00", "12:00", ""));
        _fejkadData.add(new TidsintervallVo("3", TidsintervallKalla.Egen, "13:00", "15:00", ""));
    }

    private void fejkaStandard()
    {
        _fejkadData.add(new TidsintervallVo("1", TidsintervallKalla.Standard, "08:00", "12:00", "Förmiddag"));
        _fejkadData.add(new TidsintervallVo("2", TidsintervallKalla.Standard, "13:00", "17:00", "Eftermiddag"));
        _fejkadData.add(new TidsintervallVo("3", TidsintervallKalla.Standard, "18:00", "22:00", "Kväll"));
        _fejkadData.add(new TidsintervallVo("4", TidsintervallKalla.Standard, "08:00", "22:00", "Heldag"));
    }

    @Override
    public ArrayList<TidsintervallVo> hamtaData()
    {
        return _fejkadData;
    }
}
