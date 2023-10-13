package CDdatabase;

public class DVD extends Item
{
    private String movie;
	private String director;
	private int time;
	private String comment;
    public DVD(String movie, String director, int time, String comment) {
		// super();
		this.movie = movie;
		this.director = director;
		this.time = time;
		this.comment = comment;
	}
    public void print()
    {
        System.out.println(movie + ":" + director + ":" + time + ":" + comment);
    }
}
