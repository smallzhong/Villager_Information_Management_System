package af.swing.border;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

/* Swing入门篇 6.3 节 */

public class AfBorder
{
	// 设置填充
	public static void addPadding(JComponent c, int size)
	{
		addPadding(c, size, size, size, size);
	}
	
	// 设置填充
	public static void addPadding(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			// padding 在 border内层
			border = BorderFactory.createCompoundBorder(oldBorder, border);
		}
		c.setBorder(border);
	}
	
	// 设置间距
	public static void addMargin(JComponent c, int size)
	{
		addMargin(c, size, size, size, size);
	}
	
	// 设置间距
	public static void addMargin(JComponent c, int top, int left, int bottom, int right)
	{
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		Border oldBorder = c.getBorder();
		if(oldBorder != null)
		{
			// margin在外层
			border = BorderFactory.createCompoundBorder(border, oldBorder);
		}
		c.setBorder(border);
	}
	
	// 把标准控件包在一个Box里, 然后给Box设置一个边距
	public static JComponent wrap(JComponent c, int gap)
	{
		return wrap(c, gap,gap, gap, gap);
	}
	
	public static JComponent wrap(JComponent c, int top, int left, int bottom, int right)
	{
		Box box = Box.createHorizontalBox();
		box.setOpaque(true);
		box.setBackground(Color.YELLOW);
		box.add(c);
		Border border = BorderFactory.createEmptyBorder(top, left, bottom, right);
		box.setBorder(border);
		return box;
	}
}
