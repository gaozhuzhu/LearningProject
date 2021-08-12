package com.happy.panda.design.impl;

import androidx.annotation.NonNull;

import com.happy.panda.design.interfaces.ITea;

import java.math.BigDecimal;

/**
 * 功能描述 原味奶茶
 */
public class OriginalTea implements ITea {


    @Override
    public BigDecimal getTeaPrice() {
        return BigDecimal.valueOf(10);
    }

    @Override
    public String getTeaName() {
        return "原味奶茶";
    }

    @NonNull
    @Override
    public String toString() {
        return getTeaName()+"--"+getTeaPrice();
    }
}
