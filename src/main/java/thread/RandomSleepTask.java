package thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 随机睡眠时间 —— Callable 接口练习
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

    public static void main(String[] args) {
        System.out.println("测试开始...");
        RandomSleepTask r = new RandomSleepTask();
        try {
            System.out.println("随机的休眠毫秒数：" + r.call());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("测试结束.");

    }

}
