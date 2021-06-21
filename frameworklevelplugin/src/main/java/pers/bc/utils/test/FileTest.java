package pers.bc.utils.test;

import java.io.File;
import java.io.IOException;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.pub.StringUtilbc;

public class FileTest
{
    public static void main(String[] args)
    {
        // String targetFileUrl = "E:\\pub\\pub_applypluginlbc_1.8+\\pers\\bc";
        String targetFileUrl = "C:\\Users\\licheng\\Desktop\\pers\\bc";
        try
        {
            FileUtilbc.codeConvertFileList(new File(targetFileUrl), PubConsUtilbc.UTF_8, "GBK");
            
        }
        catch (IOException e)
        {
            System.err.println(StringUtilbc.toString(e));
            e.printStackTrace();
        }
    }
}
