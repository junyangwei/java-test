package thread;

/**
 * 并发 —— 创建一个简单的线程
 * @author junyangwei
 * @date 2021-09-10
 */
public class TestRunnable {
    public static void main(String[] args) {
        Runnable r = () -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("线程打印：" + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
    }
}
