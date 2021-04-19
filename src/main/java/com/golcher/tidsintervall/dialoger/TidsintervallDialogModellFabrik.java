package com.golcher.tidsintervall.dialoger;

import com.golcher.tidsintervall.dbproviders.ITidsIntervallDbProvider;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;

import java.util.ArrayList;

public class TidsintervallDialogModellFabrik
{
    public static TidsintervallDialogModell skapaModell
    (
        ITidsIntervallDbProvider standardProvider,
        ITidsIntervallDbProvider egenProvider,
        ITidsIntervallDbProvider valdaProvider
    )
    {
        ArrayList<TidsintervallVo> standardIntervall = standardProvider.hamtaData();
        ArrayList<TidsintervallVo> egnaIntervall = egenProvider.hamtaData();
        ArrayList<TidsintervallVo> valdaIntervall = valdaProvider.hamtaData();
        TidsintervallDialogModell modell = new TidsintervallDialogModell(standardIntervall, egnaIntervall, valdaIntervall);
        return modell;
    }
}
