package com.golcher.tidsintervall.dialoger;

import com.golcher.tidsintervall.komponenter.valbara.EgnaIntervallPanel;
import com.golcher.tidsintervall.komponenter.valbara.StandardIntervallPanel;
import com.golcher.tidsintervall.komponenter.valda.ValdaIntervallPanel;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

import javax.swing.*;
import java.awt.*;

public class TidsintervallDialogPanel extends JPanel
{
    private StandardIntervallPanel _standardIntervallPanel;
    private EgnaIntervallPanel _egnaIntervallPanel;
    private ValdaIntervallPanel _valdaIntervallPanel;

    public TidsintervallDialogPanel(TidsintervallDialogModell modell, IEgnaIntervallRedigerare redigerare)
    {
        _standardIntervallPanel = new StandardIntervallPanel(modell);
        _egnaIntervallPanel = new EgnaIntervallPanel(modell, redigerare);
        _valdaIntervallPanel = new ValdaIntervallPanel(modell);

        fixaLayout();
    }

    private void fixaLayout()
    {
        this.setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel valbaraPanel = new JPanel();
        valbaraPanel.setLayout(new BoxLayout(valbaraPanel, BoxLayout.Y_AXIS));
        valbaraPanel.add(_standardIntervallPanel);
        valbaraPanel.add(_egnaIntervallPanel);
        this.add(valbaraPanel);
        this.add(_valdaIntervallPanel);
    }
}
