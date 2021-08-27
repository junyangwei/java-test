package set;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 集合 —— 测试代码
 * @author junyangwei
 * @date 2021-08-27
 */
public class Test {
    public static void main(String[] args) {
        // 使用LinkedList，在链表中间插入元素
        List<String> staff = new LinkedList<>();
        staff.add("A");
        staff.add("B");
        System.out.println(staff);
        ListIterator<String> iter = staff.listIterator();
        iter.next();
        iter.add("C");
        System.out.println(staff);
        iter.next();
        iter.remove();
        System.out.println(staff);
        iter.previous();
        iter.set("D");
        System.out.println(staff);
        // AB -> A|B -> AC|B -> ACB| -> AC| -> A|C -> A|D
    }
}
