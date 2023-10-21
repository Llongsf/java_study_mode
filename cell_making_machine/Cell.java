package cell_making_machine;
public class Cell
{
    boolean cell_alive = false;
    public void reborn()
    {
        cell_alive = true;
    }

    public boolean isCellAlive()
    {
        return cell_alive;
    }
    public void Celldie()
    {
        cell_alive = false;
    }

    public void draw()
    {
        
    }
}