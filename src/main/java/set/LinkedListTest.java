package set;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 集合 —— 链表测试
 * @author junyangwei
 * @date 2021-08-27
 */
public class LinkedListTest {
    public static void main(String[] args) {
        List<String> a = new LinkedList();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");

        List<String> b = new LinkedList<>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");

        // 将b中的元素合并到a
        ListIterator<String> aIter = a.listIterator();
        Iterator<String> bIter = b.iterator();

        while (bIter.hasNext()) {
            if (aIter.hasNext()) {
                aIter.next();
            }
            aIter.add(bIter.next());
        }

        System.out.println(a);

        // 删除b中偶数位置的元素（第2、4...位）
        bIter = b.listIterator();
        while (bIter.hasNext()) {
            bIter.next();
            if (bIter.hasNext()) {
                bIter.next();
                bIter.remove();
            }
        }

        System.out.println(b);

        // 删除a中所有b剩余的元素
        a.removeAll(b);

        System.out.println(a);
    }
}
