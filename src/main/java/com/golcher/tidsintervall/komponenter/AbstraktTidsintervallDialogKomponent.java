package com.golcher.tidsintervall.komponenter;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.dialoger.ITidsintervallModellLyssnare;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallNyckel;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;
import com.golcher.tidsintervall.komponenter.util.TidsintervallBreddRadgivare;

import javax.swing.*;
import java.util.ArrayList;

public abstract class AbstraktTidsintervallDialogKomponent extends JPanel implements ITidsintervallModellLyssnare
{
    private final ITidsintervallModellFasad _modellFasad;
    private final KomponentFilter _komponentFilter;
    private TidsintervallBreddRadgivare _breddRadgivare;

    public AbstraktTidsintervallDialogKomponent
    (
        ITidsintervallModellFasad modellFasad,
        KomponentFilter filter
    )
    {
        _modellFasad = modellFasad;
        _komponentFilter = filter;
        _breddRadgivare = new TidsintervallBreddRadgivare(_modellFasad);

        // Registrera oss som lyssnare på dialogmodellen
        _modellFasad.registreraLyssnare(this);
    }

    protected ArrayList<TidsintervallWrapper> hamtaKomponentDataFranDialogModell()
    {
        return _modellFasad.hamtaModellFor(_komponentFilter);
    }

    protected void valj(TidsintervallNyckel nyckel)
    {
        _modellFasad.valj(nyckel);
    }

    protected void valjBort(TidsintervallNyckel nyckel)
    {
        _modellFasad.valjBort(nyckel);
    }

    protected void uppdateraEgnaIntervallFranRedigerare(ArrayList<TidsintervallVo> egnaIntervall)
    {
        _modellFasad.uppdateraEgnaIntervallFranRedigerare(egnaIntervall);
        _breddRadgivare = new TidsintervallBreddRadgivare(_modellFasad);
    }

    protected int hamtaLampligBredd()
    {
        return _breddRadgivare.hamtaBredd();
    }

}
