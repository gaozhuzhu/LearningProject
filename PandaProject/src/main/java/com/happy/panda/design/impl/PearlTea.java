package com.happy.panda.design.impl;

import androidx.annotation.NonNull;

import com.happy.panda.design.interfaces.ITea;

import java.math.BigDecimal;

/**
 * 功能描述 珍珠奶茶
 */
public class PearlTea implements ITea {

    private final OriginalTea mTea;

    public PearlTea(OriginalTea tea) {
        this.mTea = tea;
    }

    @Override
    public BigDecimal getTeaPrice() {
        return mTea.getTeaPrice().add(BigDecimal.valueOf(10));
    }

    @Override
    public String getTeaName() {
        return "珍珠" + mTea.getTeaName();
    }

    @NonNull
    @Override
    public String toString() {
        return getTeaName() + "--" + getTeaPrice();
    }
}
