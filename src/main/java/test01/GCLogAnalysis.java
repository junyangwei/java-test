import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * GC 日志分析
 * 命令行打印 GC 日志命令：
 *  - 使用Java8默认GC：java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m GCLogAnalysis
 *  - 使用串行GC：java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseSerialGC GCLogAnalysis
 *  - 使用Java8的并行GC：java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseParallelGC GCLogAnalysis
 *  - 使用CMS GC：java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseConcMarkSweepGC GCLogAnalysis
 *  - 使用G1 GC：java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx512m -Xms512m -XX:+UseG1GC GCLogAnalysis
 *  - 使用G1 GC（没有详情）：java -XX:+PrintGC -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseG1GC GCLogAnalysis
 * 可尝试修改 -Xms 和 -Xmx 修改堆内存大小来看看结果变化
 * @author junyangwei
 * @date 2021-09-22
 */
public class GCLogAnalysis {
    /**
     * 定义一个随机数对象
     */
    private static Random random = new Random();

    public static void main(String[] args) {
        // 当前毫秒时间戳
        long startMills = System.currentTimeMillis();
        // 持续运行毫秒数；可根据需要进行修改
        long timeoutMills = TimeUnit.SECONDS.toMillis(1);
        // 结束时间戳
        long endMills = startMills + timeoutMills;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行...");
        // 缓存一部分对象；进入老年代
        int cacheSize = 2000;
        Object[] cachedGarbage = new Object[cacheSize];
        // 在此时间范围内，持续循环
        while (System.currentTimeMillis() < endMills) {
            // 生成垃圾对象
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize) {
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束！共生成对象次数：" + counter.longValue());
    }

    private static Object generateGarbage(int max) {
        int randomSize = random.nextInt(max);
        int type = randomSize % 4;
        Object result = null;
        switch (type) {
            case 0:
                result = new int[randomSize];
                break;
            case 1:
                result = new byte[randomSize];
                break;
            case 2:
                result = new double[randomSize];
                break;
            default:
                StringBuilder builder = new StringBuilder();
                String randomString = "randomString-Anything";
                while (builder.length() < randomSize) {
                    builder.append(randomString);
                    builder.append(max);
                    builder.append(randomSize);
                }
                result = builder.toString();
                break;
        }
        return result;
    }
}
