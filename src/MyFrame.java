package my;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class MyFrame extends JFrame
{


    public MyFrame(String title)
    {
        super(title);

        // Content Pane
        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new BorderLayout());

        // 添加菜单
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        // 菜单 文件
        JMenu fileMenu = new JMenu("文件");
        menubar.add(fileMenu);
        JMenuItem fileOpenCmd = new JMenuItem("打开");
        JMenuItem fileSaveCmd = new JMenuItem("保存");
        JMenuItem fileSaveAsCmd = new JMenuItem("另存为...");
        fileMenu.add(fileOpenCmd);
        fileMenu.add(fileSaveCmd);
        fileMenu.add(fileSaveAsCmd);

        JMenuItem fileExitCmd = new JMenuItem("退出");
        fileMenu.addSeparator();
        fileMenu.add(fileExitCmd);

        // 帮助菜单
        JMenu helpMenu = new JMenu("帮助");
        menubar.add(helpMenu);
        JMenuItem helpAbout = new JMenuItem("关于");
        JMenuItem helpManual = new JMenuItem("打开帮助");
        helpMenu.add(helpAbout);
        helpMenu.add(helpManual);

        // 菜单事件响应
        fileExitCmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
    }
}
