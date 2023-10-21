package cell_making_machine;
import javax.swing.JFrame;
public class CellMachine {
    public static void main(String[] args)
    {
        Field field = new Field(30,30);
        for (int i = 0; i < field.getWidth(); i++)
        {
            for(int j = 0; j < field.getHeight(); j++)
            {
                field.place(i , j , new Cell());//将细胞放在空间中的操作
            }
        }

        for (int i = 0; i < field.getWidth(); i++)
        {
            for(int j = 0; j < field.getHeight(); j++)
            {
                //
                Cell cell = new Cell();
                if(Math.random() < 0.5)//调用随机数作为判断条件
                {
                    cell.reborn();//生成细胞
                }
            }
        }

        View view = new View(field);
        JFrame jframe = new JFrame();
        //设置单击右上角关闭之后，系统会做出怎么样的处理
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit_on_close结束窗口所在的应用程序
        //设置窗口是否可调整大小
        jframe.setResizable(false);
        //设置标题
        jframe.setTitle("Cells");
        //向容器中增加组件
        jframe.add(view);
        //
        jframe.pack();;

        jframe.setVisible(true);

        for(int i = 0 ;i < 1000; i++)
        {
            for(int row = 0 ;row <field.getHeight();row++)
            {
                for(int col = 0 ;col < field.getWidth();col++)
                {
                    Cell cell = (Cell)field.get(row,col);//现在cell是一个Cell类型的二维数组对象
                    Cell[] neibours = field.getneibours(row,col);
                    int numofalive = 0 ;
                    for(Cell c:neibours)
                    {
                        if(c.isCellAlive())//统计邻居细胞存活数量
                        {
                            numofalive++;
                        }
                    }
                    
                    System.out.print("[" + row + "][" + col + "]:");
                    System.out.print(cell.isCellAlive() ? "true" : "false");
                    System.out.print(":" + numofalive + "-->");
                }
            }
        }




    }
}
