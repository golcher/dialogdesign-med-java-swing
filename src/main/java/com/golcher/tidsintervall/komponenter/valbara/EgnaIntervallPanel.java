package com.golcher.tidsintervall.komponenter.valbara;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class EgnaIntervallPanel extends AbstraktTidsintervallValbarKomponent
{
    private final IEgnaIntervallRedigerare _redigerare;
    public EgnaIntervallPanel
    (
        ITidsintervallModellFasad modellFasad,
        IEgnaIntervallRedigerare redigerare
    )
    {
        super("Egna intervall", "Välj", modellFasad, KomponentFilter.Egen);
        _redigerare = redigerare;
        if(null!=_redigerare) this.add(redigeringsKnapp());
    }

    private JButton redigeringsKnapp()
    {
        JButton knapp = new JButton("Redigera");
        knapp.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ArrayList<TidsintervallVo> egnaIntervall = _redigerare.visaRedigeringsDialog();
                uppdateraEgnaIntervallFranRedigerare(egnaIntervall);
            }
        });
        return knapp;
    }

}
