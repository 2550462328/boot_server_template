package com.iflytek.yys.business.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Description:
 *
 * @author createdBy huizhang43.
 * @date createdAt 2022/3/10 14:08
 **/
public class NumberUtils {
    
    /**
     * 计算占比
     * <p>
     * 默认四舍五入
     *
     * @param scale
     *         保留小数点位数
     * @param isReserve
     *         是否保留小数
     * @param isPercent
     *         是否计算百分比
     * @param b1
     *         分子
     * @param bs
     *         分母 （多个自动计算和）
     *
     * @return
     */
    public static Number computeProportion(int scale, boolean isReserve, boolean isPercent, BigDecimal b1, BigDecimal... bs) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        BigDecimal b2 = BigDecimal.ZERO;
        
        for (BigDecimal b : bs) {
            if (b != null) {
                b2 = b2.add(b);
            }
        }
        
        if (b1.compareTo(BigDecimal.ZERO) == 0 && b2.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        } else if (b1.compareTo(BigDecimal.ZERO) == 0) {
            return 0;
        } else if (b2.compareTo(BigDecimal.ZERO) == 0) {
            return 100;
        }
        
        if (!isPercent) {
            if (scale > 0) {
                return b1.divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
            } else {
                return b1.divide(b2, scale, RoundingMode.HALF_UP).intValue();
            }
        } else {
            if (scale > 0) {
                if (isReserve) {
                    return b1.multiply(BigDecimal.valueOf(100)).divide(b2, scale, RoundingMode.HALF_UP).doubleValue();
                } else {
                    return b1.divide(b2, scale, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).intValue();
                }
            } else {
                return b1.multiply(BigDecimal.valueOf(100)).divide(b2, scale, RoundingMode.HALF_UP).intValue();
            }
        }
    }
}
