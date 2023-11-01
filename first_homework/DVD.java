package first_homework;


// 子类 DVD 继承  父类
class DVD extends MediaDatabase
{
    private String director;

    public DVD(String title, int duration, String director) 
    {
        super(title, duration);
        this.director = director;
    }
    //获取DVD的制作导演
    public String getDirector() {
        return director;
    }

    //重写父类方法
    public void showDetails() 
    {
        super.showDetails();
        System.out.println("Artist: " + getDirector());
        
    }
}
