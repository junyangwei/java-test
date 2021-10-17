package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Lock 锁的练习
 * @author junyangwei
 * @date 2021-10-17
 */
public class LockCounter {
    private int sum = 0;

    /**
     * 可重入锁 + 公平锁
     */
    private Lock lock = new ReentrantLock(true);

    public int addAndGet() {
        try {
            lock.lock();
            return ++sum;
        } finally {
            lock.unlock();
        }
    }

    public int getSum() {
        return sum;
    }

    // 测试代码
    public static void main(String[] args) {
        // 循环 100W 次
        int loopNum = 100_0000;
        LockCounter counter = new LockCounter();
        IntStream.range(0, loopNum).parallel().forEach(i -> counter.addAndGet());
        System.out.println("最终的sum值:" + counter.getSum());
    }
}
