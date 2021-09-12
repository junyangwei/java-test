package leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode —— 最大储存水面积
 * 题目源：https://leetcode.com/problems/container-with-most-water/
 * @author junyangwei
 * @date 2021-09-12
 */
public class ContainerWithMostWater {
    /*
        题目解析：
        首先，该题目要求最大储水面积，简单做可以:
        1. 从两边同时开始，设最左边从0位索引开始，变量为i，最右边从length-1位索引开始，变量为j
        2. 求得两边的储存水面积，公式为：第i位和第j位最小值 * (j - 1)
        3. 若面积大于上一次求得的最大面积，则替换
        4. 若第i位数值 小于 第j位数值，则将i索引递增，向中间靠
        5. 否则，将j索引递减，向中间靠
    */

    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, max = 0;
        while (i < j) {
            int area = Math.min(height[i], height[j]) * (j - i);
            if (area > max) {
                max = area;
            }

            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }

    /**
     * 单元测试
     */
    public static void main(String[] args) {
        List<ContainerWithMostWaterTest> tests = new ArrayList<>();
        tests.add(new ContainerWithMostWaterTest(new int[]{1, 2, 3}, 2));
        tests.add(new ContainerWithMostWaterTest(new int[]{1, 1}, 1));
        tests.add(new ContainerWithMostWaterTest(new int[]{4, 3, 2, 1, 4}, 16));
        tests.add(new ContainerWithMostWaterTest(new int[]{1, 2, 1}, 2));

        ContainerWithMostWater solution = new ContainerWithMostWater();
        for (ContainerWithMostWaterTest test : tests) {
            int result = solution.maxArea(test.height);
            System.out.println("测试结果：" + (test.result == result));
            assert (test.result == result);
        }
    }
}

/**
 * 最大储存水面积测试类
 */
class ContainerWithMostWaterTest {
    int[] height;
    int result;
    ContainerWithMostWaterTest(int[] height, int result) {
        this.height = height;
        this.result = result;
    }
}
