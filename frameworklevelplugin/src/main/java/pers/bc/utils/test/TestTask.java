package pers.bc.utils.test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TestTask
{
    public static void main(String[] args)
    {
        List<String> list = Arrays.asList("aa", "bb", "cc");
        
        for (int i = 0; i < 3; i++)
        {
            
            
//            new Thread()
//            {
//                public void run()
//                {
//                    list.add(UUID.randomUUID().toString().substring(0, 8));
//                    System.err.println(list);
//                }
//            }.start();
//
//            new Thread(()->
//            {
//                list.add(UUID.randomUUID().toString().substring(0,8));
//                System.err.println(list);
//            },String.valueOf(i)).start();
        }
    }
}
