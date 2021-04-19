package com.golcher.tidsintervall.komponenter.valda;

import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class ValdaIntervallListModell implements ListModel<TidsintervallWrapper>
{
    private ArrayList<TidsintervallWrapper> _lista;
    private ArrayList<ListDataListener> _lyssnare;

    public ValdaIntervallListModell()
    {
        _lista = new ArrayList<>();
        _lyssnare = new ArrayList<>();
    }

    public void uppdateraModellen(ArrayList<TidsintervallWrapper> nyLista)
    {
        _lista = nyLista;
        for(ListDataListener l : _lyssnare)
        {
            l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, _lista.size()-1));
        }
    }

    @Override
    public int getSize()
    {
        return _lista.size();
    }

    @Override
    public TidsintervallWrapper getElementAt(int index)
    {
        if(index < 0 || index > _lista.size()-1) throw new ArrayIndexOutOfBoundsException(index);
        return _lista.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l)
    {
        _lyssnare.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l)
    {
        _lyssnare.remove(l);
    }
}
