package my;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class addVillageposDialog extends JDialog
{
    private boolean retValue = false;

    JTextField src = null;
    JTextField dest = null;
    JTextField distance = null;

    public addVillageposDialog(JFrame owner)
    {
        super(owner, "增加村庄位置信息", true);
        this.setSize(600, 100);

        add(new JLabel("该村在地图上的x坐标"));
        src = new JTextField(7);
        add(src);

        add(new JLabel("该村在地图上的y坐标"));
        dest = new JTextField(7);
        add(dest);

        add(new JLabel("村庄名"));
        distance = new JTextField(7);
        add(distance);

        JButton okButton = new JButton("确定");
        add(okButton);

        okButton.addActionListener((e) ->
        {
            if (checkValid())
            {
                retValue = true; // 设置对话框 的返回值
                setVisible(false); // MyDialog.this.setVisible(false)
            }
        });

        // 设置为流式布局
        this.setLayout(new FlowLayout());

        setVisible(true);
    }

    // 检查输入有效性
    public boolean checkValid()
    {
        Vector<String> v = this.getValue();
        if (v.get(0).isEmpty())
        {
            JOptionPane.showMessageDialog(this, "村庄在地图上的x坐标不能为空！请重新输入！");
            return false;
        }
        if (v.get(1).isEmpty())
        {
            JOptionPane.showMessageDialog(this, "村庄在地图上的y不能为空！请重新输入！");
            return false;
        }
        if (v.get(2).isEmpty())
        {
            JOptionPane.showMessageDialog(this, "村庄名不能为空");
            return false;
        }

        return true;
    }

    public boolean getRetValue()
    {
        return this.retValue;
    }

    public Vector<String> getValue()
    {
        Vector<String> v = new Vector<String>();
        v.add(src.getText().trim());
        v.add(dest.getText().trim());
        v.add(distance.getText().trim());

        return v;
    }
}
