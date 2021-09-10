package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode —— 模拟正则匹配
 * 题目源：https://leetcode.com/problems/regular-expression-matching/
 * @author junyangwei
 * @date 2021-09-09
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int indexS = s.length() - 1;
        int indexP = p.length() - 1;
        return helper(s, p, indexS, indexP);
    }

    public boolean helper(String s, String p, int indexS, int indexP) {
        // 从后往前遍历
        while (indexS >= -1 && indexP >= 0) {
            if (p.charAt(indexP) != '*') {
                /*
                    不匹配条件：
                    1. 字符串索引已到结尾
                    2. 指定索引字符和匹配项不符
                        - 应匹配的字符不为.（符号.能匹配任意字符）
                        - 字符串指定字符 和 应匹配的字符不相同
                 */
                boolean noMatch = indexS == -1
                        || (p.charAt(indexP) != '.' && s.charAt(indexS) != p.charAt(indexP));
                if (noMatch) {
                    return false;
                }
                indexS--;
                indexP--;
            } else {
                /*
                    嵌套调用helper：
                    - 出现类似a*这种情况则表示可为0个，也可为多个，可以暂时直接跳过
                    - 直接检测a*这种匹配之前的匹配项
                    - 如匹配项是ba*这种情况，则直接跳过a*，先从b开始匹配
                    - 若匹配成功则直接返回true（嵌套的所有匹配都已成功）
                 */
                if (helper(s, p, indexS, indexP - 2)) {
                    return true;
                }
                /*
                    当嵌套调用helper失败时：
                    - 如要匹配字符串ba，匹配项为：ba*，此时会优先将字符串末尾的a与匹配项b（a*的前一位）进行比较
                    - 此时返回false，则进行下面的校验
                    - 那么进行校验，不匹配的条件是:
                        1. 字符串索引已到结尾
                        2. 指定索引字符和后一位的匹配项不符（拿a与匹配项*的前一位a进行比较）
                            - 应匹配的字符不为.（符号.能匹配任意字符）
                            - 字符串指定字符 和 指定的应匹配字符不同
                 */
                boolean noMatch = indexS == -1
                        || (p.charAt(indexP - 1) != '.' && s.charAt(indexS) != p.charAt(indexP - 1));
                if (noMatch) {
                    return false;
                }

                // 如果匹配成功了，则拿indexS的下一位继续进行比较
                return helper(s, p, indexS - 1, indexP);
            }
        }

        if (indexS == -1 && indexP == -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<IsMatchTest> tests = new ArrayList<>();
        // 变更遍历索引起点条件：字符匹配、符号"."匹配
        tests.add(new IsMatchTest("bbbba", "b*.", true));
        tests.add(new IsMatchTest("bbbba", "b*..", true));
        tests.add(new IsMatchTest("bbbba", "b*..", true));
        tests.add(new IsMatchTest("bbbba", "b*...", true));
        tests.add(new IsMatchTest("bbbba", "b*....", true));
        tests.add(new IsMatchTest("bbbba", "b*.....", true));
        tests.add(new IsMatchTest("bbbba", "b*.ba", true));
        tests.add(new IsMatchTest("bbbba", "b*b.ba", true));
        tests.add(new IsMatchTest("acaabbaccbbacaabbbba", "a*.*b*.*a*ba*a*", true));
        tests.add(new IsMatchTest("bbbba", ".*", true));
        tests.add(new IsMatchTest("bbbba", ".*a*a", true));
        tests.add(new IsMatchTest("aaca", "ab*a*c*a", true));
        tests.add(new IsMatchTest("aaa", "ab*a*c*a", true));
        tests.add(new IsMatchTest("aaa", "ab*aaa*c*a", false));
        tests.add(new IsMatchTest("aaa", "ab*a.a*c*a", false));
        tests.add(new IsMatchTest("aaa", "ab*aa*c*aa*", true));
        tests.add(new IsMatchTest("aaba", "ab*aa*c*aa*", false));
        tests.add(new IsMatchTest("aa", "a", false));
        tests.add(new IsMatchTest("aa", "a*", true));
        tests.add(new IsMatchTest("ab", ".*", true));
        tests.add(new IsMatchTest("ab", ".*ab", true));
        tests.add(new IsMatchTest("aab", "c*a*b", true));
        tests.add(new IsMatchTest("mississippi", "mis*is*p*.", false));
        tests.add(new IsMatchTest("mississippi", "mis*sis*issip*.", false));
        tests.add(new IsMatchTest("mississippi", "mis*ssis*ssip*i", true));
        tests.add(new IsMatchTest("mississippi", "mis*ssis*ssip*.", true));
        tests.add(new IsMatchTest("mississippi", "mis*sis*ssip*", false));
        tests.add(new IsMatchTest("mississipp", "mis*sis*ssip*", true));
        tests.add(new IsMatchTest("mississippi", "mis*sis*ssip.", false));
        tests.add(new IsMatchTest("mississippi", "mis*sis*ssip..", true));
        tests.add(new IsMatchTest("mississippi", "mis*sis*ssipp.", true));
        tests.add(new IsMatchTest("ab", "b*.", false));
        tests.add(new IsMatchTest("ab", "ab*.", true));
        tests.add(new IsMatchTest("ab", ".*ab.", false));
        tests.add(new IsMatchTest("aaa", "aaaa", false));

        RegularExpressionMatching solution = new RegularExpressionMatching();
        for (IsMatchTest test : tests) {
            boolean result = solution.isMatch(test.s, test.p);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}

/**
 * 表达式匹配测试类
 */
class IsMatchTest {
    String s;
    String p;
    boolean result;
    IsMatchTest(String s, String p, boolean result) {
        this.s = s;
        this.p = p;
        this.result = result;
    }
}

