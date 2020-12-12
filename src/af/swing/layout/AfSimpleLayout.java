package af.swing.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Rectangle;

/* Swing入门篇 7.3节  */ 

public abstract class AfSimpleLayout implements LayoutManager
{

	@Override
	public void addLayoutComponent(String name, Component comp)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLayoutComponent(Component comp)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension preferredLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent)
	{
		// TODO Auto-generated method stub
		return null;
	}

	//@Override
	//public void layoutContainer(Container parent);
	
	// 扣除边框宽度，得到内部的矩形
	// rect: 容器大小
	// insets: 容器的边框宽度
	protected Rectangle excludeInsets (Rectangle rect, Insets insets)
	{
		Rectangle r = new Rectangle(rect);
		r.x += insets.left;
		r.y += insets.top;
		r.width -= ( insets.left + insets.right );
		r.height -= ( insets.top + insets.bottom );
		return r;
	}
}
