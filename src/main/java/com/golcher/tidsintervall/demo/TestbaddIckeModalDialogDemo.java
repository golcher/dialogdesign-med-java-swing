package com.golcher.tidsintervall.demo;

import com.golcher.tidsintervall.dbproviders.ITidsIntervallDbProvider;
import com.golcher.tidsintervall.dbproviders.MockupProvider;
import com.golcher.tidsintervall.dialoger.ITidsintervallModellLyssnare;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModell;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModellFabrik;
import com.golcher.tidsintervall.dialoger.ickemodal.IckeModalDialogDemo;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

public class TestbaddIckeModalDialogDemo implements ITidsintervallModellLyssnare
{
    public static void main(String[] args)
    {
        new TestbaddIckeModalDialogDemo();
    }

    private final TidsintervallDialogModell _modell;

    public TestbaddIckeModalDialogDemo()
    {
        IEgnaIntervallRedigerare redigerare = null;
        ITidsIntervallDbProvider standardProvider = new MockupProvider(KomponentFilter.Standard);
        ITidsIntervallDbProvider egenProvider = new MockupProvider(KomponentFilter.Egen);
        ITidsIntervallDbProvider valdaProvider = new MockupProvider(KomponentFilter.Valda);
        _modell = TidsintervallDialogModellFabrik.skapaModell(standardProvider, egenProvider, valdaProvider);
        _modell.registreraLyssnare(this);
        IckeModalDialogDemo ickeModalDialog = new IckeModalDialogDemo(_modell, redigerare);
    }

    @Override
    public void modellenHarUppdaterats()
    {
        System.out.println("Modellen har uppdaterats:");
        for(TidsintervallWrapper wrapper : _modell.hamtaModellFor(KomponentFilter.Valda))
        {
            System.out.println(wrapper.toString());
        }
    }
}
