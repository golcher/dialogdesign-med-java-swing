package com.golcher.tidsintervall.dialoger.ickemodal;

import com.golcher.tidsintervall.dbproviders.ITidsIntervallDbProvider;
import com.golcher.tidsintervall.dbproviders.MockupProvider;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModell;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModellFabrik;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogPanel;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IckeModalDialogDemo extends JDialog
{
    private TidsintervallDialogPanel _panel;
    private TidsintervallDialogModell _modell;

    public IckeModalDialogDemo(TidsintervallDialogModell modell, IEgnaIntervallRedigerare redigerare)
    {
        _modell = modell;
        _panel = new TidsintervallDialogPanel(_modell, redigerare);

        this.setModal(false);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(_panel, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
