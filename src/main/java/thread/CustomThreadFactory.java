package thread;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用 ThreadFactory 的示例
 * @author junyangwei
 * @date 2021-10-15
 */
public class CustomThreadFactory implements ThreadFactory {
    private AtomicInteger serial = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        // 根据需要设置守护线程
        thread.setDaemon(true);
        thread.setName("CustomThread-" + serial.getAndIncrement());
        return thread;
    }

    /**
     * ThreadPoolExecutor 示例
     * 创建线程池的示例代码
     */
    public static ThreadPoolExecutor initThreadPoolExecutor() {
        // 定义核心线程数为当前CPU核心数
        int coreSize = Runtime.getRuntime().availableProcessors();
        // 定义最大核心线程数为当前 CPU 核心数的两倍，保证充分利用 CPU
        int maxSize = Runtime.getRuntime().availableProcessors() * 2;
        // 使用LinkedBlockingDeque创建
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(500);
        // 线程工厂就用这个类
        CustomThreadFactory threadFactory = new CustomThreadFactory();
        // 创建线程池执行器（这里没有显式地设置拒绝测录，而是使用默认的AbortPolicy）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(coreSize, maxSize,
                1, TimeUnit.MINUTES, workQueue, threadFactory);
        return executor;

    }
}
