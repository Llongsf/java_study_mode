import java.util.Arrays;
import java.util.Scanner;

public class Quick_Sort  
{
    
    public static void Sort(int arr[],int low,int high)
    {   
        
        if (low > high)//加上判断条件，防止递归的时候数组越界
        {
            return;
        }
        int temp = arr[low];
        int left = low;//左标签
        int right = high;//右标签
        int t;
        while(left < right)
        {
            //为什么要先right标签向左遍历，首先是因为需要升序排序操作
            //如果先从右边开始，最后i、j停留的位置的 值 肯定是要小于 temp的
            //如果先从左边开始，循环结束后的 i j碰面的时的值肯定是要 大于 key的 
            //此时再交换key与索引位置 相当于把比key大的值放到了key左边,也就违背了快排的原则
            while(left < right && arr[right] >= temp)//right向左遍历，寻找到比基准数字小的就停止
            {
                right--;
            }
            while(left < right && arr[left] <= temp)//left标签向右遍历，如果比temp小的话就跳过
            {
                left++;
            }
            
            if(left < right)//如果是正常情况就进行交换
            {
                t = arr[left];
                arr[left] = arr[right];
                arr[right] = t;
            }
            
        }
        t = arr[low];
        arr[low] = arr[left];//将基准数字和标号为i和j的值交换(i/j都行)
        arr[left] = t;
        Sort(arr,low, right -1);
        Sort(arr,left + 1, high);
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

        System.out.println("\n~~array before quicksort:");
        System.out.println(Arrays.toString(arr));
        
        Sort(arr,0, arr.length - 1);//开始排序
        
        System.out.println("\n~~array after quicksort:");
        System.out.println(Arrays.toString(arr));

    }
}
//算法描述，源程序，复杂度分析，程序的运行结果及截图，个人总结

