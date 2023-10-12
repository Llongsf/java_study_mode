import java.util.concurrent.TimeUnit;

public class Display
{
    private int value = 0;
    private int limit = 0;
    public Display(int limit)
    {
        this.limit = limit;
    }
    public void increase()
    {
        value++;
        if (value == limit)
        {
            value = 0;
        }
    }

    public int getValue()
    {
        return value;
    }
    public static void main(String[] args)
    {
        Display ii = new Display(24);
        for( ;; )
        {
            ii.increase();
            System.out.println(ii.getValue());
        }
    }
}
class Clock
{
    private Display hour = new Display(24);
    private Display minute = new Display(60);
    private Display second = new Display(60);
    public void start()
    {
        while(true)
        {   
            try 
            {
                TimeUnit.SECONDS.sleep(1);
                second.increase();
                if (second.getValue() == 0)
                {
                    minute.increase(); 
                    if (minute.getValue() == 0)
                    {
                        hour.increase();
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("异常");
            }
            
           
            
            System.out.printf("%02d:%02d:%02d\n",hour.getValue(),minute.getValue(),second.getValue());
        }
        
    }
    public static void main(String[] args)
    {
        Clock clock = new Clock();
        clock.start();
    }
} 