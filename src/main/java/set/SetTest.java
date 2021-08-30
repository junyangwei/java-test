package set;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**
 * 集合 —— 散列表练习
 * @author junyangwei
 * @date 2021-08-30
 */
public class SetTest {
    public static void main(String[] args) {
        // HashSet实现了Set接口
        Set<String> words = new HashSet<>();
        long totalTime = 0;

        try (Scanner in = new Scanner(System.in)) {
            System.out.println("输入字母: ");
            while (in.hasNext()) {
                String word = in.next();
                if ("break".equals(word)) {
                    break;
                }
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iter = words.iterator();
        for (int i = 1; i <= 20 && iter.hasNext(); i++) {
            System.out.println(iter.next());
        }
        System.out.println("...");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
    }
}
