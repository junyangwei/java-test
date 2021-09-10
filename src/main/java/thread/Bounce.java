package thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * 并发 —— 什么是线程之弹力球练习
 * @author junyangwei
 * @date 2021-09-10
 */
public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * 带有球组件和按钮的框架
 */
class BounceFrame extends JFrame {
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    /**
     * 构造带有展示弹力球以及开始和关闭按钮的框架
     */
    public BounceFrame() {
        setTitle("弹力球");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "开始", event -> addBall());
        addButton(buttonPanel, "结束", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    /**
     * 向容器中添加一个球
     * @param c 容器
     * @param title 按钮的名字
     * @param listener 按钮的监听事件
     */
    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * 在面板上添加一个弹力球并使其跳动1000次
     */
    public void addBall() {
        try {
            Ball ball = new Ball();
            comp.add(ball);

            for (int i = 1; i <= STEPS; i++) {
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
        }
    }
}
