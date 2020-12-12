package my;


import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class EditVillagerDialog extends JDialog
{
    public JTextField idField = new JTextField(20);
    public JTextField nameField = new JTextField(20);
    public JComboBox<String> sexField = new JComboBox<>();
    public JTextField phoneField = new JTextField(20);
    public JTextField birthField = new JTextField(20);

    JButton okButton = new JButton("确定");

    // 默认是“取消"
    private boolean retValue = false;

    public EditVillagerDialog(JFrame owner)
    {
        super(owner, "编辑学生信息", true);
        this.setSize(300, 300);

        // 设置一个容器
        AfPanel root = new AfPanel();
        this.setContentPane(root);
        root.setLayout(new AfColumnLayout(10));
        root.padding(10);

        // 中间面板
        AfPanel main = new AfPanel();
        root.add(main, "1w"); // 占居中间区域
        main.setLayout(new AfColumnLayout(10));
        main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        main.padding(10);

        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("村庄"), "50px");
            row.add(idField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("姓名"), "50px");
            row.add(nameField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("性别"), "50px");
            row.add(sexField, "1w");

            sexField.addItem("女");
            sexField.addItem("男");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("手机号"), "50px");
            row.add(phoneField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("身份证号"), "50px");
            row.add(birthField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("身份证号"), "50px");
            row.add(birthField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("居住地址"), "50px");
            row.add(birthField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("电话号码"), "50px");
            row.add(birthField, "1w");
        }

        // 底下
        AfPanel bottom = new AfPanel();
        root.add(bottom, "30px"); // 底部区域 30px
        bottom.setLayout(new AfRowLayout(10));
        bottom.add(new JLabel(), "1w"); // 占位
        bottom.add(okButton, "auto"); // 按钮靠右显示


        // 此处 使用 Lambda 表达式 的写法，参考3.5 节
        okButton.addActionListener((e) ->
        {
            if (checkValid())
            {
                retValue = true; // 设置对话框 的返回值
                setVisible(false); // MyDialog.this.setVisible(false)
            }
        });

    }

    // 返回值为 true : 表示用户点了"确定"
    // 返回值为 false : 表示用户叉掉了窗口，或者点了”取消"
    public boolean exec()
    {
        // 相对owner居中显示
        Rectangle frmRect = this.getOwner().getBounds();
        int width = this.getWidth();
        int height = this.getHeight();
        int x = frmRect.x + (frmRect.width - width) / 2;
        int y = frmRect.y + (frmRect.height - height) / 2;
        this.setBounds(x, y, width, height);

        // 显示窗口 ( 阻塞 ，直接对话框窗口被关闭)
        this.setVisible(true);

        return retValue;
    }

    // 检查输入有效性
    public boolean checkValid()
    {
        Student v = getValue();
        if (v.id.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "学号不得为空!");
            return false;
        }
        if (v.name.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "姓名不得为空!");
            return false;
        }
        return true;
    }

    // 设置初始值
    public void setValue(Student v)
    {
        idField.setEditable(false); // 学号不允许再编辑
        idField.setText(v.id);
        nameField.setText(v.name);
        sexField.setSelectedIndex(v.sex ? 1 : 0); // 条件表达式
        phoneField.setText(v.cellphone);
        birthField.setText(v.birthday);
    }

    // 获取用户的输入
    public Student getValue()
    {
        Student v = new Student();
        v.id = idField.getText().trim();
        v.name = nameField.getText().trim();
        v.sex = sexField.getSelectedIndex() == 1;
        v.cellphone = phoneField.getText().trim();
        v.birthday = birthField.getText().trim();

        return v;
    }
}
