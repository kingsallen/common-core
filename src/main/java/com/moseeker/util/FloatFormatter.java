package com.moseeker.util;

import java.text.DecimalFormat;

/**
 *
 * 金额格式化展示工具
 * @Author: jack
 * @Date: 2018/10/11
 */
public class FloatFormatter {

    private static DecimalFormat bonusFormat = new DecimalFormat("###################");
    private static DecimalFormat bonus1Format = new DecimalFormat("###################.#");
    private static DecimalFormat bonus2Format = new DecimalFormat("###################.##");

    /**
     * 小数点展示规则
     * 只显示小数点后两位有效数值
     * 如果小数点后最后一位为0则不展示
     * @param bonus 被格式化的数值
     * @return 格式化后的数值
     */
    public static String toString(double bonus) {
        if (bonus - (int)bonus == 0) {
            return bonusFormat.format(bonus);
        } else if(bonus*10 - (int)(bonus*10) == 0) {
            return bonus1Format.format(bonus);
        } else {
            return bonus2Format.format(bonus);
        }
    }

    /**
     * 十进制向左移动两位再格式化
     * @param bonus 被格式化的数值
     * @return 格式化后的数值
     */
    public static String dive100(double bonus) {
        bonus = bonus / 100;
        return toString(bonus);
    }
}
