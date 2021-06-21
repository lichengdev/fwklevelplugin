package pers.bc.utils.math;
import java.util.Random;

/**
 * Java工具集-数学(随机数工具)
 * @qualiFild pers.bc.utils.math.MathRandomUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-3<br>
 * @version 1.0<br>
 */
public class MathRandomUtilbc
{
    
    public static final Random JVM_RANDOM = new JVMRandom();
    
    /**
     * 功能描述: 〈返回一个int类型随机数〉
     * 
     * @params : []
     * @return : int
     * @author : cwl
     * @date : 2019/6/13 15:53
     */
    public static int nextInt()
    {
        return nextInt(JVM_RANDOM);
    }
    
    /**
     * 功能描述: 〈返回一个int随机数n倍的随机数〉
     * 
     * @params : [n]
     * @return : int
     * @author : cwl
     * @date : 2019/6/13 16:05
     */
    public static int nextInt(int n)
    {
        return nextInt(JVM_RANDOM, n);
    }
    
    /**
     * 功能描述: 〈〉
     * 
     * @params : [random, n]
     * @return : int
     * @author : cwl
     * @date : 2019/6/13 16:08
     */
    public static int nextInt(Random random, int n)
    {
        // 这里需要校验返回的数值不能是传进来的n
        int nextInt = random.nextInt(n);
        if (n == nextInt)
        {
            return nextInt(random, n);
        }
        return nextInt;
    }
    
    /**
     * 功能描述: 〈返回自定义类型随机数〉
     * 
     * @params : [random]
     * @return : int
     * @author : cwl
     * @date : 2019/6/13 15:53
     */
    public static int nextInt(Random random)
    {
        return random.nextInt();
    }
    
    /**
     * 功能描述: 〈获得long类型随机数〉
     * 
     * @params : []
     * @return : long
     * @author : cwl
     * @date : 2019/6/13 16:26
     */
    public static long nextLong()
    {
        return nextLong(JVM_RANDOM);
    }
    
    /**
     * 功能描述: 〈根据自定义random获取一个long类型随机数〉
     * 
     * @params : [random]
     * @return : long
     * @author : cwl
     * @date : 2019/6/13 16:26
     */
    public static long nextLong(Random random)
    {
        return random.nextLong();
    }
    
    /**
     * 功能描述: 〈〉
     * 
     * @params : []
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/13 16:28
     */
    public static boolean nextBoolean()
    {
        return nextBoolean(JVM_RANDOM);
    }
    
    /**
     * 功能描述: 〈获得一个随机的布尔值〉
     * 
     * @params : [random]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/13 16:31
     */
    public static boolean nextBoolean(Random random)
    {
        return random.nextBoolean();
    }
    
    /**
     * 功能描述: 〈获得一个float类型随机数〉
     * 
     * @params : []
     * @return : float
     * @author : cwl
     * @date : 2019/6/13 16:32
     */
    public static float nextFloat()
    {
        return nextFloat(JVM_RANDOM);
    }
    
    /**
     * 功能描述: 〈获得自定义Random规则的float随机数〉
     * 
     * @params : [random]
     * @return : float
     * @author : cwl
     * @date : 2019/6/13 16:32
     */
    public static float nextFloat(Random random)
    {
        return random.nextFloat();
    }
    
    /**
     * 功能描述: 〈〉
     * 
     * @params : []
     * @return : double
     * @author : cwl
     * @date : 2019/6/13 16:32
     */
    public static double nextDouble()
    {
        return nextDouble(JVM_RANDOM);
    }
    
    /**
     * 功能描述: 〈获得一个自定义random类型随机数〉
     * 
     * @params : [random]
     * @return : double
     * @author : cwl
     * @date : 2019/6/13 16:33
     */
    public static double nextDouble(Random random)
    {
        return random.nextDouble();
    }
    
    private static final class JVMRandom extends Random
    {
        // 实现序列化接口
        private static final long serialVersionUID = 1L;
        // 确保只有一个构造函数可以被调用
        private boolean constructed = false;
        
        public JVMRandom()
        {
            this.constructed = true;
        }
        
        /**
         * 功能描述: 〈确保只有一个对象会被创建〉
         * 
         * @params : [seed]
         * @return : void
         * @author : cwl
         * @date : 2019/6/13 15:15
         */
        public synchronized void setSeed(long seed)
        {
            if (this.constructed)
            {
                throw new UnsupportedOperationException();
            }
        }
        
        /**
         * 功能描述: 〈仅是对父类方法的覆盖,只是需要执行子类方法〉
         * 
         * @params : []
         * @return : double
         * @author : cwl
         * @date : 2019/6/13 15:41
         */
        public synchronized double nextGaussian()
        {
            throw new UnsupportedOperationException();
        }
        
        /**
         * 功能描述: 〈仅是对父类方法的覆盖,只是需要执行子类方法〉
         * 
         * @params : [byteArray]
         * @return : void
         * @author : cwl
         * @date : 2019/6/13 15:41
         */
        public void nextBytes(byte[] byteArray)
        {
            throw new UnsupportedOperationException();
        }
        
        /**
         * 功能描述: 〈int最大值的的随机数返回〉
         * 
         * @params : []
         * @return : int
         * @author : cwl
         * @date : 2019/6/13 15:42
         */
        public int nextInt()
        {
            return nextInt(Integer.MAX_VALUE);
        }
        
        /**
         * 功能描述: 〈返回int最大值倍数的随机数〉
         * 
         * @params : [n]
         * @return : int
         * @author : cwl
         * @date : 2019/6/13 15:43
         */
        public int nextInt(int n)
        {
            if (n <= 0)
            {
                throw new IllegalArgumentException("Upper bound for nextInt must be positive");
            }
            return (int) (Math.random() * n);
        }
        
        /**
         * 功能描述: 〈获得一个Long倍数的Random随机数〉
         * 
         * @params : []
         * @return : long
         * @author : cwl
         * @date : 2019/6/13 15:46
         */
        public long nextLong()
        {
            // 可能会因为Long值太大而损失了精度
            return nextLong(Long.MAX_VALUE);
        }
        
        /**
         * 功能描述: 〈获得一个Long倍数的Random随机数〉
         * 
         * @params : [n]
         * @return : long
         * @author : cwl
         * @date : 2019/6/13 15:46
         */
        public static long nextLong(long n)
        {
            if (n <= 0)
            {
                throw new IllegalArgumentException("Upper bound for nextInt must be positive");
            }
            return (long) (Math.random() * n);
        }
        
        /**
         * 功能描述: 〈返回随机数中大于0.5的随机数〉
         * 
         * @params : []
         * @return : boolean
         * @author : cwl
         * @date : 2019/6/13 15:47
         */
        public boolean nextBoolean()
        {
            return Math.random() > 0.5;
        }
        
        /**
         * 功能描述: 〈返回一个float类型的随机数〉
         * 
         * @params : []
         * @return : float
         * @author : cwl
         * @date : 2019/6/13 15:48
         */
        public float nextFloat()
        {
            return (float) Math.random();
        }
        
        /**
         * 功能描述: 〈返回一个Double类型的随机数〉
         * 
         * @params : []
         * @return : double
         * @author : cwl
         * @date : 2019/6/13 15:49
         */
        public double nextDouble()
        {
            return Math.random();
        }
    }
}
