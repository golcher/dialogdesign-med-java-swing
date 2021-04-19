package com.golcher.tidsintervall.dialoger.modal;

import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;

import java.util.ArrayList;

public class DialogUtdata
{
    private final AnvandarensVal _anvandarensVal;
    private final ArrayList<TidsintervallVo> _utdata;

    public DialogUtdata(AnvandarensVal anvandarensVal)
    {
        _anvandarensVal = anvandarensVal;
        _utdata = new ArrayList<>();
    }

    public void laggTillPost(TidsintervallVo post)
    {
        _utdata.add(post);
    }

    public ArrayList<TidsintervallVo> hamtaUtdata()
    {
        return _utdata;
    }

    public AnvandarensVal hamtaAnvandarensVal()
    {
        return _anvandarensVal;
    }

}
