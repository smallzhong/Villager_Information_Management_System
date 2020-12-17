package my;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog
{
    JTextField usr = null;
    JPasswordField  pwd = null;
    JButton okButton = null;

    // 设置登录的账号和密码
    String USERNAME = "钟雨初";
    String PASSWORD = "123456";

    public LoginDialog(JFrame owner)
    {
        super(owner, "增加村庄位置信息", true);
        this.setSize(300, 100);


        add(new JLabel("登录账号"));
        usr = new JTextField(7);
        add(usr);

        add(new JLabel("密码"));
        pwd = new JPasswordField(7);
        add(pwd);

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
}
