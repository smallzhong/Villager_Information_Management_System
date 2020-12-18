package my;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginDialog extends JDialog implements KeyListener
{
    JTextField usr = null;
    JPasswordField  pwd = null;
    JButton okButton = null;

    // 设置登录的账号和密码
    String USERNAME = "钟雨初";
    String PASSWORD = "123456";

    public LoginDialog(JFrame owner)
    {
        super(owner, "登录 钟雨初 8003119075", true);
        this.setSize(300, 100);

        // 增加按键捕捉
        addKeyListener(this);


        add(new JLabel("登录账号"));
        usr = new JTextField(7);
        add(usr);

        usr.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {

            }

            @Override
            public void keyReleased(KeyEvent e)
            {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER))
                {
                    // 如果按了enter则把焦点转移到填写密码的窗口上面去
                    pwd.requestFocus();
                }
            }
        });

        add(new JLabel("密码"));
        pwd = new JPasswordField(7);
        add(pwd);

        // 如果在输入密码的时候按了enter则视为按了确定键
        pwd.addKeyListener(new KeyListener()
        {
            @Override
            public void keyTyped(KeyEvent e)
            {

            }

            @Override
            public void keyPressed(KeyEvent e)
            {
                if ((e.getKeyCode() == KeyEvent.VK_ENTER))
                {
                    setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e)
            {

            }
        });

        JButton okButton = new JButton("确定");
        add(okButton);

        // 设置为流式布局
        this.setLayout(new FlowLayout());

//        this.setLocation(500, 500);
        // 设置居中显示
        this.setLocationRelativeTo(null);

        okButton.addActionListener(e ->
        {
            setVisible(false);
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setVisible(true);
    }


    // 用来检查输入的账号密码是否正确
    public boolean checkValid()
    {
        // TODO:这里可以加上数据库，把账号密码放到数据库里面。
        if (!usr.getText().equals(USERNAME))
        {
//            JOptionPane.showMessageDialog(null,
//                    "账号输入错误！", "错误", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if (!pwd.getText().equals(PASSWORD)) return false;
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.printf("%d\n", e.getKeyCode());
        if ((e.getKeyCode() == KeyEvent.VK_ENTER))
        {
            // 如果焦点在JFrame里面而且点击了enter，也结束。（好像这个并不会被触发）
            setVisible(false);
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
