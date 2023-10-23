package cell_making_machine;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
public class View extends JPanel{
    //将Field中的东西调用出来，并显示在UI界面上
    private static final int Grid_Size = 16;
    private static final long serialVersionUID = -5258995676212660595L;
    private Field the_field;
    public View(Field field)
    {
        the_field = field;
    }

    //paint是一个封装在java.awt包中的一个方法，这里相当于是重写
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);//访问父类的paint方法
        for(int row = 0; row < the_field.getHeight(); row++)
        {
            for(int col = 0;col < the_field.getWidth();col++)
            {
                Cell ce = the_field.get(row,col);//拿取坐标的所在的细胞信息
                if(ce!=null)//如果有细胞就画出来
                {
                    ce.draw(g, col*Grid_Size, row*Grid_Size, Grid_Size);
                }
            }
        }
    }

    @Override
    //此方法是在容易布局器内设置尺寸的
    public Dimension getPreferredSize()
    {
        //返回一个Dimension类型的尺寸设置
        // Dimension类封装单个对象中组件的宽度和高度（以整数精度）。
        // 这里设置的是panel的大小
        return new Dimension(the_field.getWidth()*Grid_Size+1,the_field.getHeight()*Grid_Size+1);
    }
}
