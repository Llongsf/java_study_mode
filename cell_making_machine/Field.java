package cell_making_machine;

import java.util.ArrayList;

public class Field {
    //创建一个Field类，来存放数据等等
    //结合Cell中对本类的引用，Field的作用有点类似于一个结构体

    private int width;
    private int height;
    private Cell[][] fields;

    public Field(int width, int height)
    {
        this.width = width;
        this.height = height;
        fields = new Cell[width][height];
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }
    public Cell get(int row,int col)//创建一个get函数，用于返回一个细胞，即Cell类型的field二维数组对象
    {
        return fields[row][col];
    }
    public Cell[] getneibours(int row,int col)
    {
        ArrayList<Cell> list = new ArrayList<Cell>();
        for(int i = -1; i < 2;i++)
        {
            for(int j = -1; j < 2; j++)
            {
                int r = row + i;
                int c = col + j;
                //一个九宫格，以中间的格子为基准的话(称做主格)，-1行就是它上面的那一行
                //-1的作用是防止 当主格在第0行或第0列时越界，r<height等的操作 也是防止主格在最右边或最左边时候 数周边细胞越界
                if(r > -1 && r < height && c > -1 && c < width &&  !(r == width && c == height))
                {
                    list.add(fields[row][col]);//list增添一个field块
                }
            }
            
        }
        //to Array是将Arraylist 对象转换为数组
        return list.toArray(new Cell[list.size()]);//创建一个Cell类型的数组，长度和list一样
        //因为那边是需要Cell[]类型的数组
    }
    public void place(int left ,int right, Cell ce)
    {   
        //Cell re = fields[left][right];
        fields[left][right] = ce;
    }
}
