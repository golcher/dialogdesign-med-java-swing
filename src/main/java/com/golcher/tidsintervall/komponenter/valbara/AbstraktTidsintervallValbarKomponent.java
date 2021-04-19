package com.golcher.tidsintervall.komponenter.valbara;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.komponenter.AbstraktTidsintervallDialogKomponent;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallNyckel;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class AbstraktTidsintervallValbarKomponent extends AbstraktTidsintervallDialogKomponent
{
    private final JComboBox _valbara;
    private TidsintervallValbarModell _komponentModell;

    public AbstraktTidsintervallValbarKomponent
    (
        String ramRubrik,
        String knappRubrik,
        ITidsintervallModellFasad modellFasad,
        KomponentFilter filter
    )
    {
        super(modellFasad, filter);
        this.setBorder
        (
            BorderFactory.createTitledBorder
            (
                BorderFactory.createEtchedBorder(),
                ramRubrik,
                TitledBorder.LEFT,
                TitledBorder.TOP
            )
        );
        this.setLayout(new FlowLayout(FlowLayout.LEFT));

        _komponentModell = new TidsintervallValbarModell();
        _valbara = new JComboBox<>(_komponentModell);
        _valbara.setPreferredSize(new Dimension(hamtaLampligBredd(), 20));
        this.add(_valbara);

        JButton valjKnapp = new JButton(knappRubrik);
        this.add(valjKnapp);

        valjKnapp.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(AbstraktTidsintervallValbarKomponent.this._valbara.getItemCount() > 0)
                {
                    TidsintervallWrapper wrapper = (TidsintervallWrapper) AbstraktTidsintervallValbarKomponent.this._valbara.getSelectedItem();
                    if(null!=wrapper)
                    {
                        TidsintervallNyckel nyckel = wrapper.nyckel();
                        valj(nyckel);
                    }
                }
            }
        });

        uppdateraLokalModell();
    }

    private void uppdateraLokalModell()
    {
        ArrayList<TidsintervallWrapper> sorteradLista = hamtaKomponentDataFranDialogModell();
        _komponentModell.uppdateraModellen(sorteradLista);
        _valbara.setPreferredSize(new Dimension(hamtaLampligBredd(),20));
        _valbara.invalidate();
    }

    @Override
    public void modellenHarUppdaterats()
    {
        uppdateraLokalModell();
    }


}