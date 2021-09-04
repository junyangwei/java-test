package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— Z字形变化
 * 题目源：https://leetcode.com/problems/zigzag-conversion/
 * @author junyangwei
 * @date 2021-09-04
 */
public class Solution {
    /*
        题目分析：
        - 首先，这道题是将一个字符串，转换成指定行数的，类似反N字结构的形状；再由第一行
        开始，以从左到右，从上到下的顺序，重新组装成一个字符串。

        - 从实现上来看，最简单的实现方式就是构造一个二维字符数组，根据题目要求的格式将字符
        串中的所有元素贴到二维字符数组中，再遍历二维字符数组即可（按序提取非空元素）

        - 但是，当我尝试将反N形状的字符转换成它们原来所在字符串中的位置时，发现是有规律的
        假设我的字符串为abcdefghijklmn，指定行数为4时，它们就变为
        反转字符串              |    对应原字符串位置索引
        a        g        m    |    0        6          12
        b     f  h     l  n    |    1     5  7      11  13
        c  e     i  k          |    2  4     8  10
        d        j             |    3        9

        可以看出，它的位置计算规律是（假设I是每行第I个元素）
        第一行：0 + 2*(4 - 1) * I
        第二行：1 + 2*(4 - 2) + 2*(2 - 1) + 2*(4 - 2) + 2*(2 - 1) 奇数偶数列加的数不同I = 5
        第三行：2 + 2*(4 - 3) + 2*(3 - 1) + 2*(4 - 3) + 2*(3 - 1) 奇数偶数列加的数不同I = 5
        第四行：3 + 2*(4 - 1) * I

        规律汇总：假设行数为n
        1. 第一行和最后一行的元素位置索引计算公式：(n - 1) + 2*(n - 1) * I
        2. 第2到n-1行奇数列和偶数列的间隔计算公式，奇数：2*(行数 - 1)，偶数：2*(n - 行数)
        3. 因为索引下标从0开始，因此第2点，奇数偶数位置要注意，并且使用累加的形式计算每个
           位置的索引下标
        4. 第1列的元素是起始值，就是本身，不需要计算任何公式
    */

    public String convert(String s, int numRows) {
        int l = s.length();
        if (l <= numRows || numRows == 1) {
            return s;
        }

        char[] strs = s.toCharArray();
        char[] result = new char[l];

        int count = 0;
        for (int i = 0; i < numRows; i++) {
            // 将第一列元素添加仅字符数组
            result[count++] = strs[i];

            // 定义根据公式计算得到的数组位置索引
            int index = i;
            for (int k = 1; k < l; k++) {
                if (i == 0 || i == numRows - 1) {
                    index = index + 2 * (numRows - 1);
                } else if (k % 2 == 1) {
                    index = index + 2 * (numRows - 1 - i);
                } else {
                    index = index + 2 * i;
                }

                if (index >= l) {
                    break;
                }
                result[count++] = strs[index];
            }
        }
        return new String(result);
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<ConvertTest> tests = new ArrayList<>();
        tests.add(new ConvertTest("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR"));
        tests.add(new ConvertTest("PAYPALISHIRING", 4, "PINALSIGYAHRPI"));
        tests.add(new ConvertTest("A", 1, "A"));
        tests.add(new ConvertTest("A", 2, "A"));
        tests.add(new ConvertTest("AB", 1, "AB"));
        tests.add(new ConvertTest("AB", 3, "AB"));

        Solution solution = new Solution();
        for (ConvertTest test : tests) {
            String result = solution.convert(test.s, test.numRow);
            System.out.println("测试结果：" + test.result.equals(result));
            assert (test.result.equals(result));
        }
    }
}

/**
 * Z字形结果测试类
 */
class ConvertTest {
    String s;
    int numRow;
    String result;
    ConvertTest(String s, int numRow, String result) {
        this.s = s;
        this.numRow = numRow;
        this.result = result;
    }
}
