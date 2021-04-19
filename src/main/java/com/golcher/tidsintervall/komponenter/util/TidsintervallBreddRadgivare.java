package com.golcher.tidsintervall.komponenter.util;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import javax.swing.*;
import java.util.ArrayList;

public class TidsintervallBreddRadgivare
{
    private int _bredd = 0;

    public TidsintervallBreddRadgivare(ITidsintervallModellFasad modell)
    {
        int storstaBredd = 0;
        ArrayList<TidsintervallWrapper> allaElement = new ArrayList<>();
        allaElement.addAll(modell.hamtaModellFor(KomponentFilter.Standard));
        allaElement.addAll(modell.hamtaModellFor(KomponentFilter.Egen));
        allaElement.addAll(modell.hamtaModellFor(KomponentFilter.Valda));
        for(TidsintervallWrapper wrapper : allaElement)
        {
            JLabel tmp = new JLabel(wrapper.toString());
            int bredd = tmp.getPreferredSize().width + 20;
            if(bredd > storstaBredd) storstaBredd = bredd;
        }
        _bredd = storstaBredd;
    }

    public int hamtaBredd()
    {
        return _bredd;
    }
}
