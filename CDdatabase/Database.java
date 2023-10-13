package CDdatabase;

import java.util.ArrayList;

public class Database {
    ArrayList<CD> cd = new ArrayList<CD>();
    //ArrayList每个元素都是一个管理者(指针)，指向一个CD类的元素(这里CD有点像一个结构体)
    public void add1(CD c) 
    {
        cd.add(c);
    }

    public void list()
    {
        for (CD c : cd)
        {
            c.print();
        }
    }

    public static void main(String[] args)
    {
        Database db = new Database();
        db.add1(new CD("Qinghuaci","Jaychou",50,"..."));
        //db为Database类对象，调用add1方法(为了避免混淆，加了一个1)，传入的参数是一个带参数的新建的CD方法类型
        //传入后，再用ArrayList方法的add方法为cd新添加一个元素(c是形参)
        db.list();
    }
}
