package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 将字符串转化为整型
 * 题目源：https://leetcode.com/problems/string-to-integer-atoi/
 * @author junyangwei
 * @date 2021-09-08
 */
public class StringToInteger {
    /*
        题目要求的myAtoi算法：
        1. 忽略所有空格
        2. 如果下一个字符是-或+，并且它们不在结尾处，则表示它的符号是负数还是正数
        3. 遍历输入项，若下一个是非字符或者已到达结尾，则忽略字符串后面的部分
        4. 将字符数字转换成数字，如果没有数字存在，则返回0
        5. 如果获得的数值超过Integer的取值范围，则取最接近的Integer的边界值
        6. 最终返回数值类型为Integer

        题目分析：

    */

    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        char[] numStr = new char[s.length()];
        int index = 0;
        boolean hasSign = false;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            // 校验是否是数字，是则正常写入
            if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5'
                    || c == '6' || c == '7' || c == '8' || c == '9' || c == '0') {
                numStr[index++] = c;
                continue;
            }

            // 校验是否是空格或+号
            if (c == ' ') {
                // 若是空格，且还在第一位，则直接跳过
                if (index == 0) {
                    continue;
                }
                // 否则结束循环
                break;
            }

            // 校验是否为正数
            if (c == '+') {
                // 若为正数，且还没有符号，则直接跳过
                if (index == 0 && !hasSign) {
                    numStr[index++] = c;
                    hasSign = true;
                    continue;
                }
                // 否则结束循环
                break;
            }

            // 校验是否是负数
            if (c == '-') {
                // 若是负数，且还在第一位，并且还没符号，则直接赋值为负数，index递增
                if (index == 0 && !hasSign) {
                    numStr[index++] = c;
                    hasSign = true;
                    continue;
                }
                // 否则结束循环
                break;
            }

            // 否则直接结束
            break;
        }

        String str = new String(numStr).trim();
        if ("-".equals(str) || "+".equals(str) || str.length() == 0) {
            return 0;
        }

        int result;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            if (str.charAt(0) == '-') {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        return result;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<StringToIntegerTest> tests = new ArrayList<>();
        tests.add(new StringToIntegerTest("  +0  123", 0));
        tests.add(new StringToIntegerTest("42", 42));
        tests.add(new StringToIntegerTest("   -42", -42));
        tests.add(new StringToIntegerTest("4193 with words", 4193));
        tests.add(new StringToIntegerTest("words and 987", 0));
        tests.add(new StringToIntegerTest("-91283472332", -2147483648));
        tests.add(new StringToIntegerTest("+1", 1));
        tests.add(new StringToIntegerTest("+-12", 0));
        tests.add(new StringToIntegerTest("20000000000000000000", Integer.MAX_VALUE));
        tests.add(new StringToIntegerTest("-20000000000000000000", Integer.MIN_VALUE));
        tests.add(new StringToIntegerTest("  +  413", 0));

        StringToInteger solution = new StringToInteger();
        for (StringToIntegerTest test : tests) {
            int result = solution.myAtoi(test.s);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}


/**
 * 将字符串转换成整型测试类
 */
class StringToIntegerTest {
    String s;
    int result;
    StringToIntegerTest(String s, int result) {
        this.s = s;
        this.result = result;
    }
}
