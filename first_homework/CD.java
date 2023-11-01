package first_homework;

// 子类 CD
class CD extends MediaDatabase
{
    private String artist;

    // 构造方法
    public CD(String title, int duration, String artist) 
    {
        super(title, duration);
        this.artist = artist;
    }

    // 获取音乐CD的艺术家/乐队名称
    public String getArtist() {
        return artist;
    }

    // 重写父类的showDetails()方法，展示音乐CD的详细信息
    @Override
    public void showDetails() {
        super.showDetails();
        System.out.println("Artist: " + artist);
    }
}