package com.golcher.tidsintervall.dbproviders;

import com.golcher.tidsintervall.komponenter.data.TidsintervallVo;

import java.util.ArrayList;

public interface ITidsIntervallDbProvider
{
    ArrayList<TidsintervallVo> hamtaData();
}
