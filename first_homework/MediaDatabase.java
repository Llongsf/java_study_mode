package first_homework;
interface Compare
{
    int longer_duration(int duration1,int duration2);
}
//总体上是MediaDatabase是作为一个最高层次的类，利用Items类作为父类，分管CD和DVD两个类
// CD & DVD 的相同之处在于他们都有标题和时长，不同之处在于他们的制作者分别是艺术家/乐队 和 导演
// 父类 Database
public class MediaDatabase implements Compare
{
    //MediaDatabase类实现Compare接口
    @Override
    public int longer_duration(int duration1, int duration2)
    {
        if(duration1 > duration2)
        {
            System.out.println("CD's duration is longer");
        }
        else if (duration1 < duration2)
        {
            System.out.println("DVD's duration is longer");
        }
        else
        {
            System.out.println("CD's duration equal DVD's duration");
        }
        return 0;
    }
    private String title;
    private int duration;//duration是静态变量

    //一个无参构造方法
    public MediaDatabase()
    {

    }
    //构造方法
    public MediaDatabase(String title, int duration) 
    {
        this.title = title;
        this.duration = duration;
    }
    // 获取音像制品标题
    public String getTitle() {
        return title;
    }
    // 获取音像制品时长
    public int getDuration() {
        return duration;
    }

    //展示音像制品的详细信息
    public void showDetails() 
    {
        System.out.println("Title: " + title);
        System.out.println("Duration: " + duration + " minutes");
    }

    //一个判断影像时长和大众最喜欢时长的差距的方法
    public void upup(int duration1) 
    {
        //大众最喜欢的影像时长为30min
        int loved_time = duration1 - 30;
        System.out.println("The gap between being liked by the general public: " + loved_time + "minutes");
    }

    public static void main(String[] args) 
    {
        //使用父类类型的引用变量指向子类对象，创建 CD 对象和 DVD 对象
        MediaDatabase cd = new CD("青花瓷", 60, "周杰伦");
        MediaDatabase dvd = new DVD("《湄公河》", 120, "姜文");
        // 多态性：通过父类引用变量调用相同的方法，实际上会根据对象的实际类型调用相应的子类方法

        // 调用 show() 方法展示详细信息
        System.out.println("CD Details:");
        cd.showDetails();
        
        //向上转型，使得子类对象能够调用父类方法
        cd.upup(60);
        System.out.println();

        System.out.println("DVD Details:");
        dvd.showDetails();
        //向上转型，能够调用父类方法
        dvd.upup(120);

        MediaDatabase mdb = new MediaDatabase();//创建的mdb对象调用的是无参的构造方法
        //使用mdb对象调用longer_duration方法，比较cd和dvd两个时长的长度
        System.out.println();
        mdb.longer_duration(cd.getDuration(),dvd.getDuration());        

    }
}