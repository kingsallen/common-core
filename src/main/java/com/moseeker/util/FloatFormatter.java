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

    public static String toString(double bonus) {
        if (bonus - (int)bonus == 0) {
            return bonusFormat.format(bonus);
        } else if(bonus*10 - (int)(bonus*10) == 0) {
            return bonus1Format.format(bonus);
        } else {
            return bonus2Format.format(bonus);
        }
    }
}
