package runoob;
import java.util.Arrays;

public class Quick_Sort  
{
    
    public static void Sort(int arr[],int low,int high)
    {   
        
        if (low > high)//加上判断条件，防止递归的时候数组越界
        {
            return;
        }
        int temp = arr[low];
        int left = low;
        int right = high;
        int t;
        while(left < right)
        {
            //为什么要先right标签向左遍历，首先是因为需要升序排序操作
            //如果先从右边开始，最后i、j停留的位置的 值 肯定是要小于 temp的
            //如果先从左边开始，循环结束后的 i j碰面的时的值肯定是要 大于 key的 
            //此时再交换key与索引位置 相当于把比key大的值放到了key左边 也就违背了快排的条件
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
        int arr[] = {5,3,6,9,7,44,55,61,25,10};
        Sort(arr,0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

    }
}


