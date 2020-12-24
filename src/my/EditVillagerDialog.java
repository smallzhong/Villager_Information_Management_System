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


    // 通过正则表达式校验身份证号是否合法
    public static boolean isIDNumber(String IDNumber)
    {
        if (IDNumber == null || "".equals(IDNumber))
        {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾


        boolean matches = IDNumber.matches(regularExpression);

        //判断第18位校验值
        if (matches)
        {

            if (IDNumber.length() == 18)
            {
                try
                {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++)
                    {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase()))
                    {
                        return true;
                    }
                    else
                    {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() +
                                "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e)
                {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }


    public EditVillagerDialog(JFrame owner)
    {
        super(owner, "增加村民信息", true);
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

        if (!isIDNumber(v.id))
        {
            JOptionPane.showMessageDialog(this, "身份证号的格式不合法，请重新输入！");
            return false;
        }

        // 判断身份证号中有无非法字符（应全为数字或者X或者x）
//        for (int i = 0; i < v.id.length(); i++)
//        {
//            // 如果发现不是字符或者不是X和x
//            if (Character.isDigit(v.id.charAt(i)) == false)
//            {
//                if ((i == v.id.length() - 1) && (v.id.charAt(i) == 'x' || v.id.charAt(i) == 'X'))
//                {
//                    continue;
//                }
//                JOptionPane.showMessageDialog(this, "身份证号的格式不合法，请重新输入！");
//                return false;
//            }
//        }


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

    public boolean setValue(Villager v)
    {
        villageField.setText(v.village);
        nameField.setText(v.name);

        if (v.sex.equals("男"))
        {
            sexField.setSelectedIndex(1);
        }
        else if (v.sex.equals("女"))
        {
            sexField.setSelectedIndex(0);
        }
        else
        {
            System.out.println("v.sex错误！");
        }

        idField.setText(v.id);
        addrField.setText(v.addr);
        phone_numberFiled.setText(v.phone_number);

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
