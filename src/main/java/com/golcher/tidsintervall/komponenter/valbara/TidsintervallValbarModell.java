package com.golcher.tidsintervall.komponenter.valbara;

import com.golcher.tidsintervall.komponenter.data.TidsintervallWrapper;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class TidsintervallValbarModell implements ComboBoxModel
{
    private ArrayList<TidsintervallWrapper> _lista;
    private ArrayList<ListDataListener> _lyssnare;
    private TidsintervallWrapper _selectedItem;

    public TidsintervallValbarModell()
    {
        _lista = new ArrayList<>();
        _lyssnare = new ArrayList<>();
        _selectedItem = null;
    }

    public void uppdateraModellen(ArrayList<TidsintervallWrapper> nyLista)
    {
        _lista = nyLista;

        TidsintervallWrapper currentItem = null;
        if(_lista.size() > 0)
        {
            currentItem = _lista.get(0);
        }
        _selectedItem = currentItem;

        for(ListDataListener l : _lyssnare)
        {
            l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, _lista.size()-1));
        }
    }

    @Override
    public void setSelectedItem(Object anItem)
    {
        if(null!=anItem && anItem instanceof TidsintervallWrapper)
        {
            _selectedItem = (TidsintervallWrapper) anItem;
        }
    }

    @Override
    public Object getSelectedItem()
    {
        return _selectedItem;
    }

    @Override
    public int getSize()
    {
        return _lista.size();
    }

    @Override
    public Object getElementAt(int index)
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
