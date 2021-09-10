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

        // 创建线程的另一个方式
        MyThreadTest t2 = new MyThreadTest();
        t2.start();
        // 需要额外注意，调用run方法只会执行同一个线程中的任务，而不会启动新线程
        t2.run();
    }
}

class MyThreadTest extends Thread {
    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程打印2：" + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
