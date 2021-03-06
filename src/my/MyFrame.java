package my;

import java.util.LinkedList;
import java.util.Queue;

import java.awt.event.*;
import java.sql.*;

import java.awt.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MyFrame extends JFrame
{
    // 调试时设置为true，发布时设置为false
    private final boolean isdebug = true;

    int[][] barrier = {{2, 0, 1, 0, 0, 2, 2, 2, 2, 2, 0, 2, 1, 1, 2, 2, 0, 1, 2, 0, 2, 0, 2, 2, 0, 2, 2, 0, 0, 0, 0, 1},
            {1, 0, 2, 2, 2, 0, 2, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 2, 1, 2, 2, 1},
            {2, 1, 1, 1, 1, 1, 1, 0, 2, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 0, 2, 0, 1, 0, 0, 2, 2, 2, 0, 2, 1},
            {0, 2, 1, 0, 2, 1, 0, 0, 1, 2, 2, 0, 2, 1, 2, 0, 1, 0, 1, 1, 1, 0, 2, 0, 2, 2, 2, 2, 1, 2, 2, 1},
            {2, 0, 0, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 1, 0, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 2, 0, 2, 1, 0},
            {0, 2, 1, 2, 2, 1, 0, 2, 2, 1, 0, 0, 0, 1, 0, 0, 2, 2, 2, 1, 1, 1, 2, 2, 0, 2, 1, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 0, 1, 2, 0, 0, 1, 2, 2, 2, 1, 2, 0, 0, 2, 0, 2, 2, 0, 0, 1, 1, 0, 2, 0, 2, 1},
            {1, 2, 2, 2, 2, 2, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 2, 1, 2, 1, 1, 0, 2, 0, 2, 0, 1, 2, 1, 1, 2, 2},
            {2, 0, 1, 1, 0, 2, 0, 2, 2, 0, 0, 1, 1, 1, 1, 2, 0, 1, 2, 2, 2, 1, 2, 0, 0, 1, 2, 0, 1, 0, 2, 1},
            {1, 0, 1, 2, 0, 1, 2, 0, 0, 2, 1, 2, 2, 1, 2, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 2, 2, 1, 0, 2, 2, 1},
            {0, 2, 0, 0, 2, 0, 2, 2, 1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 0, 0, 0, 2, 1, 1, 2, 0, 1, 2, 0},
            {0, 0, 1, 0, 0, 1, 1, 1, 2, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1, 2, 0, 2, 2, 0, 2, 1, 0, 2, 2, 1, 2, 2},
            {1, 2, 1, 1, 2, 0, 1, 0, 0, 1, 0, 0, 0, 2, 1, 2, 1, 2, 1, 0, 1, 0, 2, 0, 2, 1, 2, 0, 1, 2, 2, 1},
            {2, 0, 1, 1, 1, 1, 2, 1, 0, 1, 1, 0, 1, 1, 1, 2, 0, 0, 2, 0, 1, 0, 2, 0, 1, 1, 0, 0, 0, 0, 0, 1},
            {2, 1, 2, 2, 0, 2, 2, 0, 1, 1, 2, 0, 0, 0, 2, 0, 0, 2, 2, 1, 2, 1, 1, 0, 1, 2, 2, 0, 2, 0, 2, 1},
            {1, 2, 1, 0, 0, 2, 0, 2, 1, 0, 2, 1, 2, 1, 1, 0, 2, 0, 2, 1, 1, 0, 2, 1, 1, 2, 0, 2, 1, 2, 2, 0},
            {2, 0, 1, 2, 2, 0, 1, 0, 0, 2, 2, 0, 1, 2, 2, 2, 2, 1, 2, 0, 2, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1},
            {2, 0, 2, 1, 2, 1, 0, 2, 1, 2, 2, 0, 1, 0, 0, 1, 0, 1, 2, 0, 1, 0, 1, 1, 1, 2, 0, 0, 1, 1, 2, 0},
            {1, 0, 2, 1, 1, 1, 0, 0, 1, 2, 0, 0, 1, 1, 1, 2, 1, 2, 1, 2, 1, 1, 1, 2, 2, 1, 2, 1, 1, 0, 0, 0},
            {2, 2, 2, 2, 2, 2, 1, 1, 0, 1, 2, 1, 1, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 1, 0, 2, 2, 0, 2, 2, 1, 0},
            {0, 0, 0, 1, 0, 2, 1, 0, 0, 2, 1, 2, 1, 1, 0, 1, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1, 0, 1, 1},
            {0, 1, 1, 0, 0, 1, 2, 2, 2, 0, 2, 0, 0, 2, 1, 0, 1, 0, 2, 2, 2, 0, 1, 1, 2, 2, 2, 2, 0, 0, 1, 1},
            {1, 1, 0, 2, 2, 1, 0, 1, 2, 2, 2, 2, 2, 2, 0, 2, 0, 2, 2, 0, 1, 2, 1, 1, 2, 0, 2, 2, 2, 0, 0, 1},
            {0, 2, 2, 2, 1, 1, 2, 1, 1, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 1, 0, 2, 2, 1, 0, 1, 0, 2, 1, 0, 1, 0},
            {1, 2, 1, 1, 2, 1, 0, 0, 2, 1, 0, 0, 0, 0, 0, 1, 2, 2, 1, 2, 0, 1, 2, 2, 1, 1, 2, 0, 0, 1, 1, 0},
            {0, 0, 2, 0, 2, 0, 2, 0, 1, 2, 1, 2, 1, 1, 1, 0, 1, 1, 1, 2, 1, 1, 1, 0, 2, 2, 1, 2, 0, 1, 2, 0},
            {0, 0, 2, 0, 2, 1, 0, 2, 1, 1, 0, 1, 2, 2, 2, 0, 2, 2, 0, 0, 2, 2, 2, 0, 2, 0, 0, 0, 1, 1, 1, 0},
            {0, 1, 0, 1, 0, 2, 0, 0, 1, 2, 2, 1, 0, 0, 2, 0, 0, 2, 2, 2, 1, 2, 2, 2, 0, 0, 2, 0, 1, 2, 2, 2},
            {1, 0, 2, 2, 1, 0, 0, 2, 2, 1, 2, 0, 2, 2, 1, 0, 0, 0, 1, 2, 1, 1, 1, 2, 2, 1, 0, 2, 2, 1, 0, 0},
            {2, 0, 0, 0, 2, 1, 2, 1, 2, 2, 1, 1, 0, 0, 2, 0, 0, 1, 1, 0, 2, 1, 1, 0, 2, 1, 1, 0, 2, 1, 0, 0},
            {0, 2, 2, 1, 1, 2, 0, 2, 2, 1, 1, 1, 0, 1, 0, 2, 0, 1, 1, 2, 2, 2, 0, 2, 1, 0, 1, 1, 1, 2, 2, 0},
            {2, 2, 1, 1, 1, 2, 1, 1, 0, 0, 1, 1, 2, 2, 2, 1, 0, 0, 0, 0, 2, 2, 2, 1, 1, 2, 0, 1, 2, 1, 2, 2}
    };

    private final int CSIZE = 20; // 网格中的边框大小
    private final int cell_ct = 31; // 一排有多少个格子
    private final int MAP_HEIGHT = CSIZE * cell_ct;
    private final int MAP_WIDTH = CSIZE * cell_ct;


    // 是最喜欢的丁香紫啊
    private final Color zyc_lilac = new Color(0xc8a2c8);

    // 是否正在调用算法解题
    private boolean issolving = false;

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/yc_data";

    // 数据库的用户名与密码
    static final String SQL_USER = "root";
    static final String SQL_PASS = "123456";

    // 主布局，卡片
    private final JPanel cards = new JPanel();

    // 村民信息表格数据
    private final DefaultTableModel VillagertableModel = new DefaultTableModel();

    // 村民信息显示表格
    private final JTable Villagerrtable = new JTable(VillagertableModel);

    // 村庄距离表格数据
    private final DefaultTableModel DistancetableModel = new DefaultTableModel();

    // 村庄距离显示表格
    private final JTable Distancetable = new JTable(DistancetableModel);

    // 村庄信息Combobox
    private final JComboBox<String> VillageCombobox = new JComboBox();

    // VillageCombobox里面选择的元素的下标，刚开始的时候要跳过
    private int VillageSelected = -1;

    // 指示是否需要更新Combobox中的元素的Flag
    boolean UpdateComboboxFlag = true;

    static private final int ZYC_WIDTH = 1000;
    static private final int ZYC_HEIGHT = 750;

    JComboBox<String> startPointComboBox = null;
    JComboBox<String> endPointCombobox = null;

    Map roadMap;

    private int village1x = -1;
    private int village1y = -1;
    private int village2x = -1;
    private int village2y = -1;

    boolean vis[][];
    Pair prev[][];
    boolean ispath[][];

    private boolean showInq = false;

    void init()
    {
        ispath = new boolean[cell_ct][cell_ct];
        prev = new Pair[cell_ct][cell_ct];

        // 初始化vis数组
        vis = new boolean[cell_ct][cell_ct];
        for (int i = 0; i < cell_ct; i++)
        {
            for (int j = 0; j < cell_ct; j++)
            {
                vis[i][j] = false;
                ispath[i][j] = false;
            }
        }

        // 添加条目选中状态改变的监听器
        VillageCombobox.addItemListener(e ->
        {
            if (e.getStateChange() == ItemEvent.SELECTED)
            {
                // 只有当有改动的时候才更新
                if (VillageCombobox.getSelectedIndex() != VillageSelected)
                {
                    System.out.println("SELECTED");
                    MyFrame.this.UpdateVillagerDataEx(VillageCombobox.getSelectedIndex());
                }
            }
        });
    }

    // 测试函数
    void test()
    {
        // showMapJframe();
    }

    // 展示最短路可视化对话框
    private void showMapJFrame()
    {
        JFrame frame = new JFrame();
        frame = new JFrame();
        frame.setVisible(true);
        // 不可调整大小，没有设置重绘方法
        frame.setResizable(false);
        frame.setSize(ZYC_WIDTH, ZYC_HEIGHT);
        frame.setTitle("村庄地图 8003119075 钟雨初");
//        frame.setLocationRelativeTo(null);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
//        frame.getContentPane().setLayout(new FlowLayout()); // TODO:先设置为流式布局，之后完善

        roadMap = new Map();
        roadMap.setBounds(250, 10, MAP_WIDTH + 1, MAP_HEIGHT + 1); // 要+1，防止边上的线不显示

        frame.add(roadMap);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
//        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBounds(10, 10, 230, MAP_HEIGHT + 1);
//        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createTitledBorder("操作面板"));

        // 开始搜索
        JButton beginSearch = new JButton("开始搜索");
        panel.add(beginSearch);

        // TODO:这里要能够刷新这个combobox里面的数据
        // 起点村庄对话框
        startPointComboBox = getVillageCombobox();
        panel.add(startPointComboBox);

        // 用户一选择就直接更新图上的点
        startPointComboBox.addActionListener(e ->
        {
            // 一旦更改，直接全部清空
            ispath = new boolean[cell_ct][cell_ct];
            vis = new boolean[cell_ct][cell_ct];

            if (startPointComboBox.getSelectedItem().equals(endPointCombobox.getSelectedItem()))
                return;
            getSelectVillagePos();
            roadMap.repaint();
        });

        // 终点村庄对话框
        endPointCombobox = getVillageCombobox();
        panel.add(endPointCombobox);

        // 用户一选择就直接更新图上的点
        endPointCombobox.addActionListener(e ->
        {
            // 一旦更改，直接全部清空
            ispath = new boolean[cell_ct][cell_ct];
            vis = new boolean[cell_ct][cell_ct];
            if (startPointComboBox.getSelectedItem().equals(endPointCombobox.getSelectedItem()))
                return;
            getSelectVillagePos();
            roadMap.repaint();
        });

        JButton refreshCombobox = new JButton("刷新村庄信息");
//        panel.add(refreshCombobox);
        refreshCombobox.addActionListener(e ->
        {
            // 如果两个一样，说明用户没有对村庄进行选择
            if (startPointComboBox.getSelectedItem().equals(endPointCombobox.getSelectedItem()))
                return;
            // 更新选择的村庄的信息
            getSelectVillagePos();
            // 重绘画布
            roadMap.repaint();
        });

        JButton showInqPath = new JButton("展示搜索过程中搜索到的点");
        panel.add(showInqPath);
        showInqPath.addActionListener(e ->
        {
            if (showInq) showInq = false;
            else showInq = true;
            roadMap.repaint();
        });

        frame.add(panel);

        beginSearch.addActionListener(e ->
        {
            // 特判起点和终点村庄不能一样
            if (startPointComboBox.getSelectedItem().equals(endPointCombobox.getSelectedItem()))
            {
                JOptionPane.showMessageDialog(null,
                        "起点和终点村庄不能一样！请重新选择！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            getSelectVillagePos();
            System.out.printf("%d %d %d %d\n", village1x, village1y, village2x, village2y);

            // 获取了起点和终点，重绘一遍
            roadMap.repaint();


            solve();

            // 解决了之后重绘一遍
            roadMap.repaint();
        });
    }

    private boolean check(int x, int y)
    {
        if (x < 0 || y < 0 || x >= cell_ct || y >= cell_ct) return false;
        if (vis[x][y]) return false;
        if (barrier[x][y] == 0) return false;

        return true;
    }

    private void solve()
    {
        // 初始化prev(之前的那个会被gc掉，不用自己处理)
        prev = new Pair[cell_ct][cell_ct];
        // 初始化vis
        for (int i = 0; i < cell_ct; i++)
        {
            for (int j = 0; j < cell_ct; j++)
            {
                vis[i][j] = false;
                ispath[i][j] = false;
            }
        }

        //初始化队列
        Queue<Pair> q = new LinkedList<Pair>();
        int[] dy = {0, 1, 0, -1}, dx = {-1, 0, 1, 0};

        vis[village1x][village1y] = true;
        q.offer(new Pair(village1x, village1y));

        while (!q.isEmpty())
        {
            Pair pair = q.poll();
            if (pair.x == village2x && pair.y == village2y)
            {
                System.out.printf("找到了！\n");
                break;
            }

            for (int i = 0; i < 4; i++)
            {
                int tx = pair.x + dx[i];
                int ty = pair.y + dy[i];
                if (check(tx, ty))
                {
                    q.offer(new Pair(tx, ty));
                    vis[tx][ty] = true;

                    roadMap.repaint(); // 每一次设置一个vis为true就repaint一遍

                    prev[tx][ty] = pair;
                }
            }
        }

        int x = village2x, y = village2y;
        while (x != village1x || y != village1y)
        {
            Pair t = prev[x][y];
            ispath[t.x][t.y] = true;
            x = t.x;
            y = t.y;
        }

    }

    // TODO：在map上画出起点和终点
    private void paintStartPointAndEndPoint()
    {
        System.out.printf("正调用repaint!");
        roadMap.repaint();
    }

    // 用来获取被选中的村庄的信息并更新信息
    private boolean getSelectVillagePos()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT x, y FROM village_pos WHERE village = \"" + startPointComboBox.getSelectedItem() + "\"";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                village1x = rs.getInt("x");
                village1y = rs.getInt("y");
            }

            sql = "SELECT x, y FROM village_pos WHERE village = \"" + endPointCombobox.getSelectedItem() + "\"";
            rs = stmt.executeQuery(sql);

            while (rs.next())
            {
                village2x = rs.getInt("x");
                village2y = rs.getInt("y");
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
            return false;
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
            return false;
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
                return false;
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
                return false;
            }
        }

        return true;
    }

    // 获取一个有全部村庄信息的combobox
    private JComboBox getVillageCombobox()
    {
        JComboBox<String> c = new JComboBox<String>();
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT x, y, village FROM village_pos";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
//                int x = rs.getInt("x");
//                int y = rs.getInt("y");
                String village = rs.getString("village");

                // 添加到combobox里面去
                c.addItem(village);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        return c;
    }

    // TODO：完善Map画图
    // 内部类，用来做出图
    class Map extends JPanel implements MouseListener, MouseMotionListener
    {
        public Map()
        {
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        public void paintComponent(Graphics g)
        {
            System.out.printf("repainting!\n");
            super.paintComponent(g);
            int width = this.getWidth();
            int height = this.getHeight();
            System.out.printf("width = %d, height = %d\n", width, height);

            g.setColor(Color.WHITE);
            g.fillRect(0, 0, width, height);

            // 画出障碍物（丁香紫色）
            g.setColor(zyc_lilac);
            setWalls(g);

            // 当起点被设置了
            // TODO:这里可以换成 `~village1x`
            if (village1x != -1 && village1y != -1)
            {
                // 起点是黑色的
                g.setColor(Color.BLACK);
                g.fillRect(village1x * CSIZE, village1y * CSIZE, CSIZE, CSIZE);
            }

            // 当终点被设置了
            if (village2x != -1 && village2y != -1)
            {
                // 终点是绿色的
                g.setColor(Color.GREEN);
                g.fillRect(village2x * CSIZE, village2y * CSIZE, CSIZE, CSIZE);
            }

            // 最后画蓝色的线（防止线被覆盖）
            g.setColor(Color.BLUE); // 网格线设置为蓝色
            drawGrid(g);
        }

        // 画出基本的网格，防止网格线被填充的颜色覆盖
        private void drawGrid(Graphics g)
        {
            for (int i = 0; i < 31; i++)
            {
                for (int j = 0; j < 31; j++)
                {
                    g.drawRect(i * CSIZE, j * CSIZE, CSIZE, CSIZE);
                }
            }
        }

        private void setWalls(Graphics g)
        {
            for (int i = 0; i < cell_ct; i++)
            {
                for (int j = 0; j < cell_ct; j++)
                {
                    if (barrier[i][j] == 0)
                    {
                        g.setColor(zyc_lilac);
                        g.fillRect(i * CSIZE, j * CSIZE, CSIZE, CSIZE);
                    }

                    // 如果这个曾经入过队而且设置了展示搜索路径
                    if (vis[i][j] && showInq)
                    {
                        g.setColor(Color.RED);
                        g.fillRect(i * CSIZE, j * CSIZE, CSIZE, CSIZE);
                    }
                    if (ispath[i][j])
                    {
                        g.setColor(Color.PINK);
                        g.fillRect(i * CSIZE, j * CSIZE, CSIZE, CSIZE);
                    }
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e)
        {

        }

        @Override
        public void mousePressed(MouseEvent e)
        {

        }

        @Override
        public void mouseReleased(MouseEvent e)
        {

        }

        @Override
        public void mouseEntered(MouseEvent e)
        {

        }

        @Override
        public void mouseExited(MouseEvent e)
        {

        }

        @Override
        public void mouseDragged(MouseEvent e)
        {

        }

        @Override
        public void mouseMoved(MouseEvent e)
        {

        }
    }

    private boolean login()
    {
        LoginDialog l = new LoginDialog(this);
        return l.checkValid();
    }

    public MyFrame(String title)
    {
        super(title);

        // 不断弹出登录窗口，直到登录成功
        if (isdebug == false)
        {
            if (!login())
            {
                JOptionPane.showMessageDialog(null,
                        "登录失败，用户名或密码错误！请重新登录！",
                        "登录失败，用户名或密码错误！", JOptionPane.INFORMATION_MESSAGE);
                // 登录错误直接退出
                System.exit(0);
            }

            JOptionPane.showMessageDialog(null,
                    "登录成功！", "登录成功！", JOptionPane.INFORMATION_MESSAGE);
        }

        test();

        // 初始化
        init();

        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar);

        // 菜单 文件
        JMenu fileMenu = new JMenu("视图");
        menubar.add(fileMenu);
        JMenuItem villagePosView = new JMenuItem("村庄位置信息");
        JMenuItem view2 = new JMenuItem("视图2");
        JMenuItem villagerInfoView = new JMenuItem("村民信息");
        JMenuItem searchVillager = new JMenuItem("搜索村民");
        JMenuItem shortestPathVisible = new JMenuItem("展示最短路可视化对话框");
        fileMenu.add(villagerInfoView);
//        fileMenu.add(fileSaveCmd);
        fileMenu.add(villagePosView);
        // fileMenu.add(searchVillager);
        fileMenu.add(shortestPathVisible);

        JMenuItem fileExitCmd = new JMenuItem("退出");
        fileMenu.addSeparator();
        fileMenu.add(fileExitCmd);

        // 帮助菜单
        JMenu operateMenu = new JMenu("操作");
        menubar.add(operateMenu);
        JMenuItem refreshVillageInfo = new JMenuItem("刷新全部信息");
        JMenuItem deleteSelectedItem = new JMenuItem("删除数据");
        JMenuItem addItem = new JMenuItem("增加数据");
        JMenuItem addDistanceInfo = new JMenuItem("增加村庄距离信息");

        operateMenu.add(refreshVillageInfo);
        operateMenu.add(addDistanceInfo);
        operateMenu.add(deleteSelectedItem);
        operateMenu.add(addItem);

        // 展示地图按钮
        shortestPathVisible.addActionListener(e ->
        {
            showMapJFrame();
        });

        addDistanceInfo.addActionListener(e ->
        {
            addVillageposDialog test = new addVillageposDialog(this);

            Vector<String> v = test.getValue();

            // 如果点了取消
            if (!test.getRetValue()) return;

            // 调用addOneDistanceData来在距离表中插入一行数据
            addOneDistanceData(v);
        });

        // 添加数据
        addItem.addActionListener(e ->
        {
            EditVillagerDialog villagerDialog = new EditVillagerDialog(this);

            // 如果用户点击了取消或者关闭了窗口，则直接返回
            if (!villagerDialog.exec())
                return;

            else
            {
                Villager v = villagerDialog.getValue();

                // 录入数据
                if (addOneVillagerData(v))
                {
                    UpdateVillagerData();
                    JOptionPane.showMessageDialog(null,
                            v.name + "的信息录入成功！",
                            "错误", JOptionPane.INFORMATION_MESSAGE);

                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "录入数据出错！请检查数据有效性！", "错误", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        // 删除数据
        deleteSelectedItem.addActionListener(e ->
        {
            if (Villagerrtable.getSelectedRows().length == 0)
            {
                JOptionPane.showMessageDialog(null,
                        "您并未选中任何一行数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 弹出对话框确认
            int select = JOptionPane.showConfirmDialog(this,
                    "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                    "确认", JOptionPane.YES_NO_OPTION);
            if (select != 0) return; // 0号按钮是'确定'按钮

            Connection conn = null;
            Statement stmt = null;
            try
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);
                stmt = conn.createStatement();

                int[] count = Villagerrtable.getSelectedRows(); // 获取你选中的行号（记录）
                for (int j : count)
                {
                    // 读取身份证号字段的值
                    String id = Villagerrtable.getValueAt(j, 3).toString();
                    System.out.println(id);

                    String sql = "delete from yc_villagers where id = \"" + id + "\";";
                    System.out.printf("%s 被执行\n", sql);
                    stmt.execute(sql);
                }

                stmt.close();
                conn.close();
            } catch (SQLException se)
            {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception eee)
            {
                // 处理 Class.forName 错误
                eee.printStackTrace();
            } finally
            {
                // 关闭资源
                try
                {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2)
                {
                }// 什么都不做
                try
                {
                    if (conn != null) conn.close();
                } catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }


            // TODO：删除后要更新数据
            UpdateVillagerData();
        });

        // 更新村民数据（重新执行查询）
        // 同时更新距离信息和村庄信息
        refreshVillageInfo.addActionListener(
                e ->
                {
                    System.out.printf("刷新！idx = %d\n", VillageCombobox.getSelectedIndex());
                    // 更新村民数据和村庄信息
                    UpdateVillagerData();
                    // 更新距离信息
                    UpdateDistanceData();
                });

        // 删除数据操作
//        deleteData.addActionListener(e -> onDelete());

        // 退出事件响应
        fileExitCmd.addActionListener(e -> System.exit(0));
        // 切换到村民信息视图
        villagePosView.addActionListener(e -> switchCard(1));
        view2.addActionListener(e -> switchCard(0));
        villagerInfoView.addActionListener(e -> switchCard(2));
        searchVillager.addActionListener(e -> switchCard(3));
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
        JPanel p4 = panel4();

        cards.setLayout(new CardLayout());
        cards.add(p1, "buttons"); // 添加第一张卡片, 名字叫 buttons
        cards.add(p2, "text"); // 添加第二张卡片，名字叫 text
        cards.add(p3, "table");
        cards.add(p4, "search");

        switchCard(2);
    }

    // 防止多次调用UpdateVillagerData
    private boolean UpdateVillagerDataEx(int idx)
    {
        // 如果和上次的idx一样，说明没改变，直接返回，防止重复调用
        if (idx == VillageSelected) return false;
        else
        {
            VillageSelected = idx;
            // 如果选中的条目更改了则更新，先设置为false，防止更新combobox导致出错
            UpdateComboboxFlag = false;
            UpdateVillagerData();
            if (UpdateComboboxFlag == false)
            {
                JOptionPane.showMessageDialog(null,
                        "内部错误！UpdateComboboxFlag == false", "错误", JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
        }
    }

    private boolean UpdateTableInfoWithSelectedItem()
    {
        return true;
    }

    private boolean addOneDistanceData(Vector<String> v)
    {
//        double distance = 0;
//        try
//        {
//            distance = Double.valueOf(v.get(2));
//        } catch (NumberFormatException e)
//        {
//            JOptionPane.showMessageDialog(null,
//                    "输入的距离数据不合法！请重新输入！", "错误", JOptionPane.INFORMATION_MESSAGE);
//
//            return false;
//        }

        Connection conn = null;
        Statement stmt = null;
        // 准备语句
        PreparedStatement ps = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);
            stmt = conn.createStatement();


            // 写sql语句
            String sql = "insert into village_pos" +
                    " (x, y, village) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(v.get(0)));
            ps.setInt(2, Integer.parseInt(v.get(1)));
            ps.setString(3, v.get(2));

            // 执行update操作
            int resultSet = ps.executeUpdate();
            System.out.printf("resultset = %d\n", resultSet);
            if (resultSet > 0)
                System.out.println("success");
            else
                System.out.println("failure");

            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            JOptionPane.showMessageDialog(null,
                    "录入数据错误！两个人的身份证号不能一致，请检查您的输入！",
                    "出错啦", JOptionPane.INFORMATION_MESSAGE);
            se.printStackTrace();
            return false;
        } catch (Exception eee)
        {
            // 处理 Class.forName 错误
            eee.printStackTrace();
            return false;
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
                return false;
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
                return false;
            }
        }

        // 更新村民信息表格
//        UpdateVillagerData();

        // 更新距离信息
        UpdateDistanceData();

        return true;
    }

    // 往村民数据库中增加一条数据
    private boolean addOneVillagerData(Villager v)
    {
        Connection conn = null;
        Statement stmt = null;
        // 准备语句
        PreparedStatement ps = null;

        try
        {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);
            stmt = conn.createStatement();


            // 写sql语句
            String sql = "insert into yc_villagers (village, name, sex, id, addr, phone_number) values(?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, v.village);
            ps.setString(2, v.name);
            ps.setString(3, v.sex);
            ps.setString(4, v.id);
            ps.setString(5, v.addr);
            ps.setString(6, v.phone_number);

            // 执行update操作
            int resultSet = ps.executeUpdate();
            System.out.printf("resultset = %d\n", resultSet);
            if (resultSet > 0)
                System.out.println("success");
            else
                System.out.println("failure");

            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            JOptionPane.showMessageDialog(null,
                    "录入数据错误！两个人的身份证号不能一致，请检查您的输入！",
                    "出错啦", JOptionPane.INFORMATION_MESSAGE);
            se.printStackTrace();
            return false;
        } catch (Exception eee)
        {
            // 处理 Class.forName 错误
            eee.printStackTrace();
            return false;
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
                return false;
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
                return false;
            }
        }

        // 录入数据之后更新表格
        UpdateVillagerData();

        return true;
    }

    // 往村民数据库中一次添加多条数据
    private boolean addMultipleData()
    {
        return true;
    }

    private boolean UpdateVillageCombobox()
    {
        // 先把原来里面的所有元素删除
        if (true)
        {
            int ct = VillageCombobox.getItemCount();
            System.out.printf("ct = %d\n", ct);
            for (int i = ct - 1; i >= 0; i--)
            {
                System.out.printf("removing %d\n", i);
                VillageCombobox.removeItemAt(i);
            }
        }

        // 获取村庄名
        Vector<String> villages = getVillages();

        // 先把idx = 0的位置设置为所有村庄
        VillageCombobox.addItem("所有村庄");

        for (String s : villages)
        {
            VillageCombobox.addItem(s);
        }

        return true;
    }

    // 获取所有的村庄名（且DISTINCT）
    private Vector<String> getVillages()
    {
        Vector<String> v = new Vector<String>();

        // 连接数据库，获取所有的村庄名字
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT DISTINCT village FROM yc_villagers";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village = rs.getString("village");

                v.add(village);

                System.out.println(village);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        return v;
    }

    private void showVillagerContextMenu(MouseEvent e)
    {
        // 弹出右键菜单
        JPopupMenu menu = new JPopupMenu();
        JMenuItem deleteMenuCmd = new JMenuItem("删除选中项");
        JMenuItem updateMenuCmd = new JMenuItem("修改选中项");
        JMenuItem addMenuCmd = new JMenuItem("增加一项记录");
        menu.add(deleteMenuCmd);
        menu.add(updateMenuCmd);
        menu.add(addMenuCmd);

        addMenuCmd.addActionListener(ee ->
        {
            // 添加数据
            EditVillagerDialog villagerDialog = new EditVillagerDialog(this);

            // 如果用户点击了取消或者关闭了窗口，则直接返回
            if (!villagerDialog.exec())
                return;

            else
            {
                Villager v = villagerDialog.getValue();

                // 录入数据
                if (addOneVillagerData(v))
                {
                    UpdateVillagerData();
                    JOptionPane.showMessageDialog(null,
                            v.name + "的信息录入成功！",
                            "错误", JOptionPane.INFORMATION_MESSAGE);
                    UpdateVillagerData();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "录入数据出错！请检查数据有效性！", "错误", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        updateMenuCmd.addActionListener(ee ->
        {
            if (Villagerrtable.getSelectedRows().length == 0)
            {
                JOptionPane.showMessageDialog(null,
                        "您并未选中任何一行数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int[] count = Villagerrtable.getSelectedRows(); // 获取你选中的行号（记录）
            if (count.length != 1)
            {
                JOptionPane.showMessageDialog(null,
                        "一次只能修改一行数据，请只选择一行！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int idx = count[0];
            EditVillagerDialog villagerDialog = new EditVillagerDialog(this);


            Villager v = new Villager();
            v.village = (String) Villagerrtable.getValueAt(idx, 0);
            v.name = (String) Villagerrtable.getValueAt(idx, 1);
            v.sex = (String) Villagerrtable.getValueAt(idx, 2);
            v.id = (String) Villagerrtable.getValueAt(idx, 3);
            v.addr = (String) Villagerrtable.getValueAt(idx, 4);
            v.phone_number = (String) Villagerrtable.getValueAt(idx, 5);

            // 设置弹出的对话框中的值为默认值
            villagerDialog.setValue(v);

            // 如果没有选确定，则直接返回
            if (!villagerDialog.exec())
                return;
            else
            {
                // 重新取回数据
                Villager v2 = villagerDialog.getValue();
                if (v2.equals(v))
                {
                    // 如果并没有对数据进行任何修改
                    JOptionPane.showMessageDialog(null,
                            "您并没有对数据进行修改！", "错误", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                else
                {
                    // 连接数据库进行update
                    if (true)
                    {
                        Connection conn = null;
                        Statement stmt = null;
                        try
                        {
                            Class.forName(JDBC_DRIVER);
                            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);
                            stmt = conn.createStatement();

                            // 这里进行操作
                            PreparedStatement ps =
                                    conn.prepareStatement("UPDATE yc_villagers " +
                                            "SET village=?, name=?, sex=?, id=?, addr=?, phone_number=? WHERE id=?");

                            // 注意索引从1开始
                            ps.setString(1, v2.village);
                            ps.setString(2, v2.name);
                            ps.setString(3, v2.sex);
                            ps.setString(4, v2.id);
                            ps.setString(5, v2.addr);
                            ps.setString(6, v2.phone_number);
                            ps.setString(7, v.id);

                            int n = ps.executeUpdate(); // 返回更新的行数
                            if (n == 1)
                            {
                                JOptionPane.showMessageDialog(null,
                                        v.name + "的数据修改成功！", "错误", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else if (n < 1)
                            {
                                JOptionPane.showMessageDialog(null,
                                        "出错！数据库中的记录并没有被成功修改！", "错误", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                        "有多行数据被修改！", "错误", JOptionPane.INFORMATION_MESSAGE);

                            }

                            stmt.close();
                            conn.close();
                        } catch (SQLException se)
                        {
                            // 处理 JDBC 错误
                            se.printStackTrace();
                        } catch (Exception eee)
                        {
                            // 处理 Class.forName 错误
                            eee.printStackTrace();
                        } finally
                        {
                            // 关闭资源
                            try
                            {
                                if (stmt != null) stmt.close();
                            } catch (SQLException se2)
                            {
                            }// 什么都不做
                            try
                            {
                                if (conn != null) conn.close();
                            } catch (SQLException se)
                            {
                                se.printStackTrace();
                            }
                        }

                        // 最后更新下显示的数据
                        UpdateVillagerData();
                    }
                }
            }

        });

        deleteMenuCmd.addActionListener(ee ->
        {
            if (Villagerrtable.getSelectedRows().length == 0)
            {
                JOptionPane.showMessageDialog(null,
                        "您并未选中任何一行数据！", "错误", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 弹出对话框确认
            int select = JOptionPane.showConfirmDialog(this,
                    "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                    "确认", JOptionPane.YES_NO_OPTION);
            if (select != 0) return; // 0号按钮是'确定'按钮

            Connection conn = null;
            Statement stmt = null;
            try
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);
                stmt = conn.createStatement();

                int[] count = Villagerrtable.getSelectedRows(); // 获取你选中的行号（记录）
                for (int j : count)
                {
                    // 读取身份证号字段的值
                    String id = Villagerrtable.getValueAt(j, 3).toString();
                    System.out.println(id);

                    String sql = "delete from yc_villagers where id = \"" + id + "\";";
                    System.out.printf("%s 被执行\n", sql);
                    stmt.execute(sql);
                }

                stmt.close();
                conn.close();
            } catch (SQLException se)
            {
                // 处理 JDBC 错误
                se.printStackTrace();
            } catch (Exception eee)
            {
                // 处理 Class.forName 错误
                eee.printStackTrace();
            } finally
            {
                // 关闭资源
                try
                {
                    if (stmt != null) stmt.close();
                } catch (SQLException se2)
                {
                }// 什么都不做
                try
                {
                    if (conn != null) conn.close();
                } catch (SQLException se)
                {
                    se.printStackTrace();
                }
            }

            UpdateVillagerData();
        });

        menu.show(e.getComponent(), e.getX(), e.getY());
    }

    // 更新一条数据
    private boolean updateOneData()
    {
        return true;
    }

    private boolean updateMultipleData()
    {
        return true;
    }

    void setVillagerTableInfo()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
//            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
//            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM yc_villagers";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village = rs.getString("village");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String id = rs.getString("id");
                String addr = rs.getString("addr");
                String phone_number = rs.getString("phone_number");

                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village);
                rowData.add(name);
                rowData.add(sex);
                rowData.add(id);
                rowData.add(addr);
                rowData.add(phone_number);
                VillagertableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }
    }

    private void onDelete()
    {
        // 获取选中的行的索引
        int[] rows = Villagerrtable.getSelectedRows();
        if (rows.length == 0) return;

        // 弹出对话框确认

        int select = JOptionPane.showConfirmDialog(this,
                "删除操作将会将这条记录从数据库中永久删除且无法恢复，是否确认删除？",
                "确认", JOptionPane.YES_NO_OPTION);
        if (select != 0) return; // 0号按钮是'确定'按钮

        // 技巧：从后往前删除
        for (int i = rows.length - 1; i >= 0; i--)
        {
            VillagertableModel.removeRow(rows[i]);
        }
    }

    private void UpdateDistanceData()
    {
        // 先把表格中全部的数据删除，避免重复数据出现
        int rows_ct = Distancetable.getRowCount();
        // 从后往前删除，防止下标变化导致删除失败
        for (int i = rows_ct - 1; i >= 0; i--)
        {
            DistancetableModel.removeRow(i);
        }

        // 连接数据库更新距离信息
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;

            sql = "SELECT x, y, village FROM village_pos";
            System.out.printf("sql语句：%s被执行了\n", sql);
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String x = "" + rs.getInt("x");
                String y = "" + rs.getInt("y");
                // 把double转为String
                String village_name = rs.getString("village");

                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village_name);
                rowData.add(x);
                rowData.add(y);
                DistancetableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        // 更新完表格中信息后更新Combobox中的信息
        if (UpdateComboboxFlag)
            UpdateVillageCombobox();
        else
        {
            UpdateComboboxFlag = true;
        }
    }

    // 根据当前选中的条目来更新VillagerData
    void UpdateVillagerData()
    {
        // 先把表格中全部的数据删除，避免重复数据出现
        int rows_ct = Villagerrtable.getRowCount();
        // 从后往前删除，防止下标变化导致删除失败
        for (int i = rows_ct - 1; i >= 0; i--)
        {
            VillagertableModel.removeRow(i);
        }

        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;

            // 如果选中的是全部村庄
            if (VillageCombobox.getSelectedIndex() == 0)
                sql = "SELECT * FROM yc_villagers";
                // 如果选中的是某个特定的村庄
            else
            {
                sql = "SELECT * FROM yc_villagers where village=\"" + VillageCombobox.getSelectedItem() + "\"";
            }
            System.out.printf("sql语句：%s被执行了\n", sql);
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village = rs.getString("village");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String id = rs.getString("id");
                String addr = rs.getString("addr");
                String phone_number = rs.getString("phone_number");

                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village);
                rowData.add(name);
                rowData.add(sex);
                rowData.add(id);
                rowData.add(addr);
                rowData.add(phone_number);
                VillagertableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        // 更新完表格中信息后更新Combobox中的信息
        if (UpdateComboboxFlag)
            UpdateVillageCombobox();
        else
        {
            UpdateComboboxFlag = true;
        }
    }

    void addTableRow(Villager item)
    {
        Vector<Object> rowData = new Vector<>();
        rowData.add(item.village);
        rowData.add(item.name);
        rowData.add(item.sex);
        rowData.add(item.id);
        rowData.add(item.addr);
        rowData.add(item.phone_number);
        VillagertableModel.addRow(rowData);
    }


    JPanel panel1()
    {
        // 创建第一个面板
        JPanel p1 = new JPanel();

        // 设置为BorderLayout边框布局
        p1.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(Distancetable);
        Distancetable.setFillsViewportHeight(true); // 把高度剩下的部分用白色填满
        Distancetable.setRowSelectionAllowed(true); // 整行选择
        p1.add(scrollPane);

        DistancetableModel.addColumn("村庄名");
        DistancetableModel.addColumn("该村在地图上的x坐标");
        DistancetableModel.addColumn("该村在地图上的y坐标");

        setDistanceInfo();

        // 添加Distance表格的右键相应事件
        Distancetable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                    showDistanceContextMenu(e);
            }
        });

        UpdateDistanceData();

        return p1;
    }

    private void showDistanceContextMenu(MouseEvent e)
    {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem deleteMenuCmd = new JMenuItem("删除选中项");
        menu.add(deleteMenuCmd);

        // TODO:这里要添上一个新的Dialog
        deleteMenuCmd.addActionListener(ee ->
        {
            // 添加数据
            EditVillagerDialog villagerDialog = new EditVillagerDialog(this);

            // 如果用户点击了取消或者关闭了窗口，则直接返回
            if (!villagerDialog.exec())
                return;
            else
            {
                Villager v = villagerDialog.getValue();

                // 录入数据
                if (addOneVillagerData(v))
                {
                    UpdateVillagerData();
                    JOptionPane.showMessageDialog(null,
                            v.name + "的信息录入成功！",
                            "错误", JOptionPane.INFORMATION_MESSAGE);
                    UpdateVillagerData();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                            "录入数据出错！请检查数据有效性！", "错误", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    // TODO：设置距离信息
    private boolean setDistanceInfo()
    {
        Connection conn = null;
        Statement stmt = null;
        try
        {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            conn = DriverManager.getConnection(DB_URL, SQL_USER, SQL_PASS);

            // 执行查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT x, y, village FROM village_pos";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next())
            {
                // 通过字段检索
                String village1 = "" + rs.getInt("x");
                String village2 = "" + rs.getInt("y");

                // 把double转为String
                String distance = rs.getString("village");


                // 添加到table中去
                Vector<Object> rowData = new Vector<>();
                rowData.add(village1);
                rowData.add(village2);
                rowData.add(distance);
                DistancetableModel.addRow(rowData);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se)
        {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e)
        {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally
        {
            // 关闭资源
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException se2)
            {
            }// 什么都不做
            try
            {
                if (conn != null) conn.close();
            } catch (SQLException se)
            {
                se.printStackTrace();
            }
        }

        return true;
    }


    JPanel panel3()
    {
        // Content Pane
        JPanel panel3 = new JPanel();
//        this.setContentPane(root); // 设置为默认背景
        panel3.setLayout(new BorderLayout());
//        panel3.setLayout(new FlowLayout());

        UpdateVillageCombobox();
        panel3.add(VillageCombobox, BorderLayout.NORTH);

        // 添加到主界面
        JScrollPane scrollPane = new JScrollPane(Villagerrtable);
        Villagerrtable.setFillsViewportHeight(true); // 把高度剩下的部分用白色填满
        Villagerrtable.setRowSelectionAllowed(true); // 整行选择
        panel3.add(scrollPane, BorderLayout.CENTER);
//        panel3.add(scrollPane, BorderLayout.SOUTH);

        // 初始化设置：添加5列
        VillagertableModel.addColumn("村庄");
        VillagertableModel.addColumn("姓名");
        VillagertableModel.addColumn("性别");
        VillagertableModel.addColumn("身份证号");
        VillagertableModel.addColumn("地址");
        VillagertableModel.addColumn("电话号码");

        setVillagerTableInfo();

        // 添加表格的右键响应事件
        Villagerrtable.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                    showVillagerContextMenu(e);
            }
        });

        UpdateVillagerData();

        return panel3;
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
        else if (cardnum == 3)
        {
            cardLayout.show(cards, "search");
        }
    }
    JPanel panel4()
    {
        JPanel t = new JPanel();
        return t;
    }
}