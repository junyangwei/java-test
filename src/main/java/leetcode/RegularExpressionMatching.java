package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author junyangwei
 * @date 2021-09-09
 */
public class RegularExpressionMatching {
    /*
        题目要求：
        1. 实现正则匹配"."：匹配任意单个字符
        2. 实现正则匹配"*"：匹配前面字符一个或多个

        题目分析：
        1. 首先，遍历字符串p，将字符串中的*和.字符给拆分出来
        1. 设置两个索引遍历项，字符串的和匹配项
        2.

        mis*sis*ssip*.拆分成：["mi", "s*", "ssp", "i*", "."];

    */
    public boolean isMatch1(String s, String p) {
        return Pattern.matches(p, s);
    }

    public boolean isMatch(String s, String p) {
        char[] sChars = s.toCharArray();
        char[] pChars = p.toCharArray();
        int indexS = 0;
        int indexP = 0;
        int matchIndexS = 0;
        while (indexP <= pChars.length - 1) {
            if (indexP == pChars.length - 1 && pChars[indexP] != '.') {
                if (sChars[sChars.length - 1] != pChars[indexP]) {
                    return false;
                }
            }
            if (indexS > sChars.length - 1) {
                if (indexP != (pChars.length - 1) && pChars[indexP + 1] == '*') {
                    indexP = indexP + 2;
                    continue;
                }
                return false;
            }

            if (indexP != (pChars.length - 1) && pChars[indexP + 1] == '*') {
                char pChar = pChars[indexP];
                indexP = indexP + 2;

                // 处理特殊情况 .*，匹配所有字符的情况，已匹配字符串直接为总数
                if (pChar == '.') {
                    matchIndexS = sChars.length;
                    continue;
                }

                // 若匹配项已完全匹配，则可直接跳过
                if (matchIndexS >= sChars.length) {
                    continue;
                }


                if (matchIndexS > indexS && matchIndexS < sChars.length) {
                    boolean isMatch = false;
                    while (indexS <= sChars.length - 1 && indexS <= matchIndexS + 1) {
                        if (sChars[indexS] == pChar) {
                            isMatch = true;
                            indexS++;
                            if (matchIndexS < indexS) {
                                matchIndexS++;
                            }
                            break;
                        }
                        indexS++;
                    }
                    if (!isMatch) return false;
                    continue;
                }

                // 若跟下一项不配也可直接跳过
                if (indexS < sChars.length - 1 && pChar != sChars[indexS]) {
                    continue;
                }

                // 更新实际已匹配项，感觉这里还可以优化！！！
                int rIndexS = indexS;
                while (rIndexS <= sChars.length - 1) {
                    if (pChar == sChars[rIndexS] && rIndexS >= matchIndexS) {
                        rIndexS++;
                        matchIndexS++;
                        continue;
                    }
                    break;
                }
                continue;
            }

            if (pChars[indexP] == '.') {
                indexS++;
                indexP++;
                if (matchIndexS < sChars.length) matchIndexS++;
                continue;
            }

            if (matchIndexS > indexS && matchIndexS <= sChars.length) {
                boolean isMatch = false;
                while (indexS <= sChars.length - 1 && indexS <= matchIndexS + 1) {
                    if (sChars[indexS] == pChars[indexP]) {
                        isMatch = true;
                        indexS++;
                        if (matchIndexS < indexS) {
                            matchIndexS++;
                        }
                        break;
                    }
                    indexS++;
                }
                if (!isMatch) return false;
                indexP++;
                continue;
            }

            if (pChars[indexP] == '.' || pChars[indexP] == sChars[indexS]) {
                indexS++;
                indexP++;
                if (matchIndexS < indexS) {
                    matchIndexS++;
                }
                continue;
            }

            return false;
        }

        if (matchIndexS <= sChars.length - 1) {
            return false;
        }

        return true;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<IsMatchTest> tests = new ArrayList<>();
        // 变更遍历索引起点条件：字符匹配、符号"."匹配
        tests.add(new IsMatchTest("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*", false));
        tests.add(new IsMatchTest("bbbba", ".*b", false));
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

