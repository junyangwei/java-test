package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 反转数字
 * 题目源：https://leetcode.com/problems/reverse-integer/
 * @author junyangwei
 * @date 2021-09-04
 */
public class ReverseInteger {
    /*
        题目分析：
        - 这是一道简单的数值反转的题目，要求将数字反转并且保留原来的符号

        - 比较简单的做法就是：
            1. 首先，对传入数值取绝对值
            2. 其次，使用循环，每次将数组与10相除求余数
            3. 再将余数加到新的数值中（防止超过Integer范围，使用Long来存储结果）
            4. 最后，根据传入符号返回数值
    */

    public int reverse(int x) {
        int absX = Math.abs(x);
        long result = 0;
        while (absX > 0) {
            result = result * 10 + (absX % 10);
            absX /= 10;
        }
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
        }
        if (x < 0) {
            return -(int) result;
        }
        return (int) result;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<ReverseTest> tests = new ArrayList<>();
        tests.add(new ReverseTest(123, 321));
        tests.add(new ReverseTest(-123, -321));
        tests.add(new ReverseTest(0, 0));
        tests.add(new ReverseTest(1534236469, 0));
        tests.add(new ReverseTest(-2147483648, 0));

        ReverseInteger solution = new ReverseInteger();
        for (ReverseTest test : tests) {
            int result = solution.reverse(test.num);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}

/**
 * 反转数字测试类
 */
class ReverseTest {
    int num;
    int result;
    ReverseTest(int num, int result) {
        this.num = num;
        this.result = result;
    }
}
