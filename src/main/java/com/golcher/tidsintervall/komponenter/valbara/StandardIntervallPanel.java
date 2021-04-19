package com.golcher.tidsintervall.komponenter.valbara;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;

public class StandardIntervallPanel extends AbstraktTidsintervallValbarKomponent
{
    public StandardIntervallPanel
    (
        ITidsintervallModellFasad modellFasad
    )
    {
        super("Standardintervall", "Välj", modellFasad, KomponentFilter.Standard);
    }
}
