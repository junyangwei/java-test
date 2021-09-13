package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 整型转罗马数字
 * 题目源：https://leetcode.com/problems/integer-to-roman/
 * @author junyangwei
 * @date 2021-09-13
 */
public class IntegerToRoman {
    /*
        题目解析：
        要把数字转换成罗马数，按照如下规则：
        I = 1, IV = 4, V = 5, IX = 9,
        X = 10, XL = 40, L = 50, XC = 90,
        C = 100, CD = 400, D = 500, CM = 900, M = 1000
        给定整数的范围为1到3999

        思路：
        1. 首先，我们的主要思路就是由大到小的分别解析出：千位、百位、十位、个位
        2. 再根据各个位置的数字，转换成相应的罗马数字
        3. 最后，合并罗马数字

        解题流程：
        1. 定义一个字符拼接的变量romanString，以及位数的计数器count
        2. 使用while循环，针对传入的参数num设置循环条件：num > 0
        3. 获取num与10的余数remain，再将num整除10
        4. 获取当前位数，count=0为个位"I"表示，count=1为十位"X"表示，以此类推
           处理完后，count++
        5. 调用拼接罗马数字方法
            - 该方法根据位数、罗马数映射规则，做switch筛选即可
            - 需要注意的是，因为字符拼接是从个位开始，后面需要反转字符串，因此每个位数的
              4和9都需要反转一次，比如个位4原本为IV，需要改为VI
        6. 跳入下一次循环，继续上述流程
        7. 最后，反转一遍字符串，返回相关的字符串
    */

    public String intToRoman(int num) {
        StringBuffer romanString = new StringBuffer();
        int count = 0;
        while (num > 0) {
            int remain = num % 10;
            num /= 10;
            String numBig;
            switch (count++) {
                case 0:
                    numBig = "I";
                    break;
                case 1:
                    numBig = "X";
                    break;
                case 2:
                    numBig = "C";
                    break;
                default:
                    numBig = "M";
            }
            this.formatRomanString(romanString, remain, numBig);
        }
        // 反转字符串返回
        return romanString.reverse().toString();
    }

    public void formatRomanString(StringBuffer romanString, int i, String numBig) {
        switch (numBig) {
            case "M": {
                while (i > 0) {
                    romanString.append("M");
                    i--;
                }
                break;
            }
            case "C": {
                while (i > 0) {
                    if (i == 4) {
                        romanString.append("DC");
                        i -= 4;
                        continue;
                    } else if (i == 5) {
                        romanString.append("D");
                        i -= 5;
                        continue;
                    } else if (i == 9) {
                        romanString.append("MC");
                        i -= 9;
                        continue;
                    }
                    romanString.append("C");
                    i--;
                }
                break;
            }
            case "X": {
                while (i > 0) {
                    if (i == 4) {
                        romanString.append("LX");
                        i -= 4;
                        continue;
                    } else if (i == 5) {
                        romanString.append("L");
                        i -= 5;
                        continue;
                    } else if (i == 9) {
                        romanString.append("CX");
                        i -= 9;
                        continue;
                    }
                    romanString.append("X");
                    i--;
                }
                break;
            }
            default: {
                while (i > 0) {
                    if (i == 4) {
                        romanString.append("VI");
                        i -= 4;
                        continue;
                    } else if (i == 5) {
                        romanString.append("V");
                        i -= 5;
                        continue;
                    } else if (i == 9) {
                        romanString.append("XI");
                        i -= 9;
                        continue;
                    }
                    romanString.append("I");
                    i--;
                }
            }
        }
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<IntegerToRomanTest> tests = new ArrayList<>();
        tests.add(new IntegerToRomanTest(1, "I"));
        tests.add(new IntegerToRomanTest(2, "II"));
        tests.add(new IntegerToRomanTest(3, "III"));
        tests.add(new IntegerToRomanTest(4, "IV"));
        tests.add(new IntegerToRomanTest(5, "V"));
        tests.add(new IntegerToRomanTest(6, "VI"));
        tests.add(new IntegerToRomanTest(7, "VII"));
        tests.add(new IntegerToRomanTest(8, "VIII"));
        tests.add(new IntegerToRomanTest(9, "IX"));
        tests.add(new IntegerToRomanTest(10, "X"));
        tests.add(new IntegerToRomanTest(11, "XI"));
        tests.add(new IntegerToRomanTest(12, "XII"));
        tests.add(new IntegerToRomanTest(13, "XIII"));
        tests.add(new IntegerToRomanTest(14, "XIV"));
        tests.add(new IntegerToRomanTest(15, "XV"));
        tests.add(new IntegerToRomanTest(16, "XVI"));
        tests.add(new IntegerToRomanTest(17, "XVII"));
        tests.add(new IntegerToRomanTest(18, "XVIII"));
        tests.add(new IntegerToRomanTest(19, "XIX"));
        tests.add(new IntegerToRomanTest(20, "XX"));
        tests.add(new IntegerToRomanTest(40, "XL"));
        tests.add(new IntegerToRomanTest(50, "L"));
        tests.add(new IntegerToRomanTest(90, "XC"));
        tests.add(new IntegerToRomanTest(100, "C"));
        tests.add(new IntegerToRomanTest(58, "LVIII"));
        tests.add(new IntegerToRomanTest(1994, "MCMXCIV"));

        IntegerToRoman solution = new IntegerToRoman();
        for (IntegerToRomanTest test : tests) {
            String result = solution.intToRoman(test.num);
            System.out.println("测试结果：" + (test.result.equals(result)));
            assert (test.result.equals(result));
        }
    }
}

/**
 * 整型转罗马数值测试类
 */
class IntegerToRomanTest {
    int num;
    String result;
    IntegerToRomanTest(int num, String result) {
        this.num = num;
        this.result = result;
    }
}
