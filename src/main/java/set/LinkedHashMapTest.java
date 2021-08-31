package set;

import common.Dog;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 集合 —— 链接散列集与映射
 * @author junyangwei
 * @date 2021-08-31
 */
public class LinkedHashMapTest<K, V> extends LinkedHashMap<K, V> {

    public static void main(String[] args) {
        Dog dog1 = new Dog("brown", "JY", 1);
        Dog dog2 = new Dog("brown", "KJ", 2);
        Dog dog3 = new Dog("black", "YY", 2);
        Dog dog4 = new Dog("yellow", "kk", 3);

        // 链接散列集与映射
        Map<String, Dog> dogs2 = new LinkedHashMap<>();
        dogs2.put("cde", dog1);
        dogs2.put("bcd", dog2);
        dogs2.put("def", dog3);
        dogs2.put("abc", dog3);

        // 按照插入顺序遍历，打印：cde bcd def abc
        printKeys(dogs2.keySet());

        // 链接散列映射练习，实现记录高频访问的元素
        Map<String, Dog> cache = new LinkedHashMapTest<>(7, 0.75F, true);
        cache.put("a", dog1);
        cache.put("b", dog2);
        cache.put("c", dog3);
        cache.put("d", dog4);

        // 按照访问顺序遍历，打印：a b c d
        printKeys(cache.keySet());

        // 按照最新的访问顺序遍历，打印：d e f g
        cache.put("e", dog1);
        cache.put("f", dog2);
        cache.put("g", dog3);
        printKeys(cache.keySet());

        // 访问开始插入的键值对（预期返回null，只存储最新的4个高频键值对）
        System.out.println(cache.get("a"));

        // 访问已存在的键e，修改访问顺序（预期结果d f g e）
        System.out.println(cache.get("e"));
        printKeys(cache.keySet());

        // 重复覆盖键值对d，修改访问顺序（预期结果f g e d）
        cache.put("d", dog1);
        printKeys(cache.keySet());
    }

    private static void printKeys(Set<String> keysSet) {
        for (String key : keysSet) {
            System.out.print(key + " ");
        }
        System.out.println();
    }

    public LinkedHashMapTest(int initialCapacity,
                            float loadFactor,
                            boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * 覆盖removeEldestEntry方法（旧的方法恒定返回false）
     * 覆盖方法触发的效果：
     *  1. 当元素个数小于等于4时：
     *     - 调用get方法
     *         - 当键/值对存在时：会将键/值对的位置放到链表尾部(表示最近访问的映射)
     *           假设原来链表中键的顺序为A|B|C，访问B时，链表顺序将改为A|C|B
     *         - 当键/值对不存在时，直接返回null
     *     - 调用put方法
     *         - 当键/值对存在时：替换原来的键值对
     *           假设原来链表中键的顺序为A|B|C，覆盖B时，链表顺序改为A|C|B
     *         - 当键/值对不存在时：直接添加键/值对
     *           假设原来链表中键的顺序为A|B|C，添加D时，链表顺序改为A|C|B|D
     *  2. 当元素个数大于4时
     *     - 调用get方法
     *          - 当键/值对存在时：会将键/值对的位置放到链表尾部(表示最近访问的映射)
     *            假设原来链表中键的顺序为A|B|C|D，访问B时，链表顺序将改为A|C|D|B
     *          - 当键/值对不存在时，直接返回null
     *     - 调用put方法
     *         - 当键/值对存在时：替换原来的键值对
     *           假设原来链表中键的顺序为A|B|C|D，覆盖B时，链表顺序改为A|C|D|B
     *         - 当键/值对不存在时：直接添加键/值对，并去除链表中最前面的元素(最低频)
     *           假设原来链表中键的顺序为A|C|D|B，添加H时，链表顺序改为C|B|D|H
     * @param eldest 最高配的键值对
     * @return true or false
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > 4;
    }
}
