package com.golcher.tidsintervall.dialoger.modal;

import com.golcher.tidsintervall.dbproviders.ITidsIntervallDbProvider;
import com.golcher.tidsintervall.dbproviders.MockupProvider;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModell;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogModellFabrik;
import com.golcher.tidsintervall.dialoger.TidsintervallDialogPanel;
import com.golcher.tidsintervall.komponenter.data.KomponentFilter;
import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;
import com.golcher.tidsintervall.redigerare.IEgnaIntervallRedigerare;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ModalDialogDemo extends JDialog
{
    private TidsintervallDialogPanel _panel;
    private TidsintervallDialogModell _modell;
    private AnvandarensVal _anvandarensVal;


    public ModalDialogDemo(
            ITidsIntervallDbProvider standardProvider,
            ITidsIntervallDbProvider egenProvider,
            ITidsIntervallDbProvider valdaProvider,
            IEgnaIntervallRedigerare redigerare
    )
    {
        _anvandarensVal = AnvandarensVal.IngetValGjort;
        _modell = TidsintervallDialogModellFabrik.skapaModell(standardProvider, egenProvider, valdaProvider);
        _panel = new TidsintervallDialogPanel(_modell, redigerare);

        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // this.add(fixaStatusRad(), BorderLayout.NORTH);
        this.add(_panel, BorderLayout.CENTER);
        this.add(fixaKnappar(), BorderLayout.SOUTH);

        this.setSize(530, 250);

        this.setVisible(false);
    }

    private JPanel fixaStatusRad()
    {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4, 4, 4, 4)));
        final JLabel status = new JLabel();
        statusBar.add(status);

        addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                status.setText(ModalDialogDemo.this.getWidth() + "x" + ModalDialogDemo.this.getHeight());
            }
        });

        return statusBar;
    }

    private JPanel fixaKnappar()
    {
        JButton avbryt = new JButton("Avbryt");
        avbryt.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                avbryt();
            }
        });

        JButton spara = new JButton("Spara");
        spara.addActionListener(new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                spara();
            }
        });

        JPanel knappar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        knappar.setBackground(Color.DARK_GRAY);
        knappar.setOpaque(true);
        knappar.add(avbryt);
        knappar.add(spara);
        return knappar;
    }

    private void avbryt()
    {
        _anvandarensVal = AnvandarensVal.Avbryt;
        this.setVisible(false);
        this.dispose();
    }

    private void spara()
    {
        _anvandarensVal = AnvandarensVal.Spara;
        this.setVisible(false);
        this.dispose();
    }

    public DialogUtdata visaDialog()
    {
        this.setVisible(true); // Pausar tills användaren gör något som stänger dialogen (avbryt, spara, stänger fönstret)

        DialogUtdata utdata = new DialogUtdata(_anvandarensVal);
        if (_anvandarensVal.equals(AnvandarensVal.Spara))
        {
            for (TidsintervallWrapper wrapper : _modell.hamtaModellFor(KomponentFilter.Valda))
            {
                utdata.laggTillPost(wrapper.varde());
            }
        }
        return utdata;
    }
}
