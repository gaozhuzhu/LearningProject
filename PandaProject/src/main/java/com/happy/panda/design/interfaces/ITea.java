package com.happy.panda.design.interfaces;

import java.math.BigDecimal;

/**
 * 茶类接口
 */
public interface ITea {

    /**
     * 奶茶价格
     */
    BigDecimal getTeaPrice();

    /**
     * 获取奶茶名
     *
     * @return 奶茶名
     */
    String getTeaName();
}
