package com.worksn.interfaces;

import com.worksn.objects.MyContext;

public interface NetCallback {
    public void callback(MyContext data, Integer result, String stringData);
}
