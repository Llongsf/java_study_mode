package CDdatabase;

public class CD extends Item
{
    private String song;
	private String singer;
	private int time;
	private String comment;
    public CD(String song, String singer, int time, String comment) {
		// super();
		this.song = song;
		this.singer = singer;
		this.time = time;
		this.comment = comment;
	}
    public void print()
    {
        System.out.println(song + ":" + singer + ":" + time + ":" + comment);
    }
}
