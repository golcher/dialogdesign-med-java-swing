package com.golcher.tidsintervall.demo;

import com.golcher.tidsintervall.dbproviders.ITidsIntervallDbProvider;
import com.golcher.tidsintervall.dbproviders.MockupProvider;
import com.golcher.tidsintervall.dialoger.modal.AnvandarensVal;
import com.golcher.tidsintervall.dialoger.modal.DialogUtdata;
import com.golcher.tidsintervall.dialoger.modal.ModalDialogDemo;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

public class TestbaddModalDialogDemo
{
    public static void main(String[] args)
    {
        ITidsIntervallDbProvider standardProvider = new MockupProvider(KomponentFilter.Standard);
        ITidsIntervallDbProvider egenProvider = new MockupProvider(KomponentFilter.Egen);
        ITidsIntervallDbProvider valdaProvider = new MockupProvider(KomponentFilter.Valda);
        IEgnaIntervallRedigerare redigerare = null;

        ModalDialogDemo dialog = new ModalDialogDemo(standardProvider, egenProvider, valdaProvider, redigerare);
        DialogUtdata utdata = dialog.visaDialog();

        System.out.println("Användaren valde " + utdata.hamtaAnvandarensVal().toString());
        if(utdata.hamtaAnvandarensVal().equals(AnvandarensVal.Spara))
        {
            for(TidsintervallVo vo : utdata.hamtaUtdata())
            {
                System.out.println(vo.toString());
            }
        }
    }
}
