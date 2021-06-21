package pers.bc.utils.pub;

/**
 * 
 * @qualiFild nc.pub.itf.tools.pub.IntegerUtils.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class IntegerUtils
{
    
    private static int[] wi = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    /**
     * *********************************************************** <br>
     * 说明： 算出前17位编码加权和值<br>
     * @see <br>
     * @param id
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.IntegerUtils#getSum <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 下午8:50:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSum(String id)
    {
        int sum = 0;
        String tempid = id.substring(0, 6) + "19" + id.substring(6);
        int[] ids = new int[17];
        for (int i = 0; i < 17; i++)
        {
            ids[i] = Integer.parseInt(tempid.substring(i, i + 1));
        }
        for (int i = 0; i < 17; i++)
        {
            sum += wi[i] * ids[i];
        }
        return sum;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 求数组的最大值(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:22:00
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMax(int[] arr)
    {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (max < arr[i])
            {
                max = arr[i];
            }
        }
        return max;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 求数组的最小值(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:22:08
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMin(int[] arr)
    {
        int min = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (min > arr[i])
            {
                min = arr[i];
            }
        }
        return min;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 得到数组最大值的下标(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:23:57
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMaxIndex(int[] arr)
    {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[maxIndex] < arr[i])
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 得到数组最小值的下标(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:24:06
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMinIndex(int[] arr)
    {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[minIndex] > arr[i])
            {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：获得数组之和(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:43:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSum(int[] arr)
    {
        int sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            sum += arr[i];
        }
        return sum;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获得平均值(int)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:43:45
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getAverage(int[] arr)
    {
        int avg = getSum(arr) / arr.length;
        return avg;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：打印数组(int)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:43:56
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void printArray(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (i != arr.length - 1)
            {
                System.out.print(arr[i] + ",");
            }
            else
            {
                System.out.println(arr[i]);
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 选择排序对数据进行降序排序(int)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:44:09
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void selectSortDescendingArray(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {// i<arr.length-1;最后一个不用比较
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] < arr[j])
                {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 选择排序对数据进行升序排序(int)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:21:29
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void selectSortAscendingArray(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {// i<arr.length-1;最后一个不用比较
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] > arr[j])
                {
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 冒泡排序对数据进行降序排序(int)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:21:38
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void bubbleSortDescendingArray(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                if (arr[j] < arr[j + 1])
                {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 冒泡排序对数据进行升序排序(int)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:21:49
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void bubbleSortAscendingArray(int[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 二分查找(int)
     * @see
     * @param arr
     * @param key
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:44:26
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int binarySearch(int[] arr, int key)
    {
        int min, mid, max;
        min = 0;
        max = arr.length - 1;
        while (arr[min] < arr[max])
        {
            mid = (min + max) / 2;
            if (key > arr[mid])
            {
                min = mid + 1;
            }
            else if (key < arr[mid])
            {
                max = mid - 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 求数组的最大值(double)
     * @see
     * @param arr
     * @return
     * @double
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:45:02
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double getMax(double[] arr)
    {
        double max = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (max < arr[i])
            {
                max = arr[i];
            }
        }
        return max;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 求数组的最小值(double)
     * @see
     * @param arr
     * @return
     * @double
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:45:17
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double getMin(double[] arr)
    {
        double min = arr[0];
        for (int i = 0; i < arr.length; i++)
        {
            if (min > arr[i])
            {
                min = arr[i];
            }
        }
        return min;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 得到数组最大值的下标(double)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:45:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMaxIndex(double[] arr)
    {
        int maxIndex = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[maxIndex] < arr[i])
            {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 得到数组最小值的下标(double)
     * @see
     * @param arr
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:45:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getMinIndex(double[] arr)
    {
        int minIndex = 0;
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[minIndex] > arr[i])
            {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获得数组之和(double)
     * @see
     * @param arr
     * @return
     * @double
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:19:58
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double getSum(double[] arr)
    {
        double sum = 0;
        for (int i = 0; i < arr.length; i++)
        {
            sum += arr[i];
        }
        return sum;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获得平均值(double)
     * @see
     * @param arr
     * @return
     * @double
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:20:09
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double getAverage(double[] arr)
    {
        double avg = getSum(arr) / arr.length;
        return avg;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 打印数组(double)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:20:19
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void printArray(double[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (i != arr.length - 1)
            {
                System.out.print(arr[i] + ",");
            }
            else
            {
                System.out.println(arr[i]);
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 选择排序对数据进行降序排序(double)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:20:41
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void selectSortDescendingArray(double[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {// i<arr.length-1;最后一个不用比较
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] < arr[j])
                {
                    double temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 选择排序对数据进行升序排序(double)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:20:55
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void selectSortAscendingArray(double[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {// i<arr.length-1;最后一个不用比较
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] > arr[j])
                {
                    double temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 冒泡排序对数据进行降序排序(double)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:21:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void bubbleSortDescendingArray(double[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                if (arr[j] < arr[j + 1])
                {
                    double temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 冒泡排序对数据进行升序排序(double)
     * @see
     * @param arr
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:21:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void bubbleSortAscendingArray(double[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = 0; j < arr.length - 1 - i; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    double temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 二分查找(double)
     * @see
     * @param arr
     * @param key
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午9:22:08
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int binarySearch(double[] arr, double key)
    {
        int min, mid, max;
        min = 0;
        max = arr.length - 1;
        while (arr[min] < arr[max])
        {
            mid = (min + max) / 2;
            if (key > arr[mid])
            {
                min = mid + 1;
            }
            else if (key < arr[mid])
            {
                max = mid - 1;
            }
            else
            {
                return mid;
            }
        }
        return -1;
    }
    
}
