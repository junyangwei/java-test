package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 罗马数字转整型
 * 题目源：https://leetcode.com/problems/roman-to-integer/
 * @author junyangwei
 * @date 2021-09-14
 */
public class RomanToInteger {
    /*
        题目分析：
        这次要把罗马数换成整数，按照如下规则：
        I = 1, IV = 4, V = 5, IX = 9,
        X = 10, XL = 40, L = 50, XC = 90,
        C = 100, CD = 400, D = 500, CM = 900, M = 1000
        最终整数的范围为1到3999

        思路：
        从头开始遍历字符串，校验和下一位是否是组合字符，是则获取组合字符
        比如：MCM校验得出M 1000, CM=900，合计1900
    */

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == 'M') {
                result += 1000;
                i++;
                continue;
            } else if (chars[i] == 'C') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'M') {
                        result += 900;
                        i += 2;
                        continue;
                    }
                    if (chars[i + 1] == 'D') {
                        result += 400;
                        i += 2;
                        continue;
                    }
                }
                result += 100;
                i++;
                continue;
            } else if (chars[i] == 'D') {
                result += 500;
                i++;
                continue;
            } else if (chars[i] == 'X') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'C') {
                        result += 90;
                        i += 2;
                        continue;
                    }
                    if (chars[i + 1] == 'L') {
                        result += 40;
                        i += 2;
                        continue;
                    }
                }
                result += 10;
                i++;
                continue;
            } else if (chars[i] == 'L') {
                result += 50;
                i++;
                continue;
            } else if (chars[i] == 'I') {
                if (i + 1 < chars.length) {
                    if (chars[i + 1] == 'X') {
                        result += 9;
                        i += 2;
                        continue;
                    }
                    if (chars[i + 1] == 'V') {
                        result += 4;
                        i += 2;
                        continue;
                    }
                }
                result += 1;
                i++;
                continue;
            } else if (chars[i] == 'V') {
                result += 5;
                i++;
                continue;
            }
            i++;
        }
        return result;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<RomanToIntegerTest> tests = new ArrayList<>();
        tests.add(new RomanToIntegerTest(1, "I"));
        tests.add(new RomanToIntegerTest(2, "II"));
        tests.add(new RomanToIntegerTest(3, "III"));
        tests.add(new RomanToIntegerTest(4, "IV"));
        tests.add(new RomanToIntegerTest(5, "V"));
        tests.add(new RomanToIntegerTest(6, "VI"));
        tests.add(new RomanToIntegerTest(7, "VII"));
        tests.add(new RomanToIntegerTest(8, "VIII"));
        tests.add(new RomanToIntegerTest(9, "IX"));
        tests.add(new RomanToIntegerTest(10, "X"));
        tests.add(new RomanToIntegerTest(11, "XI"));
        tests.add(new RomanToIntegerTest(12, "XII"));
        tests.add(new RomanToIntegerTest(13, "XIII"));
        tests.add(new RomanToIntegerTest(14, "XIV"));
        tests.add(new RomanToIntegerTest(15, "XV"));
        tests.add(new RomanToIntegerTest(16, "XVI"));
        tests.add(new RomanToIntegerTest(17, "XVII"));
        tests.add(new RomanToIntegerTest(18, "XVIII"));
        tests.add(new RomanToIntegerTest(19, "XIX"));
        tests.add(new RomanToIntegerTest(20, "XX"));
        tests.add(new RomanToIntegerTest(40, "XL"));
        tests.add(new RomanToIntegerTest(50, "L"));
        tests.add(new RomanToIntegerTest(90, "XC"));
        tests.add(new RomanToIntegerTest(100, "C"));
        tests.add(new RomanToIntegerTest(58, "LVIII"));
        tests.add(new RomanToIntegerTest(1994, "MCMXCIV"));

        RomanToInteger solution = new RomanToInteger();
        for (RomanToIntegerTest test : tests) {
            int result = solution.romanToInt(test.s);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}

/**
 * 整型转罗马数值测试类
 */
class RomanToIntegerTest {
    String s;
    int result;
    RomanToIntegerTest(int result, String s) {
        this.result = result;
        this.s = s;
    }
}
