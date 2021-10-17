package thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 随机睡眠时间 —— Callable 接口练习 & Future 接口练习
 * @author junyangwei
 * @date 2021-10-17
 */
public class RandomSleepTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        // 随机时间为10秒内（10 * 1000 毫秒）
        int sleep = new Random().nextInt(10000);
        TimeUnit.MILLISECONDS.sleep(sleep);
        return sleep;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("测试开始...");
        RandomSleepTask r = new RandomSleepTask();
        try {
            System.out.println("随机的休眠毫秒数：" + r.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试结束.");

        /*
         Future 接口练习
         */
        Callable<Integer> task = new RandomSleepTask();
        // 初始化执行线程池
        ExecutorService executorService = CustomThreadFactory.initThreadPoolExecutor();
        Future<Integer> future1 = executorService.submit(task);
        Future<Integer> future2 = executorService.submit(task);
        // 等待执行结果
        Integer result1 = future1.get(10, TimeUnit.SECONDS);
        Integer result2 = future2.get(10, TimeUnit.SECONDS);
        System.out.println("result1=" + result1);
        System.out.println("result2=" + result2);
    }

}
