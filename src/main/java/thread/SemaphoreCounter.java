package thread;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

/**
 * 信号量 - 计数器 练习
 * @author junyangwei
 * @date 2021-10-18
 */
public class SemaphoreCounter {
    private int sum = 0;
    // 初始化读的信号量，同一时刻允许100个线程并发读
    private Semaphore readSemaphore = new Semaphore(100, true);
    // 初始化写的信号量，并设置独占锁（同一时刻只能有一个线程写），相当于 synchronized
    private Semaphore writeSemaphore = new Semaphore(1);

    public int incrAndGet() {
        try {
            writeSemaphore.acquireUninterruptibly();
            return ++sum;
        } finally {
            writeSemaphore.release();
        }
    }
    public int getSum() {
        try {
            readSemaphore.acquireUninterruptibly();
            return sum;
        } finally {
            readSemaphore.release();
        }
    }

    // 测试代码
    public static void main(String[] args) {
        // 循环 100W 次
        int loopNum = 100_0000;
        SemaphoreCounter counter = new SemaphoreCounter();
        IntStream.range(0, loopNum).parallel().forEach(i -> counter.incrAndGet());
        System.out.println("最终的sum值:" + counter.getSum());
    }
}
