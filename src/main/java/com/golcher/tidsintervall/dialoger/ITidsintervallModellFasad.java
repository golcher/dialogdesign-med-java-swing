package com.golcher.tidsintervall.dialoger;

import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallNyckel;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import java.util.ArrayList;

public interface ITidsintervallModellFasad
{
    ArrayList<TidsintervallWrapper> hamtaModellFor(KomponentFilter filter);
    void registreraLyssnare(ITidsintervallModellLyssnare lyssnare);
    void valj(TidsintervallNyckel nyckel);
    void valjBort(TidsintervallNyckel nyckel);
    void uppdateraEgnaIntervallFranRedigerare(ArrayList<TidsintervallVo> egnaIntervall);
}
