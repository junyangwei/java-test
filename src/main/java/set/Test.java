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
        rawList2.add(new Date());
    }

    enum Weekday { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY };
}
