package cell_making_machine;

import java.awt.Graphics;

public class Cell
{
    boolean cell_alive = false;
    public void reborn()
    {
        cell_alive = true;
    }

    public boolean isCellAlive()
    {
        return cell_alive;
    }
    public void Celldie()
    {
        cell_alive = false;
    }

    public void draw(Graphics g, int row, int col,int size)
    {   
        //Graphics类drawRect()方法
        g.drawRect(row,col,size,size);//不管活的还是死的，都要画一个矩形边框，表示细胞
        if (isCellAlive())
        {
            g.fillRect(row, col, size, size);//活细胞就填充
        }
    }
}