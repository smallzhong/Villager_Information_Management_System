package my;


import af.swing.AfPanel;
import af.swing.layout.AfColumnLayout;
import af.swing.layout.AfRowLayout;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class EditVillagerDialog extends JDialog
{
    public JTextField villageField = new JTextField(15);
    public JTextField nameField = new JTextField(15);
    public JComboBox<String> sexField = new JComboBox<>();
    public JTextField idField = new JTextField(15);
    public JTextField addrField = new JTextField(15);
    public JTextField phone_numberFiled = new JTextField(15);

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
        root.setLayout(new AfColumnLayout(7));
        root.padding(10);

        // 中间面板
        AfPanel main = new AfPanel();
        root.add(main, "1w"); // 占居中间区域
        main.setLayout(new AfColumnLayout(7));
        main.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        main.padding(10);

        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("村庄"), "50px");
            row.add(villageField, "1w");
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
            row.add(new JLabel("身份证号"), "50px");
            row.add(idField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("居住地址"), "50px");
            row.add(addrField, "1w");
        }
        if (true)
        {
            AfPanel row = new AfPanel();
            main.add(row, "24px");
            row.setLayout(new AfRowLayout(10));
            row.add(new JLabel("电话号码"), "50px");
            row.add(phone_numberFiled, "1w");
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
        Villager v = getValue();
        if (v.village.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "村庄不能为空！");
            return false;
        }
        if (v.name.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "姓名不能为空！");
            return false;
        }
        if (v.sex.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "性别不能为空！");
            return false;
        }

        // 判断身份证号中有无非法字符（应全为数字或者X或者x）
        for (int i = 0; i < v.id.length(); i++)
        {
            // 如果发现不是字符或者不是X和x
            if (Character.isDigit(v.id.charAt(i)) == false)
            {
                if ((i == v.id.length() - 1) && (v.id.charAt(i) == 'x' || v.id.charAt(i) == 'X'))
                {
                    continue;
                }
                JOptionPane.showMessageDialog(this, "身份证号的格式不合法，请重新输入！");
                return false;
            }
        }


        if (v.id.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "身份证号为空！");
            return false;
        }
        if (v.addr.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "居住地址不能为空！");
            return false;
        }
        if (v.phone_number.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "电话号码不能为空！");
            return false;
        }

        return true;
    }

    // 设置初始值
//    public void setValue(Student v)
//    {
//        villageField.setEditable(false); // 学号不允许再编辑
//        villageField.setText(v.id);
//        nameField.setText(v.name);
//        sexField.setSelectedIndex(v.sex ? 1 : 0); // 条件表达式
//        idField.setText(v.cellphone);
//        addrField.setText(v.birthday);
//    }

    // 获取用户的输入
    public Villager getValue()
    {
        Villager v = new Villager();
        // 除掉首尾的无效字符
        v.village = villageField.getText().trim();
        v.name = nameField.getText().trim();
        v.sex = (sexField.getSelectedIndex() == 1) ? "男" : "女";
        v.id = idField.getText().trim();
        v.addr = addrField.getText().trim();
        v.phone_number = phone_numberFiled.getText().trim();

        return v;
    }
}
