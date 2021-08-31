package set;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 * 集合 —— 优先级队列练习
 * @author junyangwei
 * @date 2021-08-30
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<LocalDate> pq = new PriorityQueue<>();
        pq.add(LocalDate.of(1906, 12, 9));
        pq.add(LocalDate.of(1815, 12, 10));
        pq.add(LocalDate.of(1903, 12, 3));
        pq.add(LocalDate.of(1910, 6, 22));

        System.out.println("Iterating over elements...");
        Iterator<LocalDate> iter = pq.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        for (LocalDate date : pq) {
            System.out.println(date);
        }
        System.out.println("Removing elements...");
        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }
    }
}
