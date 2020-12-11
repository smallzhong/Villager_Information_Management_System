package my;


import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MyFrame extends JFrame
{
    // 主布局，卡片
    JPanel cards = new JPanel();

    // 表格数据
    DefaultTableModel tableModel = new DefaultTableModel();

    // 显示表格
    JTable table = new JTable(tableModel);

    public MyFrame(String title)
    {
        super("8003119075 钟雨初");

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        // 菜单 文件
        JMenu fileMenu = new JMenu("视图");
        menubar.add(fileMenu);
        JMenuItem fileOpenCmd = new JMenuItem("视图1");
        JMenuItem fileSaveCmd = new JMenuItem("视图2");
        JMenuItem fileSaveAsCmd = new JMenuItem("村民信息");
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

        // 退出事件响应
        fileExitCmd.addActionListener(e -> System.exit(0));
        // 切换到村民信息视图
        fileOpenCmd.addActionListener(e -> switchCard(1));
        fileSaveCmd.addActionListener(e -> switchCard(0));
        fileSaveAsCmd.addActionListener(e->switchCard(2));
//        fileOpenCmd.addActionListener(e ->
//                JOptionPane.showMessageDialog(null, "钟雨初", "InfoBox: ", JOptionPane.INFORMATION_MESSAGE));

        Container contentPane = getContentPane();

        // 给顶层容器，设置 BorderLayout
        contentPane.setLayout(new BorderLayout());
        contentPane.add(cards, BorderLayout.CENTER);

        // 两张卡片
        JPanel p1 = panel1();
        JPanel p2 = panel2();
        JPanel p3 = panel3();

        cards.setLayout(new CardLayout());
        cards.add(p1, "buttons"); // 添加第一张卡片, 名字叫 buttons
        cards.add(p2, "text"); // 添加第二张卡片，名字叫 text
        cards.add(p3, "table");

        switchCard(1);
        switchCard(0);
        switchCard(2);
    }

    JPanel panel3()
    {
        // Content Pane
        JPanel root = new JPanel();
//        this.setContentPane(root); // 设置为默认背景
        root.setLayout(new BorderLayout());

        // 添加到主界面
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(true); // 整行选择
        root.add(scrollPane, BorderLayout.CENTER);

        // 初始化设置：添加5列
        tableModel.addColumn("学号");
        tableModel.addColumn("姓名");
        tableModel.addColumn("性别");
        tableModel.addColumn("出生日期");
        tableModel.addColumn("手机号");

        // 添加数据行
        Student stu = new Student();
        stu.id = "20180001";
        stu.name = "li";
        stu.sex = true;
        stu.cellphone = "13810012345";
        stu.birthday = "1982-2-2";
        addTableRow(stu);

        Student stu2 = new Student();
        stu2.id = "20180002";
        stu2.name = "wang";
        stu2.sex = false;
        stu2.cellphone = "14788889999";
        stu2.birthday = "1982-2-3";
        addTableRow(stu2);

        return root;
    }

    private void addTableRow(Student item)
    {
        // java.util.Vector 是个范型 ，表示数组
        Vector<Object> rowData = new Vector<>();
        rowData.add(item.id);
        rowData.add(item.name);
        rowData.add(item.sex);
        rowData.add(item.birthday);
        rowData.add(item.cellphone);
        tableModel.addRow(rowData); // 添加一行

//		Object[] rowData = new Object[5];
//		rowData[0] = item.id;
//		rowData[1] = item.name;
//		rowData[2] = item.sex;
//		rowData[3] = item.birthday;
//		rowData[4] = item.cellphone;
//		tableModel.addRow( rowData );
    }

    JPanel panel1()
    {
        // 创建第一个面板
        JPanel p1 = new JPanel();
        p1.add(new JButton("红"));
        p1.add(new JButton("绿"));
        p1.add(new JButton("蓝"));

        return p1;
    }

    JPanel panel2()
    {
        // 创建第二个面板
        JPanel p2 = new JPanel();
        p2.add(new JLabel("输入"));
        p2.add(new JTextField(20));
        return p2;
    }

    // 切换到cardnum卡片
    public void switchCard(int cardnum)
    {
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        if (cardnum == 1)
        {
            cardLayout.show(cards, "buttons");
        }
        else if (cardnum == 0)
        {
            cardLayout.show(cards, "text");
        }
        else if (cardnum == 2)
        {
            cardLayout.show(cards, "table");
        }
    }
}