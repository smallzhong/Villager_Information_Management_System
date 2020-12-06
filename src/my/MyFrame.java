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
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MyFrame extends JFrame
{
    JPanel root = new JPanel();

    // Model : 负责数据
    DefaultTableModel tableModel = new DefaultTableModel();

    // View : 负责显示， 创建JTable的时候指定一个Model
    JTable table = new JTable(tableModel);

    public MyFrame(String title)
    {
        super("8003119075 钟雨初");

        // Content Pane

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
        // 添加一条分割线
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
        fileExitCmd.addActionListener(e -> System.exit(0));

        // 设置表格
        this.initTable();
    }

    private void initTable()
    {
        // 设置表格
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        root.add(scrollPane, BorderLayout.CENTER);

        // 初始化设置：添加4列
        tableModel.addColumn("姓名");
        tableModel.addColumn("性别");
        tableModel.addColumn("身份证号");
        tableModel.addColumn("居住地址");
        tableModel.addColumn("电话号码");

        Villager villager = new Villager();
        villager.id = "444444444444444444";
        villager.sex = "男";
        villager.addr = "南昌大学青山湖校区";
        villager.name = "钟雨初";
        villager.phone_number = "13333333333";

        addTableRow(villager);
    }

    private void addTableRow(Villager villager)
    {
        Vector<Object> rowData = new Vector<>();
        rowData.add(villager.name);
        rowData.add(villager.sex);
        rowData.add(villager.id);
        tableModel.addRow(rowData); // 添加一行
    }
}

