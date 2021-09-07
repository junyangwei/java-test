package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 回文数字（从左到右和从右到左顺序一致）
 * 题目源：https://leetcode.com/problems/palindrome-number/
 * @author junyangwei
 * @date 2021-09-07
 */
public class PalindromeNumber {
    /*
        题目分析：
        - 这是一道简单的校验数字是否是回文数值的题目

        - 比较简单的做法就是：
            1. 将数字转换成字符数组
            2. 从左到右依次遍历，遍历次数为数组长度 / 2
            3. 校验左右两边对称的数值相等
    */

    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] xStr = String.valueOf(x).toCharArray();
        for (int i = 0; i < (xStr.length >> 1); i++) {
            if (xStr[i] != xStr[xStr.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<PalindromeNumberTest> tests = new ArrayList<>();
        tests.add(new PalindromeNumberTest(121, true));
        tests.add(new PalindromeNumberTest(-123, false));
        tests.add(new PalindromeNumberTest(10, false));
        tests.add(new PalindromeNumberTest(0, true));
        tests.add(new PalindromeNumberTest(-101, false));

        PalindromeNumber solution = new PalindromeNumber();
        for (PalindromeNumberTest test : tests) {
            boolean result = solution.isPalindrome(test.x);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}


/**
 * 反转数字测试类
 */
class PalindromeNumberTest {
    int x;
    boolean result;
    PalindromeNumberTest(int x, boolean result) {
        this.x = x;
        this.result = result;
    }
}
