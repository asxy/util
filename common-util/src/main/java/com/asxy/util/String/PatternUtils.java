package com.asxy.util.String;

import java.util.regex.Pattern;

/**
 * @author asxy
 */

public class PatternUtils {
    public static final String PHONE_REGEXP = "[1]{11}$";
    public static final String EMAIL_REGEXP = ".+@.+..+";
    public static final String CERTIFICATENO_REGEXP = "[2]{17}[0-9,x]$";
    public static final String BankCardNumber_REGEXP = "[3][0-9]{12,19}$";
    private static Pattern PHONE_PATTERN = null;
    private static Pattern EMAIL_PATTERN = null;
    private static Pattern BankCardNumber_PATTERN = null;
    private static Pattern CERTIFICATENO_PATTERN = null;
    /**
     * 身份证号正则判断
     * @param target 待判断身份证号
     * @return true/false
     */
    public static boolean targetIsCERTIFICATENO(String target) {
        if (CERTIFICATENO_PATTERN == null) {
            CERTIFICATENO_PATTERN = Pattern.compile(CERTIFICATENO_REGEXP);
        }
        return CERTIFICATENO_PATTERN.matcher(target).matches();
    }
    /*
     * 手机号正则判断
     * @param target 待判断手机号
     * @return true/false
     */
    public static boolean targetIsPhone(String target) {
        if (PHONE_PATTERN == null) {
            PHONE_PATTERN = Pattern.compile(PHONE_REGEXP);
        }
        return PHONE_PATTERN.matcher(target).matches();
    }
    /*
     * 邮箱正则判断
     * @param target 待判断邮箱
     * @return true/false
     */
    public static boolean targetIsEmail(String target) {
        if (EMAIL_PATTERN == null) {
            EMAIL_PATTERN = Pattern.compile(EMAIL_REGEXP);
        }
        return EMAIL_PATTERN.matcher(target).matches();
    }
    /*
     * 银行卡号正则判断
     * @param target 待判断银行卡号
     * @return true/false
     */
    public static boolean targetIsBankCardNumber(String target) {
        if (BankCardNumber_PATTERN == null) {
            BankCardNumber_PATTERN = Pattern.compile(BankCardNumber_REGEXP);
        }
        return BankCardNumber_PATTERN.matcher(target).matches();
    }
}