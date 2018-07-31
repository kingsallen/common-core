package com.moseeker.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

import com.moseeker.exception.BaseException;

/**
 * @category BigDecimal计算工具类
 */
public final class CalculateUtil {

	public static final BigDecimal parsePrice(BigDecimal price) {
		return parseValue(price, C.PRECISION_PRICE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parsePrice(Integer price) {
		return parseValue(price, C.PRECISION_PRICE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parsePrice(Long price) {
		return parseValue(price, C.PRECISION_PRICE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parsePrice(String price) {
		return parseValue(price, C.PRECISION_PRICE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseAmount(BigDecimal amount) {
		return parseValue(amount, C.PRECISION_AMOUNT, RoundingMode.UP);
	}

	public static final BigDecimal parseAmount(Integer amount) {
		return parseValue(amount, C.PRECISION_AMOUNT, RoundingMode.UP);
	}

	public static final BigDecimal parseAmount(Long amount) {
		return parseValue(amount, C.PRECISION_AMOUNT, RoundingMode.UP);
	}

	public static final BigDecimal parseAmount(String amount) {
		return parseValue(amount, C.PRECISION_AMOUNT, RoundingMode.UP);
	}

	public static final BigDecimal parseQuantity(BigDecimal quantity) {
		return parseValue(quantity, C.PRECISION_QUANTITY, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseQuantity(Integer quantity) {
		return parseValue(quantity, C.PRECISION_QUANTITY, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseQuantity(Long quantity) {
		return parseValue(quantity, C.PRECISION_QUANTITY, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseQuantity(String quantity) {
		return parseValue(quantity, C.PRECISION_QUANTITY, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseRate(BigDecimal rate) {
		return parseValue(rate, C.PRECISION_RATE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseRate(Integer rate) {
		return parseValue(rate, C.PRECISION_RATE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseRate(Long rate) {
		return parseValue(rate, C.PRECISION_RATE, RoundingMode.HALF_UP);
	}

	public static final BigDecimal parseRate(String rate) {
		return parseValue(rate, C.PRECISION_RATE, RoundingMode.HALF_UP);
	}
	
	public static final BigDecimal parseCoin(BigDecimal coin) {
		return parseValue(coin, C.PRECISION_CURRENCY, RoundingMode.DOWN);
	}

	public static final BigDecimal parseCoin(Integer coin) {
		return parseValue(coin, C.PRECISION_CURRENCY, RoundingMode.DOWN);
	}

	public static final BigDecimal parseCoin(Long coin) {
		return parseValue(coin, C.PRECISION_CURRENCY, RoundingMode.DOWN);
	}

	public static final BigDecimal parseCoin(String coin) {
		return parseValue(coin, C.PRECISION_CURRENCY, RoundingMode.DOWN);
	}

	private static final BigDecimal parseValue(Serializable v, Integer scale, RoundingMode mode) {
		if (v == null) {
			return BigDecimal.ZERO;
		}
		if (v instanceof BigDecimal) {
			BigDecimal value = (BigDecimal) v;
			return value.setScale(scale, mode).stripTrailingZeros();
		} else if (v instanceof BigInteger) {
			BigInteger value = (BigInteger) v;
			return new BigDecimal(value).setScale(scale, mode).stripTrailingZeros();
		} else if (v instanceof Integer) {
			Integer value = (Integer) v;
			return new BigDecimal(value).setScale(scale, mode).stripTrailingZeros();
		} else if (v instanceof Double) {
			Double value = (Double) v;
			return new BigDecimal(value).setScale(scale, mode).stripTrailingZeros();
		} else if (v instanceof String) {
			String value = (String) v;
			return new BigDecimal(value).setScale(scale, mode).stripTrailingZeros();
		}
		throw new BaseException(C.EMPTY, "不支持数据类型[" + v.getClass() + "]");
	}

	/**
	 * 计算金额
	 * 
	 * @param price
	 * @param quantity
	 * @return
	 */
	public static final BigDecimal calcAmount(BigDecimal price, BigDecimal quantity) {
		BigDecimal p = parsePrice(price);
		BigDecimal q = parseQuantity(quantity);
		BigDecimal amount = p.multiply(q).setScale(C.PRECISION_AMOUNT, RoundingMode.UP);
		return amount.stripTrailingZeros();
	}

	/**
	 * 计算家币
	 * 
	 * @param price
	 * @param vipPrice
	 * @return
	 */
	public static final BigDecimal calcCoin(BigDecimal price, BigDecimal vipPrice) {
		BigDecimal p = parsePrice(price);
		BigDecimal vp = parsePrice(vipPrice);
		BigDecimal coin = p.subtract(vp).setScale(C.PRECISION_CURRENCY, RoundingMode.DOWN);
		return coin.stripTrailingZeros();
	}

	/**
	 * 比率转换为百分比
	 * 
	 * @param rate
	 * @return
	 */
	public static final String parseRatePercentDesc(BigDecimal rate) {
		if (rate == null) {
			return "0%";
		}
		return parseValue(rate, C.PRECISION_RATE, RoundingMode.HALF_UP).multiply(new BigDecimal("100")).stripTrailingZeros().longValue() + "%";
	}

	/**
	 * 求最小公倍数
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static final int minCommonMultiple(int m, int n) {
		return m * n / maxCommonDivisorRecursion(m, n);
	}

	/**
	 * 求最大公约数(递归)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static final int maxCommonDivisorRecursion(int m, int n) {
		if (m < n) {// 保证m>n,若m<n,则进行数据交换
			int temp = m;
			m = n;
			n = temp;
		}
		if (m % n == 0) {// 若余数为0,返回最大公约数
			return n;
		} else { // 否则,进行递归,把n赋给m,把余数赋给n
			return maxCommonDivisorRecursion(n, m % n);
		}
	}

	/**
	 * 求最大公约数(循环)
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public static final int maxCommonDivisorLoop(int m, int n) {
		if (m < n) {// 保证m>n,若m<n,则进行数据交换
			int temp = m;
			m = n;
			n = temp;
		}
		while (m % n != 0) {// 在余数不能为0时,进行循环
			int temp = m % n;
			m = n;
			n = temp;
		}
		return n;// 返回最大公约数
	}

	public static final double parseExcelNumericalValue(String value) {
		BigDecimal ret = BigDecimal.ZERO;
		try {
			ret = new BigDecimal(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ret = ret.stripTrailingZeros();
		return ret.doubleValue();
	}

	public static final double parseExcelNumericalValue(String value, Integer scale, RoundingMode rd) {
		BigDecimal ret = BigDecimal.ZERO;
		try {
			ret = new BigDecimal(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ret = ret.setScale(scale, rd).stripTrailingZeros();
		return ret.doubleValue();
	}

	public static final double parseExcelNumericalValue(BigDecimal value) {
		if (value != null) {
			value = value.stripTrailingZeros();
		} else {
			value = BigDecimal.ZERO;
		}
		return value.doubleValue();
	}

	public static final double parseExcelNumericalValue(BigDecimal value, Integer scale, RoundingMode rd) {
		if (value != null) {
			value = value.setScale(scale, rd).stripTrailingZeros();
		} else {
			value = BigDecimal.ZERO;
		}
		return value.doubleValue();
	}

	public static final BigDecimal calcDefaultCurrentChangeQty(BigDecimal minPackageQty, BigDecimal minSalesQty, BigDecimal needChangeQty, BigDecimal availableQty) {
		try {
			BigDecimal qty;
			if (availableQty != null && availableQty.compareTo(needChangeQty) < 0) {
				qty = availableQty.divideToIntegralValue(minPackageQty).multiply(minPackageQty);
			} else {
				qty = needChangeQty.divideToIntegralValue(minPackageQty).multiply(minPackageQty);
			}
			if (qty.compareTo(minSalesQty) >= 0) {
				return qty;
			} else {
				return BigDecimal.ZERO;
			}
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

}
