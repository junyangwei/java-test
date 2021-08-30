package set;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 集合 —— 树集练习
 * @author junyangwei
 * @date 2021-08-30
 */
public class TreeSetTest {
    public static void main(String[] args) {
        // 创建一个Item对象的树集，按照Item对象默认排序顺序（partNumber大小)
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Modem", 9912));
        parts.add(new Item("Widget", 4562));
        System.out.println(parts);

        // 创建一个Item对象的树集，指定排序方式为按照Item对象的description
        NavigableSet<Item> sortByDescription = new TreeSet<>(
                Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }
}
