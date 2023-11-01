import java.util.Arrays;
import java.util.Scanner;

public class BinarySearch {
    public int Bs_search(int num ,int arr[],int left , int right)
    {
        //开始二分搜索
        if (left > right)//稳妥起见，加一个判断条件防止传错
        {
            return -1;
        }

        int mid = (left + right)/2;//设置一个中间标号
        if(num < arr[mid])//
        {
            return Bs_search(num, arr, left, mid-1);//返回mid左边片区
        }
        else if (num > arr[mid])
        {
            return Bs_search(num, arr, mid+1, right);//返回mid右边片区
        }
        else
        {
            return num;
        }
    }
    public static void main(String[] args)
    {
        int[] arr = new int[]{1,2,3,5,20,21,44,55,89,95,99,100};//创建一个数组
        Scanner scan = new Scanner(System.in);//创建一个Scanner对象

        System.out.println("\n~~The array of Binary Search:");
        System.out.println(Arrays.toString(arr));//输出数组
        System.out.println("please input the number you want to search:");

        int num = scan.nextInt();
        
        
        scan.close();
        

        System.out.println("\n~~Result of Binary Search:");

        BinarySearch bi = new BinarySearch();//创建一个BinarySearch类型的对象
        int final_number = bi.Bs_search(num, arr,0,arr.length-1);

        if (final_number == -1)
        {
            System.out.println("The number is not exist!");
        }
        else
        {
            System.out.println("The number " + final_number + " is exist~");
        }
        
    }
}
