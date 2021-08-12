package com.happy.panda.design.impl;

import androidx.annotation.NonNull;

import com.happy.panda.design.interfaces.ITea;

import java.math.BigDecimal;

/**
 * 功能描述 啵啵奶茶
 */
public class BoBoTea implements ITea {

    private OriginalTea mTea;

    public BoBoTea(OriginalTea tea) {
        this.mTea = tea;
    }

    @Override
    public BigDecimal getTeaPrice() {
        return mTea.getTeaPrice().add(BigDecimal.valueOf(20));
    }

    @Override
    public String getTeaName() {
        return "啵啵" + mTea.getTeaName();
    }

    @NonNull
    @Override
    public String toString() {
        return getTeaName() + "--" + getTeaPrice();
    }
}
