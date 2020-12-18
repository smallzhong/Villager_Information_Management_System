package my;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class zyc_village_information_editor
{
    private static void createGUI()
    {
        JFrame frame = new MyFrame("8003119075 钟雨初");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置窗口大小
        frame.setSize(600, 300);

        // 显示窗口
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        // 此段代码间接地调用了 createGUI()
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                createGUI();
            }
        });

    }
}
