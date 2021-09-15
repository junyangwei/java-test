package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 字符串数组的公共（最长）前缀
 * 题目源：https://leetcode.com/problems/longest-common-prefix/
 * @author junyangwei
 * @date 2021-09-15
 */
public class LongestCommonPrefix {
    /*
        题目分析：
        要找最长的字符串前缀，最简单的方法就是进行双层循环
          - 第一层循环字符串数组
          - 第二层循环每个字符串
        这个循环过程相当于一个过滤的过程，以第一个字符串为主，越往后，过滤掉不同的元素
        如果第一个字符都不相同，就可以直接返回空字符串""

        详细逻辑：
        1. 首先获取字符串数组的第一个字符串，转成字符数组
        2. 循环字符串数组（直接从第二个开始）
        3. 嵌套循环当前字符串的字符（循环次数小于当前字符串长度，并且小于前缀字符串长度）
        4. 嵌套循环终止条件，对应位置的字符不匹配
        5. 返回对应位置的字符串
    */

    public String longestCommonPrefix(String[] strs) {
        char[] chars = strs[0].toCharArray();
        int end = chars.length - 1;
        for (int i = 1; i < strs.length; i++) {
            if (end < 0) {
                return "";
            }
            if (end > strs[i].length() - 1) {
                end = strs[i].length() - 1;
            }
            for (int k = 0; k < strs[i].length() && k <= end; k++) {
                if (strs[i].charAt(k) != chars[k]) {
                    end = k - 1;
                    break;
                }
            }
        }

        char[] result = new char[end+1];
        for (int i = 0; i <= end; i++) {
            result[i] = chars[i];
        }
        return new String(result);

    }
    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<LongestCommonPrefixTest> tests = new ArrayList<>();
        tests.add(new LongestCommonPrefixTest(new String[]{"c", "c"}, "c"));
        tests.add(new LongestCommonPrefixTest(new String[]{"flower", "flow", "flight"}, "fl"));
        tests.add(new LongestCommonPrefixTest(new String[]{"dog", "cat", "boy"}, ""));

        LongestCommonPrefix solution = new LongestCommonPrefix();
        for (LongestCommonPrefixTest test : tests) {
            String result = solution.longestCommonPrefix(test.strs);
            System.out.println("测试结果：" + (test.result.equals(result)));
            assert (test.result.equals(result));
        }
    }
}

/**
 * 最长的公共前缀测试类
 */
class LongestCommonPrefixTest {
    String[] strs;
    String result;
    LongestCommonPrefixTest(String[] strs, String result) {
        this.strs = strs;
        this.result = result;
    }
}
