@FunctionalInterface
interface Printable
{
    void printe(StringUtil su,String str);
}
class StringUtil
{
    public void printUpper(String str)
    {
        System.out.println(str.toUpperCase());
    }
}

public class Lambda_l
{   

    private static void printU(StringUtil su, String str , Printable pt)
    {
        pt.printe(su,str);
    }
    public static void main(String[] args)
    {
        printU(new StringUtil() , "fuckyou" , StringUtil::printUpper);
    }
}

