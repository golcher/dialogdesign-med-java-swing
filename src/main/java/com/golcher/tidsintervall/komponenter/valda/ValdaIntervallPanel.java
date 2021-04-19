package com.golcher.tidsintervall.komponenter.valda;

import com.golcher.tidsintervall.dialoger.ITidsintervallModellFasad;
import com.golcher.tidsintervall.komponenter.AbstraktTidsintervallDialogKomponent;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class ValdaIntervallPanel extends AbstraktTidsintervallDialogKomponent
{
    private static final String ramRubrik = "Valda intervall";
    private static final String rensaRubrik = "Rensa";
    private static final String taBortRubrik = "Ta bort markerade";

    private ValdaIntervallListModell _komponentModell;
    private JList<TidsintervallWrapper> _valda;

    public ValdaIntervallPanel
    (
        ITidsintervallModellFasad modellFasad
    )
    {
        super(modellFasad, KomponentFilter.Valda);
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

        this.setLayout(new BorderLayout());

        _komponentModell = new ValdaIntervallListModell();
        _valda = new JList(_komponentModell);
        _valda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _valda.setPreferredSize(new Dimension(hamtaLampligBredd(), 100));
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.add(_valda);
        scrollPane.setPreferredSize(new Dimension(hamtaLampligBredd(), 100));
        this.add(scrollPane, BorderLayout.CENTER);

        byggKnappPanel();

        uppdateraLokalModell();
    }

    private void byggKnappPanel()
    {
        JButton rensaKnapp = new JButton(rensaRubrik);
        rensaKnapp.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ValdaIntervallPanel.this.rensa();
            }
        });

        JButton taBortKnapp = new JButton(taBortRubrik);
        taBortKnapp.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ValdaIntervallPanel.this.taBort();
            }
        });

        JPanel knappPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        knappPanel.add(rensaKnapp);
        knappPanel.add(taBortKnapp);

        this.add(knappPanel, BorderLayout.SOUTH);
    }

    private void uppdateraLokalModell()
    {
        ArrayList<TidsintervallWrapper> sorteradLista = hamtaKomponentDataFranDialogModell();
        _komponentModell.uppdateraModellen(sorteradLista);
        _valda.setPreferredSize(new Dimension(hamtaLampligBredd(), 100));
        _valda.invalidate();
    }

    public void rensa()
    {
        // Vi kommer uppdatera modellen direkt när vi kör metoden valjBort, så vi måste först lägga över
        // de element som finns i listan i en egen array
        ArrayList<TidsintervallWrapper> elementAttTaBort = new ArrayList<>();
        for(int index=0; index < _komponentModell.getSize(); index++)
        {
            TidsintervallWrapper wrapper = _komponentModell.getElementAt(index);
            elementAttTaBort.add(wrapper);
        }

        // Nu kan vi anropa modellen och förändra, utan att vi snubblar
        // på index som ändras medan vi itererar över innehållet
        for(TidsintervallWrapper wrapper : elementAttTaBort)
        {
            valjBort(wrapper.nyckel());
        }
    }

    public void taBort()
    {
        int index = _valda.getSelectedIndex();
        if(index>=0)
        {
            TidsintervallWrapper wrapper = _komponentModell.getElementAt(index);
            valjBort(wrapper.nyckel());
        }
    }

    @Override
    public void modellenHarUppdaterats()
    {
        uppdateraLokalModell();
    }
}
