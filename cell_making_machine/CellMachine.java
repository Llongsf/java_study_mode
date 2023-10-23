package cell_making_machine;
import javax.swing.JFrame;

public class CellMachine {
    public static void main(String[] args)
    {
        Field field = new Field(30,30);
        for (int i = 0; i < field.getHeight(); i++)
        {
            for(int j = 0; j < field.getWidth(); j++)
            {
                field.place(i , j , new Cell());//将细胞放在空间中的操作
            }
        }

        for (int i = 0; i < field.getHeight(); i++)
        {
            for(int j = 0; j < field.getWidth(); j++)
            {
                //
                Cell cell = field.get(i,j);
                if(Math.random() < 0.5)//调用随机数作为判断条件
                {
                    cell.reborn();//生成细胞
                }
            }
        }

        //可视化的组件view，基于field
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
        jframe.pack();

        jframe.setVisible(true);

        for(int i = 0 ;i < 100; i++)
        {   
            System.out.println("Time : " + i);
            for(int row = 0 ;row <field.getHeight();row++)
            {
                
                for(int col = 0 ;col < field.getWidth();col++)
                {
                    System.out.print(" ");
                    Cell cell = (Cell)field.get(row,col);//现在cell是一个Cell类型的二维数组对象
                    Cell[] neibours = field.getneibours(row,col);//获取邻居信息

                    int numofalive = 0 ;
                    for(Cell c:neibours)
                    {
                        if(c.isCellAlive())//统计邻居细胞存活数量
                        {
                            numofalive++ ;
                        }
                    }
                    
                    System.out.print("[" + row + "][" + col + "]:");
                    System.out.print(cell.isCellAlive() ? "live" : "die");
                    System.out.print(":" + numofalive + "-->");

                    if(cell.isCellAlive())
                    {
                        if(numofalive < 2 || numofalive > 3)
                        {
                            cell.Celldie();
                            System.out.print("die");
                        }
                    }
                    else if (numofalive == 3 )
                    {
                        cell.reborn();
                        System.out.print("reborn");
                    }
                }
                System.out.println();
            }
            //全部格子都遍历了一次之后
            System.out.println("\nUPDATE");
            //
            jframe.repaint();
            try
            {
                Thread.sleep(200);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("end~~");
        System.exit(0);
    }
}
