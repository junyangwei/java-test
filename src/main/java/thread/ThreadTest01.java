package thread;

/**
 * 新启动一个线程的简单示例
 * @author junyangwei
 * @date 2021-10-14
 */
public class ThreadTest01 {
    public static void main(String[] args) {
        Runnable task = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread t = Thread.currentThread();
            System.out.println("当前线程:" + t.getName());
        };
        Thread thread = new Thread(task);
        thread.setName("test-thread-1");
        /*
        设置为守护线程，参数设置为true
        对 JVM 进程来说，若当前正在运行的线程都是守护线程，JVM就会直接停掉当前进程
        因此，这里设置新线程为守护线程，当main方法主线程走完后，实际上并不会执行我们的run方法

        解决方式：
            1. 注释掉 thread.setDaemon(true); 让新起的线程不是守护线程
            2. 在Thread.start(); 下面加上 Thread.sleep(5500); 保证在主线程还没执行完成前，能够执行守护线程的run方法
         */
        thread.setDaemon(true);
        // 启动线程，让 JVM 启动操作系统线程执行 run 方法中的代码逻辑
        thread.start();

//        Thread.sleep(5500);
    }
}
