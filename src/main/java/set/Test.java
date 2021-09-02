package set;

import common.Dog;

import java.util.*;

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

        EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
        EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
        EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WEDNESDAY, Weekday.FRIDAY);

        for (Weekday wd : always) {
            System.out.print(wd + " ");
        }
        System.out.println();

        for (Weekday nv : never) {
            System.out.print(nv + " ");
        }
        System.out.println();

        for (Weekday wod : workday) {
            System.out.print(wod + " ");
        }
        System.out.println();

        for (Weekday f : mwf) {
            System.out.print(f + " ");
        }

        EnumMap<Weekday, Dog> dogInCharge = new EnumMap<>(Weekday.class);
        dogInCharge.put(Weekday.MONDAY, new Dog());
        dogInCharge.forEach(((weekday, dog) -> {
            System.out.println("weekday:" + weekday + ", dog:" + dog);
        }));

        // 泛型集合很容易将错误类型的元素混入，导致出错
        ArrayList<String> strings = new ArrayList<>();
        ArrayList rawList = strings;
        // 将错误类型插入时不会报错
        rawList.add(new Date());
        // 调用get方法并转换成指定类型时就会抛错
        // String str = (String) rawList.get(0);

        // 正确的方式：使用受查视图的方式探测add方法插入的是否是正确类型
        List<String> safeStrings = Collections.checkedList(strings, String.class);
        List rawList2 = safeStrings;
        // 此时调用add方法就会抛错
        // rawList2.add(new Date());

        // 找数组最大元素
        Integer[] numbers = new Integer[]{ 3, 2, 5, 1, 4 };
        System.out.println("[max] max number: " + max(numbers));
        System.out.println("[maxCommon] max number: " + maxCommon(Arrays.asList(numbers)));

        // 找数组列表最大值
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("c");
        stringList.add("b");
        stringList.add("f");
        stringList.add("a");
        stringList.add("e");
        System.out.println("[max] max string: " + max(stringList));
        System.out.println("[maxCommon] max string: " + maxCommon(stringList));

        // 找链表最大值
        LinkedList<String> stringLinkedList = new LinkedList<>();
        stringLinkedList.add("C");
        stringLinkedList.add("B");
        stringLinkedList.add("F");
        stringLinkedList.add("A");
        stringLinkedList.add("E");
        System.out.println("[max] max linked string: " + max(stringLinkedList));
        System.out.println("[maxCommon] max linked string: " + maxCommon(stringLinkedList));
    }

    enum Weekday { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY };

    /**
     * 泛型集合 —— 找数组的最大值
     * @param a 数组
     * @param <T> 数组对象要求实现了Comparable接口
     * @return 数组对象的最大值
     */
    public static <T extends Comparable> T max(T[] a) {
        if (a.length == 0) {
            throw new NoSuchElementException();
        }
        T largest = a[0];
        for (int i = 1; i < a.length; i++) {
            if (largest.compareTo(a[i]) < 0) {
                largest = a[i];
            }
        }
        return largest;
    }

    /**
     * 泛型集合 —— 找数组列表的最大值
     * @param a 数组列表
     * @param <T> 数组列表对象要求实现了Comparable接口
     * @return 数组列表对象的最大值
     */
    public static <T extends Comparable> T max(ArrayList<T> a) {
        if (a.size() == 0) {
            throw new NoSuchElementException();
        }
        T largest = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (largest.compareTo(a.get(i)) < 0) {
                largest = a.get(i);
            }
        }
        return largest;
    }

    /**
     * 泛型集合 —— 找链表的最大值
     * @param a 链表
     * @param <T> 链表对象要求实现了Comparable接口
     * @return 链表对象的最大值
     */
    public static <T extends Comparable> T max(LinkedList<T> a) {
        if (a.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> iter = a.iterator();
        T largest = iter.next();
        while (iter.hasNext()) {
            T next = iter.next();
            if (largest.compareTo(next) < 0) {
                largest = next;
            }
        }
        return largest;
    }

    /**
     * 泛型集合 —— 找数组/数组列表/链表的最大值【通用方法】
     *   - 测试后发现这是真.通用方法，根本不需要写额外三个max方法
     * @param c 实现了Collection接口的对象
     * @param <T> 类型，并要求它实现了Comparable接口
     * @return 数组/数组列表/链表的最大值
     */
    public static <T extends Comparable> T maxCommon(Collection<T> c) {
        if (c.isEmpty()) {
            throw new NoSuchElementException();
        }
        Iterator<T> iter = c.iterator();
        T largest = iter.next();
        while (iter.hasNext()) {
            T next = iter.next();
            if (largest.compareTo(next) < 0) {
                largest = next;
            }
        }
        return largest;
    }
}
