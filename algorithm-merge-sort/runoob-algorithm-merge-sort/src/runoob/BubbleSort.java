import java.util.Arrays;
import java.util.Scanner;

public class BubbleSort {
    static void Bubble_Sort(int arr[]) 
    {
        int arrbound = arr.length -1;//定义它是无序数组的边界(右边)，每次比较比到这里为止
        int lastIndex = 0;

        for(int i = 0; i<arr.length; i++)
        {
            boolean flag = true;//设置flag，目的是用于减少比较次数
            for(int j = 0; j< arrbound; j++)
            {
                int temp = 0;
                if(arr[j] >= arr[j+1])//判断并进行元素交换
                {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    flag = false;//如果该次搜索发生了交换，就证明数组可能还未排好顺序
                    lastIndex =j;
                }
                
            }
            arrbound = lastIndex;//把最后一次交换元素的位置的值赋给无序数组的边界
            if(flag)//如果该次下层搜索没有发生交换，那么就说明已经排好序，直接跳出外层循环
            {
                break;
            }
        }
    }
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);//创建一个Scanner对象
        System.out.println("please input length of array:");

        int num = scan.nextInt();
        int[] arr = new int[num];//创建一个数组

        System.out.println("please input the numbers of array:");
        for (int i = 0; i < num; i++)//输入数组的内容
        {
            arr[i] = scan.nextInt();
        }
        
        scan.close();
        System.out.println("\n~~array before Bubble sort:");
        System.out.println(Arrays.toString(arr));//输出排序前的数组

        System.out.println("\n~~array after Bubble sort:");
        Bubble_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
