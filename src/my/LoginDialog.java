package my;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog
{
    JTextField usr = null;
    JTextField pwd = null;

    public LoginDialog(JFrame owner)
    {
        super(owner, "增加村庄位置信息", true);
        this.setSize(300, 100);


        add(new JLabel("登录账号"));
        usr = new JTextField(7);
        add(usr);

        add(new JLabel("密码"));
        pwd = new JTextField(7);
        add(pwd);

        JButton okButton = new JButton("确定");
        add(okButton);

        // 设置为流式布局
        this.setLayout(new FlowLayout());

//        this.setLocation(500, 500);
        // 设置居中显示
        this.setLocationRelativeTo(null);



        setVisible(true);
    }
}
