import java.io.*;
public class Ser implements Serializable{
    private String name;
    private int id;
    public Ser(String name, int id)
    {   
        System.out.println("aminos!");
        this.name = name;
        this.id = id;
    }
    @Override
    public String toString()
    {
        return "name: " + name + ", id: " + id; 
    }

}
class Writingio
{
    public static void main(String[] args)
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("aminos.txt"));
            Ser ser = new Ser("aminos",133);
            oos.writeObject(ser);
            oos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
          
        
    }
}
class Readio {
  public static void main(String[] args) {
    //创建一个ObjectInputStream输入流
      try 
        {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("aminos.txt"));
            Ser ser_out = (Ser) ois.readObject();//主要的更改是将 ois.readObject() 的返回值赋值给 ser_out 变量，并将其强制转换为 Serout 类型。
            //这样可以确保从文件中读取的对象正确地反序列化为 Serout 对象
            System.out.println(ser_out);
            ois.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
  }
}
