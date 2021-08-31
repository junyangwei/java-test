package set;

import common.Dog;

import java.util.HashMap;
import java.util.Map;

/**
 * 集合 —— 映射练习Map
 * @author junyangwei
 * @date 2021-08-31
 */
public class MapTest {
    public static void main(String[] args) {
        // 映射基本操作 HashMap实现了Map接口
        Dog dog1 = new Dog("brown", "JY", 1);
        Dog dog2 = new Dog("brown", "KJ", 2);
        Dog dog3 = new Dog("black", "YY", 2);
        Dog dog4 = new Dog("yellow", "kk", 3);

        Map<String, Dog> dogs = new HashMap<>();
        dogs.put("bcd", dog1);
        dogs.put("efg", dog2);
        dogs.put("abc", dog3);

        // 打印dogs
        System.out.println(dogs);

        // 使用get以及getOrDefault获取元素
        System.out.println("Key of efg: " + dogs.get("efg"));
        System.out.println("Key of bcd: " + dogs.getOrDefault("bcd", dog3));
        System.out.println("Key of aaa: " + dogs.get("aaa"));
        System.out.println("Key of aaa: " + dogs.getOrDefault("aaa", dog3));

        // 重复对同一个key调用put方法
        dogs.put("abc", dog4);
        System.out.println(dogs);

        // 移除元素
        dogs.remove("abc");
        System.out.println(dogs);

        // 打印映射元素个数
        System.out.println(dogs.size());

        // 遍历映射的键和值
        // 需要注意的是TreeMap也实现了Map接口，遍历键和值时，会根据键的比较顺序按序遍历
        dogs.forEach((k, v) -> System.out.println("The key of " + k + ": " + v));
    }
}
