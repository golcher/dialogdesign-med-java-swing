package com.golcher.tidsintervall.dialoger;

import com.golcher.tidsintervall.komponenter.data.*;

import java.util.*;

public class TidsintervallDialogModell implements ITidsintervallModellFasad
{
    private final HashMap<TidsintervallNyckel, TidsintervallWrapper> _nyckelTillNodMap;
    private final ArrayList<ITidsintervallModellLyssnare> _lyssnare;
    private boolean _anvandarenAvbrot;

    public TidsintervallDialogModell
    (
        ArrayList<TidsintervallVo> standardIntervall,
        ArrayList<TidsintervallVo> egnaIntervall,
        ArrayList<TidsintervallVo> redanValdaIntervall
    )
    {
        _nyckelTillNodMap = new HashMap<>();
        laggTill(standardIntervall);
        laggTill(egnaIntervall);
        initieraRedanValda(redanValdaIntervall);
        _lyssnare = new ArrayList<>();
        _anvandarenAvbrot = false;
    }

    private void initieraRedanValda
    (
        ArrayList<TidsintervallVo> redanValdaIntervall
    )
    {
        for(TidsintervallVo vo : redanValdaIntervall)
        {
            TidsintervallNyckel nyckel = new TidsintervallNyckel(vo);
            if(_nyckelTillNodMap.containsKey(nyckel))
            {
                _nyckelTillNodMap.get(nyckel).sattStatus(true);
            }
        }
    }

    private void laggTill
    (
        ArrayList<TidsintervallVo> lista
    )
    {
        if (null != lista)
        {
            for (TidsintervallVo vo : lista)
            {
                TidsintervallWrapper wrapper = new TidsintervallWrapper(vo);
                wrapper.sattStatus(false);
                _nyckelTillNodMap.put(wrapper.nyckel(), wrapper);
            }
        }
    }

    @Override
    public ArrayList<TidsintervallWrapper> hamtaModellFor (KomponentFilter filter)
    {
        ArrayList<TidsintervallWrapper> komponentDataModell = new ArrayList<>();
        for (Map.Entry<TidsintervallNyckel, TidsintervallWrapper> entry : _nyckelTillNodMap.entrySet())
        {
            TidsintervallWrapper wrapper = entry.getValue();
            if (wrapper.arVald() && filter.equals(KomponentFilter.Valda))
            {
                komponentDataModell.add(wrapper);
            }
            else if
            (
                !wrapper.arVald() &&
                (
                    (wrapper.varde().getKalla().equals(TidsintervallKalla.Standard) && filter.equals(KomponentFilter.Standard))
                    ||
                    (wrapper.varde().getKalla().equals(TidsintervallKalla.Egen) && filter.equals(KomponentFilter.Egen))
                )
            )
            {
                komponentDataModell.add(wrapper);
            }
        }

        Collections.sort(komponentDataModell, (o1, o2) ->
        {
            String s1 = o1.toString();
            String s2 = o2.toString();
            return s1.compareTo(s2);
        });

        return komponentDataModell;
    }

    @Override
    public void registreraLyssnare (ITidsintervallModellLyssnare lyssnare)
    {
        if (!_lyssnare.contains(lyssnare)) _lyssnare.add(lyssnare);
    }

    // Dessa två metoder används från komponenterna för att påverka modellen
    public void valj (TidsintervallNyckel nyckel)
    {
        _nyckelTillNodMap.get(nyckel).sattStatus(true);
        notifieraLyssnare();
    }
    public void valjBort (TidsintervallNyckel nyckel)
    {
        _nyckelTillNodMap.get(nyckel).sattStatus(false);
        notifieraLyssnare();
    }

    @Override
    public void uppdateraEgnaIntervallFranRedigerare(ArrayList<TidsintervallVo> egnaIntervall)
    {
        for(TidsintervallVo vo : egnaIntervall)
        {
            TidsintervallNyckel nyckel = new TidsintervallNyckel(vo);
            if(_nyckelTillNodMap.containsKey(nyckel))
            {
                TidsintervallWrapper wrapper = _nyckelTillNodMap.get(nyckel);
                wrapper.sattVarde(vo);
            }
            else
            {
                TidsintervallWrapper wrapper = new TidsintervallWrapper(vo);
                _nyckelTillNodMap.put(wrapper.nyckel(), wrapper);
            }
        }
    }

    private void notifieraLyssnare ()
    {
        for (ITidsintervallModellLyssnare l : _lyssnare)
        {
            l.modellenHarUppdaterats();
        }
    }
}